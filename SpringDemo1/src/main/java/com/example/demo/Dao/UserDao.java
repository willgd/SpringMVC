package com.example.demo.Dao;

import com.example.demo.bean.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class UserDao {
    @Resource
    private JdbcTemplate template;
    public int out(int id,double money){
        String sql = "update test.table_user set money = money - ? where id = ?";
        return template.update(sql,money,id);
    }
    public int inAccount(int id, double money) {
        String sql = "update test.table_user set money = money + ? where id = ?";
        return template.update(sql,money,id);
    }
    public List<User> queryAllUser(){
        String sql = "select * from test.table_user";
        return template.query(sql,new BeanPropertyRowMapper<User>(User.class));
    }

    /**
     * 根据id查用户
     * @param id
     * @return
     */
    public User queryByID(int id){
        String sql = "select * from test.table_user where id = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),id);
    }

    /**
     * 添加用户
     * @param name
     * @param money
     * @return
     */
    public int insertUser(String name,Double money){
        String sql = "insert into test.table_user(name, money) VALUES (?,?)";
        return template.update(sql,name,money);
    }

    /**
     * 修改用户
     * @param name
     * @param money
     * @return
     */
    public int updateUser(String name,Double money,int id){
        String sql = "update test.table_user set name = ? ,  money = ? where id = ?";
        return template.update(sql,name,money,id);
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    public int deleteUserByID(int id){
        String sql = "delete from test.table_user where id = ?";
        return template.update(sql,id);
    }
}
