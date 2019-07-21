package com.stylefeng.guns.rest.modular.film.service.impl;

import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stylefeng.guns.rest.common.persistence.dao.*;
import com.stylefeng.guns.rest.common.persistence.model.*;
import com.stylefeng.guns.rest.modular.film.service.FilmService;
import com.stylefeng.guns.rest.modular.film.vo.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Component
@Service(interfaceClass = FilmService.class)
public class FilmServiceImpl implements FilmService {

    @Autowired
    MtimeFilmTMapper filmTMapper;

    @Autowired
    MtimeFilmInfoTMapper filmInfoTMapper;

    @Autowired
    MtimeCatDictTMapper catDictTMapper;

    @Autowired
    MtimeSourceDictTMapper sourceDictTMapper;

    @Autowired
    MtimeFilmActorTMapper filmActorTMapper;

    @Autowired
    MtimeActorTMapper actorTMapper;

    @Autowired
    MtimeYearDictTMapper yearDictTMapper;

    @Autowired
    MtimeBannerTMapper bannerTMapper;

    //查询影片信息(影片查询接口)
    @Override
    public ResultVO getFilms(Parameter parameter) {
        PageHelper.startPage(parameter.getNowPage(), parameter.getPageSize());
        EntityWrapper<MtimeFilmT> entityWrapper = new EntityWrapper<>();
        /*entityWrapper.eq("film_type", parameter.getShowType());
        entityWrapper.eq("film_cats", parameter.getCatId());
        entityWrapper.eq("film_source", parameter.getSortId());
        entityWrapper.eq("film_date", parameter.getYearId());*/
        List<MtimeFilmT> mtimeFilmTS = filmTMapper.selectList(entityWrapper);
        List<FilmShortVO> list = new ArrayList<>();
        for (MtimeFilmT mtimeFilmT : mtimeFilmTS) {
            FilmShortVO filmShortVO = new FilmShortVO();
            filmShortVO.setFilmId(mtimeFilmT.getUuid());
            filmShortVO.setFilmName(mtimeFilmT.getFilmName());
            filmShortVO.setFilmType(mtimeFilmT.getFilmType());
            filmShortVO.setFilmScore(mtimeFilmT.getFilmScore());
            filmShortVO.setImgAddress(mtimeFilmT.getImgAddress());
            list.add(filmShortVO);
        }
        PageInfo<FilmShortVO> pageInfo = new PageInfo<>(list);
        //Page page = new Page(parameter.getNowPage(), parameter.getPageSize());
        ResultVO resultVO = new ResultVO();
        resultVO.setData(pageInfo.getList());
        resultVO.setTotalPage((int)pageInfo.getTotal());
        resultVO.setNowPage(parameter.getNowPage());
        resultVO.setImgPre("http://img.meetingshop.cn/");
        resultVO.setStatus(0);
        return resultVO;
    }

