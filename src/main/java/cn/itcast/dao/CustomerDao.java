package cn.itcast.dao;

import cn.itcast.domain.Customer;
import java.util.List;

public interface CustomerDao {
    List<Customer> findAll();
    int save();
}
