package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.common.persistence.model.FieldT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.rest.modular.vo.FilmInfoVO;
import com.stylefeng.guns.rest.modular.vo.HallInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 放映场次表 Mapper 接口
 * </p>
 *
 * @author cskaoyan
 * @since 2019-07-16
 */
public interface FieldTMapper extends BaseMapper<FieldT> {
    List<FilmInfoVO> getFilmInfos(@Param("cinemaId") int cinemaId);
    FilmInfoVO getFilmInfoByfieldId(@Param("fieldId") int fieldId);
    HallInfoVO getHallInfo(@Param("fieldId") int fieldId);

}
