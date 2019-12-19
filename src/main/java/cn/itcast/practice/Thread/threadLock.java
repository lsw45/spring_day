package cn.itcast.practice.Thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.IOException;

public class threadLock {
    static class Account implements Runnable {
        // 创建一个 final 对象 balLock 作为锁（对象锁）：保护账户余额
        private final Object balLock = new Object();
        // 账户余额
        private Integer balance;
        // 创建一个 final 对象 pwLock 作为锁：保护账户密码
        private final Object pwLock = new Object();
        // 账户密码
        private String password;

        // 取款
        void withdraw(Integer amt) throws InterruptedException {
            synchronized (balLock) {
                if (this.balance > amt) {
                    System.out.println(this.balance + " " +Thread.currentThread().getName()+" withdraw begin");
                    this.balance -= amt;
                    Thread.sleep(3000); //延长时间，让串行看地更明显一点
                }
            }
        }

        // 无锁方式取款
        void push(Integer amt) {
            System.out.println(this.balance + " " +Thread.currentThread().getName()+" push begin");
            this.balance -= amt;
        }
        // 无锁方式查看余额
        Integer check()  {
            return balance;
        }

        // 查看余额
        Integer getBalance() throws InterruptedException {
            synchronized (balLock) {
                Thread.sleep(5000); //让任务执行慢点
                return balance;
            }
        }

        // 更改密码
        void updatePassword(String pw) {
            synchronized (pwLock) {
                this.password = pw;
            }
        }

        // 查看密码
        String getPassword() {
            synchronized (pwLock) {
                return password;
            }
        }

        public Account(Integer balance) {
            this.balance = balance;
        }

        @Override
        public void run() {
            try {
                push(10);
//                withdraw(10);
                System.out.println(this.balance + " "+Thread.currentThread().getName()+" end");
                Thread.sleep(1); //让任务执行慢点
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        int corePoolSize = 4;
        int maximumPoolSize = 8;
        long keepAliveTime = 20; //如果线程池当前拥有超过corePoolSize的线程，那么多余的线程在空闲时间超过keepAliveTime时会被终止
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        ThreadFactory threadFactory = new NameTreadFactory(); //新线程使用ThreadFactory创建
        RejectedExecutionHandler handler = new MyIgnorePolicy();//拒绝策略
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        executor.prestartAllCoreThreads(); // 预启动所有核心线程
        Account account = new Account(new Integer(1000));

        for (int i = 1; i <= 15; i++) {
            executor.execute(account);//execute(Runnable)方法中提交新任务
            System.out.println(account.check()+" done"); //加锁任务，同步输出；无锁任务，异步输出。因为锁定的是当前实例对象 this
        }

        System.in.read(); //阻塞主线程
    }

    static class NameTreadFactory implements ThreadFactory {

        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
            System.out.println(t.getName() + " has been created");
            return t;
        }
    }

    public static class MyIgnorePolicy implements RejectedExecutionHandler {

        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            doLog(r, e);
        }

        private void doLog(Runnable r, ThreadPoolExecutor e) {
            // 可做日志记录等
            System.err.println( r.toString() + " rejected");
        }
    }
}