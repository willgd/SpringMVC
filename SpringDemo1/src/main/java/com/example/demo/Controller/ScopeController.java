package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ScopeController {
    @RequestMapping("/testServletAPI")
    public String testReq(HttpServletRequest request){
        request.setAttribute("testRequestScope","ServletAPI");
        return "success";
    }
    @RequestMapping("/testModelAndView")
    public ModelAndView testMAV(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("testRequestScope","model and view");
        mav.setViewName("success");
        return mav;
    }
    @RequestMapping("/testMap")
    public String testMap(Map<String,Object>map){
        map.put("testRequestScope","hello map");
        return "success";
    }
    @RequestMapping("/testModel")
    public String testModel(Model model){
        model.addAttribute("testRequestScope","hello model");
        return "success";
    }
    @RequestMapping("/testModelMap")
    public String testModelMap(ModelMap modelMap){
        modelMap.addAttribute("testRequestScope","hello model map");
        return "success";
    }
}
