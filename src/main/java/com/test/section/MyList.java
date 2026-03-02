package com.test.section;

import org.example.pojo.ListNode;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 链表public static void heapSort(int[] arr) {
 *         int n = arr.length;
 *
 *         // 构建最大堆
 *         for (int i = n / 2 - 1; i >= 0; i--) {
 *             heapify(arr, n, i);
 *         }
 *
 *         System.out.println("构建堆后：");
 *         printArray(arr);
 *
 *         // 逐个提取元素
 *         for (int i = n - 1; i > 0; i--) {
 *             swap(arr, 0, i);
 *             heapify(arr, i, 0);
 *
 *             System.out.print("提取元素 " + arr[i] + " 后：");
 *             printArray(arr);
 *         }
 *     }
 *
 *     private static void heapify(int[] arr, int n, int i) {
 *         int largest = i;
 *         int left = 2 * i + 1;
 *         int right = 2 * i + 2;
 *
 *         if (left < n && arr[left] > arr[largest])
 *             largest = left;
 *
 *         if (right < n && arr[right] > arr[largest])
 *             largest = right;
 *
 *         if (largest != i) {
 *             swap(arr, i, largest);
 *             heapify(arr, n, largest);
 *         }
 *     }
 *
 *     private static void swap(int[] arr, int i, int j) {
 *         int temp = arr[i];
 *         arr[i] = arr[j];
 *         arr[j] = temp;
 *     }
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
            // 需要分析两个数字取值不同的原因是什么
            for(int i=0; i<k; i++) {
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

    /**
     * 合并两个有序链表
     */
    public ListNode Merge (ListNode pHead1, ListNode pHead2) {
        if(pHead1==null || pHead2==null) {
            return pHead1!=null ? pHead1 : pHead2;
        }

        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (pHead1!=null || pHead2!=null) {
            if(pHead1==null) {
                cur.next = pHead2;
                break;
            }else if(pHead2==null) {
                cur.next = pHead1;
                break;
            }
            if(pHead1.val <= pHead2.val) {
                cur.next = pHead1;
                pHead1 = pHead1.next;
            }else {
                cur.next = pHead2;
                pHead2 = pHead2.next;
            }
            cur = cur.next;
        }

        return dummy.next;
    }

    /**
     * 合并K个已排序的链表
     */
    public ListNode mergeKLists (ArrayList<ListNode> lists) {
        if(lists==null || lists.size()==0) {
            return null;
        }else if(lists.size()==1) {
            return lists.get(0);
        }

        // 归并排序
        return mergeKLists(lists, 0, lists.size()-1);
    }

    private ListNode mergeKLists(ArrayList<ListNode> lists, int start, int end) {
        if(start==end) {
            return lists.get(start);
        }
        int mid = start + (end - start) / 2;
        ListNode left = mergeKLists(lists, start, mid);
        ListNode right = mergeKLists(lists, mid+1, end);
        return mergeKLists(left, right);
    }

    private ListNode mergeKLists(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        while (left!=null || right!=null) {
            if(left==null) {
                cur.next = right;
                break;
            }else if(right==null) {
                cur.next = left;
                break;
            }
            if(left.val <= right.val) {
                cur.next = left;
                left = left.next;
            }else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }

        return dummy.next;
    }

    /**
     * 判断链表中是否有环
     */
    public boolean hasCycle(ListNode head) {
        if(head==null || head.next==null) {
            return false;
        }

        // 为什么这么整呢
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast!=null && fast.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                return true;
            }
        }

        return false;
    }

    /**
     * 链表中入环节点
     */
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if(pHead==null || pHead.next==null) {
            return null;
        }

        // 此处为什么快慢指针相同呢
        ListNode slow = pHead;
        ListNode fast = pHead;
        while (fast!=null && fast.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                break;
            }
        }

        if(fast==null || fast.next==null) {
            return null;
        }

        fast = pHead;
        while(fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return fast;
    }

    /**
     * 链表中倒数最后k个结点
     * 重点 注意边界条件 比较多和复杂
     */
    public ListNode FindKthToTail (ListNode pHead, int k) {
        if(pHead==null || k<=0) {
            return null;
        }

        int n = k;
        ListNode cur = pHead;
        while(n>0 && cur!=null) {
            cur = cur.next;
            n--;
        }

        // 当为空的时候需要全部返回 边界条件真麻烦
        if(n==0 && cur==null) {
            return pHead;
        }else if(cur==null) {
            return cur;
        }

        ListNode pre = pHead;
        while (cur!=null) {
            cur = cur.next;
            pre = pre.next;
        }

        return pre;
    }

    /**
     * 删除链表的倒数第n个节点
     * 注意边界条件
     */
    public ListNode removeNthFromEnd (ListNode head, int n) {
        return null;
    }

    /**
     * 两个链表的第一个公共节点
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if(pHead1==null || pHead2==null) {
            return null;
        }

        int n = 0;
        ListNode cur = pHead1;
        while (cur!=null) {
            n++;
            cur = cur.next;
        }

        int m = 0;
        cur = pHead2;
        while (cur!=null) {
            m++;
            cur = cur.next;
        }

        ListNode h1 = pHead1;
        ListNode h2 = pHead2;
        if(n > m) {
            while (n > m) {
                h1 = h1.next;
                n--;
            }
        }else if(n < m) {
            while (n < m) {
                h2 = h2.next;
                m--;
            }
        }

        while (h1!=null && h2!=null) {
            if(h1==h2) {
                break;
            }
            h1 = h1.next;
            h2 = h2.next;
        }

        return h1;
    }

    /**
     * 链表相加II
     */
    public ListNode addInList (ListNode head1, ListNode head2) {
        if(head1==null || head2==null) {
            return head1==null ? head2 : head1;
        }

        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        ListNode cur1 = ReverseList(head1);
        ListNode cur2 = ReverseList(head2);

        int carry = 0;
        while(cur1!=null || cur2!=null) {
            int sum = carry;
            if(cur1!=null) {
                sum += cur1.val;
                cur1 = cur1.next;
            }
            if(cur2!=null) {
                sum += cur2.val;
                cur2 = cur2.next;
            }
            if(sum > 9) {
                sum = sum % 10;
                carry = 1;
            }else {
                carry = 0;
            }
            cur.next = new ListNode(sum);
            cur = cur.next;
        }

        if(carry>0) {
            cur.next = new ListNode(carry);
            cur = cur.next;
        }

        return ReverseList(dummy.next);
    }

    /**
     * 单链表的排序
     */
    public ListNode sortInList (ListNode head) {
        if(head==null || head.next==null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head.next;
        while(fast!=null && fast.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode mid = slow.next;
        slow.next = null;
        ListNode left = sortInList(head);
        ListNode right = sortInList(mid);

        return mergeSortInList(left, right);
    }

    private ListNode mergeSortInList(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        while(left!=null || right!=null) {
            if(left==null) {
                cur.next = right;
                break;
            }else if(right==null) {
                cur.next = left;
                break;
            }
            if(left.val <= right.val) {
                cur.next = left;
                left = left.next;
            }else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }

        return dummy.next;
    }

    /**
     * 判断一个链表是不是回文结构
     */
    public boolean isPail (ListNode head) {
        if(head==null || head.next==null) {
            return true;
        }

        LinkedList<ListNode> queue = new LinkedList<>();
        queue.addFirst(head);

        ListNode slow = head;
        ListNode fast = head.next;
        while (fast!=null && fast.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
            queue.addFirst(slow);
        }

        ListNode cur = slow.next;
        while(cur!=null && !queue.isEmpty()) {
            ListNode temp = queue.getLast();
            if(temp.val != cur.val) {
                return false;
            }
            cur = cur.next;
        }

        if(queue.size()==1) {
            return true;
        }

        return false;
    }

    /**
     * 链表的奇偶重排
     */
    public ListNode oddEvenList (ListNode head) {
        if(head==null) {
            return head;
        }

        ListNode oddDummy = new ListNode(-1);
        ListNode odd = oddDummy;
        ListNode evenDummy = new ListNode(-1);
        ListNode even = evenDummy;
        ListNode tailOdd = null;

        ListNode cur = head;
        boolean flag = true;
        while(cur!=null) {
            if(flag) {
                odd.next = new ListNode(cur.val);
                odd = odd.next;
                tailOdd = odd;
            }else {
                even.next = new ListNode(cur.val);
                even = even.next;
            }
            cur = cur.next;
            flag = !flag;
        }

        if(evenDummy.next!=null) {
            tailOdd.next = evenDummy.next;
        }

        return oddDummy.next;
    }

    /**
     * 删除有序链表中重复的元素-I
     * 总结下什么时候需要构建节点 什么时候直接引用节点并且不出错
     */
    public ListNode deleteDuplicates (ListNode head) {
        if(head==null || head.next==null) {
            return head;
        }

        ListNode dummy = new ListNode(-1);
        ListNode pre = new ListNode(head.val);
        dummy.next = pre;
        ListNode cur = head.next;

        while(cur!=null) {
            if(pre.val != cur.val) {
                pre.next = new ListNode(cur.val);
                pre = pre.next;
            }
            cur = cur.next;
        }

        return dummy.next;
    }

    /**
     * 删除有序链表中重复的元素-II
     * 显示只出现一次的元素
     */
    public ListNode deleteDuplicates1 (ListNode head) {
        if(head==null || head.next==null) {
            return head;
        }

        return null;
    }

}