    //查询影片详情
    @Override
    public ResultVO getFilmsDetail(int filmId, int searchType) {
        //判断查询条件，影片编号还是影片名称

        if(searchType == 0){
            EntityWrapper<MtimeFilmInfoT> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("film_id", filmId);
            //获取影片本身信息
            MtimeFilmInfoT filmInfoT = filmInfoTMapper.selectById(filmId);
            //获取影片外部信息
            MtimeFilmT filmT = filmTMapper.selectById(filmId);
            //根据分类编号查询分类名(info01)
            String filmCats = filmT.getFilmCats();
            String[] split = filmCats.split("#");
            StringBuffer sb = new StringBuffer();
            for (int i = 1; i < split.length-1; i++) {
                MtimeCatDictT catDictT = catDictTMapper.selectById(split[i]);
                sb.append(catDictT.getShowName() + ",");
            }
            String substring = sb.substring(0, sb.length() - 1);
            //获取影片来源(info02)
            MtimeSourceDictT sourceDictT = sourceDictTMapper.selectById(filmInfoT.getFilmId());
            //获取影片上映时间和地点(info03 用已知条件添加)
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String info3 = format.format(filmT.getFilmTime());
            //imgVO
            String filmImgs = filmInfoT.getFilmImgs();
            String[] split1 = filmImgs.split(",");
            HashMap<Object, Object> imgMap = new HashMap<>();
            imgMap.put("mainImg", split1[0]);
            imgMap.put("img01", split1[1]);
            imgMap.put("img02", split1[2]);
            imgMap.put("img03", split1[3]);
            imgMap.put("img04", split1[4]);
            //根据影片id查询角色信息
            EntityWrapper<MtimeFilmActorT> wrapper = new EntityWrapper<>();
            wrapper.eq("film_id", filmId);
            List<MtimeFilmActorT> filmActorTS = filmActorTMapper.selectList(wrapper);
            List<Object> list = new ArrayList<>();
            HashMap<Object, Object> actorsMap = new HashMap<>();
            for (MtimeFilmActorT filmActorT : filmActorTS) {
                MtimeActorT mtimeActorT = actorTMapper.selectById(filmActorT.getActorId());
                Actor actor = new Actor();
                actor.setDirectorName(mtimeActorT.getActorName());
                actor.setImgAddress(mtimeActorT.getActorImg());
                actor.setRoleName(filmActorT.getRoleName());
                list.add(actor);
                if(mtimeActorT.getUuid() == filmInfoT.getDirectorId()){
                    Actor actor1 = new Actor();
                    actor1.setDirectorName(actor.getDirectorName());
                    actor1.setImgAddress(actor.getImgAddress());
                    actorsMap.put("director", actor1);
                }
            }
            actorsMap.put("actors", list);
            //从上面得到了actors
            //封装info4
            FilmInfo filmInfo = new FilmInfo();
            filmInfo.setActors(actorsMap);
            filmInfo.setBiopgraphy(filmInfoT.getBiography());
            filmInfo.setFilmId(filmInfoT.getFilmId());
            filmInfo.setImgVO(imgMap);
            //封装filmDetail
            FilmDetail filmDetail = new FilmDetail();
            filmDetail.setFilmEnName(filmInfoT.getFilmEnName());
            filmDetail.setFilmId(filmInfoT.getFilmId());
            filmDetail.setFilmName(filmT.getFilmName());
            filmDetail.setImgAddress(filmT.getImgAddress());
            filmDetail.setInfo01(substring);
            filmDetail.setInfo02(sourceDictT.getShowName() + " / " + filmInfoT.getFilmLength());
            filmDetail.setInfo03(info3 + sourceDictT.getShowName() + "上映");
            filmDetail.setInfo04(filmInfo);
            filmDetail.setScore(filmInfoT.getFilmScore());
            filmDetail.setScoreNum(filmInfoT.getFilmScoreNum());
            filmDetail.setTotalBox(filmT.getFilmBoxOffice());
            //封装返回前端的VO
            ResultVO resultVO = new ResultVO();
            resultVO.setStatus(0);
            resultVO.setImgPre("http://img.meetingshop.cn/");
            resultVO.setData(filmDetail);
            return resultVO;
        }
        return null;
    }

    //获取类型信息
    @Override
    public List<CatInfo> getCatInfo(int catId) {
        EntityWrapper<MtimeCatDictT> wrapper = new EntityWrapper<>();
        List<MtimeCatDictT> catDictTList = catDictTMapper.selectList(wrapper);
        List<CatInfo> list = new ArrayList<>();
        for (MtimeCatDictT mtimeCatDictT : catDictTList) {
            CatInfo catInfo = new CatInfo();
            catInfo.setCatId(mtimeCatDictT.getUuid());
            catInfo.setCatName(mtimeCatDictT.getShowName());
            if(mtimeCatDictT.getUuid() == catId){
                catInfo.setActive(true);
            }else {
                catInfo.setActive(false);
            }
            list.add(catInfo);
        }
        return list;
    }

    //获取来源信息
    @Override
    public List<SourceInfo> getSourceInfo(int sourceId) {
        EntityWrapper<MtimeSourceDictT> wrapper = new EntityWrapper<>();
        List<MtimeSourceDictT> sourceDictTS = sourceDictTMapper.selectList(wrapper);
        List<SourceInfo> list = new ArrayList<>();
        for (MtimeSourceDictT sourceDictT : sourceDictTS) {
            SourceInfo sourceInfo = new SourceInfo();
            sourceInfo.setSourceId(sourceDictT.getUuid());
            sourceInfo.setSourceName(sourceDictT.getShowName());
            if(sourceDictT.getUuid() == sourceId){
                sourceInfo.setActive(true);
            }else {
                sourceInfo.setActive(false);
            }
            list.add(sourceInfo);
        }
        return list;
    }

