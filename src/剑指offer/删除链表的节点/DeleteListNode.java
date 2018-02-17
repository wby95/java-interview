package 剑指offer.删除链表的节点;
/**
 * Created by wby on 2018/1/29.
 */
/*
* 总的时间法度为O(1)，但是它是基于一个假设：要删除的节点是在链表中的
* */
public class DeleteListNode {
    public static void main(String[] args) {
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
        DeleteNode1 deleteNode=new DeleteNode1();
        // deleteNode.deleteNode(null, l1);
        // deleteNode.deleteNode(head, l3);
        // deleteNode.deleteNode(head, l5);
        while (l1!=null){
            System.out.println(l1 + ":" + l1.val);
            l1=l1.nextNode;
        }
    }
}
class DeleteNode1 {

  public void deleteNode(ListNode pListHead, ListNode pToBeDeleted) {

      //头节点为空，待删除节点为空
      if(pListHead==null||pToBeDeleted==null){
          return;
      }
      //要删除的节点不是尾节点
      if(pToBeDeleted.nextNode!=null){
          /*我们要删除pToBeDeleted节点，先把pToBeDeleted的下一个节点 pToBeDeleted.nextNode的
          内容复制到pToBeDeleted，然后把pToBeDeleted的节点指向 pToBeDeleted.nextNode的下一个
          节点，此时再删除节点 pToBeDeleted.nextNode，其效果刚好是把节点pToBeDeleted删除了
          * */
          ListNode pNext=pToBeDeleted.nextNode;
          pToBeDeleted.val=pNext.val;
          pToBeDeleted.nextNode=pNext.nextNode;
          pNext=null;
      }
      //链表中只有一个节点，我们要删除的即使头节点也是尾节点
      else if(pListHead==pToBeDeleted){
          pListHead=null;
          pToBeDeleted=null;
      }
      //链表中有多个节点，删除尾节点
      else{
          ListNode pNode=pListHead;
          while (pNode.nextNode!=pToBeDeleted){
              pNode=pNode.nextNode;

          }
          pNode.nextNode=null;
          pToBeDeleted.nextNode=null;
      }

    }
}