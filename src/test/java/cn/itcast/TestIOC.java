package cn.itcast;

import cn.itcast.config.SpringConfig;
import cn.itcast.dao.UserDao;
import cn.itcast.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class TestIOC {
    @Autowired
    UserService userService;

    @Test
    public void test1(){
        userService.saveUser();
    }

    @Test
    public void test2(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = (UserDao) ac.getBean("userDao");
        userDao.save();
    }

    @Test
    public void test3(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = (UserService) ac.getBean("userService");
        UserService userService1 = (UserService) ac.getBean("userService");

        System.out.println(userService == userService1);
        ((AnnotationConfigApplicationContext)ac).close();
    }
}
