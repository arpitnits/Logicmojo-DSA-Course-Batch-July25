package Graph;

import java.util.*;

public class ShortestPath {

    static class Node {
        int id;
        int weight;

        Node(int id, int w) {
            this.id = id;
            this.weight = w;
        }
    }

    private ArrayList<ArrayList<Node>> convertToAdj(int n, int[][] edges) {
        ArrayList<ArrayList<Node>> adj = new ArrayList<>();
        for(int i=0;i<n;i++) adj.add(new ArrayList<>());

        //[u,v,d] : u--->v(distance = d)
        // v-->u (d)
        for(int[] edge : edges) {
            adj.get(edge[0]).add(new Node(edge[1], edge[2]));
            adj.get(edge[1]).add(new Node(edge[0], edge[2]));
        }
        return adj;
    }

    //TC=O(V*E) --> reprocessing same node multiple times
    public List<Integer> shortestPath(int n, int m, int[][] edges, int src) {
        ArrayList<ArrayList<Node>> adj = convertToAdj(n, edges);

        List<Integer> dis = new ArrayList<>();
        for(int i=0;i<n;i++)    dis.add(i, Integer.MAX_VALUE);
        dis.set(src, 0);

        Queue<Integer> q = new ArrayDeque<>();
        q.add(src);
        while (!q.isEmpty()) {
            int currNodeID = q.poll();
            //Process all neighbours
            for(Node node : adj.get(currNodeID)) {
                int existingDistance = dis.get(node.id);
                int newDistance = dis.get(currNodeID) + node.weight;

                if(newDistance < existingDistance) {
                    dis.set(node.id, newDistance);
                    q.add(node.id);
                }
            }
        }
        return dis;
    }

    // TC = O(V+E)
    public List<Integer> shortestPathDAG(int n, int m, int[][] edges, int src) {
        ArrayList<ArrayList<Node>> adj = convertToAdj(n, edges);

        List<Integer> dis = new ArrayList<>();
        for(int i=0;i<n;i++)    dis.add(i, Integer.MAX_VALUE);
        dis.set(src, 0);

        int[] sortedTopologicalOrder = topologicalSortWithWeight(n, adj);

        for(int i=0;i<n;i++) {
            int currNodeID = sortedTopologicalOrder[i];
            //Process all neighbours
            for(Node node : adj.get(currNodeID)) {
                int existingDistance = dis.get(node.id);
                int newDistance = dis.get(currNodeID) + node.weight;

                if(newDistance < existingDistance) {
                    dis.set(node.id, newDistance);
                }
            }
        }
        return dis;
    }

    public int[] topologicalSortWithWeight(int V, ArrayList<ArrayList<Node>> adj) {
        Queue<Integer> q = new ArrayDeque<>();

        //Create an inDegree array
        int[] inDegree = new int[V];
        for(int i=0;i<V;i++) {
            // i--> neighbour
            for(Node node : adj.get(i)) {
                inDegree[node.id]++;
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
            for(Node node : adj.get(curr)) {
                inDegree[node.id]--;

                if(inDegree[node.id]==0) q.add(node.id);
            }
        }

        //If not guarented for DAG, then check if all nodes are processed
        return output;
    }

}
