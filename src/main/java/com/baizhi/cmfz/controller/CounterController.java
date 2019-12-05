package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("counter")
public class CounterController {
    @Autowired
    private CounterService counterService;
    @RequestMapping("showCounter")
    public Map showCounter(String uid,String cid){
        Map hashMap = counterService.findByUidAndCid(uid, cid);
        return hashMap;
    }
    @RequestMapping("add")
    public Map add(String uid,String cid,String title){
        Map map = counterService.addCounter(uid, cid, title);
        return map;
    }
    @RequestMapping("delete")
    public Map delete(String uid,String cid,String id){
        Map map = counterService.deleteCounter(uid, cid, id);
        return map;
    }
    @RequestMapping("update")
    public Map update(String uid,String cid,String id,Integer count){
        Map map = counterService.updateCounter(uid, cid, id, count);
        return map;
    }
}
