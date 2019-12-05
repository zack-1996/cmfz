package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.dao.UserDao;
import com.baizhi.cmfz.entity.MapVO;
import com.baizhi.cmfz.entity.User;
import com.baizhi.cmfz.service.UserService;
import com.baizhi.cmfz.util.MessageUtil;
import com.baizhi.cmfz.util.SecurityCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserService userService;
    //前台登录结口
    @RequestMapping("login")
    public Map login(String phone, String password, HttpSession Session){
        Map login = userService.login(phone, password);
        User user =(User) login.get("user");
        Session.setAttribute("user",user);
        return login;
    }
    //发送验证码
    @RequestMapping("yzm")
    public Map yzm(String phone){
        HashMap hashMap=new HashMap();
        try {
            String code = SecurityCode.getSecurityCode();
            //code存入redis
            stringRedisTemplate.opsForValue().set(phone,code,60, TimeUnit.SECONDS);
            MessageUtil.sendMessage(phone,code);
            //发送验证法
            hashMap.put("status","200");
            hashMap.put("message",code);

        }catch (Exception e){
            hashMap.put("status","-200");
            hashMap.put("message","code");
        }finally {
            return hashMap;
        }
    }
    //注册接口
    @RequestMapping("register")
    public Map register(String code){
        HashMap hashMap=new HashMap();
        String s = stringRedisTemplate.opsForValue().get("15515242561");
        if (code.equals(s)){
            //比较验证码正确

            hashMap.put("status","200");
            hashMap.put("message","验证码正确，注册");
        }else {
            hashMap.put("status","-200");
            hashMap.put("message","验证码错误");
        }
        return hashMap;
    }
    //补充个人信息接口
    @RequestMapping("UserXinXi")
    public Map UserXinXi(String id,String password,String photo,String name,String nick_name,String sex,String sign,String location){
        HashMap hashMap=new HashMap();
        User user = new User().setId(id).setPassword(password).setPhoto(photo).setName(name).setNick_name(nick_name).setSex(sex).setSign(sign).setLocation(location);
        try {
            userService.update(user);
            User user1 = userDao.selectByPrimaryKey(new User().setId(user.getId()));
            hashMap.put("status","200");
            hashMap.put("user",user1);
        }catch (Exception e){
            hashMap.put("status","-200");
            hashMap.put("message","补充个人信息错误");
        }finally {
            return hashMap;
        }
    }
    //修改个人信息
    @RequestMapping("update")
    public Map update(String uid,String sex,String photo,String location,String sign,String nick_name,String password){
        HashMap hashMap=new HashMap();
        User user= new User().setId(uid).setSex(sex).setPhone(photo).setLocation(location).setSign(sign).setNick_name(nick_name).setPassword(password);
        try {
            userService.update(user);
            User user1 = userDao.selectOne(user.setId(uid));
            hashMap.put("status","-200");
            hashMap.put("user",user1);
        }catch (Exception e){
            hashMap.put("status","-200");
            hashMap.put("message","修改个人信息错误");
        }finally {
            return hashMap;
        }
    }
    @RequestMapping("findAll")
    public Map findAll(Integer page, Integer rows) {
        Map hashMap = userService.findAll(page, rows);
        return hashMap;
    }

    @Autowired
    private UserDao userDao;

    @RequestMapping("edit")
    public Map edit(User user, String oper) {
        Map hashMap = new HashMap();
        if ("add".equals(oper)) {
            System.out.println(hashMap.get("bannerId"));
        }
        if ("edit".equals(oper)) {
            System.out.println(user);
            userService.update(user);
        }
        if ("del".equals(oper)) {

        }
        return hashMap;
    }
    @RequestMapping("showUsersCount")
    public Map showUsersCount(){
        Map userCount = userService.findUserCount();
        System.out.println(userCount);
        return userCount;

    }
    @RequestMapping("showUsersLocation")
    public List<MapVO> showUsersLocation(){
        List<MapVO> mapVOS = userDao.findByLocation();
        return mapVOS;
    }
}