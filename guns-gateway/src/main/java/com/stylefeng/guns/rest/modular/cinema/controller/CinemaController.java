package com.stylefeng.guns.rest.modular.cinema.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.rest.modular.cinema.Service.CinemaService;
import com.stylefeng.guns.rest.modular.cinema.vo.CinemaQueryVO;
import com.stylefeng.guns.rest.modular.cinema.vo.CinemaVO;
import com.stylefeng.guns.rest.modular.cinema.vo.ResponseVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cinema")
public class CinemaController  {
    @Reference
    CinemaService cinemaService;

    @ResponseBody
    @RequestMapping("/getCondition")
    public ResponseVO getCondition(int brandId,int areaId,int hallType){
        ResponseVO responseVO=new ResponseVO();
        List<Object> list=new ArrayList<>();
        list.add(cinemaService.getBrands(brandId));
        list.add(cinemaService.getAreas(areaId));
        list.add(cinemaService.getHallType(hallType));
        responseVO.setData(list);
        responseVO.setStatus(0);
        return null;
    }

    @ResponseBody
    @RequestMapping("/getCinemas")
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
        responseVO.setImgPre("http://img.meetingshop.cn/");
        responseVO.setNowPage((int)page.getPages());
        responseVO.setTotalPage((int)page.getTotal());
        return responseVO;
    }


}
