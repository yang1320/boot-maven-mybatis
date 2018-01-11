package com.slcf.mapper;

import java.util.Map;

public interface UserMapper {
	
	public int selectOne(String id);
	
	public String selectRoleIdById(Map map);
	
}
