package cn.itcast.servlet;

import cn.itcast.model.Account;
import cn.itcast.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AccountServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从ServletContext中获取唯一的IOC容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println(ac);
        AccountService accountService = (AccountService) ac.getBean("accountService");
        List<Account> accounts = accountService.findAllAccount();
        request.setAttribute("accounts", accounts);
        request.getRequestDispatcher("/accounts.jsp").forward(request, response);
    }
}
