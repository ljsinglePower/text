package controller;
import bean.Employee;
import bean.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.EmployeeService;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @RequestMapping("/empS")
    @ResponseBody
    public Msg getEmpsWithJson(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model){
        //引入mybatis分页查询
        PageHelper.startPage(pn,5);
        //startPage紧跟的查询是分页查询
        List<Employee> emp= employeeService.getAll();
        //使用PageInfo包装结果 连续显示5页
        PageInfo page=new PageInfo(emp,5);
        return Msg.success().add("pageInfo",page);
    }
     //查询员工数据（分页）

    /*@RequestMapping("/empS")
    public String getEmpS(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model){
       //引入mybatis分页查询
        PageHelper.startPage(pn,5);
        //startPage紧跟的查询是分页查询
        List<Employee> emp= employeeService.getAll();
        //使用PageInfo包装结果 连续显示5页
        PageInfo page=new PageInfo(emp,5);
        model.addAttribute("pageInfo",page);
        System.out.println(page);
        return "list";
    }*/

}
