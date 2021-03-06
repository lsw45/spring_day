package cn.itcast;

import cn.itcast.config.SpringConfig;
import cn.itcast.service.AccountService;
import com.sun.org.glassfish.gmbal.ManagedData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class TestTransferMoney {
    @Autowired
    AccountService accountService;

    @Test
    public void test1(){
        accountService.transfer(1L, 2L, 100.0);
    }

    @Test
    public void testTransaction(){
        System.out.println(accountService.findById(1L));
    }
}
