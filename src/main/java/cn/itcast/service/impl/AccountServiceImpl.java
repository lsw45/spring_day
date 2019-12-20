package cn.itcast.service.impl;

import cn.itcast.dao.AccountDao;
import cn.itcast.model.Account;
import cn.itcast.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("accountService")
//@Transactional//该类中所有的方法都加可读写的事务
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

    @Override
//    @Transactional(readOnly=true)//只读事务
    public Account findById(Long id){
        Account account = accountDao.findById(id);
        /*
         * 应该是只能做查询操作，但现在做了修改操作，是不被允许的.
         * 怎么才能不允许在查询方法中，做增删改操作呢？给该方法加只读事务
         */
        account.setMoney(100000.0);
        accountDao.update(account);
        return account;
    }
}
