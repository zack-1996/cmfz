package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.dao.ChapterDao;
import com.baizhi.cmfz.entity.Chapter;
import com.baizhi.cmfz.service.ChapterService;
import com.baizhi.cmfz.util.GetMp3;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
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
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;
    @ResponseBody
    @RequestMapping("findAll")
    public Map find(Integer page, Integer rows,String album_id){
        Map map = chapterService.findAll(page, rows, album_id);
        return map;
    }
    @RequestMapping("edit")
    @ResponseBody
    public Map edit(Chapter chapter, String oper, MultipartFile url, HttpServletRequest request) throws IOException {
        Map hashMap = new HashMap();
        if ("add".equals(oper)){
            hashMap= chapterService.add(chapter);
        }
        if ("edit".equals(oper)){
            chapterService.update(chapter);
        }
        if ("del".equals(oper)){
            Chapter chapter1 = chapterDao.selectByPrimaryKey(chapter.getId());
            String realPath = request.getServletContext().getRealPath("/upload/audio");
            String url1 = chapter1.getUrl();
            String[] split = url1.split("/");
            String s=split[split.length-1];
            File file=new File(realPath,s);
            file.delete();
            chapterService.delete(chapter.getId());
        }
        return hashMap;
    }
    @Autowired
    private ChapterDao chapterDao;
    @RequestMapping("upload")
    @ResponseBody
    public void upload(MultipartFile url, String chapterId,String album_id, HttpSession session, HttpServletRequest request) throws Exception {
        Logger.getLogger("org.jaudiotagger").setLevel(Level.OFF);
        // 获取路径
        String realPath = session.getServletContext().getRealPath("/upload/audio");
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
        String uri = http+"://"+localHost.split("/")[1]+":"+serverPort+contextPath+"/upload/audio/"+originalFilename;
        File realFile=new File(realPath,originalFilename);
        Chapter chapter = new Chapter();
        int length = 0;
        MP3File mp3File = (MP3File) AudioFileIO.read(realFile);
        MP3AudioHeader audioHeader = (MP3AudioHeader) mp3File.getAudioHeader();
        length = audioHeader.getTrackLength();  // 单位为秒
        int fen=length/60;
        int miao=length%60;
        String time=fen+"分"+miao+"秒";
        //文件大小保留两位小数
        String size = GetMp3.getFormatSize(realFile.length());
        chapter.setId(chapterId);
        chapter.setUrl(uri);
        chapter.setAlbum_id(album_id);
        chapter.setTime(time);
        chapter.setSize(size);
        System.out.println(size);
        chapterDao.updateByPrimaryKeySelective(chapter);
    }

}
