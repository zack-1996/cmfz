package com.baizhi.cmfz;


import com.baizhi.cmfz.dao.*;
import com.baizhi.cmfz.entity.*;
import com.baizhi.cmfz.service.GuruService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmfzApplication.class)
public class CmfzApplicationTests {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private BannerDao bannerDao;
    @Test
    public void contextLoads() {
        Admin login = adminDao.login("admin", "admin");
        System.out.println(login==null);
    }
    @Test
    public void ii(){
        List<Banner> banners = bannerDao.selectAll();
        banners.forEach(banner-> System.out.println(banner));

    }
    @Autowired
    private AlbumDao albumDao;
    @Test
    public void tttt(){
        Example example=new Example(Album.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", 2);
        List<Album> albums = albumDao.selectByExample(example);
        albums.forEach(album -> System.out.println(album));
    }
    @Autowired
    private ChapterDao chapterDao;
    @Test
    public void cccc(){
        Example example=new Example(Chapter.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("album_id", 2);
        List<Chapter> chapters = chapterDao.selectByExample(example);
        chapters.forEach(chapter -> System.out.println(chapter));
    }

    @Autowired
    private GuruService guruService;
    @Test
    public  void yee(){
        Map<String, Object> all = guruService.findAll(1, 10);
        ArrayList rows = (ArrayList) all.get("rows");
        rows.forEach(row-> System.out.println(row));
    }
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private GuruDao guruDao;
    @Test
    public void dyfau(){
        List<Article> articles = articleDao.selectAll();
        for (Article article:articles){
            Guru guru = guruDao.selectByPrimaryKey(article.getGuru_id());
            System.out.println(guru);
            article.setGuru(guru);
        }
        articles.forEach(article -> System.out.println(article));
    }
    @Autowired
    private UserDao userDao;
    @Test
    public void tyty(){
        List<MapVO> byLocation = userDao.findByLocation();
        byLocation.forEach(vo-> System.out.println(vo));
        Integer s = userDao.findCountSexAndDay("男", 365);
        System.out.println(s);

    }
}
