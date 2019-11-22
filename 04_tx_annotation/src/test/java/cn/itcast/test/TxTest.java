package cn.itcast.test;

import cn.itcast.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TxTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void test1(){
        accountService.transfer(1l,2l,100.0);
    }

    @Test
    public void test2() {
//        accountService.transfer(1L,2L,100.0);
        accountService.findAccountById(1L);
    }
}
