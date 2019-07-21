package com.stylefeng.guns.rest.modular.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.modular.film.vo.OrderVO;
import com.stylefeng.guns.rest.modular.order.service.OrderService;
import com.stylefeng.guns.rest.modular.film.vo.ResponseVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author WPM
 * @Create 2019/7/19 17:53
 **/

@RestController
@RequestMapping("/order")
public class OrderController {

    @Reference(interfaceClass = OrderService.class, check = false)
    OrderService orderService;


    //这里需要调用用户模块的接口获得userId
    @RequestMapping("/buyTickets")
    public ResponseVO buyTickets(Integer fieldId, String soldSeats, String seatsName){
        int userId = 1; //待获取
        OrderVO order = orderService.createOrder(fieldId, soldSeats, seatsName, userId);
        //boolean trueSeats = orderService.BooleanisTrueSeats(fieldId, soldSeats);
        boolean isSoldSeats = orderService.BooleanisSoldSeats(fieldId, soldSeats);
        ResponseVO responseVO = new ResponseVO();
        if(!isSoldSeats){
            responseVO.setData(order);
            responseVO.setStatus(0);
        }
        return responseVO;
    }

    //查询用户订单信息
    @RequestMapping("/getOrderInfo")
    public ResponseVO getOrderInfo(int nowPage, int pageSize){
        int userId = 1; //待获取
        List<OrderVO> orders = orderService.getOrderInfo(userId, nowPage, pageSize);
        ResponseVO responseVO = new ResponseVO();
        responseVO.setData(orders);
        responseVO.setStatus(0);
        return responseVO;
    }


}
