package com.stylefeng.guns.rest.modular.film.service;

import com.stylefeng.guns.rest.modular.film.vo.Parameter;
import com.stylefeng.guns.rest.modular.film.vo.ResultVO;

public interface FilmService {
    ResultVO getFilms(Parameter parameter);
}
