package cn.itcast.dao.impl;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("customerDao")
public class CustomerDaoImpl implements CustomerDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
//        this.jdbcTemplate = jdbcTemplate;
//    }

    @Override
    public List<Customer> findAll(){
        List<Customer> list = jdbcTemplate.query("select * from cst_customer",new BeanPropertyRowMapper<Customer>(Customer.class));
        return list;
    }
}
