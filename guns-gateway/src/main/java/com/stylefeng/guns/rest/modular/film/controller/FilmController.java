package com.stylefeng.guns.rest.modular.film.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.modular.film.service.FilmService;

import com.stylefeng.guns.rest.modular.film.vo.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.stylefeng.guns.rest.modular.film.vo.Parameter;
import com.stylefeng.guns.rest.modular.film.vo.ResultVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/film")
public class FilmController {

    private static String IMG_PRE = "http://img.meetingshop.cn/";

    @Reference(interfaceClass = FilmService.class, check = false)
    private FilmService filmService;

    @RequestMapping(value = "/getIndex", method = RequestMethod.GET)
    public ResponseVO getIndex(){
        FilmIndexVO filmIndexVO = new FilmIndexVO();
        List<BannerVO> banners = filmService.getBanners();
        List<FilmRankVO> expectRanking = filmService.getExpectRanking(10);
        FilmsVO hotFilms = filmService.getHotFilms(8, true);
        List<FilmRankVO> ranking = filmService.getRanking(10);
        FilmsVO soonFilms = filmService.getSoonFilms(8, true);
        List<FilmRankVO> top100 = filmService.getTop100(10);
        filmIndexVO.setBanners(banners);
        filmIndexVO.setBoxRanking(ranking);
        filmIndexVO.setExpectRanking(expectRanking);
        filmIndexVO.setHotFilms(hotFilms);
        filmIndexVO.setSoonFilms(soonFilms);
        filmIndexVO.setTop100(top100);
        ResponseVO responseVO = new ResponseVO();
        responseVO.setData(filmIndexVO);
        responseVO.setImgPre(IMG_PRE);
        responseVO.setStatus(0);
        return responseVO;
    }


    @RequestMapping(value = "/getFilms", method = RequestMethod.GET)
    public ResultVO getFilms(Parameter parameter){
        ResultVO resultVO = filmService.getFilms(parameter);
        return resultVO;
    }

    @RequestMapping(value = "/films/{filmId}", method = RequestMethod.GET)
    public ResultVO getFilmsDetail(@PathVariable int filmId, int searchType){
        ResultVO  resultVO = filmService.getFilmsDetail(filmId, searchType);
        return resultVO;
    }

    @RequestMapping(value = "/getConditionList", method = RequestMethod.GET)
    public ResultVO getConditionList(int catId, int sourceId, int yearId){

        List<CatInfo> catInfoList = filmService.getCatInfo(catId);
        List<SourceInfo> sourceInfoList = filmService.getSourceInfo(sourceId);
        List<YearInfo> yearInfoList = filmService.getYearInfo(yearId);
        ConditionInfo conditionInfo = new ConditionInfo();
        conditionInfo.setCatInfo(catInfoList);
        conditionInfo.setSourceInfo(sourceInfoList);
        conditionInfo.setYearInfo(yearInfoList);
        ResultVO resultVO = new ResultVO();
        resultVO.setData(conditionInfo);
        resultVO.setStatus(0);
        return resultVO;
    }

}
