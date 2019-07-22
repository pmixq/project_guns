package com.stylefeng.guns.rest.common.persistence.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.rest.common.persistence.model.CinemaT;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 影院信息表 Mapper 接口
 * </p>
 *
 * @author cskaoyan
 * @since 2019-07-16
 */
@Component
@Mapper
public interface CinemaTMapper extends BaseMapper<CinemaT> {

}
