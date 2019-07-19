package com.stylefeng.guns.rest.modular.cinema.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.rest.common.persistence.dao.AreaDictTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.BrandDictTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.CinemaTMapper;

import com.stylefeng.guns.rest.common.persistence.dao.HallDictTMapper;
import com.stylefeng.guns.rest.common.persistence.model.AreaDictT;
import com.stylefeng.guns.rest.common.persistence.dao.FieldTMapper;

import com.stylefeng.guns.rest.common.persistence.model.BrandDictT;
import com.stylefeng.guns.rest.common.persistence.model.CinemaT;
import com.stylefeng.guns.rest.common.persistence.model.HallDictT;
import com.stylefeng.guns.rest.modular.cinema.Service.CinemaService;
import com.stylefeng.guns.rest.modular.cinema.vo.*;
import com.stylefeng.guns.rest.modular.cinema.vo.FilmInfoVO;
import com.stylefeng.guns.rest.modular.cinema.vo.HallInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@Service(interfaceClass = CinemaService.class)
public class CinemaServiceImpl implements CinemaService {
    @Autowired
    CinemaTMapper cinemaTMapper;
    @Autowired
    BrandDictTMapper brandDictTMapper;
    @Autowired
    AreaDictTMapper areaDictTMapper;
    @Autowired
    HallDictTMapper hallDictTMapper;
    @Autowired(required = false)
    FieldTMapper fieldTMapper;


    @Override
    public Page<CinemaVO> getCinemas(CinemaQueryVO cinemaQueryVO) {
        List<CinemaVO> list = new ArrayList<>();
        int brandId=cinemaQueryVO.getBrandId();
        int areaId=cinemaQueryVO.getAreaId();
        int hallType=cinemaQueryVO.getHallType();

        EntityWrapper<CinemaT> entityWrapper = new EntityWrapper<>();
        Page page=new Page(cinemaQueryVO.getNowPage(), cinemaQueryVO.getPageSize());

        if(brandId!=99) {
            entityWrapper.eq("brand_id", brandId);
        }
        if(areaId!=99){
            entityWrapper.eq("area_id", areaId);
        }
        if(hallType!=99){
            entityWrapper.like("hall_ids", "#" + hallType + "#");
        }
        List<CinemaT> cinemaTList= cinemaTMapper.selectPage(page, entityWrapper);
        for (CinemaT cinemaT : cinemaTList) {
            CinemaVO cinemaVO = new CinemaVO();
            cinemaVO.setUuid(cinemaT.getUuid());
            cinemaVO.setCinemaName(cinemaT.getCinemaName());
            cinemaVO.setCinemaAddress(cinemaT.getCinemaAddress());
            cinemaVO.setMinimumPrice(cinemaT.getMinimumPrice());
            list.add(cinemaVO);
        }
        Page<CinemaVO> result=new Page<>();
        result.setRecords(list);

        return result;
    }

    @Override
    public List<BrandVO> getBrands(int brandId) {
        List<BrandVO> list = new ArrayList<>();
        EntityWrapper<BrandDictT> entityWrapper = new EntityWrapper<>();
        List<BrandDictT> brandDictTList;
        if(brandId==99) {
            brandDictTList = brandDictTMapper.selectList(entityWrapper);
        }else{
            return null;
        }
        for (BrandDictT brandDictT : brandDictTList) {
            BrandVO brandVO = new BrandVO();
            brandVO.setBrandId(brandDictT.getUuid());
            brandVO.setBrandName(brandDictT.getShowName());
            if (brandId == brandDictT.getUuid()) {
                brandVO.setActive(true);
            } else {
                brandVO.setActive(false);
            }
            list.add(brandVO);
        }
        return list;
    }

    @Override
    public List<AreaVO> getAreas(int areaId) {
        List<AreaVO> list = new ArrayList<>();
        EntityWrapper<AreaDictT> entityWrapper = new EntityWrapper<>();
        List<AreaDictT> areaDictTList;
        if(areaId==99) {
            areaDictTList = areaDictTMapper.selectList(entityWrapper);
        }else{
            return null;
        }
        for (AreaDictT areaDictT : areaDictTList) {
            AreaVO areaVO = new AreaVO();
            areaVO.setAreaId(areaDictT.getUuid());
            areaVO.setAreaName(areaDictT.getShowName());
            if (areaId == areaDictT.getUuid()) {
                areaVO.setActive(true);
            } else {
                areaVO.setActive(false);
            }
            list.add(areaVO);
        }
        return list;
    }

    @Override
    public List<HallTypeVO> getHallType(int hallType) {
        List<HallTypeVO> list = new ArrayList<>();
        EntityWrapper<HallDictT> entityWrapper = new EntityWrapper<>();
        List<HallDictT> hallDictTList;
        if(hallType==99) {
            hallDictTList = hallDictTMapper.selectList(entityWrapper);
        }else{
            return null;
        }
        for (HallDictT hallDictT : hallDictTList) {
            HallTypeVO hallTypeVO = new HallTypeVO();
            hallTypeVO.setHalltypeId(hallDictT.getUuid());
            hallTypeVO.setHalltypeName(hallDictT.getShowName());
            if (hallType == hallDictT.getUuid()) {
                hallTypeVO.setActive(true);
            } else {
                hallTypeVO.setActive(false);
            }
            list.add(hallTypeVO);
        }
        return list;
    }

    @Override
    public CinemaInfoVO getCinemaInfoById(int cinemaId) {
        CinemaInfoVO cinemaInfoVO=new CinemaInfoVO();
        CinemaT cinemaT=cinemaTMapper.selectById(cinemaId);
        cinemaInfoVO.setCinemaId(cinemaT.getUuid());
        cinemaInfoVO.setCinemaName(cinemaT.getCinemaName());
        cinemaInfoVO.setCinemaAdress(cinemaT.getCinemaAddress());
        cinemaInfoVO.setCinemaPhone(cinemaT.getCinemaPhone());
        cinemaInfoVO.setImgUrl(cinemaT.getImgAddress());
        return cinemaInfoVO;
    }

    @Override
    public List<FilmInfoVO> getFilmInfoById(int cinemaId) {
        List<FilmInfoVO> filmInfoVOS= fieldTMapper.getFilmInfos(cinemaId);
        return filmInfoVOS;
    }

    @Override
    public FilmInfoVO getFilemInfoByFieldId(int fieldId) {
        FilmInfoVO filmInfoVO=fieldTMapper.getFilmInfoByfieldId(fieldId);
        return filmInfoVO;
    }

    @Override
    public HallInfoVO getFilmFieldInfo(int fieldId) {
        HallInfoVO hallInfoVO=fieldTMapper.getHallInfo(fieldId);
        return hallInfoVO;

    }
}


