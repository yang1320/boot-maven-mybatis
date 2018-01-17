package com.slcf.tool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonConvert {
	public static String toJson(Object object) throws Exception{
		String jsonString;
		if (object==null)
			throw new Exception("the target can not be null!");
				
		try {
			if(object instanceof Iterable || object.getClass().isArray() ){ 
				jsonString=JSONArray.fromObject(object).toString();
			}else{
				jsonString=JSONObject.fromObject(object).toString();
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("json convert error!");
		}

		return jsonString;
	}
}
