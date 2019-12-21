package cn.itcast.practice.sync;

import java.util.concurrent.*;

public class ThreadExchanger {
    public static void main(String[] args){
        ExecutorService threadpool = Executors.newCachedThreadPool();
        Exchanger<String> exchanger = new Exchanger<>();

        // 绑匪
        threadpool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String content = exchanger.exchange("大桥");
                    System.out.println("绑匪用大桥换"+content);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 家属
        threadpool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String content = exchanger.exchange("100万");
                    System.out.println("家属用100万换回"+content);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadpool.shutdown();
    }
}
