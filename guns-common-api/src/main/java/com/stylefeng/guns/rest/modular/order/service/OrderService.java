package com.stylefeng.guns.rest.modular.order.service;

import com.stylefeng.guns.rest.modular.film.vo.OrderVO;

import java.util.List;

/**
 * @Author WPM
 * @Create 2019/7/19 19:59
 **/
public interface OrderService {

    OrderVO createOrder(Integer fieldId, String soldSeats, String seatsName, Integer userId);

    boolean BooleanisTrueSeats(Integer fieldId, String soldSeats);

    boolean BooleanisSoldSeats(Integer fieldId, String soldSeats);

    String getSoldSeatsByFieldId(Integer fieldId);

    List<OrderVO> getOrderInfo(int userId, int nowPage, int pageSize);
}
