package com.slcf.tool;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

public class MapAndBean {
	
	
	/**
	 * 
	 * @param location	properties文件路径
	 * @return	map
	 * @throws IOException 
	 */
	public Map<String,Object> getMapByProperties(String location) throws IOException{
		Properties prop=new Properties();
		 
		FileInputStream inputStream=new FileInputStream(location);
//		InputStream inputStream = ClassLoader.getResourceAsStream(location); 
		prop.load(new InputStreamReader(inputStream, "UTF-8") );
		  
		inputStream.close();
		Map<String,Object> map=(Map)prop;
		return map;
	}
	
	/**
	 * 
	 * @param cl	类名
	 * @param map	属性key-value map
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public <T>  Object fetchBeanByMap(Class cl,Map<String,Object> map) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Object obj=cl.newInstance();
		for (String key : map.keySet()) {
			setValue(obj, key, map.get(key));
		}
		
		return obj;
	}
	
	/**
	 * 
	 * @param obj	被赋值的对象
	 * @param attr	属性名
	 * @param value	属性值
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public void setValue(Object obj,String attr,Object value) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		Method[] methods=obj.getClass().getDeclaredMethods();
		attr=convertSetMethodName(attr);
		for (Method method : methods) {
			if(attr.equals(method.getName())){
				Class cl=method.getParameterTypes()[0];
				method.invoke(obj,cl.cast(value));
			}
		}
		
	}
	
	/**
	 * 
	 * @param attr	属性名称
	 * @return	该属性对应的set方法名
	 */
	public  String convertSetMethodName(String attr){
		return "set"+StringUtils.upperCase(attr.substring(0, 1))+attr.substring(1);
	}
	
	
	/**
	 * 
	 * @param cl	指定实体类
	 * @param locationName	properties文件路径
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	public <T> Object getFetchedObject(Class<T> cl,String locationName) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException{
		return fetchBeanByMap(cl, getMapByProperties(locationName));
		
	}
}
