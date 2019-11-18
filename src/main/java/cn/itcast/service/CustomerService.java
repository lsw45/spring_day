package cn.itcast.service;

import cn.itcast.domain.Customer;
import java.util.List;

public interface CustomerService {
    List<Customer> findAllCustomer();
}
