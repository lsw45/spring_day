package cn.itcast.practice;

class Father
{
    static{
        System.out.println("父类静态代码块初始化" );
    }
    {
        System.out.println("父类代码块初始化" );
    }
    private static String s=print();
    public static String print()
    {
        System.out.println("父类静态方法" );
        return "返回父类静态方法";
    }
    public Father()
    {
        System.out.println("父类无参构造函数初始化完成" );
        show();
    }
    public void show()
    {
        System.out.println("父类show()方法"+s );
    }
}
class Son extends Father
{
    static{
        System.out.println("子类静态代码块初始化" );
    }
    {
        System.out.println("子类代码块初始化" );
    }
    private static int i=1;
    private String s="子类私有成员变量" ;
    public void show()
    {
        System.out.println("子类show()方法：i=" +i);
    }
    public Son()
    {
        System.out.println("子类构造函数初始化完成" );
        System.out.println("子类成员变量初始化完成：s="+s);
        show();
    }
}
public class JavaClassLoadSeq {
    public static void main(String[]args)
    {
        //因为创建的是son实例，所以父类里的show方法被初始化时，实际调用的是子类show方法
        new Son();
    }

}
