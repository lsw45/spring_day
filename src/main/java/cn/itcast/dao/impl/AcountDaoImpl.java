package cn.itcast.dao.impl;

import cn.itcast.dao.AccountDao;
import cn.itcast.model.Account;
import cn.itcast.model.AccountRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("accountDao")
public class AcountDaoImpl implements AccountDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Account account){
        jdbcTemplate.update("insert into account(name,money) values(?,?)", account.getName(), account.getMoney());
    }

    @Override
    public void update(Account account) {
        jdbcTemplate.update("update account set name = ?,money = ? where id = ?",account.getName(),account.getMoney(),account.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("delete from account where id = ?",id);
    }

    @Override
    public Double findMoney(Long id) {
        Double money = jdbcTemplate.queryForObject("select money from account where id = ?", Double.class,id);
        return money;
    }

    @Override
    public Account findById(Long id) {
//        Account account = jdbcTemplate.queryForObject("select * from account where id = ?", new AccountRowMapper(), id);
        Account account = null;
        return account;
    }

    @Override
    public List<Account> findAll() {
//        List<Account> list = jdbcTemplate.query("select * from account", new AccountRowMapper());
        List<Account> list = null;
        return list;
    }

}
