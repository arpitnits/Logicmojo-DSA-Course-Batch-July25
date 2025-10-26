package Graph;

import java.util.ArrayList;

public class CycleDetection_UndirectedGraph {

    private boolean isCyclic(int curr, int parent, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {
        vis[curr] = true;

        for(int neighbour : adj.get(curr)) {
            if(!vis[neighbour]) {
                if(isCyclic(neighbour, curr, adj, vis)) return true; //If cycle detected return immediately
            } else if(neighbour != parent) {
                //If already visited and not parent
                return true;
            }
        }
        return false;
    }

    public boolean detectCycleDFS(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] vis = new boolean[V];

        for(int i=0;i<V;i++) {
            if(!vis[i]) {
                if(isCyclic(i, -1, adj, vis)) return true;
            }
        }
        return false;
    }

    /*
    For BFS

    Maintain parentArray or keep (node,parent) into queue to achieve similar goal
     */
}
