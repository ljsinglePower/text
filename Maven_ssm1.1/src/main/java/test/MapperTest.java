package test;

import bean.Department;
import bean.DepartmentExample;
import bean.Employee;
import dao.DepartmentMapper;
import dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.UUID;


/*
测试department
* 测试dao层的工作
* 或者使用spring test
* */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationConfig.xml"})
public class MapperTest {
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    SqlSession sqlsession;
@Test
    public void testCRUD(){

    /*//1创建SpringIOC容器
     ApplicationContext ioc=new ClassPathXmlApplicationContext("applicationConfig.xml");
    //从容器中获取mapper
     DepartmentMapper de=ioc.getBean(DepartmentMapper.class);
     EmployeeMapper employeeMapper=ioc.getBean(EmployeeMapper.class);
     System.out.println(employeeMapper);
     //插入几个部门...
    //插入员工
    de.insertSelective(null, "hhh");
    employeeMapper.insertSelective(new Employee(null,"lj","M","ljjjj@qq.com",1));*/
    System.out.println(employeeMapper);
   // departmentMapper.insertSelective(new Department(null, "hhh"));
  //employeeMapper.insertSelective(new Employee(null,"lj","M","1195517897@qq.com",1));
  //employeeMapper.insertSelective(new Employee(null,"gcy","M","17671179649@qq.com",2));
  //  List<Employee> list= employeeMapper.selectByExampleWithDept(null);
    //System.out.println(list.size());
  //批量插入
    sqlsession.getMapper(EmployeeMapper.class);
    for (int i=0;i<100;i++){
       String uid= UUID.randomUUID().toString().substring(0,5)+i;
        employeeMapper.insertSelective(new Employee(null,uid,"W",uid+"@QQ.com",2));
    }
    System.out.println("批量完成");
}
}
