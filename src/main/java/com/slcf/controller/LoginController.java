package com.slcf.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;





import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.slcf.bean.Permission;
import com.slcf.mapper.PermissionMapper;
import com.slcf.mapper.UserMapper;
import com.slcf.tool.Encryption;

import java.lang.Exception;

@Controller
public class LoginController {
	@Autowired
	private UserMapper um;
	@Autowired
	private PermissionMapper pm;
	
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpServletResponse response,@Param("name") String name,@Param("pwd") String pwd) throws Exception {
		
		System.out.println(" into login method!");
		
		Map map=new HashMap();
		map.put("name", name);
		try {
			map.put("pwd", Encryption.encryption(pwd));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new NoSuchAlgorithmException("转换异常！");
		}
		String roleId=um.selectRoleIdById(map);
		System.out.println(pm.selectByRoleId(roleId));
		if("".equals(roleId) || null==roleId){
			//跳转到登录页面
			return "login";
		}else{
			List<String> perSet=pm.selectByRoleId(roleId);
			request.getSession().setAttribute("permissions", perSet);
			
		}
		
		return "hello1";
	}
	
	@RequestMapping("/exit")
	public String exit(HttpServletRequest request){
		request.getSession().invalidate();
		return "login";
	}
	
	@RequestMapping("/")
	public String welcome(HttpServletRequest request){
		System.out.println("进入 welcome！");
		request.getSession().invalidate();
		return "login";
	}
	
	@RequestMapping("/test")
	@ResponseBody
	public String test(HttpServletRequest request){
		System.out.println("into test!");
		return "test!";
	}
}
