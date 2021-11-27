package com.example.demo.Controller;

import com.example.demo.Dao.UserDao;
import com.example.demo.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserDao userDao;
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String getAllUser(Model model){
        System.out.println("查询所有用户信息");
        List<User> users = userDao.queryAllUser();
        model.addAttribute("users",users);
        return "UserList";
    }
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public String getUserByID(@PathVariable("id")int id,Model model){
        System.out.println("根据id查用户:id="+id);
        User user = userDao.queryByID(id);
        model.addAttribute("user",user);
        return "UserUpdate";
    }
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public String insertUser(String name,Double money){
        System.out.println("添加用户"+name+"，"+money);
        int rows = userDao.insertUser(name, money);
        return "redirect:/user";
    }
    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public String updateUser(User user){
        int rows = userDao.updateUser(user.getName(), user.getMoney(), user.getId());
        System.out.println(rows);
        System.out.println("修改用户："+user);
        return "redirect:/user";
    }
    @RequestMapping(value = "/user/{id}",method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable("id") int id){
        int rows = userDao.deleteUserByID(id);
        System.out.println("删除用户："+id);
        return "redirect:/user";
    }

}
