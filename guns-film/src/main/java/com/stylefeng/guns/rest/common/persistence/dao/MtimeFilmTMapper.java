package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.common.persistence.model.MtimeFilmT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.rest.modular.film.vo.FilmShort;
import com.stylefeng.guns.rest.modular.film.vo.Parameter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 影片主表 Mapper 接口
 * </p>
 *
 * @author wpm
 * @since 2019-07-16
 */
@Repository
public interface MtimeFilmTMapper extends BaseMapper<MtimeFilmT> {

    List<FilmShort> queryFilmByParameter(@Param("param") Parameter parameter);
}
