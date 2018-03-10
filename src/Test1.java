
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wby on 2018/2/27.
 */
/*
*  将多空格换成单空格
* */

 class parent{
    public static int A=1;
    static {
        A=2;
    }
}
class Sub extends parent{
    public static int B=A;
}
public class Test1 {
    public static void main(String[] args) {
        final int[]a={1,2,3};
        int[] another={4,5,6};
        a[0]=2;

        String target="";
        String s="S  tr in    g";
        Pattern p=Pattern.compile("\\s+");
        Matcher m=p.matcher(s);
        target=m.replaceAll(" ");
        System.out.println(target);
        System.out.println(Sub.B);

    }
}
