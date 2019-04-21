package test;

import bean.Employee;
import com.github.pagehelper.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.List;

//使用spring测试模块
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:*.xml"})
public class mvcTest {
    //传入Springmvc的ioc
    @Autowired
    WebApplicationContext webApplicationContext;
    //虚拟mvc请求
    MockMvc mockMvc;
    @Before
    public void initMokMvc(){
        MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    public void testPage() throws Exception {
        //模拟请求拿到返回值
      MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.get("/empS").param("pn","1"))
                            .andReturn();
        //请求成功后，会有pageinfo
        MockHttpServletRequest mockServerRequest=mvcResult.getRequest();
       PageInfo<Employee> pageInfo=(PageInfo) mockServerRequest.getAttribute("pageInfo");
       System.out.println("当前页码"+pageInfo.getPageNum());
        System.out.println("总页码"+pageInfo.getPages());
        System.out.println("总记录数"+pageInfo.getTotal());
        System.out.println("在页面需要连续显示的页码");
        int []nums=pageInfo.getNavigatepageNums();
        for (int i:nums){
            System.out.println(" "+i);
        }
       //获取员工数据
        List<Employee> list=pageInfo.getList();
        for (Employee employee:list){
            System.out.println("ID"+employee.getEmpId()+"==>name:"+employee.getEmpName());
        }
    }
}
