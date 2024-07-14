import java.io.*;
import java.util.*;
import crio.ds.List.ListNode;

class Solution {
    public static ListNode partition(ListNode head, int x) {
        ListNode before_start = null;
        ListNode before_end = null;
        ListNode after_start = null;
        ListNode after_end = null;
        ListNode equal_start = null;
        ListNode equal_end = null;

        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = null;

            if (current.val < x) {
                if (before_start == null) {
                    before_start = current;
                    before_end = before_start;
                } else {
                    before_end.next = current;
                    before_end = current;
                }
            } else if (current.val > x) {
                if (after_start == null) {
                    after_start = current;
                    after_end = after_start;
                } else {
                    after_end.next = current;
                    after_end = current;
                }
            } else {
                if (equal_start == null) {
                    equal_start = current;
                    equal_end = equal_start;
                } else {
                    equal_end.next = current;
                    equal_end = current;
                }
            }
            current = next;
        }

        if (before_start == null) {
            if (equal_start == null) {
                return after_start;
            }
            equal_end.next = after_start;
            return equal_start;
        }
        if (equal_start == null) {
            before_end.next = after_start;
            return before_start;
        }
        before_end.next = equal_start;
        equal_end.next = after_start;
        return before_start;
    }
}
