package Graph;

import java.util.ArrayList;

public class ConnectedComponents {

    //TC = O(V+E)
    private static void dfsRecursive(int curr, ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> output, boolean[] vis) {
        output.add(curr);
        vis[curr] = true;

        for(int neighbour : adj.get(curr)) {
            if(!vis[neighbour]) {
                dfsRecursive(neighbour, adj, output, vis);
            }
        }
    }

    public static int countConnectedComponent(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] vis = new boolean[V];
        ArrayList<Integer> output = new ArrayList<>();
        int counter=0;

        for(int v=0;v<V;v++) {
            if(!vis[v]) {
                counter++;
                dfsRecursive(v, adj, output, vis);
            }
        }
        return counter;
    }
}
