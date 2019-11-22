package cn.itcast.service.impl;

import cn.itcast.dao.AccountDao;
import cn.itcast.domain.Account;
import cn.itcast.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional//该类中所有的方法都会加事务
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;


    @Override
    public void transfer(Long fromId, Long toId, Double money) {
        //查询转出账户
        Account fromAccount = accountDao.findById(fromId);
        //查询转入账户
        Account toAccount = accountDao.findById(toId);
        //转出账户减钱
        fromAccount.setMoney(fromAccount.getMoney() - money);
        //转入账户加钱
        toAccount.setMoney(toAccount.getMoney() + money);
        //更新转出账户
        accountDao.update(fromAccount);
        int i = 1 / 0;
        //更新转入账户
        accountDao.update(toAccount);
    }

    //如果类上和方法上都有Transactional，以方法上的为准
    @Transactional(readOnly = true)
    @Override
    public Account findAccountById(Long id) {
        Account account = accountDao.findById(id);
        account.setMoney(account.getMoney() + 100);
        accountDao.update(account);
        return account;
    }
}
