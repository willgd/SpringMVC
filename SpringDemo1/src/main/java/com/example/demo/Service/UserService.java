package com.example.demo.Service;

import com.example.demo.Dao.UserDao;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserDao userDao;
    public int updateTrans(int outID,int inID,double money){
        int code = 0;
        int outRow = userDao.out(outID, money);
        int i = 1/0;
        int inRow = userDao.inAccount(inID, money);
        if (outRow == inRow){
            code = 1;
        }
        return code;
    }
}
