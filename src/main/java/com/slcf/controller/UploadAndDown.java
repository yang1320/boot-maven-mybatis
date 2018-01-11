package com.slcf.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;









import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;













import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.slcf.tool.ServletUtils;



@Controller
public class UploadAndDown {
	@RequestMapping("/uploadFiles")
	public String uploadFiles(HttpServletRequest request,HttpServletResponse response,MultipartFile [] files) throws IOException{
		
//		System.out.println(files.length);
//		System.out.println(System.getProperty("user.dir"));
		String uploadAndDown=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\uploadAndDown";
		
		
		String uploadAndDown1=request.getSession().getServletContext().getRealPath("");
		System.out.println(uploadAndDown);
		System.out.println("路径1："+uploadAndDown1);
		
		//response.getWriter().write("<script>alert('ddddddddddd');history.go(-1);</script>");
		
		for (MultipartFile multipartFile : files) {
			File saveFile=new File(uploadAndDown+"\\"+multipartFile.getOriginalFilename());
			if(!saveFile.exists()){
				saveFile.createNewFile();
			}
			
			InputStream input=multipartFile.getInputStream();
			
			OutputStream out=new FileOutputStream(saveFile,true);
			
			byte[] bz=new byte[2048];
			int readSize=0;
			while((readSize=input.read(bz))!=-1){
				out.write(bz, 0, readSize);
				
			}
			
			out.close();
			input.close();
		}
		
//		try {
//			//System.out.println(request.getInputStream());    
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println("上传成功！");
		System.out.println(JSONArray.fromObject(files));
		return "hello33";
	}
	
	@RequestMapping("/down")
	@ResponseBody
	public String downFile(HttpServletResponse response,HttpServletRequest request,String name) throws IOException{
		   System.out.println("1:"+this.getClass().getResource(""));
		   System.out.println("2:"+this.getClass().getResource("/static/uploadAndDown"));
		   String path3=this.getClass().getResource("/")+"\\static\\uploadAndDown";
		   System.out.println("3:"+this.getClass().getClassLoader().getResource("") ); //返回的是classpath的位置
		   System.out.println("4:"+this.getClass().getClassLoader().getResource("/")); // 错误的!!
		String uploadAndDown=System.getProperty("user.dir");
		System.out.println("user.dir："+uploadAndDown);
		uploadAndDown=uploadAndDown.substring(0,uploadAndDown.indexOf("boot-maven-mybatis"))+"boot-maven-mybatis\\src\\main\\resources\\static\\uploadAndDown";
		System.out.println("uploadAndDown目录："+uploadAndDown);

		String uploadAndDown1=request.getSession().getServletContext().getRealPath("");
		System.out.println(uploadAndDown);
		System.out.println("路径1："+uploadAndDown1);
		
		String uploadAndDown2=this.getClass().getResource("/").getPath()+"\\static\\uploadAndDown";
//		相对路径：/E:/Users/yang/workspace/boot-maven-mybatis/target/classes/

		System.out.println("相对路径："+uploadAndDown2);
		
		System.out.println("项目名称："+request.getSession().getServletContext().getRealPath(request.getRequestURI()));
		File downfile=new File(path3+"\\"+name);
		InputStream in=new FileInputStream(downfile);
	    response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName="+name);  
		ServletOutputStream out=response.getOutputStream();
		byte[] bz=new byte[2048];
		int readSize=0;
		while((readSize=in.read(bz))!=-1){
			out.write(bz, 0, readSize);
			
		}
		out.close();
		in.close();
		
		return "下载成功！";
	}
	
	@RequestMapping("/testReponseStream")
	@ResponseBody
	public void testStream(HttpServletResponse response){
		//response默认编码与web容器设置有关，默认ISO-8859-1.
		//当向页面输出的是字节流时不用设置编码格式，否则需要设置编码（针对中文时）
		
//		 response.setContentType("multipart/form-data");
//	        response.setHeader("Content-Disposition", "attachment;");  
	  ServletOutputStream out=null;
	try {
		out = response.getOutputStream();
		
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		
		//response.setCharacterEncoding("utf-8");
		//response.setContentType("text/html;charset=utf-8");
	  Random rd=new Random();
	  try {
		  for(int i=0;i<10;i++){
			 // out.write((rd.nextInt(Byte.MAX_VALUE-Byte.MIN_VALUE+1)+Byte.MIN_VALUE));
			  //out.write("hello world!".getBytes());
//			  out.write("你好 世界！".getBytes());
		  out.write("<h1>sfs或者能够</h1><hr/>".getBytes());
		//			  response.getWriter().write("<h1>123ss</h1>");
		  }
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	@RequestMapping("/down1")
	@ResponseBody
	public String downFile1(HttpServletResponse response,HttpServletRequest request,String name) throws IOException, URISyntaxException{
	    response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName="+name); 
        
//        war------------------------------------------------------------
//		URL url=this.getClass().getResource("/static/uploadAndDown/");
//		System.out.println(url);
//		String path=this.getClass().getResource("/static/uploadAndDown/").toURI().getPath().toString();
//		path=path+name;
//		System.out.println("path:"+path);
////		file:/E:/Users/yang/workspace/boot-maven-mybatis/target/classes/static/uploadAndDown/
////		path:/E:/Users/yang/workspace/boot-maven-mybatis/target/classes/static/uploadAndDown/green.jpg
//		ServletUtils.returnStream(response, new FileInputStream(new File(path)));
      
//      jar------------------------------------------------------------
        InputStream in=this.getClass().getResourceAsStream("/static/uploadAndDown/"+name);
        ServletUtils.returnStream(response,in);
		return "下载成功！";
	}
}
