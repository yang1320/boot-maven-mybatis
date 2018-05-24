package com.slcf;

import net.sf.json.JSONObject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.Ordered;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@SpringBootApplication
@EnableTransactionManagement 
@MapperScan("com.slcf.mapper")
@ServletComponentScan //扫描Servlet
@ImportResource("classpath:/applicationContext*.xml")
@ConfigurationProperties("application.properties")
@EnableCaching
public class ServletInitializer extends SpringBootServletInitializer implements
		EmbeddedServletContainerCustomizer {

	public static void main(String[] args) {
		System.out.println("jar main方式启动！！！！！");
		 new SpringApplicationBuilder(ServletInitializer.class).profiles("server")
         .properties("transport=local").run(args);

//		new SpringApplication().setWebEnvironment(true);
//		SpringApplication.run(ServletInitializer.class, args);

	}

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		System.out.println("进入加载方法configure;war web容器下启动！！！！！");
		application.profiles("classpath:application.properties");
//		application.profiles("classpath:applicationContext.xml");
		System.out.println(JSONObject.fromObject(application));
		return application.sources(ServletInitializer.class);
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		// TODO Auto-generated method stub
		System.out.println("加载用户自定义上下文配置！");
		container.setPort(8083);
		container.setDisplayName("bbm");
		container.setContextPath("/123");
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
