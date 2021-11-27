package com.example.demo.Controller;

import com.example.demo.bean.User;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.UnsupportedEncodingException;


@Controller
public class RequestController {
    @RequestMapping("/testRequest")
    public String testReq(@RequestBody String req ){
        System.out.println(req);
        return "success";
    }
    @RequestMapping("/testRequestBody")
    public String testReqB(RequestEntity<String> requestEntity) throws UnsupportedEncodingException {
        System.out.println("header:"+requestEntity.getHeaders());
        System.out.println("body:"+requestEntity.getBody());
        return "success";
    }
    @RequestMapping("/testResponse")
    @ResponseBody
    public String testRes(){
        return "success @ResponseBody";
    }
    @RequestMapping("/testResponseJSON")
    @ResponseBody
    public User testResJSON(){
        return new User(1,"demo",10.0);
    }

    @RequestMapping("/testResponseAJAX")
    @ResponseBody
    public String testResAJAX(String username){
        System.out.println(username);
        return "123";
    }
}
