package Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class TopologicalSort {


    public int[] topologicalSort(int V, ArrayList<ArrayList<Integer>> adj) {
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

        int[] output = new int[V];
        int i=0;

        //Process each node, reduce neighbour inDegree and add to queue if indegree
        // of neighbour becomes zero
        while(!q.isEmpty()) {
            int curr = q.poll();
            output[i++] = curr;

            //Process neighbours
            for(int neighbours : adj.get(curr)) {
                inDegree[neighbours]--;

                if(inDegree[neighbours]==0) q.add(neighbours);
            }
        }

        //If not guarented for DAG, then check if all nodes are processed
        return output;
    }

    private void dfsHelper(int curr, Stack<Integer> s, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {
        vis[curr] = true;
        for(int neighbour : adj.get(curr)) {
            if(!vis[neighbour]) {
                dfsHelper(neighbour, s, adj, vis);
            }
        }
        s.push(curr);
    }


    public int[] topologicalSortDFS(int V, ArrayList<ArrayList<Integer>> adj) {
        Stack<Integer> s = new Stack<>();
        boolean[] vis = new boolean[V];

        for(int i=0;i<V;i++) {
            if(!vis[i]) dfsHelper(i, s, adj, vis);
        }

        int[] output = new int[V];
        int i=0;

        while (!s.isEmpty()) {
            output[i++] = s.pop();
        }
        return output;
    }
}
