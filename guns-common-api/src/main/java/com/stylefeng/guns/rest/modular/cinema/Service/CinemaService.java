package com.stylefeng.guns.rest.modular.cinema.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.rest.modular.vo.*;

import java.util.List;

public interface CinemaService {
    //1、根据CinemaQueryVO查询影院列表
    Page<CinemaVO> getCinemas(CinemaQueryVO cinemaQueryVO);
    //2、根据条件获取品牌列表
    List<BrandVO> getBrands(int brandId);
    //3、获取行政区域列表
    List<AreaVO> getAreas(int areaId);
    //4、获取影厅类型列表
    List<HallTypeVO> getHallType(int hallType);
    //5、根据影院编号，获取影院信息
    CinemaInfoVO getCinemaInfoById(int cinemaId);

    List<FilmInfoVO> getFilmInfoById(int cinemaId);

}
