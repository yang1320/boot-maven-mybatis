package com.slcf.bootmavenmybatis;  
  
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.ResponseBody;
 
  
@Controller  
public class RuKou {  
  
    @RequestMapping("/hello")  
    @ResponseBody
    public String hello() {  
        return "hello,this is a springboot demo";  
    }  
    
    @RequestMapping("/hello1")  
    public String hello1() {  
        return "hello1";  
    } 
	@RequestMapping("/show")
	@ResponseBody
	public String show(){
		return "TEST!";
	}

}