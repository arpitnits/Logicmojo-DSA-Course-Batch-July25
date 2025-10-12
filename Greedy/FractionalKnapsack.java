package Greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

public class FractionalKnapsack {

    class Item {
        int val;
        int weight;
        double valuePerUnit;

        Item(int v, int w) {
            this.val = v;
            this.weight = w;
            valuePerUnit = (v*1.0) / (w*1.0);
        }
    }

    //TC = O(NlogN)
    public double fractionalKnapsack(int[] val, int[] wt, int capacity) {
        //Decreasing Order of valuePerUnit
        PriorityQueue<Item> pq = new PriorityQueue<>(
                Comparator.comparingDouble((Item item) -> item.valuePerUnit).reversed()
        );

        for(int i=0;i<wt.length;i++) pq.add(new Item(val[i], wt[i])); //O(N logN)

        int totalProfit=0;
        // O(N)
        while(!pq.isEmpty()) {
            Item currItem = pq.poll(); //O(logN)

            int eligibleCapacity = Math.min(currItem.weight, capacity);
            totalProfit+= (currItem.valuePerUnit *  eligibleCapacity*1.0);

            capacity = capacity - eligibleCapacity;
        }
        return totalProfit;
    }
}
