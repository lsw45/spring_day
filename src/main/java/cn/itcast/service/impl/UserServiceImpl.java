package cn.itcast.service.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

@Service("userService")
@Scope("singleton") //单例模式
//@Scope("prototype") //返回新的类实例
public class UserServiceImpl implements UserService {
    @Value("zhangsan")
    private String name;

//    @Autowired
//    @Qualifier("userDao2")
    @Resource(name="userDao2")
    private UserDao userDao;

    public UserServiceImpl(){
        System.out.println("调用了无参构造方法...");
    }

    @PostConstruct //构造方法之后自动调用，可以用此方法初始化对象，类似于xml的init-method方法
    public void init(){
        System.out.println("调用了init方法...");
    }

    @Override
    public void saveUser(){
        System.out.println("业务层，用户保存……"+name);
        userDao.save();
    }

    @PreDestroy //要看到@PreDestory的效果，需要调用ClassPathXmlApplicationContext.close方法，同时scope的值要是singleton
    public void destroy(){
        System.out.println("调用了destroy方法...");
    }
}
