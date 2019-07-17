package com.stylefeng.guns.rest.modular.film.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.modular.film.service.FilmService;
import com.stylefeng.guns.rest.modular.film.vo.Parameter;
import com.stylefeng.guns.rest.modular.film.vo.ResultVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("film")
public class FilmController {

    @Reference
    FilmService filmService;

    @RequestMapping("getFilms")
    public ResultVO getFilms(@RequestBody Parameter parameter){
        ResultVO resultVO = filmService.getFilms(parameter);
        return resultVO;
    }
}