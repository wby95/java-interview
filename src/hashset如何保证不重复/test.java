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
        //HashMap中使用键对象来计算hashcode值&HashSet使用成员对象来计算hashcode值，对于两个对象来说hashcode可能相同，所以equals()方法用来判断对象的相等性，如果两个对象不同的话，那么返回false
        //调用add方法时，先判断hash值是否相同，如果不同，就不会再调用equals()方法
        //HashMap几乎可以等价于Hashtable，除了HashMap是非synchronized的，并可以接受null(HashMap可以接受为null的键值(key)和值(value)，而Hashtable则不行)
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
        System.out.println();
        /*我是哈希码
                我是哈希码
        我是哈希码
                我是哈希码
                用equals
        * */  HashMap hashMap=new HashMap();
        hashMap.put("1",11);
        hashMap.put("2",22);
        System.out.println(hashMap.keySet());//[1,2]
        System.out.println(hashMap.toString());
    }

}