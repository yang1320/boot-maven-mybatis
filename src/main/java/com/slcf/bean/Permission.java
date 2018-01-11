package com.slcf.bean;

public class Permission {
	private String  id;
	private String permissionName;
	private String permissionPath;
	private String roleId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	public String getPermissionPath() {
		return permissionPath;
	}
	public void setPermissionPath(String permissionPath) {
		this.permissionPath = permissionPath;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	@Override
	public String toString() {
		return "Permission [id=" + id + ", permissionName=" + permissionName
				+ ", permissionPath=" + permissionPath + ", roleId=" + roleId
				+ "]";
	}
	
}
