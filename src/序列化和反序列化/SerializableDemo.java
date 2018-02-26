package 序列化和反序列化;

import java.io.*;

/**
 * Created by wby on 2018/2/26.
 */

/*
* 本例是继承一个 Serializable接口()
* */
class Student implements Serializable{
    private String name;
    private int sex;

    public Student() {
    }

    public Student(String name, int sex) {
        this.name = name;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
public class SerializableDemo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student student = new Student("王博越", 1);
        File file = new File("student.txt");
        /*对象的序列化:将对象转化为字节序列
        * */
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(student);
        oos.flush();
        oos.close();
        fos.close();
         /*对象的反序列化：将字节序列转化成对象
        * */
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Student student1 = (Student) ois.readObject();
        System.out.println(student1.getName() + " : " + student1.getSex());
        ois.close();
        fis.close();
    }

}
