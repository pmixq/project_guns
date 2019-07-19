package com.stylefeng.guns.rest.modular.cinema.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.rest.modular.cinema.Service.CinemaService;
import com.stylefeng.guns.rest.modular.cinema.vo.*;
import com.stylefeng.guns.rest.modular.cinema.vo.FilmInfoVO;
import com.stylefeng.guns.rest.modular.cinema.vo.HallInfoVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cinema/")
public class CinemaController  {
    private static final String img_pre ="http://img.meetingshop.cn/";
    @Reference(interfaceClass = CinemaService.class, check = false)
    CinemaService cinemaService;

    @RequestMapping(value = "getCondition" ,method = RequestMethod.GET)
    public ResponseVO getCondition(int brandId,int areaId,int hallType){
        ResponseVO responseVO=new ResponseVO();

        List<AreaVO> areaList=cinemaService.getAreas(areaId);
        List<BrandVO> brandList=cinemaService.getBrands(brandId);
        List<HallTypeVO> halltypeList=cinemaService.getHallType(hallType);

        ConditionInfo conditionInfo=new ConditionInfo(areaList,brandList,halltypeList);
        responseVO.setData(conditionInfo);
        responseVO.setStatus(0);
        return responseVO;
    }

    @RequestMapping(value = "getCinemas",method = RequestMethod.GET)
    public ResponseVO getCinemas(int brandId,int areaId,int hallType,int nowPage,int pageSize){
        ResponseVO responseVO=new ResponseVO();
        CinemaQueryVO cinemaQueryVO=new CinemaQueryVO();
        cinemaQueryVO.setBrandId(brandId);
        cinemaQueryVO.setAreaId(areaId);
        cinemaQueryVO.setHallType(hallType);
        cinemaQueryVO.setNowPage(nowPage);
        cinemaQueryVO.setPageSize(pageSize);
        Page<CinemaVO> page=cinemaService.getCinemas(cinemaQueryVO);


        responseVO.setData(page);
        responseVO.setStatus(0);
        responseVO.setImgPre(img_pre);
        responseVO.setNowPage(nowPage);
        responseVO.setTotalPage((int)page.getPages());

        return responseVO;
    }

    @RequestMapping("getFields")
    public ResponseVO getFields(int cinemaId){
        CinemaInfoVO cinemaInfoVO=cinemaService.getCinemaInfoById(cinemaId);
        List<FilmInfoVO> filmInfoVOS=cinemaService.getFilmInfoById(cinemaId);
        CinemaFieldsResponseVO cinemaFieldsResponseVO=new CinemaFieldsResponseVO();
        cinemaFieldsResponseVO.setCinemaInfo(cinemaInfoVO);
        cinemaFieldsResponseVO.setFilmList(filmInfoVOS);
        ResponseVO responseVO=new ResponseVO();
        responseVO.setData(cinemaFieldsResponseVO);
        responseVO.setStatus(0);
        responseVO.setImgPre(img_pre);
        return responseVO;
    }

    @RequestMapping("getFieldInfo")
    public ResponseVO getFieldInfo(Integer cinemaId,Integer fieldId){
        CinemaInfoVO cinemaInfoVO=cinemaService.getCinemaInfoById(cinemaId);
        FilmInfoVO filmInfoVO=cinemaService.getFilemInfoByFieldId(fieldId);
        HallInfoVO hallInfoVO=cinemaService.getFilmFieldInfo(fieldId);

        CinemaFilmResponseVO cinemaFilmResponseVO=new CinemaFilmResponseVO();
        cinemaFilmResponseVO.setCinemaInfo(cinemaInfoVO);
        cinemaFilmResponseVO.setFilmInfo(filmInfoVO);
        cinemaFilmResponseVO.setHallInfo(hallInfoVO);

        ResponseVO responsVO=new ResponseVO();
        responsVO.setImgPre(img_pre);
        responsVO.setData(cinemaFilmResponseVO);
        responsVO.setStatus(0);
        return responsVO;
    }
}
