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
		Map<String, Object> m = new HashMap<String, Object>();
		
		
		
		user.setId(IDUtil.getID());
		user.setUsername(username);
		
		if (username==null||username.equals("")) {
			m.put("result", "用户名不能为空");
			return JsonUtils.objectToJson(m);
		}
		if (!JsonUtils.validateNumAndLetter(username)) {
			m.put("result", "用户名只能输入数字或英文字母");
			return JsonUtils.objectToJson(m);
		}
		
		if (password==null||password.equals("")) {
			m.put("result", "密码不能为空");
			return JsonUtils.objectToJson(m);
		}
		
		if (!JsonUtils.validateNumAndLetter(password)) {
			m.put("result", "密码只能输入数字或英文字母");
			return JsonUtils.objectToJson(m);
		}
		
		if (!password.equals(password2)) {
			m.put("result", "两次输入密码不一致");
			return JsonUtils.objectToJson(m);
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
		System.out.println(result);
		return result;
	}
	
	@RequestMapping(value = "/loginCheck", method = RequestMethod.GET)
	@ResponseBody
	public String loginCheck(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,HttpServletResponse response) throws IOException {
		
		Map<String, Object> m = new HashMap<String, Object>();
		if (username==null||username.equals("")) {
			m.put("result", "用户名不能为空");
			return JsonUtils.objectToJson(m);
		}
		if (password==null||password.equals("")) {
			m.put("result", "密码不能为空");
			return JsonUtils.objectToJson(m);
		}
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
