package cn.itcast.dao;

import cn.itcast.model.Customer;
import java.util.List;

public interface CustomerDao {
    List<Customer> findAll();
    int save();
}
