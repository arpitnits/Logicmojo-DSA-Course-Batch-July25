package Graph;

public class UnionRank {

    private Node[] nodes;

    UnionRank(int N) {
        nodes = new Node[N];
    }

    class Node {
        int parent;
        int rank;

        Node(int p, int r) {
            this.parent = p;
            this.rank = r;
        }
    }

    public int find(int v) {
        if(nodes[v].parent==-1) return v;
        return nodes[v].parent = find(nodes[v].parent);
    }

    public void union(int a, int b) {
        int l1 = find(a);
        int l2 = find(b);

        //If belongs to different set
        if(l1 != l2) {
            if(nodes[l1].rank > nodes[l2].rank) {
                nodes[l2].parent = l1;
            } else if (nodes[l2].rank > nodes[l1].rank) {
                nodes[l1].parent = l2;
            } else {
                nodes[l1].parent = l2;
                nodes[l2].rank++;
            }
        }
    }
}
