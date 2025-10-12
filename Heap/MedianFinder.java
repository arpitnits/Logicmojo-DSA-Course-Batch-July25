package Heap;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianFinder {

    PriorityQueue<Integer> left;
    PriorityQueue<Integer> right;

    public MedianFinder() {
        left = new PriorityQueue<>(Collections.reverseOrder());//Max Heap
        right = new PriorityQueue<>();//Min Heap
    }

    public void addNum(int num) {
        if(left.isEmpty() || num < left.peek()) left.add(num);
        else right.add(num);

        if(left.size() - right.size() > 1) right.add(left.poll());
        else if(right.size() - left.size() > 1) left.add(right.poll());
    }
    class Item {
        int val;
        int weight;
        double profitPerUnit;

        Item(int val, int w) {
            this.val = val;
            this.weight = w;
            profitPerUnit = (val*1.0)/(weight*1.0);
        }
    }

    public double findMedian() {
        if(left.isEmpty())  return -1;

        if(left.size()==right.size())  return (left.peek() + right.peek())/2.0;
        else if(left.size() > right.size()) return left.peek()*1.0;
        else return right.peek()*1.0;
    }

    class Room {
        int roomId;
        int meetingEnd;
        int count;

        Room(int id) {
            this.roomId=id;
            meetingEnd = -1;
            count = 0;
        }
    }

    class Meeting {
        int start;
        int end;

        Meeting(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }

    /*
    [[0,10],[1,5],[2,7],[3,4]]

    rooms =
        [0] --> 10, 1
        [1]
     */
    public int mostBooked(int n, int[][] meetings) {
        PriorityQueue<Room> roomsPQ = new PriorityQueue<>((a, b) -> {
            // Step 1: both available = 1
            if (a.meetingEnd == b.meetingEnd) {
                return a.roomId - b.roomId; // sort by roomId ascending
            }

            // Step 3: both available = 0
            return a.meetingEnd - b.meetingEnd; // sort by meetingEnd ascending
        });

        //Sort by start increase, end increasing
        PriorityQueue<Meeting> meetingPQ = new PriorityQueue<>(
                Comparator.comparingInt((Meeting m)  -> m.start).thenComparing(m -> m.end)
        );

        for(int i=0;i<meetings.length;i++) {
            meetingPQ.add(new Meeting(meetings[i][0], meetings[i][1]));
        }

        //Create n empty rooms
        for(int i=0;i<n;i++) {
            roomsPQ.add(new Room(i));
        }

        while(!meetingPQ.isEmpty()) {
            Meeting meeting = meetingPQ.poll();

            Room room = roomsPQ.poll();
            room.meetingEnd = meeting.end;
            room.count = room.count +1;
            roomsPQ.add(room);
        }

        int mostUsed=0;
        while(!roomsPQ.isEmpty()) {
            Room room = roomsPQ.poll();
            mostUsed = Math.max(mostUsed, room.count);
        }

        return mostUsed;
    }
}
