package com.stylefeng.guns.rest.modular.film.service.impl;

import com.alibaba.dubbo.config.annotation.Service;

import com.stylefeng.guns.rest.common.persistence.dao.MtimeFilmTMapper;
import com.stylefeng.guns.rest.modular.film.service.FilmService;

import com.stylefeng.guns.rest.modular.film.vo.Parameter;
import com.stylefeng.guns.rest.modular.film.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Service(interfaceClass = FilmService.class)
public class FilmServiceImpl implements FilmService {

    @Autowired
    MtimeFilmTMapper mtimeFilmTMapper;

    //查询影片信息(影片查询接口)
   @Override
    public ResultVO getFilms(Parameter parameter) {
      /*  PageHelper.startPage(parameter.getNowPage(), parameter.getPageSize());
            List<FilmShort> filmShorts = mtimeFilmTMapper.queryFilmByParameter(parameter);
            PageInfo<FilmShort> pageInfo = new PageInfo<>(filmShorts);
            ResultVO resultVO = new ResultVO();
            resultVO.setStatus(0);
            resultVO.setImgPre("http://img.meetingshop.cn/");
            resultVO.setNowPage(parameter.getNowPage());
            resultVO.setTotalPage((int)pageInfo.getTotal());
            resultVO.setData(pageInfo.getList());*/
            return null;
    }
}
