package Heap;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class KLargest {

    int findKthLargest(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); //min heap
        if(arr.length==0)   return -1;

        for(int num : arr) {
            pq.add(num);
            if(pq.size()>k)     pq.poll(); //removing min element out of this K element
        }

        //In the end, we have K largest element of the array
        return pq.peek();
    }
}
