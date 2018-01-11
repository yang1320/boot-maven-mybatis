package com.slcf.tool;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ServletUtils {
	
	public static void returnStream(HttpServletResponse response,InputStream in) throws IOException{
		ServletOutputStream  out=response.getOutputStream();
//		out.write(in.toString().getBytes());
		
		byte[] bz=new byte[2048];
		
		while ((in.read(bz))!=-1) {
			out.write(bz);
			out.flush();
		}
		out.close();
		
	}
	
	public static void returnJson(HttpServletResponse response,Object obj) throws IOException{
		try {
			response.getOutputStream().print(JSONObject.fromObject(obj).toString());
		} catch (Exception e) {
			// TODO: handle exception
			response.getOutputStream().print(JSONArray.fromObject(obj).toString());
		}

	}

}
