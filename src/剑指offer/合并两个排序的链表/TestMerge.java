package 剑指offer.合并两个排序的链表;

/**
 * Created by wby on 2018/3/17.
 */

/*合并两个有序的链表
* */
class ListNode{
    int val;
    ListNode nextNode;
    public ListNode() {
    }
    public ListNode(int val) {
        this.val = val;
        this.nextNode=null;
    }

}
class MergeList{
    ListNode merge(ListNode pHead1,ListNode pHead2){
        ListNode returnListNode=null;
        //解决鲁棒性问题
        if(pHead1==null){
            return pHead2;
        }
        if(pHead2==null){
            return  pHead1;
        }

        if(pHead1.val<pHead2.val){
            returnListNode=pHead1;
            returnListNode.nextNode=merge(pHead1.nextNode,pHead2);

        }else {
            returnListNode=pHead2;
            returnListNode.nextNode=merge(pHead1,pHead2.nextNode);
        }

        return returnListNode;

    }

}
public class TestMerge {
    public static void main(String[] args) {

        //构建两个有序的链表
        ListNode listNode1=new ListNode(1);
        ListNode listNode2=new ListNode(3);
        ListNode listNode3=new ListNode(5);
        ListNode listNode4=new ListNode(7);
        listNode1.nextNode=listNode2;
        listNode2.nextNode=listNode3;
        listNode3.nextNode=listNode4;
        listNode4.nextNode=null;

        ListNode listNode5=new ListNode(2);
        ListNode listNode6=new ListNode(4);
        ListNode listNode7=new ListNode(6);
        ListNode listNode8=new ListNode(8);
        listNode5.nextNode=listNode6;
        listNode6.nextNode=listNode7;
        listNode7.nextNode=listNode8;
        listNode8.nextNode=null;



        ListNode listNode=new MergeList().merge(listNode1,listNode5);
        while (listNode!=null){

            System.out.println(listNode.val);
            listNode=listNode.nextNode;
        }



    }
}
