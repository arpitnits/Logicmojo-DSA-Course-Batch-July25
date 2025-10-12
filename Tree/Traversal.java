package Tree;

import java.util.*;

public class Traversal {

    static List<Integer> path = new ArrayList<>();

    /*
    TC : O(N)
    SC : O(N) - because of Queue
     */
    public static List<Integer> levelOrder(TreeNode root) {
        path = new ArrayList<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);

        while(!q.isEmpty()) {
            TreeNode currNode = q.poll(); //retrieve and remove
            if(currNode.left!=null) q.add(currNode.left);
            if(currNode.right!=null) q.add(currNode.right);

            path.add(currNode.data);
        }
        return path;
    }

    //DLR
    public static void preOrder(TreeNode curr) {
        if(curr==null) return;

        path.add(curr.data); // D
        preOrder(curr.left); // L
        preOrder(curr.right); // R
    }

    //LDR
    public static void inOrder(TreeNode curr) {
        if(curr==null) return;

        inOrder(curr.left); // L
        path.add(curr.data); // D
        inOrder(curr.right); // R
    }

    //LRD
    public static void postOrder(TreeNode curr) {
        if(curr==null) return;

        postOrder(curr.left); // L
        postOrder(curr.right); // R
        path.add(curr.data); // D
    }

    //DFS
    /*
    TC: O(N)
    SC: O(H) --> Height of Tree --> At max we will have H node in recursive stack
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(6);

        root.left = node1;
        root.right = node2;

        TreeNode node3 = new TreeNode(31);
        TreeNode node4 = new TreeNode(61);
        TreeNode node5 = new TreeNode(51);

        node1.left = node3;
        node2.left = node4;
        node2.right = node5;

        path = new ArrayList<>();
        preOrder(root);
        System.out.println("PreOrder:" +path);


        path = new ArrayList<>();
        inOrder(root);
        System.out.println("InOrder:" + path);


        path = new ArrayList<>();
        postOrder(root);
        System.out.println("PostOrder:" +path);
    }
}
