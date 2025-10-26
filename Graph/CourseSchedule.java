package Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //prerequisites[i][1] --> prerequisites[i][0]

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0;i<numCourses;i++)   adj.add(new ArrayList<>());

        for (int[] prerequisite : prerequisites) {
            int u = prerequisite[1];
            int v = prerequisite[0];

            adj.get(u).add(v);
        }

        Queue<Integer> q = new ArrayDeque<>();
        int[] dependentCourses = new int[numCourses];

        for(int i=0;i<numCourses;i++) {
            for(int neighbour : adj.get(i)) {
                dependentCourses[neighbour]++;
            }
        }

        //push with 0 dependency
        for(int i=0;i<numCourses;i++) {
            if(dependentCourses[i]==0)  q.add(i);
        }

        int totalNode=0;

        while(!q.isEmpty()) {
            int curr = q.poll();
            totalNode++;

            for(int neighbour : adj.get(curr)) {
                dependentCourses[neighbour]--;
                if(dependentCourses[neighbour]==0)  q.add(neighbour);
            }
        }

        return totalNode == numCourses;
    }
}
