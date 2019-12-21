package cn.itcast.service.impl;

import cn.itcast.dao.CustomerDao;
import cn.itcast.model.Customer;
import cn.itcast.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
    @Autowired(required = false)
    private CustomerDao customerDao;

//    public void setCustomerDao(CustomerDao customerDao) {
//        this.customerDao = customerDao;
//    }

    @Override
    public List<Customer> findAllCustomer(){
        List<Customer> list = customerDao.findAll();
        return list;
    }
}
