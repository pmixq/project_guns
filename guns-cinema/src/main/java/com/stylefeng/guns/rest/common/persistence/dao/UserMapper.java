package com.stylefeng.guns.rest.common.persistence.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.rest.common.persistence.model.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2017-08-23
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}