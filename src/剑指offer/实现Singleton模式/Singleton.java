package 剑指offer.实现Singleton模式;

/**
 * Created by wby 2018/1/18.
 */
/*
//懒汉式：只是使用于单线程的环境
/*


 */
/*
public class Singleton {
    private static Singleton instance=null;
    private Singleton(){

    }
    public static Singleton getInstance(){
        if(instance==null){
            instance=new Singleton();

        }
        return instance;
    }
}*/
//利用同步机制实现的懒汉式
    /*
    * 使用同步锁  synchronized (Singleton.class) 防止了多线程的介入，从而导致instance被多次实例化
    * */
/*

public class Singleton {
    private static Singleton instance=null;
    private Singleton(){

    }
    public static Singleton getInstance(){
        synchronized (Singleton.class) {
            if (instance == null) {
                instance = new Singleton();
            }
        }
        return instance;
    }
}*/

/*饿汉式
* */
/*
public class Singleton {
    private static Singleton instance=new Singleton();
    private Singleton(){

    }
    public static Singleton getInstance(){
        return instance;
    }
}*/


/*双重检查锁定
* */
/*public class Singleton {
    private static Singleton instance=null;
    private Singleton(){
    }
    public static Singleton getInstance() {
        if(instance==null){
            synchronized (Singleton.class){
                if(instance==null){
                    instance=new Singleton();//线程B引用的对象可能还没有完成初始化
                    *//*     线程A                      线程B
                        1 分配对象的内存空间
                        3 设置instance指向的内存空间
                                                       1判断instance 是否为null
                        （2,3发生重排序）                               2B线程初次访问对象

                         2初始化对象

                    *//*
                }
            }
        }
        return instance;
    }
}*/


//解决双重检查锁定

    /* volatile:他的两层语义
    1 可见性:一个线程中对该变量的修改会马上由工作内存写回主存
    2 禁止指令重排序的优化（2和3之间将会被禁止重排序）
    * */

/*
public class Singleton {
    private volatile static Singleton instance=null;
    private Singleton(){
    }
    public static Singleton getInstance() {
        if(instance==null){
            synchronized (Singleton.class){
                if(instance==null){
                    instanc  e=new Singleton();

                }
            }
        }
        return instance;
    }
}
*/
/*内部类的方式

线程之间重排序透明性
* */



/*
public class Singleton {
    private static class Singleton1{
           static Singleton instance=new Singleton();
    }
    private Singleton{

    }
    public static Singleton getInstance(){
        return Singleton1.instance;
    }
}
*/




/*枚举方式 ：避免反射的攻击
* */
/*
 enum Singleton1{
    INSTANCE;
    public void doSomething(){

    }

}
//调用的方式
public class Singleton{

    public static void main(String[]args){
                  Singleton1 singleton1=Singleton1.INSTANCE;
                  singleton1.doSomething();
    }
}

*/



























