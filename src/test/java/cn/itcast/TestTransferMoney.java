package cn.itcast;

import cn.itcast.config.SpringConfig;
import cn.itcast.service.AccountService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class TestTransferMoney {
    @Autowired
    AccountService accountService;

    public void test1(){
        accountService.transfer(1L, 2L, 100.0);
    }
}
