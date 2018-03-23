/**  
* @Title: CacheController.java  
* @Package com.slcf.controller  
* @Description: TODO(用一句话描述该文件做什么)  
* @author Administrator  
* @date 2018年3月21日  
* @version V1.0  
*/ 
package com.slcf.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.slcf.bean.User;
import com.slcf.inter.UserInter;

/**  
 * @ClassName: CacheController  
 * @Description: TODO(缓存测试类)  
 * @author yangfei  
 * @date 2018年3月21日  
 *    
 */
@Controller
public class CacheController {
	@Autowired
	private UserInter ui;
	
	private final String CACHE_USER="user";
	
	@GetMapping("/getOneUser")
	@ResponseBody
	@Cacheable(value=CACHE_USER,key="'user_'+#id")
	public User getOne(int id){
		System.out.println("不走缓存！！！");
		return ui.selectOne(id);
	}
	
	@GetMapping("/showOneMap")
	@ResponseBody
	public Map showUser(int id){
		System.out.println(ui);
		Map map=new HashMap();
		map.put("user",getOne(id));
		return map;
	}
	
	
	@Test
	public void showJunitBean(){
		System.out.println(ui);
	}
}
