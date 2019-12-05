package com.baizhi.cmfz.aspect;

import com.baizhi.cmfz.annotation.LogAnnotation;
import com.baizhi.cmfz.entity.Log;
import com.baizhi.cmfz.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

@Aspect
@Configuration
public class LogAspect {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LogService logService;
    @Around("@annotation(com.baizhi.cmfz.annotation.LogAnnotation)")
    public Object addLog(ProceedingJoinPoint proceedingJoinPoint){
        /**
         *谁  时间  时间  成功与否
         */
        //谁
        HttpSession session = request.getSession();
        session.setAttribute("admin","admin");
        String admin = (String) session.getAttribute("admin");
        //时间
        Date date=new Date();
        //获取方法名
        String action = proceedingJoinPoint.getSignature().getName();
        //获取注解信息
        MethodSignature signature=(MethodSignature )proceedingJoinPoint.getSignature();
        LogAnnotation annotation = signature.getMethod().getAnnotation(LogAnnotation.class);
        String value = annotation.value();
        System.out.println(value);
        try {
            Object proceed=proceedingJoinPoint.proceed();
            String status="success";
            System.out.println(admin+""+date+""+action+""+status);
            Log log=new Log().setId(UUID.randomUUID().toString()).setAction(action).setName(admin).setStatus(status).setTime(date);
            //加入数据库
            logService.add(log);
            return proceed;
        }catch (Throwable throwable){
            String status="error";
            System.out.println(admin+""+date+""+action+""+status);
            //加入数据库
            return null;
        }


    }

}
