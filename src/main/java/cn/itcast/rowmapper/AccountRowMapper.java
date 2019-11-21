package cn.itcast.rowmapper;

import cn.itcast.domain.Account;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {

    /**
     * 把账户表里的一行数据形成账户对象
     * 把通过jdbc查询出来的一行数据转变成Java对象
     */
    @Override
    public Account mapRow(ResultSet rs, int rownum) throws SQLException {
        Account account = new Account();
        account.setId(rs.getLong("id"));
        account.setName(rs.getString("name"));
        account.setMoney(rs.getDouble("money"));
        return account;
    }
}
