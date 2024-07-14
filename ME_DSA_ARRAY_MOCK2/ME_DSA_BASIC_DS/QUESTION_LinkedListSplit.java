import crio.ds.List.ListNode;
import java.util.ArrayList;
import java.util.List;

class Solution {

    public List<ListNode> linkedListSplit(ListNode head) {
        List<ListNode> result = new ArrayList<>();
        ListNode oddHead = null;
        ListNode evenHead = null;
        ListNode oddTail = null;
        ListNode evenTail = null;

        while (head != null) {
            if (head.val % 2 == 0) {
                // Even data, add to the even list
                if (evenHead == null) {
                    evenHead = head;
                    evenTail = head;
                } else {
                    evenTail.next = head;
                    evenTail = evenTail.next;
                }
            } else {
                // Odd data, add to the odd list
                if (oddHead == null) {
                    oddHead = head;
                    oddTail = head;
                } else {
                    oddTail.next = head;
                    oddTail = oddTail.next;
                }
            }
            head = head.next;
        }

        // Set the next of the tail nodes to null
        if (evenTail != null) {
            evenTail.next = null;
        }
        if (oddTail != null) {
            oddTail.next = null;
        }

        // Add odd list to the result
        result.add(oddHead);
        // Add even list to the result
        result.add(evenHead);

        return result;
    }
}
