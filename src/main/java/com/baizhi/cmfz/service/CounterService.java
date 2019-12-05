package com.baizhi.cmfz.service;

import java.util.Map;

public interface CounterService {
    Map findByUidAndCid(String uid,String cid);
    Map addCounter(String uid,String cid,String title);
    Map deleteCounter(String uid,String cid,String id);
    Map updateCounter(String uid,String cid,String id,Integer count);
}
