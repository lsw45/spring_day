package cn.itcast;

import cn.itcast.config.SpringConfig;
import cn.itcast.dao.CustomerDao;
import cn.itcast.dao.impl.CustomerDaoImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class TestProxy {
    @Autowired
    CustomerDao customerDao;

    /**
     * jdk动态代理
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        final CustomerDao customerDao = new CustomerDaoImpl();

        /*
         * newProxyInstance的三个参数解释：
         * 参数1：代理类的类加载器，同目标类的类加载器
         * 参数2：代理类要实现的接口列表，同目标类实现的接口列表
         * 参数3：回调，是一个InvocationHandler接口的实现类对象；当调用代理对象的方法时，其实执行的是回调中的invoke方法
         */
        CustomerDao proxy = (CustomerDao) Proxy.newProxyInstance(customerDao.getClass().getClassLoader(), customerDao.getClass().getInterfaces(), new InvocationHandler() {
            /*
             * invoke方法的三个参数解释：
             * 参数1：代理对象
             * 参数2：目标方法，也就是被增强的方法，代理对象调用哪个方法，method就表示该方法
             * 参数3：目标方法的形参列表
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("记录日志……");
                //调用目标方法
                Object result = method.invoke(customerDao,args);
                System.out.println("记录日志后……");

                return result;
            }
        });

        proxy.save();
        //proxy.findAll();//需要使用类变量customerDao，不能使用内部本地变量
    }

    /**
     * cglib增强
     */
    @Test
    public void test2() {
        CustomerDao CustomerDao = new CustomerDaoImpl();
        // 创建cglib的核心对象
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(CustomerDao.getClass());
        // 设置回调
        enhancer.setCallback(new MethodInterceptor() {
            /*
             * 当你调用目标方法时，实质上是调用该方法
             * intercept四个参数：
             * proxy:代理对象
             * method:目标方法
             * args：目标方法的形参
             * methodProxy:代理方法
             */
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                //添加记录的功能
                System.out.println("记录日志...");
                //调用目标(父类)方法
//          Object result = method.invoke(CustomerDao, args);
                Object result = methodProxy.invokeSuper(proxy, args);
                return result ;
            }
        });
        // 创建代理对象
        CustomerDao proxy = (CustomerDao) enhancer.create();
        proxy.save();
    }
}
