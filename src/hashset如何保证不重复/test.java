package hashset如何保证不重复;
import java.util.*;
public class test {
    public static void main(String[] args) {
        UserBean userBean1 = new UserBean();
        UserBean userBean2 = new UserBean();

        /*
          不重写时候 都为false
          public boolean equals(Object obj) {
        UserBean bean=(UserBean)obj;
        return this.name==bean.name;
    }
        * */
        System.out.println(userBean1.equals(userBean2));//用equals true
        System.out.println(userBean1 == userBean2);//false
        /*
        * 两个对象一样当时还是被添加进来了
        * */
        HashSet hashSet = new HashSet();

        //调用add方法时，先判断hash值是否相同，如果不同，就不会再调用equals()方法
        hashSet.add(new UserBean(("aa"), 11));
        hashSet.add(new UserBean(("bb"), 22));
        hashSet.add(new UserBean(("cc"), 33));
        hashSet.add(new UserBean(("dd"), 11));
        /*我是哈希码
                我是哈希码
        我是哈希码
                我是哈希码*/
        //调用add方法时，先判断hash值是否相同，如果相同，就会再调用equals()方法相比较
        hashSet.add(new UserBean(("aa"), 11));
        hashSet.add(new UserBean(("bb"), 22));
        hashSet.add(new UserBean(("cc"), 33));
        hashSet.add(new UserBean(("aa"), 11));
        /*我是哈希码
                我是哈希码
        我是哈希码
                我是哈希码
                用equals
        * */
    }
}