package com.slcf.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import com.slcf.inter.DeptInter;


@Controller
public class DeptController {
	@Autowired
	private DeptInter dii;

	private int conns=0;
	
	@RequestMapping("/showList")
	public String showList(Map<String,Object> map,HttpServletRequest request){//每一个request对象都独立没有共用，加同步锁无效
//		synchronized (this) {
			if(null==request.getServletContext().getAttribute("conns") || !"0".equals(request.getServletContext().getAttribute("conns").toString())){
				request.getServletContext().setAttribute("conns", conns++);
			}else{
				request.getServletContext().setAttribute("conns", conns);
			}
			
			System.out.println("sssssss");
			System.out.println(request.getServletContext().getAttribute("conns"));
			Object obj=dii.selectAll();
			System.out.println(obj);
			map.put("list",obj);
			map.put("single", "single");
			
//		}
		
		return "showList";
	}
	
	@RequestMapping("/per/showByPer")
	public String showByPermission(Map<String,Object> map){
		System.out.println("业务方法！");
		map.put("list", dii.selectAll());
		return "showList";
	}
	
	
	@RequestMapping("/deleteOne")
	@ResponseBody
	public String showList(Integer id) throws Exception{
		
	
			dii.deleteOne(id);
	

		return "ssss";
	}
	
	
	@SuppressWarnings("all")
	@RequestMapping("/post")
	@ResponseBody
	public String post(HttpServletRequest request) throws InterruptedException{
		System.out.println("into post!");
		Map map=new HashMap();
		map.put("name", "张三");
		map.put("age", "21");
		
		request.setAttribute("value", "value1111");
		
		//Object obj=new AsyncRestTemplate().postForEntity("http://localhost:8085/dw/show",new HttpEntity<Map>(map),String.class);
//		System.out.println(obj);
		Thread.sleep(2*1000);
		Object obj=new RestTemplate().postForObject("http://localhost:8085/dw/show", map, Object.class);
		System.out.println(obj);
		JSONArray jobj=JSONArray.fromObject(obj);
		
		System.out.println(jobj);
		System.out.println("请求线程的hash值："+Thread.currentThread().hashCode()+";当前线程Id "+Thread.currentThread().getId());
		System.out.println("111同异步测试！"+new SimpleDateFormat("HH:mm:ss:SSS").format(new Date()));

		return "ssss";
	}
	
	@RequestMapping("/call")
	@ResponseBody
	public void testCallPhone() throws InterruptedException{
		System.out.println("into testCallPhone");
		Map map=new HashMap();
		map.put("name", "张三");
		map.put("age", "21");
		int i=0;
		while(true){

			Object obj=new AsyncRestTemplate().getForEntity(
					"http://lxbjs.baidu.com/cb/call?uid=6240816&g=90661&tk=2F916E821B7D2CC79A8B7F4A2DA564BCEBF8973E3F5FC342330738C3CC5D8CBA5014A427C0DF76C485276D97BF809A8708DB3DFBF3851025404DBA506C79DFEFC202F1D4FCB7060D14ACF1890F504486&vtel=15984892050&bdcbid=9be52cff-789a-4b1f-8c38-11a6c05c4e50&callback=_lxb_jsonp_1516069934302_",
					Object.class);
			
			System.out.println(++i+"次obj值为:"+JSONArray.fromObject(obj));
			Thread.sleep(1000*60*5);
		}
//		return "调用结束！";
	}
	
	
	@RequestMapping("/call")
	@ResponseBody
	public void changeIp() throws InterruptedException{
		int i=0;
		while(true){
			Thread.sleep(1000*60*5);
		}
	}
	
}
