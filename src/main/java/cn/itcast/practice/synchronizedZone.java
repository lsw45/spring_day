package cn.itcast.practice;

public class synchronizedZone {

    public static void main(String[] args) {
        final Integer b = 2;
        Thread thread_1 = new Thread("thread_1"){
            @Override
            public void run() {
                synchronized (b) {
                    if(b<10){
                        try {
                            b.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("线程thread_1运行结束，b的值是："+b);
                }
            }
        };
        thread_1.start();

        Thread thread_2 = new Thread(new MyRunable(b),"thread_1");
        thread_2.start();
    }

    static class MyRunable implements Runnable{

        Integer b;

        public MyRunable(Integer b){
            //再次自动装箱来修该值，那么b就指向新的对象
            this.b = b+10;
        }

        @Override
        public void run() {
            //线程1、2的对象监视器已经不一样了，所以，线程2将无法按照预期唤醒线程1
            synchronized (b) {
                b.notify();
                System.out.println("线程thread_2运行结束！");
            }
        }
    }
}
