package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.dao.AlbumDao;
import com.baizhi.cmfz.dao.ArticleDao;
import com.baizhi.cmfz.dao.ChapterDao;
import com.baizhi.cmfz.dao.CourseDao;
import com.baizhi.cmfz.entity.*;
import com.baizhi.cmfz.service.AlbumService;
import com.baizhi.cmfz.service.ArticleService;
import com.baizhi.cmfz.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("front")
public class FrontController {
    @Autowired
    private BannerService bannerService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private ArticleDao articleDao;
    @RequestMapping("yiji")
    public Map yiji(String uid,String type,String sub_type){
        HashMap hashMap=new HashMap();
        if (type.equals("all")){
            List<Banner> banners = bannerService.selectAll(1, 5);
            List<Album> albums = albumDao.findByOrder();
            List<Article> articles = articleDao.findByOrder();
            hashMap.put("status","200");
            hashMap.put("head",banners);
            hashMap.put("albums",albums);
            hashMap.put("articles",articles);
        }
        if (type.equals("wen")){
            hashMap.put("status","200");
            List<Album> albums = albumDao.selectAll();
            hashMap.put("albums",albums);
        }
        if (type.equals("si")){
            //展示关注的上师的书籍
            if ("ssjy".equals(sub_type)){
                List<Article> articles = articleDao.findByGZ(uid);
                hashMap.put("status","200");
                hashMap.put("articles",articles);
            }else if ("xmfy".equals(sub_type)){
                //展示所有书籍
                hashMap.put("status","200");
                List<Article> articles = articleDao.selectAll();
                hashMap.put("articles",articles);
            }else {
                //展示所有书籍
                hashMap.put("status","200");
                List<Article> articles = articleDao.selectAll();
                hashMap.put("articles",articles);
            }


        }
        return hashMap;
    }
    @RequestMapping("article")
    public Map article(String uid,String id){
        HashMap hashMap=new HashMap();
        try {
            Article article = articleDao.selectOne(new Article().setId(id));
            hashMap.put("status","200");
            hashMap.put("article",article);
        }catch (Exception e){
            hashMap.put("status","-200");
            hashMap.put("message","文章详情查询出错");
        }finally {
            return hashMap;
        }
    }
    @RequestMapping("album")
    public Map album(String uid,String id){
        HashMap hashMap=new HashMap();
        try {
            Album album = albumDao.selectOne(new Album().setId(id));
            hashMap.put("status","200");
            hashMap.put("article",album);
        }catch (Exception e){
            hashMap.put("status","-200");
            hashMap.put("message","文章详情查询出错");
        }finally {
            return hashMap;
        }
    }
    @Autowired
    private ChapterDao chapterDao;
    @RequestMapping("chapter")
    public Map chapter(String uid,String id){
        HashMap hashMap=new HashMap();
        try {
            List<Chapter> chapters = chapterDao.findByAlbumId(id);
            hashMap.put("status","200");
            hashMap.put("chapters",chapters);
        }catch (Exception e){
            hashMap.put("status","-200");
            hashMap.put("message","章节详情查询出错");
        }finally {
            return hashMap;
        }
    }
    @Autowired
    private CourseDao courseDao;
    @RequestMapping("course")
    public Map course(String uid){
        HashMap hashMap=new HashMap();
        List<Course> courses = courseDao.findByUserId(uid);
        hashMap.put("status","200");
        hashMap.put("courses",courses);
        return hashMap;
    }

}
