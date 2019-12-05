package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.dao.AlbumDao;
import com.baizhi.cmfz.entity.Album;
import com.baizhi.cmfz.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @ResponseBody
    @RequestMapping("findAll")
    public Map findAll(Integer page, Integer rows){
        Map map = albumService.findAll(page, rows);
        return map;
    }
    @RequestMapping("edit")
    @ResponseBody
    public Map edit(Album album, String oper, MultipartFile url, HttpServletRequest request) throws IOException {
        Map hashMap = new HashMap();
        if ("add".equals(oper)){
            hashMap= albumService.add(album);
            System.out.println(hashMap.get("albumId"));
        }
        if ("edit".equals(oper)){
            albumService.update(album);
        }
        if ("del".equals(oper)){

            albumService.delete(album.getId());
        }
        return hashMap;
    }
    @Autowired
    private AlbumDao albumDao;
    @RequestMapping("upload")
    @ResponseBody
    public void upload(MultipartFile img, String albumId, HttpSession session, HttpServletRequest request) throws Exception {
        // 获取路径
        String realPath = session.getServletContext().getRealPath("/upload/img");
        // 判断路径文件夹是否存在
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }
        // 防止重名操作
        String originalFilename = img.getOriginalFilename();
        originalFilename = new Date().getTime()+"_"+originalFilename;
        try {
            img.transferTo(new File(realPath,originalFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 相对路径 : ../XX/XX/XX.jpg
        // 网络路径 : http://IP:端口/项目名/文件存放位置
        String http = request.getScheme();
        String localHost = InetAddress.getLocalHost().toString();
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();
        String imgi = http+"://"+localHost.split("/")[1]+":"+serverPort+contextPath+"/upload/img/"+originalFilename;
        Album album = new Album();
        album.setId(albumId);
        album.setImg(imgi);
        albumDao.updateByPrimaryKeySelective(album);
        System.out.println(img);
        System.out.println(albumId);
    }
}
