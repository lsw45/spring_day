package cn.itcast.practice.Thread;

/**
 * 实现Runnable接口和继承Thread类的区别：
 * java只能单继承，因此如果是采用继承Thread的方法，那么在以后进行代码重构的时候可能会遇到问题，因为你无法继承别的类了。
 */
public class ThreadAndRunnable {
    public static void main(String[] args) {
        test01();
//        test02();
    }

    public static void test01(){
        threadExample d = new threadExample("thread");
        new Thread(d,"A").start();
        new Thread(d,"B").start();
        new Thread(d,"C").start();

        // new threadExample("A").start();
        // new threadExample("B").start();
        // new threadExample("C").start();
    }

    static class threadExample extends Thread {
        private int count=10;
        private String name;
        public threadExample(String name){
            this.name = name;
        }
        public void run() {
            for(int x=0; x<count*2; x++){
                System.out.println(name+Thread.currentThread().getName()+":"+count--);
            }
        }
    }

    public static void test02(){
        runExample d = new runExample();
        new Thread(d,"A").start();
        new Thread(d,"B").start();
        new Thread(d,"C").start();

        // new Thread(new runExample(),"A").start();
        // new Thread(new runExample(),"B").start();
        // new Thread(new runExample(),"C").start();
    }

    static class runExample implements Runnable {
        private int count=10;
        public void run() {
            for(int x=0; x<count*2; x++){
                System.out.println(Thread.currentThread().getName()+":"+count--);
            }
        }
    }
}