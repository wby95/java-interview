package hashset如何保证不重复;///**
// * Created by wby on 2018/1/17.
// * <p>
// * <p>
// * Effecticetive java
// */
//
//    //考虑静态工厂方法代替构造器
//    /*  1
//    *
//    * */
//class A{
//
//}
//public class test {
//
//    public static void main(String[] args) {
//
//             String st1=new String("aa");
//        String st2=new String("aa");
//        System.out.println(st1==st2);
//        System.out.println(st1.equals(st2));//T
//        /*
//        *    public boolean equals(Object anObject) {
//        if (this == anObject) {
//            return true;
//        }
//        if (anObject instanceof String) {
//            String anotherString = (String)anObject;
//            int n = value.length;
//            if (n == anotherString.value.length) {
//                char v1[] = value;
//                char v2[] = anotherString.value;
//                int i = 0;
//                while (n-- != 0) {
//                    if (v1[i] != v2[i])
//                        return false;
//                    i++;
//                }
//                return true;
//            }
//        }
//        return false;
//    }
//        * */
//
//        A st3=new A();
//        A st4=new A();
//
//        /*
//        *   public boolean equals(Object obj) {
//        return (this == obj);
//    }*/
//        System.out.println(st3==st4);
//        System.out.println(st3.equals(st4));
//    }
//}


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
        System.out.println(userBean1.equals(userBean2));
        System.out.println(userBean1 == userBean2);


        /*
        * 两个对象一样当时还是被添加进来了
        * */
        HashSet hashSet = new HashSet();
        ArrayList arrayList=new ArrayList();
        arrayList.get(1);
        arrayList.add(0,"wby0");
        arrayList.add(1,"wby1");
        String a1 = (String) arrayList.set(1,"wby2");
        System.out.println(a1);


        LinkedList linkedList=new LinkedList();
        linkedList.add(123);
        linkedList.add(1,0);
        HashMap hashMap=new HashMap();

        arrayList.iterator().next();
        hashSet.add(new UserBean(("aa"), 11));
        hashSet.add(new UserBean(("bb"), 22));
        hashSet.add(new UserBean(("cc"), 33));
        hashSet.add(new UserBean(("aa"), 11));
        Iterator iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            UserBean userBean = (UserBean) iterator.next();
            System.out.println(userBean.getName() + " : " + userBean.getAge());
        }


/*        hashSet如何确保数据的唯一性
*
* 通过hashcode和equals,如果hashcode元素相同，才会判断equals是否为true
* hashCode相等,值不一定相等,值相等则hashCode一定相等.因此hashCode方法一定要配合equals方法重写
*
* */

    }


}