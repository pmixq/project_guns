package com.stylefeng.guns.rest.common.persistence.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.rest.modular.user.vo.RegisterIn;
import com.stylefeng.guns.rest.modular.user.vo.UserInfoData;
import com.stylefeng.guns.rest.modular.user.MtimeUserT;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author xie
 * @since 2019-07-19
 */
@Component
public interface MtimeUserTMapper extends BaseMapper<MtimeUserT> {
    int insertRegisterIn(@Param("registerIn") RegisterIn registerIn);

    int countByUsername(@Param("username") String username);

    int updateUserInfo(@Param("userinfoData") UserInfoData userInfoData);

    UserInfoData selectUserInfoByName(@Param("username") String username);

    UserInfoData selectUserInfoById(@Param("uuid") int uuid);

    String getPasswordByUsername(@Param("username") String username);

    MtimeUserT selectByUsername(@Param("username") String username);

    int getUserIdByName(@Param("username") String username);

}
