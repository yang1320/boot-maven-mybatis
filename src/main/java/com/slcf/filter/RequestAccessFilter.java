/**  
* @Title: RequestAccessFilter.java  
* @Package com.slcf.filter  
* @Description: TODO(用一句话描述该文件做什么)  
* @author yangfei 
* @date 2018年3月29日  
* @version V1.0  
*/ 
package com.slcf.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**  
 * @ClassName: RequestAccessFilter  
 * @Description: TODO(请求接入校验)  
 * @author yangfei
 * @date 2018年3月29日  
 *    
 */
public class RequestAccessFilter implements HandlerInterceptor{

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-afterCompletionHandlerInterceptorgenerated method stub
		System.out.println("方法后调用！");
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-postHandleHandlerInterceptorgenerated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		// TODO Auto-preHandleHandlerInterceptorgenerated method stub
		System.out.println("方法前调用1111111111111！");
		return true;
	}

}
