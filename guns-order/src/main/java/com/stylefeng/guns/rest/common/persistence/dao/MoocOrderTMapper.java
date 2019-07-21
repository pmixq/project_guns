package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.common.persistence.model.MoocOrderT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 订单信息表 Mapper 接口
 * </p>
 *
 * @author wpm
 * @since 2019-07-19
 */
@Repository
public interface MoocOrderTMapper extends BaseMapper<MoocOrderT> {


    //插入订单
    Integer insertOrder(MoocOrderT moocOrderT);
}
