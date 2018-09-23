package com.J621.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.J621.service.UserService;
import com.J621.utils.FinalStrings;
import com.J621.utils.IDUtil;
import com.J621.utils.IPUtil;
import com.J621.utils.JsonUtils;
import com.J621.utils.MD5Util;
import com.J621.vo.J621User;

@Controller
@RequestMapping("/J621/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/resign", method = RequestMethod.GET)
	@ResponseBody
	public String resignUser(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "password2", required = true) String password2,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "sex", required = true) String sex,
			HttpServletRequest request) {
		J621User user = new J621User();

		user.setId(IDUtil.getID());
		user.setUsername(username);
		
		
		if (!password.equals(password2)) {
			return "两次输入密码不一致";
		} else {
			user.setPassword(MD5Util.encrypt(password));
			user.setSalt(FinalStrings.SALT.toString());
		}
		user.setCreateDate(new Date());
		user.setName(name);
		user.setEmail(email);
		user.setSex(sex);
		String ip = IPUtil.getIpAddr(request);
		user.setIp(ip);
		String result =  userService.resign(user);
		
		return result;
	}
	
	@RequestMapping(value = "/loginCheck", method = RequestMethod.GET)
	@ResponseBody
	public String loginCheck(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,HttpServletResponse response) throws IOException {
				
		
		System.out.println(username);
		String json = userService.login(username, password);
		System.out.println(json);
		//response.getWriter().write(json);
		return  json;
		
		
		
	}
	@RequestMapping(value = "/dologin", method = RequestMethod.GET)
	//@ResponseBody
	public String dologin(HttpServletRequest request) throws IOException {
		String userId = request.getParameter("userId");  
		System.out.println(userId+"**");
		J621User user = userService.getUserById(userId);
		request.getSession().setAttribute("webName",user.getName());
		request.getSession().setAttribute("userId",user.getId());
		request.getSession().setAttribute("isOver","");
		return "download";
		
	}
	
	
}
