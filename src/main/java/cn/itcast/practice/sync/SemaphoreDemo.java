package cn.itcast.practice.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args){
        SemaphoreDemo demo = new SemaphoreDemo();
        demo.executeTask();
    }

    //场景需求：200个人去2个窗口买票
    public void executeTask(){
        final Semaphore semaphore = new Semaphore(2);
        int user = 100;
        Thread t1 = new Thread(new MyRunnable(semaphore,user),"t1");
        Thread t2 = new Thread(new MyRunnable(semaphore,user),"t2");

        t1.start();
        t2.start();

        /*ExecutorService pool = Executors.newCachedThreadPool();
        for(int i=0;i<20;i++){
            pool.execute(new MyRunnable(semaphore,i));
        }
        pool.shutdown();*/
    }

    static class MyRunnable implements Runnable {
        private Semaphore semaphore;
        private int user;

        MyRunnable(Semaphore semaphore,int user){
            this.semaphore = semaphore;
            this.user = user;
        }

        public void run() {
            while(user>0) {
                try {
                    semaphore.acquire();
                    System.out.println("用户" + user + "进入窗口买票");
                    Thread.sleep((long) Math.random() * 10000);
                    System.out.println("用户" + user + "买完票，离开");
                    user--;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } finally {
                    semaphore.release();
                }
            }
        }
    }
}

