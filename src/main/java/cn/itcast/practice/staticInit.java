package cn.itcast.practice;


import java.util.Random;

class Initable{
    public static final int staticFinal = 47;//他的值不变
    public static final int staticFinal2 = ClassInitialization.rand.nextInt(1000);
    static {
        System.out.println("初始化 Initable");
    }
}

class Initable2{
    public static int stationNonFinal = 147;//注意没有final
    static{
        System.out.println("初始化 Initable2");
    }
}

class Initable3{
    public static int staticNonFinal = 74;
    static{
        System.out.println("初始化 Intiable3");
    }
}

class ClassInitialization {
    public static Random rand = new Random(47);

    public static void main(String [] args) throws ClassNotFoundException{
        Class initable = Initable.class;//只是加载字节码进入虚拟机，获得引用没有初始化
        System.out.println("After creating Initable ref");
        System.out.println(Initable.staticFinal);//请求访问static final定值
        System.out.println(Initable.staticFinal2);//请求访问static final 为直接定义的值
    }
}