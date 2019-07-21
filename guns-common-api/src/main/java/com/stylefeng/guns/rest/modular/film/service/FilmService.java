package com.stylefeng.guns.rest.modular.film.service;

import com.stylefeng.guns.rest.modular.film.vo.*;

import java.util.List;

public interface FilmService {
    public ResultVO getFilms(Parameter parameter);

    public ResultVO getFilmsDetail(int filmId, int searchType);

    public List<CatInfo> getCatInfo(int catId);

    public List<SourceInfo> getSourceInfo(int sourceId);

    public List<YearInfo> getYearInfo(int yearId);

    //首页接口
    public List<BannerVO> getBanners();

    public FilmsVO getHotFilms(Integer count, Boolean isLimit);

    public FilmsVO getSoonFilms(Integer count, Boolean isLimit);

    public List<FilmRankVO> getRanking(Integer count);

    public List<FilmRankVO> getExpectRanking(Integer count);

    public List<FilmRankVO> getTop100(Integer count);
}
