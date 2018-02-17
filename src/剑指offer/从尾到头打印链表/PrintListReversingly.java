package 剑指offer.从尾到头打印链表;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by wby on 2018/1/20.
 */
public class PrintListReversingly {

    public static void main(String[] args) {
        /*构造一个 ListNode
        * */
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.nextNode = l2;
        l2.nextNode = l3;
        l3.nextNode = l4;
        l4.nextNode = l5;
        new ArrayList1().print(l1);
    }
}

/*栈先进后出的特点
* */
class ArrayList1 {
    public ArrayList <Integer> print(ListNode listNode) {
        ArrayList <Integer> arrayList = new ArrayList <>();
        Stack<ListNode>stack=new Stack <>();
        /*将节点存放于栈中
        * */
        while (listNode != null){
            stack.push(listNode);
            listNode=listNode.nextNode;
        }
        /*打印出来
        * */
        while (!stack.empty()){
            System.out.println(stack.pop().val);
        }
        return arrayList;
    }


}
