package Graph;

import java.util.*;

public class Traversal {

    //TC = O(V+E)
    //Because we are visiting each node and edge only once
    public static ArrayList<Integer> bfs(int V, ArrayList<ArrayList<Integer>> adj) {
        int startNode = 0;
        ArrayList<Integer> output = new ArrayList<>();
        boolean[] vis = new boolean[V];

        Queue<Integer> q = new LinkedList<>();
        q.add(startNode);
        vis[startNode] = true;

        while (!q.isEmpty()) {

            int currNode = q.poll();
            output.add(currNode);

            for(int node : adj.get(currNode)) {
                if(!vis[node]) {
                    q.add(node);
                    vis[node] = true;
                }
            }
        }
        return output;
    }

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

    public static ArrayList<Integer> dfs(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] vis = new boolean[V];
        ArrayList<Integer> output = new ArrayList<>();

        //This loop is needed for disconnected components
        for(int v=0;v<V;v++) {
            if(!vis[v]) {
                dfsRecursive(v, adj, output, vis);
            }
        }
        return output;
    }

    public static void main(String[] args) {
        int V = 4;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0;i<V;i++)    adj.add(new ArrayList<>());

        //0--> 1,2
        adj.get(0).add(1);
        adj.get(0).add(2);

        //1 --> 0,3
        adj.get(1).add(0);
        adj.get(1).add(3);

        //2 --> 1
        adj.get(2).add(1);

        //3-->0
        adj.get(3).add(0);

        System.out.println(dfs(V, adj));
    }
}
