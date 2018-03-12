package com.slcf.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.slcf.bean.User;
import com.slcf.inter.UserInter;
import com.slcf.mapper.UserMapper;
import com.slcf.tool.Encryption;

import org.slf4j.Logger;
@Controller
public class LogController {
	
	@Autowired
	private UserInter ui;
	
	private static final Logger log= org.slf4j.LoggerFactory.getLogger(LogController.class);
	@RequestMapping({"user1","user2","user3"})
	public String getUsers(Map map){
		System.out.println("into getUser!");
//		System.out.println("map:"+JSONObject.fromObject(map));
		try {
			map.put("pwd", Encryption.encryption("123456"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("name", "admin");
		List list=ui.selectAll();
		System.out.println("用户列表查询:"+new ArrayList<User>(list));
		List<User> list1=new ArrayList<User>();
		list1.add(new User());
		list1.add(new User());
		list1.add(new User());
		System.out.println("用户列表查询:"+new ArrayList<User>(list));
		System.out.println("自定义列表:"+list1);
		System.out.println("日志对象:"+log);
		System.out.println("---------log begin---------");
		log.debug("log debug!!!!!!!!!!!!!!!!!!");
		log.info("log info!!!!!!!!!!!!!!!!!!");
		log.error("log error!!!!!!!!!!!!!!!!!!");
		
		System.out.println("系统上下文配置属性值：");
		System.out.println("---------log end---------");
		map.put("user",ui.selectOne(001));
		map.put("users",list);
		return "log";
	} 

}
