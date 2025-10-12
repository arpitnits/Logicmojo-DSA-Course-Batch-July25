package Heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class NearlySorted {

    public void nearlySorted(int[] arr, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>(); //Default is increasing - MinHeap
        int i=0, n =arr.length;
        for( ;i<=k;i++) {
            pq.add(arr[i]);
        }

        for( ;i<n;i++) {
            System.out.println(pq.poll());
            pq.add(arr[i]);
        }

        /*

        arr[] = [6, 5, 3, 2, 8, 10, 9], k = 3
         */
    }
}
