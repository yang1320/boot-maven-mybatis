package com.slcf.mapper;


import java.util.List;


public interface PermissionMapper {

	public List<String> selectByRoleId(String roleId);
}
