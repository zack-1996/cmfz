package com.baizhi.cmfz.controller;


import com.baizhi.cmfz.util.SecurityCode;
import com.baizhi.cmfz.util.SecurityImage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

@Controller
@RequestMapping("yzm")
public class CaptchaAction {
	@RequestMapping("yzm")
	public String captcha(HttpServletResponse response, HttpServletRequest request) throws Exception{
		//用工具类生成随机验证码
		String securityCode = SecurityCode.getSecurityCode();
		//将生成的session存入session中
		HttpSession session = request.getSession();
		session.setAttribute("securityCode", securityCode);
		//生成验证码图片
		BufferedImage image = SecurityImage.createImage(securityCode);
		//响应到客户端
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "png", out);
		return null;
	}
	
}
