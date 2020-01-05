package cn.jc.algorithm;

import cn.jc.util.List;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * Example:
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class AddTwoNumbers {

    /**
     * 单链表
     */
    private static class ListNode {

        int val;

        ListNode next;

        ListNode(int x) {
            val = x;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public ListNode getNext() {
            return next;
        }

        public void next(ListNode next) {
            this.next = next;
        }
    }


    public static void main( String[] args ) {
//        ListNode l1 = new ListNode(2);
//        l1.next(new ListNode(4));
//        l1.getNext().next(new ListNode(3));
//
//        ListNode l2 = new ListNode(5);
//        l2.next(new ListNode(6));
//        l2.getNext().next(new ListNode(4));

//        ListNode l1 = new ListNode(9);
//        l1.next(new ListNode(1));
//        l1.getNext().next(new ListNode(6));
//
//        ListNode l2 = new ListNode(0);

        ListNode l1, head1;
        l1 = head1 = new ListNode(1);

        int i = 0;
        while (i < 9) {
            l1.next(new ListNode(9));
            l1 = l1.next;
            i++;
        }


        ListNode l2 = new ListNode(9);

        ListNode l3 = addTwoNumbers(head1, l2);

    }


    /**
     *  此方法会造成溢出
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        long num1 = 0;
        long num2 = 0;

        long e1 = 1;
        while (l1 != null) {
            num1 += l1.val * e1;
            e1 = e1 * 10;
            l1 = l1.next;
        }

        long e2 = 1;
        while (l2 != null) {
            num2 += l2.val * e2;
            e2 = e2 * 10;
            l2 = l2.next;
        }

        if (num1 == 0 && num2 == 0) {
            return new ListNode(0);
        }


        num1 += num2;

        ListNode l3 = null;
        ListNode head = null;
        long val;
        while (num1 > 0) {
            if (num1 >= 10) {
                val = num1 - (num1 / 10) * 10;
            } else {
                val = num1;
            }

            if (l3 == null) {
                l3 = head = new ListNode((int) val);

            } else {
                l3.next = new ListNode((int) val);
                l3 = l3.next;
            }

            num1 = num1 / 10;
        }

        return head;
    }
}
