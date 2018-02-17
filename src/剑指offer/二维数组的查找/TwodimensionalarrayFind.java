package 剑指offer.二维数组的查找;

/**
 * Created by wby on 2018/1/29.
 */
public class TwodimensionalarrayFind {
    public static void main(String[] args) {
        Find1 find1= new  Find1();
        int target = 40;

        int[][] array = new int[][]{
                {1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}
        };
        System.out.println(find1.find(target, array));
    }

}

class Find1{
     boolean find(int target, int[][] array){
         boolean found=false;
         int columns=array[0].length;
         int rows=array.length;

         if(rows>0&&columns>0){
             int row=0;
             int column=columns-1;
             while (row<rows&&column>=0){
                 if(target == array[row][column]){
                      found=true;
                     System.out.println("查找成功");
                      break;

                 }
                 else if(target<array[row][column]){
                     --column;
                     System.out.println("该数大于要查找的数，则剔除这个数所在的列");

                 }else {
                     ++row;
                     System.out.println("该数小于要查找的数，则剔除这个数所在的行");
                 }
             }


         }


         return found;
     }

}
