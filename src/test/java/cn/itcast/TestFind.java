package cn.itcast;

import cn.itcast.domain.Customer;
import cn.itcast.service.CustomerService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestFind {
    @Test
    public void test1(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        CustomerService customerService = (CustomerService) ac.getBean("customerService");
        List<Customer> customers = customerService.findAllCustomer();
        for (Customer customer:customers){
            System.out.println(customer);
        }
    }
}
