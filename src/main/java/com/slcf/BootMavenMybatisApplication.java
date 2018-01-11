package com.slcf;

import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.web.servlet.ServletComponentScan;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;




@SpringBootApplication
@EnableTransactionManagement 
@MapperScan("com.slcf.mapper")
@ServletComponentScan //扫描Servlet
@ImportResource("classpath:/applicationContext*.xml")
@ConfigurationProperties("classpath:/application.properties")
//@EnableScheduling		//定时器总开关
public class BootMavenMybatisApplication{
//	public static final Logger log = Logger.getLogger(BootMavenMybatisApplication.class);
	
	public static void main(String[] args) {
    	System.out.println("----------------jar启动！！！！！");
        new SpringApplication().setWebEnvironment(true);
        SpringApplication.run(BootMavenMybatisApplication.class, args);

	}
	

	
	@Bean
	public  ServletRegistrationBean configServlet(){
		StatViewServlet svs=new StatViewServlet();
		ServletRegistrationBean srb=new ServletRegistrationBean(svs);
		srb.addUrlMappings("/druid/*");  
        return srb;  
	}
	
	  @Bean  
	    public FilterRegistrationBean testFilterRegistration() {  
	    	WebStatFilter wsf=new WebStatFilter();
	    	FilterRegistrationBean frb=new FilterRegistrationBean(wsf);
	    	frb.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
	    	frb.addUrlPatterns("/*");
	        return frb;  
	    }  


}
