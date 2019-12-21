package cn.itcast.practice.sync;

import java.util.concurrent.*;

// 场景：聚餐，所有人到齐了才开饭
public class CycliBarrierTest {
    public static void main(String[] args){
//        CyclicBarrier cb = new CyclicBarrier(3);
        CyclicBarrier cb = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("用户到齐（CyclicBarrier 计数到0 执行）");
            }
        });
        ExecutorService pool = Executors.newCachedThreadPool();

        for(int i=0;i<3;i++) {
            final int user = i + 1;
            Runnable runable = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("用户" + user + "到场"+ (cb.getNumberWaiting() + 1) );
                        cb.await();
                        System.out.println("聚餐结束，"+ user +"回家");
                    } catch (Exception e) {
                        e.getMessage();
                    }
                }
            };
            pool.execute(runable);
        }
        pool.shutdown();
    }

}
