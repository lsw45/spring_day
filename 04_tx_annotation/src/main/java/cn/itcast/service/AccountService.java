package cn.itcast.service;

import cn.itcast.model.Account;

import java.util.List;

public interface AccountService {
    /**
     * 转账方法
     * @param fromId 转出账户id
     * @param toId 转入账户id
     * @param money 转账金额
     */
    void transfer(Long fromId, Long toId, Double money);

    Account findAccountById(Long id);

    List<Account> findAllAccount();
}
