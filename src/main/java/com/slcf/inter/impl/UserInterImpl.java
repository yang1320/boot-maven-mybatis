package com.slcf.inter.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slcf.bean.User;
import com.slcf.inter.UserInter;
import com.slcf.mapper.UserMapper;

@Transactional(rollbackFor=Exception.class)
@Service
public class UserInterImpl implements UserInter{
	@Autowired
	private UserMapper um;
	
	@Override
	public User selectOne(int id) {
		// TODO Auto-generated method stub
		
		return um.selectOne(id);
	}

	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		List userL=um.selectAll();
		return userL;
	}

	@Override
	public String selectRoleIdById(Map map) {
		// TODO Auto-generated method stub
		return um.selectRoleIdById(map);
	}



}
