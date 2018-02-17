package 剑指offer.反转链表;

/**
 * Created by wby on 2018/1/29.
 */
public class ReverseList {
    public static void main(String[]args){
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        ListNode head = new ListNode();
        head.nextNode= l1;
        l1.nextNode = l2;
        l2.nextNode = l3;
        l3.nextNode = l4;
        l4.nextNode = l5;
        List24 list24=new List24();
        ListNode listNode=list24.reverseList(head);
        while (listNode!=null){
            System.out.println(listNode.val);
            listNode=listNode.nextNode;
        }


    }
}
class  List24{
    ListNode reverseList(ListNode pHead){

        ListNode  pReverseNode=null;
        ListNode  pNode=pHead;
        ListNode  pPrev=null;
        while (pNode!=null){
            ListNode pNext=pNode.nextNode;
            if(pNext==null){
                pReverseNode=pNode;//尾部

            }
            /*我们在调整节点pNode的指针时，除了需要知道pNode本身，还需要知道pNode的前一个节点，同时，我们还要事先
            保存pNode的下一个节点，以防止链表的断开。
            * */
            pNode.nextNode=pPrev;//将当前节点的指针指向前一个节点
            pPrev=pNode;//重置前一个节点
            pNode=pNext;  //重置当前节点
        }

        return  pReverseNode;

    }

}