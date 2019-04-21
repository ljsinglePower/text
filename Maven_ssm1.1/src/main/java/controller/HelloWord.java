package controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWord {
    @RequestMapping("/success")
    public String Hello() {
        System.out.println("helloWord");
        return  "success";
    }
}
