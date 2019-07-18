package com.stylefeng.guns.rest.modular.cinema.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.rest.common.persistence.dao.AreaDictTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.BrandDictTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.CinemaTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.HallDictTMapper;
import com.stylefeng.guns.rest.common.persistence.model.AreaDictT;
import com.stylefeng.guns.rest.common.persistence.model.BrandDictT;
import com.stylefeng.guns.rest.common.persistence.model.CinemaT;
import com.stylefeng.guns.rest.common.persistence.model.HallDictT;
import com.stylefeng.guns.rest.modular.cinema.Service.CinemaService;
import com.stylefeng.guns.rest.modular.cinema.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Service;



import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
@Component
@Service
public class CinemaServiceImpl implements CinemaService {
    @Autowired
    CinemaTMapper cinemaTMapper;
    @Autowired
    BrandDictTMapper brandDictTMapper;
    @Autowired
    AreaDictTMapper areaDictTMapper;
    @Autowired
    HallDictTMapper hallDictTMapper;

    @Override
    public Page<CinemaVO> getCinemas(CinemaQueryVO cinemaQueryVO) {
        ArrayList<CinemaVO> list = new ArrayList<>();
        CinemaVO cinemaVO = new CinemaVO();
        Page<CinemaVO> page = new Page<>(cinemaQueryVO.getNowPage(), cinemaQueryVO.getPageSize());
        EntityWrapper<CinemaT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("brand_id", cinemaQueryVO.getBrandId())
                .eq("area_id", cinemaQueryVO.getAreaId())
                .eq("hall_ids", cinemaQueryVO.getHallType());

        List<CinemaT> cinemaTList = cinemaTMapper.selectList(entityWrapper);
        if (CollectionUtils.isEmpty(cinemaTList)) {
            list = null;
        }
        for (CinemaT cinemaT : cinemaTList) {
            cinemaVO.setUuid(cinemaT.getUuid());
            cinemaVO.setCinemaName(cinemaT.getCinemaName());
            cinemaVO.setCinemaAddress(cinemaT.getCinemaAddress());
            cinemaVO.setMinimumPrice(cinemaT.getMinimumPrice());
            list.add(cinemaVO);
        }
        page = page.setRecords(list);
        return page;
    }

    @Override
    public List<BrandVO> getBrands(int brandId) {
        List<BrandVO> list = new ArrayList<>();
        BrandVO brandVO = new BrandVO();
        List<BrandDictT> brandDictTList;
        EntityWrapper<BrandDictT> entityWrapper = new EntityWrapper<>();
        if (brandId == 99) {
            brandDictTList = brandDictTMapper.selectList(entityWrapper);
        } else {
            entityWrapper.eq("UUID", brandId);
            brandDictTList = brandDictTMapper.selectList(entityWrapper);
        }
        for (BrandDictT brandDictT : brandDictTList) {
            brandVO.setBrandId(brandDictT.getUuid());
            brandVO.setBrandName(brandDictT.getShowName());
            if (brandId == brandDictT.getUuid()) {
                brandVO.setIsActive(true);
            } else {
                brandVO.setIsActive(false);
            }
            list.add(brandVO);
        }
        return list;
    }

    @Override
    public List<AreaVO> getAreas(int areaId) {
        List<AreaVO> list = new ArrayList<>();
        AreaVO areaVO = new AreaVO();
        List<AreaDictT> areaDictTList;
        EntityWrapper<AreaDictT> entityWrapper = new EntityWrapper<>();
        if (areaId == 99) {
            areaDictTList = areaDictTMapper.selectList(entityWrapper);
        } else {
            entityWrapper.eq("UUID", areaId);
            areaDictTList = areaDictTMapper.selectList(entityWrapper);
        }
        for (AreaDictT areaDictT : areaDictTList) {
            areaVO.setAreaId(areaDictT.getUuid());
            areaVO.setAreaName(areaDictT.getShowName());
            if (areaId == areaDictT.getUuid()) {
                areaVO.setIsActive(true);
            } else {
                areaVO.setIsActive(false);
            }
            list.add(areaVO);
        }
        return list;
    }

    @Override
    public List<HallTypeVO> getHallType(int hallType) {
        List<HallTypeVO> list = new ArrayList<>();
        HallTypeVO hallTypeVO = new HallTypeVO();
        List<HallDictT> hallDictTList;
        EntityWrapper<HallDictT> entityWrapper = new EntityWrapper<>();
        if (hallType == 99) {
            hallDictTList = hallDictTMapper.selectList(entityWrapper);
        } else {
            entityWrapper.eq("UUID", hallType);
            hallDictTList = hallDictTMapper.selectList(entityWrapper);
        }
        for (HallDictT hallDictT : hallDictTList) {
            hallTypeVO.setHalltypeId(hallDictT.getUuid());
            hallTypeVO.setHalltype(hallDictT.getShowName());
            if (hallType == hallDictT.getUuid()) {
                hallTypeVO.setIsActive(true);
            } else {
                hallTypeVO.setIsActive(false);
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
}


