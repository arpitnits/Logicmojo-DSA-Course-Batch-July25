package Greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class JobScheduling {

    class Job {
        int profit;
        int deadline;

        Job(int p, int d) {
            this.profit = p;
            this.deadline = d;
        }
    }

    //Another way of overriding the comparator
    class ProfitComparator implements Comparator<Job> {
        @Override
        public int compare(Job j1, Job j2) {
            return j2.profit - j1.profit;
        }
    }

    public ArrayList<Integer> jobSequencing(int[] deadline, int[] profit) {

        //Sort on the basis of decreasing profit
        PriorityQueue<Job> pq = new PriorityQueue<>(
                Comparator.comparingInt((Job job) -> job.profit).reversed()
        );

        int maxDeadline=0;
        for(int i=0;i<profit.length;i++) {
            pq.add(new Job(profit[i], deadline[i])); //logN
            maxDeadline = Math.max(maxDeadline, deadline[i]);
        }

        boolean[] slots = new boolean[maxDeadline+1];
        int totalProfit = 0;
        int slotOccupied = 0;

        //O(N)
        while (!pq.isEmpty()) {
            Job currJob = pq.poll();
            int currDeadline = currJob.deadline;

            //O(MaxDeadline)
            while (currDeadline>0) {
                if(!slots[currDeadline]) { //Slot is available
                    slots[currDeadline] = true;
                    totalProfit+= currJob.profit;
                    slotOccupied++;

                    break;
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
