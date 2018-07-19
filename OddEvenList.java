package LeetCode;


import java.io.IOException;

public class OddEvenList {

    public static  class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
 }
    public ListNode oddEvenList(ListNode head) {

        if(head == null || head.next == null || head.next.next == null)
            return head;

        ListNode odd = head, tempOdd = head;
        ListNode even = head.next, tempEven = head.next;

        while(tempOdd != null && tempOdd.next != null && tempEven != null && tempEven.next != null)
        {
            tempOdd.next = tempOdd.next.next;
            tempEven.next = tempEven.next.next;
            tempOdd = tempOdd.next;
            tempEven = tempEven.next;
        }
        /*while(tempEven != null && tempEven.next != null)
        {
            tempEven.next = tempEven.next.next;
        }*/
        tempOdd.next = even;
        return odd;
    }

    public  static void main(String[] args)throws IOException
    {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        OddEvenList obj = new OddEvenList();
        head = obj.oddEvenList(head);

        while(head != null)
        {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
