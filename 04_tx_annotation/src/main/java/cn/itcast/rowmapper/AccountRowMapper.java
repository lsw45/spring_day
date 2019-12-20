package cn.itcast.rowmapper;

import cn.itcast.model.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account>{

    /**
     * 指定如何映射一行
     * @param rs 查询的结果集
     * @param row 行号
     * @return
     * @throws SQLException
     */
    @Override
    public Account mapRow(ResultSet rs, int row) throws SQLException {
        Account account = new Account();
        account.setId(rs.getLong("id"));
        account.setName(rs.getString("name"));
        account.setMoney(rs.getDouble("money"));
        return account;
    }
}
