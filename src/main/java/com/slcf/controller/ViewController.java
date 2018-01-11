package com.slcf.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {
	
	@RequestMapping("/toView")
	public String toView(@RequestParam(required=false) String vname,@RequestParam(required=false,defaultValue="12") int a){

		if(""==vname || null==vname){
			return "error1";
		}
		return vname;
	}
}
