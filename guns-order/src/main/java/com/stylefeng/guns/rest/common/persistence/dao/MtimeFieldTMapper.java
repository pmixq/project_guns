package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.common.persistence.model.MtimeFieldT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 放映场次表 Mapper 接口
 * </p>
 *
 * @author wpm
 * @since 2019-07-19
 */
@Repository
public interface MtimeFieldTMapper extends BaseMapper<MtimeFieldT> {

    String selectSeatAddress(Integer fieldId);
}
