package com.stylefeng.guns.rest.modular.cinema.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.rest.common.persistence.dao.BrandDictTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.CinemaTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.FieldTMapper;
import com.stylefeng.guns.rest.common.persistence.model.BrandDictT;
import com.stylefeng.guns.rest.common.persistence.model.CinemaT;
import com.stylefeng.guns.rest.modular.cinema.Service.CinemaService;
import com.stylefeng.guns.rest.modular.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
    @Autowired(required = false)
    FieldTMapper fieldTMapper;

    @Override
    public Page<CinemaVO> getCinemas(CinemaQueryVO cinemaQueryVO) {
        ArrayList<CinemaVO> list=new ArrayList<>();
        CinemaVO cinemaVO=new CinemaVO();
        Page<CinemaVO> page=new Page<>(cinemaQueryVO.getNowPage(),cinemaQueryVO.getPageSize());
        EntityWrapper<CinemaT> entityWrapper=new EntityWrapper<>();
        entityWrapper .eq("brand_id",cinemaQueryVO.getBrandId())
                .eq("area_id",cinemaQueryVO.getAreaId())
                .eq("hall_ids",cinemaQueryVO.getHallType());

        List<CinemaT> cinemaTList=cinemaTMapper.selectList(entityWrapper);
        if(CollectionUtils.isEmpty(cinemaTList)){
            list=null;
        }
        for(CinemaT cinemaT:cinemaTList){
            cinemaVO.setUuid(cinemaT.getUuid());
            cinemaVO.setCinemaName(cinemaT.getCinemaName());
            cinemaVO.setCinemaAddress(cinemaT.getCinemaAddress());
            cinemaVO.setMinimumPrice(cinemaT.getMinimumPrice());
            list.add(cinemaVO);
        }
        page=page.setRecords(list);
        return page;
    }

    @Override
    public List<BrandVO> getBrands(int brandId) {
        List<BrandVO> list=new ArrayList<>();
        BrandVO brandVO=new BrandVO();
        List<BrandDictT> brandDictTList;
        EntityWrapper<BrandDictT> entityWrapper = new EntityWrapper<>();
        if(brandId==99) {
            brandDictTList = brandDictTMapper.selectList(entityWrapper);
        }else{
            entityWrapper.eq("UUID",brandId);
            brandDictTList = brandDictTMapper.selectList(entityWrapper);
        }
        for(BrandDictT brandDictT:brandDictTList){
            brandVO.setBrandId(brandDictT.getUuid());
            brandVO.setBrandName(brandDictT.getShowName());
            if(brandId==brandDictT.getUuid()){
                brandVO.setIsActive(true);
            }else{
                brandVO.setIsActive(false);
            }
            list.add(brandVO);
        }
        return list;
    }

    @Override
    public List<AreaVO> getAreas(int areaId) {
        return null;
    }

    @Override
    public List<HallTypeVO> getHallType(int hallType) {
        return null;
    }

    @Override
    public CinemaInfoVO getCinemaInfoById(int cinemaId) {
        CinemaT cinemaT=cinemaTMapper.selectById(cinemaId);

        CinemaInfoVO cinemaInfoVO=new CinemaInfoVO();

        cinemaInfoVO.setCinemaAdress(cinemaT.getCinemaAddress());
        cinemaInfoVO.setCinemaId(cinemaT.getUuid());
        cinemaInfoVO.setCinemaName(cinemaT.getCinemaName());
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
