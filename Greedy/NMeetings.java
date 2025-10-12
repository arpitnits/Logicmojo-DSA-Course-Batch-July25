package Greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

public class NMeetings {

    class Schedule {
        int start;
        int end;

        Schedule(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }
    public int maxMeetings(int start[], int end[]) {
        //Sort in increasing order of end time
        PriorityQueue<Schedule> pq = new PriorityQueue<>(
                Comparator.comparingInt((Schedule sc)-> sc.end)
        );

        for(int i=0;i<start.length;i++)     pq.add(new Schedule(start[i], end[i]));

        int totalMeetings =0;
        int e=-1;

        while (!pq.isEmpty()) {
            Schedule sc = pq.poll();

            if(sc.start > e) {
                totalMeetings++;
                e=sc.end;
            }
        }
        return totalMeetings;
    }
}
