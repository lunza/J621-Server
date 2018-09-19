package com.J621.service;

import com.J621.vo.J621User;

public interface UserService {
	
	public String resign(J621User user);
	
	public J621User login(String userName,String password);

}
