package cn.itcast.dao.impl;

import cn.itcast.dao.UserDao;
import org.springframework.stereotype.Repository;

@Repository("userDao2")
public class UserDaoImpl2 implements UserDao {
    @Override
    public void save(){
        System.out.println("持久层：用户保存2222...");
    }
}
