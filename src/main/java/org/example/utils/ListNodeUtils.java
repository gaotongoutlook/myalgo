package org.example.utils;

import org.example.pojo.ListNode;

public class ListNodeUtils {


    public static ListNode buildListNode(int[] values) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        for(int val : values) {
            cur.next =  new ListNode(val);
            cur = cur.next;
        }
        return dummy.next;
    }

    public static void printListNode(ListNode result) {
        System.out.print("[");
        while(result!=null) {
            System.out.print(" "+result.val+" ");
            result = result.next;
        }
        System.out.print("]");
    }
}
