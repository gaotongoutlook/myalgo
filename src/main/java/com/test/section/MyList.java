package com.test.section;

import org.example.pojo.ListNode;

/**
 * 链表
 */
public class MyList {

    /**
     * 链表内指定区间反转
     */
    public ListNode reverseBetween (ListNode head, int m, int n) {
        if(head==null || head.next==null || m==n) {
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy;
        for(int i=1; i<m; i++) {
            pre = pre.next;
        }

        ListNode cur = pre.next;
        for(int i=0; i<n-m; i++) {
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }

        return dummy.next;
    }

    /**
     * 每K个为一组翻转链表
     */
    public ListNode reverseKGroup (ListNode head, int k) {
        if(head==null || k<=1) {
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy;
        while (true) {
            ListNode last = pre;
            for(int i=1; i<k; i++) {
                last = last.next;
                if(last==null) {
                    return dummy.next;
                }
            }

            ListNode cur = pre.next;
            ListNode next = null;
            for(int i=1; i<k; i++) {
                next = cur.next;
                cur.next = next.next;
                next.next = pre.next;
                pre.next = next;
            }

            pre = cur;
        }
    }

    /**
     * 反转链表
     */
    public ListNode ReverseList (ListNode head) {
        if(head==null || head.next==null) {
            return head;
        }

        ListNode pre = null;
        ListNode cur = head;
        while(cur!=null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }


}
