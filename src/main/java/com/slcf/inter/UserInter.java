package com.slcf.inter;

import java.util.List;
import java.util.Map;

import com.slcf.bean.User;

public interface UserInter {

	
public String selectRoleIdById(Map map);

public User selectOne(int id);

public List selectAll();
	
}
