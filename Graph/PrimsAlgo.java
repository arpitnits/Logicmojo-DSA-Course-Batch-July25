package Graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class PrimsAlgo {

    static class Node {
        int id;
        int weight;

        Node(int d, int w) {
            this.id = d;
            this.weight = w;
        }
    }

    private List<List<Node>> convertToUndirected(int V, int[][] edges) {
        List<List<Node>> adj = new ArrayList<>();
        for(int i=0;i<V;i++)    adj.add(new ArrayList<>());

        //(1-->2 , 3)
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            adj.get(u).add(new Node(v, w));
            adj.get(v).add(new Node(u, w));
        }

        return adj;
    }

    //O(VlogV + E*logV) --> Each vertex process its neighbour only once
    //TC = O(E * logV)
    public int spanningTree(int V, int[][] edges) {
        List<List<Node>> adj = convertToUndirected(V, edges);
        boolean[] vis = new boolean[V];

        //Sort in increasing order of weight
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.weight));

        //Process any node
        pq.add(new Node(0,0));
        int cost=0;
        int counter=0;

        while (!pq.isEmpty()) {
            Node curr = pq.poll(); //O(logV)
            if(vis[curr.id]) continue;

            vis[curr.id] = true;
            counter++;
            cost+= curr.weight;

            //Process non-visited neighbours
            for(Node neighbour : adj.get(curr.id)) {
                if(!vis[neighbour.id]) {
                    pq.add(new Node(neighbour.id, neighbour.weight)); //o(logV)
                }
            }
            if(counter==V)    break;
        }
        return cost;
    }
}
