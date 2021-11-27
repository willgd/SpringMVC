package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/test")
    public String toTest(){
        return "success";
    }
    @RequestMapping("/testView")
    public String toTestView(){
        return "testView";
    }
}
