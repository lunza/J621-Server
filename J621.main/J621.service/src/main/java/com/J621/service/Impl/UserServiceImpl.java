package com.J621.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.J621.dao.J621UserMapper;
import com.J621.service.UserService;
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
		J621UserExample example = new J621UserExample();
		Criteria c = example.createCriteria();
		c.andUsernameEqualTo(user.getUsername());
		
		List<J621User> li = userMapper.selectByExample(example);
		if(li!=null&&li.size()!=0) {
			return "用户已存在";
		}
		userMapper.insert(user);
		return "注册成功";
	}

	@Override
	public J621User login(String userName, String password) {
		// TODO Auto-generated method stub
		J621UserExample example = new J621UserExample();
		Criteria c = example.createCriteria();
		c.andUsernameEqualTo(userName);
		
		List<J621User> li = userMapper.selectByExample(example);
		if(li!=null&&li.size()!=0) {
			return li.get(0);
		}else {
			return null;
		}
		
	}

}
