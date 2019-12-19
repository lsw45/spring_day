package cn.itcast.practice;

import java.util.function.Function;

public class lambdaFunction {
    public static void main(String[] args) {
        testCalculate();
        test();
        test2();
    }
    
    public static void testCalculate(){
        Function<Integer,Integer> test1= i->i+1;
        Function<Integer,Integer> test2=i->i*i;
        System.out.println(calculate(test1,5));
        System.out.println(calculate(test2,5));
    }

    public static Integer calculate(Function<Integer,Integer> test,Integer number){
        return test.apply(number);
    }

    public static void test(){
        Function<Integer,Integer> A=i->i+1;
        Function<Integer,Integer> B=i->i*i;
        System.out.println("F1:"+B.apply(A.apply(5)));
        System.out.println("F2:"+A.apply(B.apply(5)));
    }

    public static void test2(){
        Function<Integer,Integer> A=i->i+1;
        Function<Integer,Integer> B=i->i*i;
        System.out.println("F1:"+B.apply(A.apply(5)));
        System.out.println("F1:"+B.compose(A).apply(5));
        System.out.println("F2:"+A.apply(B.apply(5)));
        System.out.println("F2:"+B.andThen(A).apply(5));
    }
}
