package com.slcf.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.ibatis.annotations.Param;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import com.slcf.inter.DeptInter;

@Controller
public class DeptController {
	@Autowired
	private DeptInter dii;

	private int conns = 0;

	@RequestMapping("/showList")
	public String showList(Map<String, Object> map, HttpServletRequest request) {// 每一个request对象都独立没有共用，加同步锁无效
	// synchronized (this) {
		if (null == request.getServletContext().getAttribute("conns")
				|| !"0".equals(request.getServletContext()
						.getAttribute("conns").toString())) {
			request.getServletContext().setAttribute("conns", conns++);
		} else {
			request.getServletContext().setAttribute("conns", conns);
		}

		System.out.println("sssssss");
		System.out.println(request.getServletContext().getAttribute("conns"));
		Object obj = dii.selectAll();
		System.out.println(obj);
		map.put("list", obj);
		map.put("single", "single");

		// }

		return "showList";
	}

	@RequestMapping("/per/showByPer")
	public String showByPermission(Map<String, Object> map) {
		System.out.println("业务方法！");
		map.put("list", dii.selectAll());
		return "showList";
	}

	@RequestMapping("/deleteOne")
	@ResponseBody
	public String showList(Integer id) throws Exception {

		dii.deleteOne(id);

		return "ssss";
	}

	@SuppressWarnings("all")
	@RequestMapping("/post")
	@ResponseBody
	public String post(HttpServletRequest request) throws InterruptedException {
		System.out.println("into post!");
		Map map = new HashMap();
		map.put("name", "张三");
		map.put("age", "21");

		request.setAttribute("value", "value1111");

		// Object obj=new
		// AsyncRestTemplate().postForEntity("http://localhost:8085/dw/show",new
		// HttpEntity<Map>(map),String.class);
		// System.out.println(obj);
		Thread.sleep(2 * 1000);
		Object obj = new RestTemplate().postForObject(
				"http://localhost:8085/dw/show", map, Object.class);
		System.out.println(obj);
		JSONArray jobj = JSONArray.fromObject(obj);

		System.out.println(jobj);
		System.out.println("请求线程的hash值：" + Thread.currentThread().hashCode()
				+ ";当前线程Id " + Thread.currentThread().getId());
		System.out.println("111同异步测试！"
				+ new SimpleDateFormat("HH:mm:ss:SSS").format(new Date()));

		return "ssss";
	}

	@RequestMapping("/call")
	@ResponseBody
	public void testCallPhone(HttpServletResponse response,@RequestParam(value = "phoneNum",required = false)String phoneNum) {
		System.out.println("into testCallPhone");
		String phoneRegex="^1[3|4|5|8][0-9]\\d{4,8}$";
		response.setContentType("application/json");
		Map map=new HashMap();
		if(null!=phoneNum ){
			if(!phoneNum.matches(phoneRegex)){
				map.put("msg", "please input the right phone");
				try {
					response.getWriter().print(JSONObject.fromObject(map));
					return;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}else{
			try {
				map.put("msg", "phone can not be null!");
				response.getWriter().print(JSONObject.fromObject(map));
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int i = 0;
		while (true) {

			Object obj = new AsyncRestTemplate()
					.getForEntity(
							"http://lxbjs.baidu.com/cb/call?uid=6240816&g=90661&tk=2F916E821B7D2CC79A8B7F4A2DA564BCEBF8973E3F5FC342330738C3CC5D8CBA5014A427C0DF76C485276D97BF809A8708DB3DFBF3851025404DBA506C79DFEFC202F1D4FCB7060D14ACF1890F504486&vtel="+phoneNum+"&bdcbid=9be52cff-789a-4b1f-8c38-11a6c05c4e50&callback=_lxb_jsonp_1516069934302_",
							Object.class);

			System.out.println(++i + "次异步调用返回结果为:" + JSONArray.fromObject(obj));
			try {
				Thread.sleep(1000 * 60 * 5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				try {
					map.put("msg", "Thread error");
					response.getWriter().print(JSONObject.fromObject(map));
					return;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
/*		
//		into testCallPhone
//		1次异步调用返回结果为:[{"cancelled":false,"done":false}]
*/	}

	@RequestMapping("/changeIp")
	@ResponseBody
	public void changeIp(HttpServletResponse response) {
		
	}

	@RequestMapping("/resResult")
	public void resResult(HttpServletResponse response) {

		response.setContentType("application/json; charset=utf-8");
//		response.setCharacterEncoding("utf-8");
		Map map = new HashMap();
		map.put("msg1", "中国1");
		try {
		PrintWriter out=response.getWriter();
//			out.print("");	// getWriter() has already been called for this response
			out.println(JSONObject.fromObject(map));
			Thread.sleep(1000*2);
			out.println(JSONObject.fromObject(map));
			
//			response.getOutputStream().print("中国");//Not an ISO 8859-1 character: [中]
//			response.getOutputStream().write("中国".getBytes());
			System.out.println("Writer!");
		
			response.setContentType("text/html; charset=UTF-8");
			out.println("<script> alert('123');</script>");
			System.out.println("print!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
/*
//		result: /boot-maven-mybatis/src/main/resources/static/uploadAndDown/result.jpg
		*/
	}

}