    //获取年代信息
    @Override
    public List<YearInfo> getYearInfo(int yearId) {
        EntityWrapper<MtimeYearDictT> wrapper = new EntityWrapper<>();
        List<MtimeYearDictT> yearDictTS = yearDictTMapper.selectList(wrapper);
        ArrayList<YearInfo> list = new ArrayList<>();
        for (MtimeYearDictT yearDictT : yearDictTS) {
            YearInfo yearInfo = new YearInfo();
            yearInfo.setYearId(yearDictT.getUuid());
            yearInfo.setYearName(yearDictT.getShowName());
            if(yearDictT.getUuid() == yearId){
                yearInfo.setActive(true);
            }else {
                yearInfo.setActive(false);
            }
            list.add(yearInfo);
        }
        return list;
    }

    //首页接口实现
    @Override
    public List<BannerVO> getBanners() {
        List<BannerVO> list = new ArrayList<>();
        EntityWrapper<MtimeBannerT> wrapper = new EntityWrapper<>();
        wrapper.eq("is_valid", 1);
        List<MtimeBannerT> bannerTS = bannerTMapper.selectList(wrapper);
        if(CollectionUtils.isEmpty(bannerTS)){
            return list;
        }
        for (MtimeBannerT bannerT : bannerTS) {
            BannerVO bannerVO = new BannerVO();
            bannerVO.setBannerId(bannerT.getUuid() + "");
            bannerVO.setBannerAddress(bannerT.getBannerAddress());
            bannerVO.setBannerUrl(bannerT.getBannerUrl());
            list.add(bannerVO);
        }
        return list;
    }

    @Override
    public FilmsVO getHotFilms(Integer count, Boolean isLimit) {
        FilmsVO filmsVO = new FilmsVO();
        EntityWrapper<MtimeFilmT> wrapper = new EntityWrapper<>();
        wrapper.eq("film_status", 1);
        Integer needCount = filmTMapper.selectCount(wrapper);
        List<MtimeFilmT> mtimeFilmTS;
        if(isLimit){
            Page page = new Page(1, count);
            mtimeFilmTS = filmTMapper.selectPage(page, wrapper);
        }else {
            mtimeFilmTS = filmTMapper.selectList(wrapper);
        }
        List<FilmInfoVO> list = conver2FilmInfoVO1(mtimeFilmTS);
        filmsVO.setFilmNum(needCount);
        filmsVO.setFilmInfo(list);
        return filmsVO;
    }

    private List<FilmInfoVO> conver2FilmInfoVO1(List<MtimeFilmT> mtimeFilmTS) {
        List<FilmInfoVO> list = new ArrayList<>();
        if(CollectionUtils.isEmpty(mtimeFilmTS)){
            return list;
        }
        for (MtimeFilmT film : mtimeFilmTS) {
            FilmInfoVO filmInfoVO = new FilmInfoVO();
            filmInfoVO.setFilmId(film.getUuid()+"");
            filmInfoVO.setFilmName(film.getFilmName());
            filmInfoVO.setImgAddress(film.getImgAddress());
            filmInfoVO.setFilmType(film.getFilmType());
            filmInfoVO.setFilmScore(film.getFilmScore());
            list.add(filmInfoVO);
        }
        return list;
    }

    @Override
    public FilmsVO getSoonFilms(Integer count, Boolean isLimit) {
        FilmsVO filmsVO = new FilmsVO();
        EntityWrapper<MtimeFilmT> wrapper = new EntityWrapper<>();
        wrapper.eq("film_status", 2);
        Integer needCount = filmTMapper.selectCount(wrapper);
        List<MtimeFilmT> mtimeFilmTS;
        if(isLimit){
            Page page = new Page(1, count);
            mtimeFilmTS = filmTMapper.selectPage(page, wrapper);
        }else {
            mtimeFilmTS = filmTMapper.selectList(wrapper);
        }
        List<FilmInfoVO> list = conver2FilmInfoVO2(mtimeFilmTS);
        filmsVO.setFilmNum(needCount);
        filmsVO.setFilmInfo(list);
        return filmsVO;
    }

