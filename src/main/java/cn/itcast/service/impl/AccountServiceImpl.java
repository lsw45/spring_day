package cn.itcast.service.impl;

import cn.itcast.dao.AccountDao;
import cn.itcast.domain.Account;
import cn.itcast.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDao accountDao;

    public void transfer(Long fromId,Long toId,Double money){
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
        //更新转入账户
        accountDao.update(toAccount);
    }
}
