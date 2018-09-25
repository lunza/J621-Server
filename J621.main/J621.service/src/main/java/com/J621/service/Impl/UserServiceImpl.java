package com.J621.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.J621.dao.J621UserMapper;
import com.J621.service.UserService;
import com.J621.utils.JsonUtils;
import com.J621.utils.MD5Util;
import com.J621.vo.J621User;
import com.J621.vo.J621UserExample;
import com.J621.vo.J621UserExample.Criteria;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private J621UserMapper userMapper;

	@Override
	public String resign(J621User user) {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>();
		String json = "";
		J621UserExample example = new J621UserExample();
		Criteria c1 = example.createCriteria();
		c1.andUsernameEqualTo(user.getUsername());
		
		List<J621User> li = userMapper.selectByExample(example);
		if(li!=null&&li.size()!=0) {
			result.put("result", "用户已存在");
			json = JsonUtils.objectToJson(result);
			return json;
		}
		
		if(!user.getIp().equals("本地")) {
			Criteria c2 = example.createCriteria();
			c2.andIpEqualTo(user.getIp());
			
			li = userMapper.selectByExample(example);
			if(li!=null&&li.size()!=0) {
				result.put("result", "你不能重复注册！");
				json = JsonUtils.objectToJson(result);
				return json;
			}
		}
		
		userMapper.insert(user);
		result.put("result", "注册成功");
		json = JsonUtils.objectToJson(result);
		return json;
	}

	@Override
	public String login(String userName, String password) {
		// TODO Auto-generated method stub
		J621User user = new J621User();
		Map<String, Object> result = new HashMap<String, Object>();
		String json = "";
		
		J621UserExample example = new J621UserExample();
		Criteria c = example.createCriteria();
		c.andUsernameEqualTo(userName);
		
		List<J621User> li = userMapper.selectByExample(example);
		if(li!=null&&li.size()!=0) {
			user= li.get(0);
		}else {
			user=null;
		}
		
		if(user==null) {
			result.put("result", "无此用户");
			json = JsonUtils.objectToJson(result);
			return json;
		}
		
		String pass = user.getPassword();
		
		if(!password.equals(MD5Util.decrypt(pass,user.getSalt()))) {
			result.put("result", "密码错误");
			json = JsonUtils.objectToJson(result);
			return json;
		}
		result.put("result", "登录成功");
		result.put("userId", user.getId());
		json = JsonUtils.objectToJson(result);
		return json;
		
	}

	@Override
	public J621User getUserById(String userId) {
		// TODO Auto-generated method stub
		J621User user = userMapper.selectByPrimaryKey(userId);
		return user;
	}
	

}
