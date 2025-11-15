package Graph;

import java.util.*;

public class DijkstraAlgo {

    private ArrayList<ArrayList<ShortestPath.Node>> convertToAdj(int n, int[][] edges) {
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

    //TC=O((V+E)*logV) = O(E * logV)
    //Fail for Negative Cycle
    public List<Integer> dijkstraAlgo(int n, int m, int[][] edges, int src) {
        ArrayList<ArrayList<ShortestPath.Node>> adj = convertToAdj(n, edges);

        List<Integer> dis = new ArrayList<>();
        for(int i=0;i<n;i++)    dis.add(i, Integer.MAX_VALUE);
        dis.set(src, 0);

        PriorityQueue<ShortestPath.Node> q = new PriorityQueue<>(Comparator.comparingInt(node -> node.weight)); //ASC of weight
        q.add(new ShortestPath.Node(src, 0));
        while (!q.isEmpty()) {
            int currNodeID = q.poll().id;
            //Process all neighbours
            for(ShortestPath.Node node : adj.get(currNodeID)) {
                int existingDistance = dis.get(node.id);
                int newDistance = dis.get(currNodeID) + node.weight;

                if(newDistance < existingDistance) {
                    dis.set(node.id, newDistance);
                    q.add(new ShortestPath.Node(node.id, newDistance));
                }
            }
        }
        return dis;
    }
}