    private List<FilmInfoVO> conver2FilmInfoVO2(List<MtimeFilmT> mtimeFilmTS) {
        List<FilmInfoVO> list = new ArrayList<>();
        if(CollectionUtils.isEmpty(mtimeFilmTS)){
            return list;
        }
        for (MtimeFilmT film : mtimeFilmTS) {
            FilmInfoVO filmInfoVO = new FilmInfoVO();
            filmInfoVO.setFilmId(film.getUuid()+"");
            filmInfoVO.setFilmName(film.getFilmName());
            filmInfoVO.setImgAddress(film.getImgAddress());
            filmInfoVO.setFilmType(film.getFilmType());
            filmInfoVO.setExpectNum(film.getFilmPresalenum());
            filmInfoVO.setShowTime(film.getFilmTime());
            list.add(filmInfoVO);
        }
        return list;
    }

    @Override
    public List<FilmRankVO> getRanking(Integer count) {
        List<FilmRankVO> vos = new ArrayList<>();
        Page<MtimeFilmT> page = new Page<>(1, count, "film_box_office", false);
        EntityWrapper<MtimeFilmT> wrapper = new EntityWrapper<>();
        List<MtimeFilmT> mtimeFilmTS = filmTMapper.selectPage(page, wrapper);
        if(CollectionUtils.isEmpty(mtimeFilmTS)){
            return vos;
        }
        vos = conver2RankVO1(mtimeFilmTS);    
        return vos;
    }

    private List<FilmRankVO> conver2RankVO1(List<MtimeFilmT> mtimeFilmTS) {
        List<FilmRankVO> rankVOS = new ArrayList<>();
        for (MtimeFilmT film : mtimeFilmTS) {
            FilmRankVO filmRankVO = new FilmRankVO();
            filmRankVO.setFilmId(film.getUuid()+"");
            filmRankVO.setFileName(film.getFilmName());
            filmRankVO.setImgAddress(film.getImgAddress());
            filmRankVO.setBoxNum(film.getFilmBoxOffice());
            rankVOS.add(filmRankVO);
        }
        return rankVOS;
    }

    @Override
    public List<FilmRankVO> getExpectRanking(Integer count) {
        List<FilmRankVO> vos = new ArrayList<>();
        Page<MtimeFilmT> page = new Page<>(1, count, "film_box_office", false);
        EntityWrapper<MtimeFilmT> wrapper = new EntityWrapper<>();
        List<MtimeFilmT> mtimeFilmTS = filmTMapper.selectPage(page, wrapper);
        if(CollectionUtils.isEmpty(mtimeFilmTS)){
            return vos;
        }
        vos = conver2RankVO2(mtimeFilmTS);
        return vos;
    }

    private List<FilmRankVO> conver2RankVO2(List<MtimeFilmT> mtimeFilmTS) {
        List<FilmRankVO> rankVOS = new ArrayList<>();
        for (MtimeFilmT film : mtimeFilmTS) {
            FilmRankVO filmRankVO = new FilmRankVO();
            filmRankVO.setFilmId(film.getUuid()+"");
            filmRankVO.setFileName(film.getFilmName());
            filmRankVO.setImgAddress(film.getImgAddress());
            filmRankVO.setExpectNum(film.getFilmPresalenum());
            rankVOS.add(filmRankVO);
        }
        return rankVOS;
    }

    @Override
    public List<FilmRankVO> getTop100(Integer count) {
        List<FilmRankVO> vos = new ArrayList<>();
        Page<MtimeFilmT> page = new Page<>(1, count, "film_box_office", false);
        EntityWrapper<MtimeFilmT> wrapper = new EntityWrapper<>();
        List<MtimeFilmT> mtimeFilmTS = filmTMapper.selectPage(page, wrapper);
        if(CollectionUtils.isEmpty(mtimeFilmTS)){
            return vos;
        }
        vos = conver2RankVO3(mtimeFilmTS);
        return vos;
    }

    private List<FilmRankVO> conver2RankVO3(List<MtimeFilmT> mtimeFilmTS) {
        List<FilmRankVO> rankVOS = new ArrayList<>();
        for (MtimeFilmT film : mtimeFilmTS) {
            FilmRankVO filmRankVO = new FilmRankVO();
            filmRankVO.setFilmId(film.getUuid()+"");
            filmRankVO.setFileName(film.getFilmName());
            filmRankVO.setImgAddress(film.getImgAddress());
            filmRankVO.setScore(film.getFilmScore());
            rankVOS.add(filmRankVO);
        }
        return rankVOS;
    }
}
