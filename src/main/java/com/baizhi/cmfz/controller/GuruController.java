package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Guru;
import com.baizhi.cmfz.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("guru")
public class GuruController {
    @Autowired
    private GuruService guruService;
    @RequestMapping("findAuthor")
    public StringBuffer all(){
        List<Guru> allGuru = guruService.findAllAuthor();
        StringBuffer sb=new StringBuffer();
        for (Guru guru :allGuru){
            String s="<option value="+guru.getId()+">"+guru.getName()+"</option>";
            sb.append(s);
        }
        StringBuffer st=new StringBuffer();
        st.append("<select id='guru'>");
        st.append(sb);
        st.append("</select>");
        return st;
    }
    @RequestMapping("showAll")
    public Map showAll(String uid){
        Map map = guruService.findAll();
        return map;
    }
}
