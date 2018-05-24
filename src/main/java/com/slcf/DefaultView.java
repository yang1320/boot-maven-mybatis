/**  
* @Title: DefaultView.java  
* @Package com.slcf  
* @Description: TODO(用一句话描述该文件做什么)  
* @author yangfei 
* @date 2018年3月27日  
* @version V1.0  
*/ 
package com.slcf;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**  
 * @ClassName: DefaultView  
 * @Description: TODO(默认视图配置)  
 * @author yangfei
 * @date 2018年3月27日  
 *    
 */
@Configuration
public class DefaultView extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    	//视图配置路径和@requestMapping路径相同时，优先使用视图配置
        registry.addViewController("/").setViewName("forward:/test");
        System.out.println("进入 addView！");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }
}
