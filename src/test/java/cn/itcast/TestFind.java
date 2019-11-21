package cn.itcast;

import cn.itcast.config.SpringConfig;
import cn.itcast.domain.Customer;
import cn.itcast.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:applicationContext.xml")
@ContextConfiguration(classes = SpringConfig.class)
public class TestFind {
    @Autowired
    private  CustomerService customerService;

    @Test
    public void test1() throws Exception{
//        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
//        CustomerService customerService = (CustomerService) ac.getBean("customerService");
        List<Customer> list = customerService.findAllCustomer();
        for (Customer customer:list){
            System.out.println(customer);
        }
    }
}
