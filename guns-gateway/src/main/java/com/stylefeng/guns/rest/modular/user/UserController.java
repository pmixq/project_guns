package com.stylefeng.guns.rest.modular.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.config.properties.JwtProperties;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import com.stylefeng.guns.rest.modular.user.service.UserService;
import com.stylefeng.guns.rest.modular.user.vo.RegisterIn;
import com.stylefeng.guns.rest.modular.user.vo.StatusAndMsg;
import com.stylefeng.guns.rest.modular.user.vo.StatusDataAndMsg;
import com.stylefeng.guns.rest.modular.user.vo.UserInfoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/user")
public class UserController {


    @Reference(interfaceClass = UserService.class, check = false)
    UserService userService;

    @Autowired
    JwtProperties jwtProperties;

    @Autowired
    JwtTokenUtil jwtTokenUtil;


    //用户注册入口
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public StatusAndMsg insertUser(RegisterIn registerIn) {
        String username = registerIn.getUsername();
        StatusAndMsg statusAndMsg = new StatusAndMsg();
        Boolean flag = userService.checkUsername(username);
        //检查成功可注册
        if (flag) {
            Boolean insert = userService.insertUser(registerIn);
            if (insert) {
                statusAndMsg.setStatus(0);
                statusAndMsg.setMsg("注册成功");
            } else {
                statusAndMsg.setStatus(999);
                statusAndMsg.setMsg("系统异常，请联系管理员");
            }
        } else {
            //用户已存在
            statusAndMsg.setStatus(1);
            statusAndMsg.setMsg("用户名已存在");
        }
        return statusAndMsg;
    }

    //用户检测入口
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public StatusAndMsg usernameCheck(String username) {
        //查询用户名是否存在
        StatusAndMsg statusAndMsg = new StatusAndMsg();
        boolean flag = userService.checkUsername(username);
        if (flag = false) {
            statusAndMsg.setStatus(1);
            statusAndMsg.setMsg("用户名已存在");
        } else {
            statusAndMsg.setStatus(0);
            statusAndMsg.setMsg("验证成功");
        }
        return statusAndMsg;
    }

    //用户信息查询入口
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public StatusDataAndMsg getUserInformation(HttpServletRequest request) {
        StatusDataAndMsg<UserInfoData> statusDataAndMsg = new StatusDataAndMsg<>();

        //从request里获取token,进而获取username
        String requestHeader = request.getHeader(jwtProperties.getHeader());
        String authToken = requestHeader.substring(7);
        String usernameFromToken = jwtTokenUtil.getUsernameFromToken(authToken);

        //业务异常
        if (usernameFromToken == null || "".equals(usernameFromToken)) {
            statusDataAndMsg.setStatus(1);
            statusDataAndMsg.setMsg("查询失败，用户尚未登录");
            return statusDataAndMsg;
        }

        try {
            UserInfoData userInfo = userService.getUserInfoByName(usernameFromToken);
            //查询成功
            statusDataAndMsg.setData(userInfo);
            statusDataAndMsg.setStatus(0);
        } catch (Exception e) {
            //系统异常
            statusDataAndMsg.setStatus(999);
            statusDataAndMsg.setMsg("系统出现异常，请联系管理员");
        }

        return statusDataAndMsg;
    }


    // 用户信息修改入口
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    public StatusDataAndMsg updateUserInfo(UserInfoData userInfo) {
        StatusDataAndMsg<UserInfoData> statusDataAndMsg = new StatusDataAndMsg<>();
        boolean update = false;
        try {
            update = userService.updateUserInfo(userInfo);
        } catch (Exception e) {
            statusDataAndMsg.setStatus(999);
            statusDataAndMsg.setMsg("系统出现异常，请联系管理员");
            return statusDataAndMsg;
        }

        if (update) {
            //update成功，返回查询结果
            UserInfoData userInfoReturn = userService.getUserInfoById(userInfo.getUuid());
            statusDataAndMsg.setStatus(0);
            statusDataAndMsg.setData(userInfoReturn);

        } else {
            //uodate失败
            statusDataAndMsg.setStatus(1);
            statusDataAndMsg.setMsg("用户信息修改失败");
        }
        return statusDataAndMsg;

    }


    // 用户退出登录入口

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public StatusAndMsg userLogout(HttpServletRequest request){
        //从request里取token和username，查redis里这个token是否存在
        //从request里获取token,进而获取username
        String requestHeader = request.getHeader(jwtProperties.getHeader());
        String authToken = requestHeader.substring(7);
        String usernameFromToken = jwtTokenUtil.getUsernameFromToken(authToken);

        String userTokenKey = usernameFromToken + "Token";
        StatusAndMsg statusAndMsg = new StatusAndMsg();
        boolean flag = false;
        try {
            flag = userService.jedisTokenExist(userTokenKey, authToken);
        }catch (Exception e){
            //系统异常
            statusAndMsg.setStatus(999);
            statusAndMsg.setMsg("系统异常，请联系管理员");
            return statusAndMsg;
        }

        if (flag){
            //1.token存在，则清除redis里这个token
            userService.jedisTokenClean(userTokenKey,authToken);
            statusAndMsg.setStatus(0);
            statusAndMsg.setMsg("成功退出");
        }else {
            //2.token不存在，则为未登录
            statusAndMsg.setStatus(1);
            statusAndMsg.setMsg("退出失败，用户尚未登录");
        }
        return statusAndMsg;
    }

}