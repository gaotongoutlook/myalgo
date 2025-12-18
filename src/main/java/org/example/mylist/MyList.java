package org.example.mylist;

import org.example.pojo.ListNode;

/**
 * 链表相关
 */
public class MyList {

    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        ListNode p = head;
        while (p != null) {
            ListNode next = p.next;
            if(p.val != val) {
                p.next = null;
                tail.next = p;
                tail = p;
            }
            p = next;
        }

        return dummy.next;
    }

}
