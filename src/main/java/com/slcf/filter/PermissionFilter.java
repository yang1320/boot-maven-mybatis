package com.slcf.filter;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.slcf.controller.ViewController;

public class PermissionFilter implements HandlerInterceptor{

	
	/**
	 * 调用业务方法前进行权限验证
	 * 
	 * return	false（失败跳转等录页面）	success（执行业务方法）
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("进入验证！");
		System.out.println("请求路径2："+request.getServletPath());
		//System.out.println("请求路径2："+request);
		System.out.println("请求路径3："+request.getAttribute("requestDispatcherPath"));
		// TODO Auto-generated method stub
		List perSet=(List) request.getSession().getAttribute("permissions");
		
		if(null==perSet || 0==perSet.size()){
			request.getRequestDispatcher("/login").forward(request, response);
			return false;
		}
		if(perSet.contains(request.getServletPath())){
			
			return true;
		}else{
			request.getRequestDispatcher("/error.ftl").forward(request, response);
			return false;
		}

	}


	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	

}
