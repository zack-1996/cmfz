package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.dao.ArticleDao;
import com.baizhi.cmfz.entity.Article;
import com.baizhi.cmfz.service.ArticleService;
import com.baizhi.cmfz.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @RequestMapping("findAll")
    @ResponseBody
    public Map findAll(Integer page, Integer rows){
        Map<String, Object> hashMap = articleService.findAll(page, rows);

        return hashMap;
    }
    @RequestMapping("addArticle")
    @ResponseBody
    public Map addArticle(Article article,MultipartFile inputfile, HttpServletRequest request,HttpSession session) throws Exception{
        HashMap hashMap = new HashMap();
        String dir = "/upload/article/";
        String httpUrl = HttpUtil.getHttpUrl(inputfile, request, session, dir);
        article.setImg(httpUrl);
        articleService.add(article);
        System.out.println(article);
        return hashMap;
    }
    @RequestMapping("updateArticle")
    @ResponseBody
    public Map updateArticle(Article article){
        System.out.println(article);
        HashMap hashMap = new HashMap();
        articleService.update(article);

        return hashMap;
    }
    @Autowired
    private ArticleDao articleDao;
    @RequestMapping("edit")
    @ResponseBody
    public Map edit(String oper,HttpServletRequest request,Article article){
        if ("del".equals(oper)){
            Article article1 = articleDao.selectByPrimaryKey(article.getId());
            String realPath = request.getServletContext().getRealPath("/upload/article");
            String url1 = article1.getImg();
            String[] split = url1.split("/");
            String s=split[split.length-1];
            File file=new File(realPath,s);
            file.delete();
            articleService.delete(article.getId());
        }
        return null;
    }

    //上传富文本图片
    @RequestMapping("uploadImg")
    @ResponseBody
    public Map uploadImg(MultipartFile imgFile,HttpServletRequest request,HttpSession session) {
        HashMap hashMap = new HashMap();
        String dir = "/upload/articleImg/";
        try {
            String httpUrl = HttpUtil.getHttpUrl(imgFile, request, session, dir);
            hashMap.put("error", 0);
            hashMap.put("url", httpUrl);
        } catch (Exception e) {
            hashMap.put("error", 0);
            hashMap.put("message", "上传错误");
            e.printStackTrace();
        }
        return hashMap;
    }

}
