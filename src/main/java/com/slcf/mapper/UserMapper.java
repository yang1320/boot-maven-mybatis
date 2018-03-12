package com.slcf.mapper;

import java.util.List;
import java.util.Map;

import com.slcf.bean.User;

public interface UserMapper {
		
	public String selectRoleIdById(Map map);
	
	public User selectOne(int id);
	
	public List selectAll();
	
}
