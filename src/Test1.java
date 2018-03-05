import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wby on 2018/2/27.
 */
/*
*  将多空格换成单空格
* */




public class Test1 {
    public static void main(String[] args) {

        String target="";
        String s="S  tr in    g";
        Pattern p=Pattern.compile("\\s+");
        Matcher m=p.matcher(s);
        target=m.replaceAll(" ");
        System.out.println(target);

    }
}
