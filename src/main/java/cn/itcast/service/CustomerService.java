package cn.itcast.service;

import cn.itcast.model.Customer;
import java.util.List;

public interface CustomerService {
    List<Customer> findAllCustomer();
}
