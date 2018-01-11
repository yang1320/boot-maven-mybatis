package com.slcf.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ConfigFilter extends WebMvcConfigurerAdapter{
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		
		//对访问路径带有per关键字进行权限校验
		registry.addInterceptor(new PermissionFilter()).addPathPatterns("/per/*");
		super.addInterceptors(registry);
	}
	
}
