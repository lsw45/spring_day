package cn.itcast.practice.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class futureTask {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String a = "a";
        ExecutorService executor = Executors.newFixedThreadPool(1);

        Result r = new Result();
        r.setAAA(a);

        Future<Result> future = executor.submit(new Taskc(r), r);
        Result fr = future.get();//阻塞式，直到任务执行完才会被唤醒

        System.out.println("out:"+fr.getXXX());
    }

    static class Taskc implements Runnable{
        Result r;
        // 通过构造函数传入 result
        Taskc(Result r){
            this.r = r;
        }
        public void run() {
            // 可以操作 result
            System.out.println("run:"+r.getAAA());
            r.setXXX("x");
        }
    }

    static class Result {
        private String a;
        private String x;
        public String getAAA(){
            return a;
        }

        public String getXXX(){
            return x;
        }

        public void setXXX(String x){
            this.x = x;
        }
        public void setAAA(String a){
            this.a = a;
        }
    }
}
