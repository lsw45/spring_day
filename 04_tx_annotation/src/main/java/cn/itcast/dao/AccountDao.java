package cn.itcast.dao;

import cn.itcast.domain.Account;

import java.util.List;

public interface AccountDao {


    /**
     * 修改账户
     * @param account
     */
    void update(Account account);


    /**
     * 根据id查询账户对象
     * @param id
     * @return
     */
    Account findById(Long id);

    List<Account> findAll();

}
