package 剑指offer.排序;

/**
 * Created by wby on 2018/3/25.
 */


/*快排*/
public class QuickSort {
    public static void main(String[] args) {
        int a[] = {3,1,5,7,2,4,9,6,10,8};
        QuickSort  obj=new QuickSort();
        System.out.println("初始值：");
        obj.print(a);
        int h=a.length-1;
        obj.quickSort(a,0,h);
        System.out.println("\n排序后：");
        obj.print(a);

    }

    private void print(int[] a) {
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]+" ");
        }
    }

    public void quickSort(int[] a, int low, int high) {
        if (low < high) { //判断递归是否跳出
            int middle = getMiddle(a, low, high);
            //递归
            quickSort(a, 0, middle - 1);
            quickSort(a, middle + 1, high);
        }
    }

    public int getMiddle(int[] a, int low, int high) {
        int key = a[low];//基准元素
        while (low < high) {
            while (low < high && a[high] >= key) {//从high方向一直往前找&找到比基准值小的
                high--;
            }
            a[low] = a[high];
            while (low < high && a[low] <= key) {//同理
                low++;
            }
            a[high] = a[low];
        }
        a[low] = key;//此时low=high 是基准元素的位置，也是空出来的那个位置
        return low;
    }
}























