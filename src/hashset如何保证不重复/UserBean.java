package hashset如何保证不重复;

/**
 * Created by wby on 2018/1/18.
 */
public class UserBean {
    private String name;
    private int age;
    public UserBean(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public UserBean() {
    }

    /*重写equals后 System.out.println( userBean1.equals( userBean2));为true
    * */
   @Override
    public boolean equals(Object obj) {
        UserBean bean=(UserBean)obj;
        System.out.println("调用equals");
        return this.name==bean.name&& this.age==bean.age;
    }
    public int hashCode(){
       System.out.println("我是哈希码");
       return name.hashCode()+age*36;
    }
}
