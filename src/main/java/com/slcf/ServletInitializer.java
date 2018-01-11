package com.slcf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer implements
		EmbeddedServletContainerCustomizer {

	public static void main(String[] args) {
		SpringApplication.run(ServletInitializer.class, args);
		System.out.println("war启动！！！！！");
	}

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(BootMavenMybatisApplication.class);
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		// TODO Auto-generated method stub
		System.out.println("加载用户自定义上下文配置！");
		container.setPort(8083);
		container.setDisplayName("bbm");
		container.setContextPath("123");
	}

}
