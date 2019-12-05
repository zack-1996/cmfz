package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.dao.BannerDao;
import com.baizhi.cmfz.entity.Banner;
import com.baizhi.cmfz.service.BannerService;
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
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;
    @Autowired
    private BannerDao bannerDao;
    @RequestMapping("findAll")
    @ResponseBody
    public Map<String,Object> findAll(Integer page, Integer rows){
        HashMap<String,Object> map=new HashMap<>();
        //所有信息条数
        Integer all = bannerService.selectCount();
        //所有信息
        List<Banner> list = bannerService.selectAll(page,rows);
        //获取所有页数
        Integer pages=all%rows==0?all/rows:all/rows+1;
        map.put("rows",list);
        map.put("total",pages);
        map.put("page",page);
        map.put("records",all);
        return map;
    }
    @RequestMapping("edit")
    @ResponseBody
    public Map edit(Banner banner, String oper, MultipartFile url, HttpServletRequest request) throws IOException {
        Map hashMap = new HashMap();
        if ("add".equals(oper)){
            hashMap= bannerService.add(banner);
            System.out.println(hashMap.get("bannerId"));
        }
        if ("edit".equals(oper)){
            Banner banner1 = bannerDao.selectByPrimaryKey(banner.getId());
            String realPath = request.getServletContext().getRealPath("/upload/img");
            String url1 = banner.getUrl();
            String[] split = url1.split("/");
            String s=split[split.length-1];
            File file=new File(realPath,s);
            file.delete();
            bannerService.update(banner);
        }
        if ("del".equals(oper)){

            bannerService.delete(banner.getId());
        }
        return hashMap;
    }
    @RequestMapping("upload")
    @ResponseBody
    public void upload(MultipartFile url, String bannerId, HttpSession session, HttpServletRequest request) throws UnknownHostException, UnknownHostException {
        // 获取路径
        String realPath = session.getServletContext().getRealPath("/upload/img");
        // 判断路径文件夹是否存在
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }
        // 防止重名操作
        String originalFilename = url.getOriginalFilename();
        originalFilename = new Date().getTime()+"_"+originalFilename;
        try {
            url.transferTo(new File(realPath,originalFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 相对路径 : ../XX/XX/XX.jpg
        // 网络路径 : http://IP:端口/项目名/文件存放位置
        String http = request.getScheme();
        String localHost = InetAddress.getLocalHost().toString();
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();
        String uri = http+"://"+localHost.split("/")[1]+":"+serverPort+contextPath+"/upload/img/"+originalFilename;
        Banner banner = new Banner();
        banner.setId(bannerId);
        banner.setUrl(uri);
        bannerDao.updateByPrimaryKeySelective(banner);
        System.out.println(url);
        System.out.println(bannerId);
    }
}
