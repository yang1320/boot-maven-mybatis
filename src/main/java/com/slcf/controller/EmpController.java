package com.slcf.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.slcf.inter.DeptInter;


@Controller
public class EmpController {
	@Autowired
	private DeptInter dii;
	
	@RequestMapping("/showEmpList")
	public String showList(Map<String,Object> map){
		map.put("list", dii.selectAll());
		return "showList";
	}
	
	
	@RequestMapping("/showListJsp")
	public String showListJsp(Map<String,Object> map){
		System.out.println("jsp");
		map.put("list", dii.selectAll());
		return "NewFile";
	}

}
