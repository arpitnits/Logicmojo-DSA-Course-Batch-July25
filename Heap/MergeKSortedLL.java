package Heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLL {


    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    public ListNode mergeKLists(ListNode[] lists) {
        //Add all first node into PQ

        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(
                ((ListNode ln) -> ln.val))
        );


        //Create a PQ and add first K elements
        for (ListNode ls : lists) {
            if (ls != null) pq.add(ls);
        }

        ListNode head = null;
        ListNode curr = null;

        while (!pq.isEmpty()) {
            ListNode topNode = pq.poll();

            //add next element
            if (topNode.next != null) pq.add(topNode.next);

            if (head == null) head = topNode;
            else curr.next = topNode;

            curr = topNode;
        }
        return head;
    }
}
