package cn.itcast.practice;

import java.util.Timer;
import java.util.TimerTask;

public class timeTask {
    public static void main(String[] args) {
        TimerTask task = new TimerTask () {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " 定时间隔3秒");
            }
        };
        Timer t = new Timer();
        long delay = 1000;
        long intevalTime = 3000;
        t.scheduleAtFixedRate(task, delay, intevalTime);
//        t.schedule(task,delay);
    }
}
