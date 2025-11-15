package Graph;

import java.util.ArrayList;
import java.util.Arrays;

public class BellmanFord {

    /*
    Relax (V-1) times to calculate min distance to the farthest node
    If distance changes in Vth iteration --> negative cycle
     */
    private static ArrayList<ArrayList<ShortestPath.Node>> convertToAdj(int n, int[][] edges) {
        ArrayList<ArrayList<ShortestPath.Node>> adj = new ArrayList<>();
        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        //[u,v,d] : u--->v(distance = d)
        // v-->u (d)
        for(int[] edge : edges) {
            adj.get(edge[0]).add(new ShortestPath.Node(edge[1], edge[2]));
            adj.get(edge[1]).add(new ShortestPath.Node(edge[0], edge[2]));
        }
        return adj;
    }

    public static int[] bellmanFord(int V, int[][] edges, int src) {
        ArrayList<ArrayList<ShortestPath.Node>> adj = convertToAdj(V, edges);

        int[] dis = new int[V];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[src] = 0;

        //V-1
        for(int i=0;i<V-1;i++) {
            //Process each edge --> O(E)
            for(int node=0;node<V;node++) {
                for(ShortestPath.Node neighbour : adj.get(node)) {
                    if(dis[node]!=Integer.MAX_VALUE &&
                            dis[node] + neighbour.weight < dis[neighbour.id]) {
                        dis[neighbour.id] = dis[node] + neighbour.weight;
                    }
                }
            }
        }

        //Process Nth Time
        for(int node=0;node<V;node++) {
            for(ShortestPath.Node neighbour : adj.get(node)) {
                if(dis[node]!=Integer.MAX_VALUE &&
                        dis[node] + neighbour.weight < dis[neighbour.id]) {
                    Arrays.fill(dis, -1);
                    return dis;
                }
            }
        }
        return dis;
    }
}









