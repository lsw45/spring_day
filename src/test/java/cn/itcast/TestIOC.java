package cn.itcast;

import cn.itcast.dao.UserDao;
import cn.itcast.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestIOC {
    @Test
    public void test1(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) ac.getBean("userService");
        userService.saveUser();
    }

    @Test
    public void test2(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = (UserDao) ac.getBean("userDao");
        // UserDao userDao = (UserDao) ac.getBean("userDao2");
        userDao.save();
    }

    @Test
    public void test3(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) ac.getBean("userService");
        UserService userService1 = (UserService) ac.getBean("userService");
        System.out.println(userService == userService1);
        ((ClassPathXmlApplicationContext)ac).close();
    }
}
