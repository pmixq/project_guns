package com.stylefeng.guns.rest.modular.cinema.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.modular.cinema.Service.CinemaService;
import com.stylefeng.guns.rest.modular.cinema.vo.CinemaFieldsResponseVO;
import com.stylefeng.guns.rest.modular.cinema.vo.CinemaFilmResponseVO;
import com.stylefeng.guns.rest.modular.cinema.vo.ResponsVO;
import com.stylefeng.guns.rest.modular.vo.CinemaInfoVO;
import com.stylefeng.guns.rest.modular.vo.FilmInfoVO;
import com.stylefeng.guns.rest.modular.vo.HallInfoVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cinema/")
public class CinemaController  {

    private static final String img_pre ="http://img.meetingshop.cn/";

    @Reference
    CinemaService cinemaService;

    @RequestMapping("getFields")
    public ResponsVO getFields(int cinemaId){
        CinemaInfoVO cinemaInfoVO=cinemaService.getCinemaInfoById(cinemaId);
        List<FilmInfoVO> filmInfoVOS=cinemaService.getFilmInfoById(cinemaId);
        CinemaFieldsResponseVO cinemaFieldsResponseVO=new CinemaFieldsResponseVO();
        cinemaFieldsResponseVO.setCinemaInfo(cinemaInfoVO);
        cinemaFieldsResponseVO.setFilmList(filmInfoVOS);
        ResponsVO responsVO=new ResponsVO();
        responsVO.setData(cinemaFieldsResponseVO);
        responsVO.setStatus(0);
        responsVO.setImgPre(img_pre);
        return responsVO;
    }

    @RequestMapping("getFieldInfo")
    public ResponsVO getFieldInfo(Integer cinemaId,Integer fieldId){
        CinemaInfoVO cinemaInfoVO=cinemaService.getCinemaInfoById(cinemaId);
        FilmInfoVO filmInfoVO=cinemaService.getFilemInfoByFieldId(fieldId);
        HallInfoVO hallInfoVO=cinemaService.getFilmFieldInfo(fieldId);

        CinemaFilmResponseVO cinemaFilmResponseVO=new CinemaFilmResponseVO();
        cinemaFilmResponseVO.setCinemaInfo(cinemaInfoVO);
        cinemaFilmResponseVO.setFilmInfo(filmInfoVO);
        cinemaFilmResponseVO.setHallInfo(hallInfoVO);

        ResponsVO responsVO=new ResponsVO();
        responsVO.setImgPre(img_pre);
        responsVO.setData(cinemaFilmResponseVO);
        responsVO.setStatus(0);
        return responsVO;
    }
}
