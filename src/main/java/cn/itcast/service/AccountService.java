package cn.itcast.service;

import cn.itcast.model.Account;

public interface AccountService {
    /**
     * 业务层：转账方法
     * @param fromId 转出账户id
     * @param toId 转入账户id
     * @param money 转账金额
     */
    void transfer(Long fromId,Long toId,Double money);
    Account findById(Long id);
}
