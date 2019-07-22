package com.stylefeng.guns.rest.modular.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import com.stylefeng.guns.rest.modular.user.service.UserService;
import com.stylefeng.guns.rest.modular.user.vo.StatusDataAndMsg;
import com.stylefeng.guns.rest.modular.user.vo.TokenAndRandomkey;
import com.stylefeng.guns.rest.modular.user.vo.UserAuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAuthController {
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Reference(interfaceClass = UserService.class,check = false)
    UserService userService;

    @RequestMapping(value = "/auth", method = RequestMethod.POST, params = {"username","password"})
    public StatusDataAndMsg userLogin(UserAuthRequest authRequest){
        StatusDataAndMsg<Object> statusDataAndMsg = new StatusDataAndMsg<>();

        //1.判断用户名和密码都正确
        boolean validate = userService.validate(authRequest);
        if (validate){
            //2.在网关产生token
            try {
                final String randomKey = jwtTokenUtil.getRandomKey();
                final String token = jwtTokenUtil.generateToken(authRequest.getUsername(), randomKey);
                TokenAndRandomkey tokenAndRandomkey = new TokenAndRandomkey();
                tokenAndRandomkey.setToken(token);
                tokenAndRandomkey.setRandomKey(randomKey);
                statusDataAndMsg.setData(tokenAndRandomkey);
                statusDataAndMsg.setStatus(0);
            }catch (Exception e){
                //3.产生token失败
                statusDataAndMsg.setStatus(999);
                statusDataAndMsg.setMsg("系统出现异常，请联系管理员");
            }
        }else {
            //用户名或密码错误
            statusDataAndMsg.setStatus(1);
            statusDataAndMsg.setMsg("用户名或密码错误");
        }

        return statusDataAndMsg;
    }
}



