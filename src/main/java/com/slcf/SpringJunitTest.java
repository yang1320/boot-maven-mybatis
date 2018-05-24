/**  
* @Title: SpringJunitTest.java  
* @Package com.slcf  
* @Description: TODO(用一句话描述该文件做什么)  
* @author yangfei 
* @date 2018年3月23日  
* @version V1.0  
*/ 
package com.slcf;

import java.net.URI;

import net.sf.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;

import com.slcf.tool.JsonConvert;

/**  
 * @ClassName: SpringJunitTest  
 * @Description: TODO(springboot单元测试类)  
 * @author yangfei
 * @date 2018年3月23日  
 *    
 */
@RunWith(SpringJUnit4ClassRunner.class)  
//这是Spring Boot注解，为了进行集成测试，需要通过这个注解加载和配置Spring应用上下  
@SpringBootTest(classes = SpringJunitTest.class)  
@WebAppConfiguration  
public class SpringJunitTest {  

  @Autowired  
  private WebApplicationContext context;  
    
  private MockMvc mockMvc;   

  @Before   
  public void setupMockMvc() throws Exception {   
      mockMvc = MockMvcBuilders.webAppContextSetup(context).build();   
  } 
  
  @Test
  public void testShowOneMap() throws Exception{
//	  HttpMethod.GET, new URI("/showOneMap")
	    MvcResult mvcResult = mockMvc
                .perform(// 1
                        MockMvcRequestBuilders.get("/123/showOneMap") // 2
                        .param("id","001") // 3
                )
                .andReturn();// 4
        HandlerInterceptor[] interceptors = mvcResult.getInterceptors();
        System.out.println(interceptors[0].getClass().getName());

        int status = mvcResult.getResponse().getStatus(); // 6
        String responseString = mvcResult.getResponse().getContentAsString(); // 7
        System.out.println("返回内容："+responseString);
        Assert.assertEquals("return status not equals 200", 200, status); 
  }
  
}
