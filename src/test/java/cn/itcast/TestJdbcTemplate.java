package cn.itcast;

import cn.itcast.config.SpringConfig;
import cn.itcast.dao.AccountDao;
import cn.itcast.model.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class TestJdbcTemplate {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    AccountDao accountDao;

    @Test
    public void test1(){
        jdbcTemplate.update("insert into account(name,money) values(?,?)", "张三", 1000.0);
    }

    @Test
    public void test2(){
        Account account = new Account();
        account.setMoney(100.0);
        account.setName("lsw");
        accountDao.save(account);
    }
}
