package com.stylefeng.guns.rest.modular.order.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.rest.common.persistence.dao.MoocOrderTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.MtimeCinemaTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.MtimeFieldTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.MtimeFilmTMapper;
import com.stylefeng.guns.rest.common.persistence.model.MoocOrderT;
import com.stylefeng.guns.rest.common.persistence.model.MtimeCinemaT;
import com.stylefeng.guns.rest.common.persistence.model.MtimeFieldT;
import com.stylefeng.guns.rest.common.persistence.model.MtimeFilmT;
import com.stylefeng.guns.rest.modular.order.service.OrderService;
import com.stylefeng.guns.rest.modular.film.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author WPM
 * @Create 2019/7/19 20:13
 **/
@Component
@Service(interfaceClass = OrderService.class)
public class OrderSergviceImpl implements OrderService {

    @Autowired
    MtimeFieldTMapper fieldTMapper;

    @Autowired
    MoocOrderTMapper orderTMapper;

    @Autowired
    MtimeFilmTMapper filmTMapper;

    @Autowired
    MtimeCinemaTMapper cinemaTMapper;


    //提供给影院模块的接口
    @Override
    public String getSoldSeatsByFieldId(Integer fieldId) {
        String orderSoldSeats = getSeats(fieldId);
        return orderSoldSeats;
    }


    //判断是否是合法的座位号（未完成）
    @Override
    public boolean BooleanisTrueSeats(Integer fieldId, String soldSeats) {
        String seataddress = fieldTMapper.selectSeatAddress(fieldId);

        return false;
    }


    //判断座位号是否已经卖出
    @Override
    public boolean BooleanisSoldSeats(Integer fieldId, String soldSeats) {
        String orderSoldSeats = getSeats(fieldId);
        String[] split = soldSeats.split(",");
        String[] split1 = orderSoldSeats.split(",");
        for (int i = 0; i < split.length; i++) {
            for (int j = 0; j < split1.length; j++) {
                if(split[i] == split1[j]){
                    return true;
                }
            }
        }
        return false;
    }



    //创建订单
    @Override
    public OrderVO createOrder(Integer fieldId, String soldSeats, String seatsName, Integer userId) {
        MoocOrderT moocOrderT = new MoocOrderT();
        MtimeFieldT fieldT = fieldTMapper.selectById(fieldId);
        moocOrderT.setUuid(UUID.randomUUID().toString());
        moocOrderT.setCinemaId(fieldT.getCinemaId());
        moocOrderT.setFieldId(fieldId);
        moocOrderT.setFilmId(fieldT.getFilmId());
        moocOrderT.setSeatsIds(soldSeats);
        moocOrderT.setFilmPrice(Double.valueOf(fieldT.getPrice()));
        double orderPrice = soldSeats.split(",").length * Double.valueOf(fieldT.getPrice());
        moocOrderT.setOrderPrice(orderPrice);
        moocOrderT.setOrderTime(new Date());
        moocOrderT.setOrderUser(userId);
        moocOrderT.setOrderStatus(0);
        try {
            orderTMapper.insert(moocOrderT);
        }catch (Exception e){
            e.printStackTrace();
        }
        //创建订单成功
        OrderVO orderVO = new OrderVO();
        orderVO.setOrderId(moocOrderT.getUuid());
        MtimeFilmT filmT = filmTMapper.selectById(moocOrderT.getFilmId());
        orderVO.setFilmName(filmT.getFilmName());
        orderVO.setFieldTime(fieldT.getBeginTime()); //此处存疑，无年月日
        MtimeCinemaT mtimeCinemaT = cinemaTMapper.selectById(moocOrderT.getCinemaId());
        orderVO.setCinemaNmae(mtimeCinemaT.getCinemaName());
        orderVO.setSeatsName(seatsName);
        orderVO.setOrderPrice(moocOrderT.getOrderPrice());
        orderVO.setOrderTimestamp(null);  //此处存疑  不知道这字段啥意思
        orderVO.setOrderStatus("未支付");
        return orderVO;
    }

    //查询用户订单信息
    @Override
    public List<OrderVO> getOrderInfo(int userId, int nowPage, int pageSize) {
        Page page = new Page(nowPage, pageSize );
        EntityWrapper<MoocOrderT> wrapper = new EntityWrapper<>();
        wrapper.eq("order_user", userId);
        List<MoocOrderT> moocOrderTS = orderTMapper.selectPage(page, wrapper);
        List<OrderVO> list = new ArrayList<>();
        for (MoocOrderT moocOrderT : moocOrderTS) {
            OrderVO orderVO = new OrderVO();
            orderVO.setOrderId(moocOrderT.getUuid());
            MtimeFilmT filmT = filmTMapper.selectById(moocOrderT.getFilmId());
            orderVO.setFilmName(filmT.getFilmName());
            MtimeFieldT fieldT = fieldTMapper.selectById(moocOrderT.getFieldId());
            orderVO.setFieldTime(fieldT.getBeginTime()); //此处存疑，无年月日
            MtimeCinemaT mtimeCinemaT = cinemaTMapper.selectById(moocOrderT.getCinemaId());
            orderVO.setCinemaNmae(mtimeCinemaT.getCinemaName());
            orderVO.setSeatsName(moocOrderT.getSeatsName());
            orderVO.setOrderPrice(moocOrderT.getOrderPrice());
            orderVO.setOrderTimestamp(null);  //此处存疑  不知道这字段啥意思
            orderVO.setOrderStatus("待支付");
            list.add(orderVO);
        }
        return list;
    }


    //获取已售座位的方法
    public String getSeats(Integer fieldId){
        EntityWrapper<MoocOrderT> wrapper = new EntityWrapper<>();
        wrapper.eq("field_id", fieldId);
        List<MoocOrderT> moocOrderTS = orderTMapper.selectList(wrapper);
        StringBuffer sb = new StringBuffer();
        for (MoocOrderT moocOrderT : moocOrderTS) {
            String seatsIds = moocOrderT.getSeatsIds();
            sb.append(seatsIds + ",");
        }
        String substring = sb.substring(0, sb.length() - 1);
        return substring;
    }

}
