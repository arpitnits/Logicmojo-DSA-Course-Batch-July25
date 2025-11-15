package Graph;

import java.util.*;

public class KrushkalAlgo {

    static class Edge {
        int weight;
        int src;
        int dest;

        Edge(int w, int s, int d) {
            this.weight = w;
            this.src = s;
            this.dest = d;
        }
    }

    //O(E*log E + E*logV) = O(E *logE)
    public int spanningTree(int V, int[][] edges) {
        //Sort in increasing order of weight
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.weight));
        for(int[] edge : edges) {
            pq.add(new Edge(edge[2], edge[0], edge[1]));
        }
        //TC = O(E * log E)

        UnionRank unionRank = new UnionRank(V);
        int cost=0, counter =0;

        //TC = O(E * logV)
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int l1 = unionRank.find(edge.src); //O(log V)
            int l2 = unionRank.find(edge.dest); //O(log V)

            //If both belongs to different set, then accept this edge
            if(l1 != l2) {
                unionRank.union(l1, l2); //O(log V)
                cost+= edge.weight;
                counter++;
            }

            if(counter==V-1) break;
        }
        return cost;
    }

}
