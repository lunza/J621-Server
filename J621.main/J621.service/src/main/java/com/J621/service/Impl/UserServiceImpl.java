package com.J621.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.J621.dao.J621UserMapper;
import com.J621.service.UserService;
import com.J621.vo.J621User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private J621UserMapper userMapper;

	@Override
	public void resign(J621User user) {
		// TODO Auto-generated method stub
		userMapper.insert(user);
	}

}
