package com.dream.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.dream.mapper.UserMapper;
import com.dream.model.MsgResult;
import com.dream.model.User;
import com.dream.model.UserExample;
import com.dream.service.util.MessageDigestUtil;
@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
	
	public List<User> findAll() {
		UserExample example=new UserExample();
		return userMapper.selectByExample(example);
	}
	
	public MsgResult getUser(User record) throws Exception{
		MsgResult msgResult=new MsgResult();
		if(StringUtils.isEmpty(record.getUserName())){
			msgResult.setErrorMsg("用户名不能为空");
			return msgResult;
		}
		if(StringUtils.isEmpty(record.getPassword())){
			msgResult.setErrorMsg("密码不能为空");
			return msgResult;
		}
		UserExample example=new UserExample();
		example.createCriteria().andUserNameEqualTo(record.getUserName());
		String pwdmd5 = MessageDigestUtil.getMD5(record.getPassword());
		List<User> list = userMapper.selectByExample(example);
		if(!CollectionUtils.isEmpty(list)){
			User user = list.get(0);
			if(user.getPassword().equalsIgnoreCase(pwdmd5)){
				msgResult.setSuccessMsg("验证通过", user);
			};
		}else{
			msgResult.setErrorMsg("错误的用户名和密码");
		}
		return msgResult;
	}
	
	public MsgResult add(User record) throws Exception{
		MsgResult msgResult=new MsgResult();
		if(StringUtils.isEmpty(record.getUserName())){
			msgResult.setErrorMsg("用户名不能为空");
			return msgResult;
		}
		if(StringUtils.isEmpty(record.getPassword())){
			msgResult.setErrorMsg("密码不能为空");
			return msgResult;
		}
		
		UserExample example=new UserExample();
		example.createCriteria().andUserNameEqualTo(record.getUserName());
		List<User> list = userMapper.selectByExample(example);
		if(CollectionUtils.isEmpty(list)){
			record.setPassword(MessageDigestUtil.getMD5(record.getPassword()));
			userMapper.insertSelective(record);
			msgResult.setSuccess(true);
		}else{
			msgResult.setErrorMsg("用户名存在，User exist!");
		}
		return msgResult;
	}
	
	public UserMapper getUserMapper() {
		return userMapper;
	}

	public MsgResult addOrder(){
		return null;
	}
}
