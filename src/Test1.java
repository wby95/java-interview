import java.util.Scanner;

public class Test1 {
    static int c=0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int a[][];

        for(int i = 1; i < n; i++){
            for(int j = 1; j < n; j++){
               if(i%j<=k){
                   c++;
               }
            }
        }


    }
}