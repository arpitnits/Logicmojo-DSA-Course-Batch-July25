package Graph;

public class DisjointSet_Naive {


    private int[] parent;

    DisjointSet_Naive(int N) {
        parent = new int[N];
    }

    public int find(int v) {
        if(parent[v]==-1) return v;
        return find(parent[v]);
    }

    public void union(int a, int b) {
        int l1 = find(a);
        int l2 = find(b);

        //If belongs to different set
        if(l1 != l2) {
            //combine both
            parent[l1] = l2;
        }
    }
}
