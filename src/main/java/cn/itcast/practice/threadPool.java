package cn.itcast.practice;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

class threadPool{
    public static void main(String[] args) throws InterruptedException {
        // 创建对象池
        ObjPool<Long, String> pool = new ObjPool<Long, String>(1, 2l);

        for (int i=0;i<200;i++){
            // 通过对象池获取 t，之后执行
            pool.exec(t -> {
                System.out.println(t);
                return t.toString();
            });
        }
    }
}
class ObjPool<T, R> {
    final List<T> pool;
    // 用信号量实现限流器
    final Semaphore sem;
    // 构造函数
    ObjPool(int size, T t){
        pool = new Vector<T>(){};
        for(int i=0; i<size; i++){
            pool.add(t);
        }
        sem = new Semaphore(size);
    }
    // 利用对象池的对象，调用 func
    R exec(Function<T,R> func) throws InterruptedException {
        T t = null;
        sem.acquire(); //减1
        try {
            t = pool.remove(0);
            return func.apply(t);
        } finally {
            pool.add(t);
            sem.release(); //加1
        }
    }
}
