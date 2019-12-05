package com.baizhi.cmfz.service;

import com.baizhi.cmfz.annotation.LogAnnotation;
import com.baizhi.cmfz.dao.BannerDao;
import com.baizhi.cmfz.entity.Banner;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    @LogAnnotation(value = "展示轮播图信息")
    public List<Banner> selectAll(Integer page,Integer rows) {
        Integer start=(page-1)*rows;
        List<Banner> banners = bannerDao.selectByRowBounds(new Banner(), new RowBounds(start, rows));
        return banners;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer selectCount() {
        return bannerDao.selectCount(new Banner());
    }

    @Override

    public Map add(Banner banner) {
        HashMap hashMap=new HashMap();
        String s= UUID.randomUUID().toString();
        banner.setId(s);
        hashMap.put("bannerId",s);
        hashMap.put("status",200);
        bannerDao.insert(banner);
        return hashMap;
    }

    @Override
    public Map update(Banner banner) {
        HashMap hashMap=new HashMap();
        banner.setUrl(null);
        hashMap.put("bannerId",banner.getId());
        hashMap.put("status",200);
        bannerDao.updateByPrimaryKeySelective(banner);
        return hashMap;
    }

    @Override
    public Map delete(String id) {
        HashMap hashMap=new HashMap();
        hashMap.put("bannerID",id);
        hashMap.put("status",200);
        bannerDao.deleteByPrimaryKey(id);
        return hashMap;
    }


}
