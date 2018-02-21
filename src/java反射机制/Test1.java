package java反射机制;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by wby on 2018/2/9.
 */
public class Test1 {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        User user=new User();
        /*获取得到包类名*/
        Class <User> userClass = User.class;
        userClass.newInstance();
        System.out.println(user.getClass().getName());
        /*大的Class类可以获得原生类的一些结构
        * */
        /*Class.forName(String className)返回与给定字符串名的类或接口相关联的Class 对象
        * */
        try {
            Class <?> u=Class.forName("java反射机制.User");
            u.getConstructors();
            /*获取实例*/
            User user2= (User) u.newInstance();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        /*
        *
        *
        * */
        try {
            Class<?> c=Class.forName("java反射机制.Person");
            Constructor <?>[] cons=c.getConstructors();
            Person people= (Person) cons[0].newInstance("王博越",22);

            /*打印类的构造函数
            * */
            for(Constructor<?> con:cons){
                System.out.println(con);

            }

            /*获取所有的方法,包括从父类继承下来的方法都被展现了出来
            * */
            Method[] methods=c.getMethods();
            for(Method method:methods){
                System.out.println(method);

            }
            /*获取属性
            * */
            Field[] fields=c.getDeclaredFields();
            for(Field field:fields){
                System.out.println(field);
            }

            /*通过反射操作属性和方法
            * */
            Class<?> c1 =Class.forName("java反射机制.Person");
            Object object=c1.newInstance();
            /*第一个参数方法名，第二个参数方法的参数类型，逗号隔开
            * */
            /*通过反射获取方法
            * */
            Method method = object.getClass().getMethod("setName", String.class);
            /*通过方法调用方法
            * */
            method.invoke(object,"王博越");
            Method method1=object.getClass().getMethod("getName");
            String name= (String) method1.invoke(object);
            System.out.println(name);


            Field nameField=c1.getDeclaredField("name");
            nameField.setAccessible(true);//设置可以访问私有属性
            nameField.set(object,"王博越");

            /*获取*/
            nameField.get(object);

            /*
            * 通过反射来操作类的私有属性，从一定的角度上来讲，破坏了封装性
            * */

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


    }
}
