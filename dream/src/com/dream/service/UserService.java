package com.dream.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dream.mapper.UserMapper;
import com.dream.model.User;
import com.dream.model.UserExample;
@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
	
	public List<User> findAll() {
		UserExample example=new UserExample();
		return userMapper.selectByExample(example);
	}
	public User getUser(String userName) {
		UserExample example=new UserExample();
		userMapper.selectByExample(example);
		return null;
	}
}
