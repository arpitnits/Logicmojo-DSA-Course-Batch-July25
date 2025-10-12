package Tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class dummy {

    class Job {
        int profit;
        int deadline;

        Job(int p, int d) {
            profit = p;
            deadline = d;
        }
    }
    public ArrayList<Integer> jobSequencing(int[] deadline, int[] profit) {
        // code here
        PriorityQueue<Job> pq = new PriorityQueue<>(
                Comparator.comparingInt((Job job) -> job.profit).reversed().thenComparing(job -> job.deadline).reversed()
        );

        int maxDeadline = 0;
        for(int i=0;i<profit.length;i++) {
            pq.add(new Job(profit[i], deadline[i]));
            maxDeadline = Math.max(maxDeadline, deadline[i]);
        }

        int totalProfit =0;
        boolean[] slots = new boolean[maxDeadline+1];
        int slotOccupied=0;

        while(!pq.isEmpty()) {
            Job currJob = pq.poll();
            int currDeadline = currJob.deadline;
            while(currDeadline>0) {
                if(slots[currDeadline]==false) {
                    //slot available
                    slots[currDeadline] = true;
                    slotOccupied++;

                    totalProfit+= currJob.profit;
                }
                currDeadline--;
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(slotOccupied);
        ans.add(totalProfit);

        return ans;
    }

}
