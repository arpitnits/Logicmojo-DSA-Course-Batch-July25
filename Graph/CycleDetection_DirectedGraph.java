package Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class CycleDetection_DirectedGraph {

    private boolean isCyclic(int curr, ArrayList<ArrayList<Integer>> adj, boolean[] vis, boolean[] rec) {
        vis[curr] = rec[curr] = true;

        for(int neighbour : adj.get(curr)) {
            if(!vis[neighbour]) {
                if(isCyclic(neighbour, adj, vis, rec)) return true; //If cycle detected return immediately
            } else if(rec[neighbour]) {
                //If already visited and part of path
                return true;
            }
        }
        rec[curr] = false;
        return false;
    }

    public boolean detectCycleDFS(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] vis = new boolean[V];
        boolean[] rec = new boolean[V];

        for(int i=0;i<V;i++) {
            if(!vis[i]) {
                if(isCyclic(i, adj, vis, rec)) return true;
            }
        }
        return false;
    }


    public boolean isCyclicBFS(int V, ArrayList<ArrayList<Integer>> adj) {
        Queue<Integer> q = new ArrayDeque<>();

        //Create an inDegree array
        int[] inDegree = new int[V];
        for(int i=0;i<V;i++) {
            // i--> neighbour
            for(int neighbour : adj.get(i)) {
                inDegree[neighbour]++;
            }
        }

        //Pick all node with 0 inDegree
        for(int i=0;i<V;i++) {
            if(inDegree[i]==0)  q.add(i);
        }

        //Process each node, reduce neighbour inDegree and add to queue if indegree
        // of neighbour becomes zero
        while(!q.isEmpty()) {
            int curr = q.poll();

            //Process neighbours
            for(int neighbours : adj.get(curr)) {
                inDegree[neighbours]--;

                if(inDegree[neighbours]==0) q.add(neighbours);
            }
        }

        //If not guarented for DAG, then check if all nodes are processed
        for(int i=0;i<V;i++){
            if(inDegree[i]!=0) return true;
        }

        return false;
    }
}
