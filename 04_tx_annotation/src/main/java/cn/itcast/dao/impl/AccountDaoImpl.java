package cn.itcast.dao.impl;

import cn.itcast.dao.AccountDao;
import cn.itcast.domain.Account;
import cn.itcast.rowmapper.AccountRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void update(Account account) {
        jdbcTemplate.update("update account set name = ?,money = ? where id = ?",account.getName(),account.getMoney(),account.getId());
    }


    @Override
    public Account findById(Long id) {
        Account account = jdbcTemplate.queryForObject("select * from account where id = ?", new AccountRowMapper(), id);
        return account;
    }

}
