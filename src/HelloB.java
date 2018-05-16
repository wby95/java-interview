import org.junit.Test;

/**
 * Created by wby on 2018/5/16.
 */
class HelloA{
    public HelloA(){
        System.out.println("HelloA");

    }
    {
        System.out.println("I 'm A class");

    }
    static {
        System.out.println("static A");
    }
}

public class HelloB {
    public HelloB(){
        System.out.println("HelloB");

    }
    {
        System.out.println("I 'm B class");

    }
    static {
        System.out.println("static B");
    }
    @Test
    public static void main(String[] args) {
        new HelloB();
    }
}
