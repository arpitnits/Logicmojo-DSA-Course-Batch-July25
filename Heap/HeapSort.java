package Heap;

import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;

public class HeapSort {

    public void heapSort(int[] arr, int n) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); //increase
        PriorityQueue<Integer> pq1 = new PriorityQueue<>(Collections.reverseOrder()); //descreasong

        //buildMinHeap
        MinHeap minHeap = new MinHeap(n);
        for(int x : arr) minHeap.insert(x);

        while(n>0) {
            System.out.println(minHeap.extractMinElement());
            n--;
        }
    }

}
