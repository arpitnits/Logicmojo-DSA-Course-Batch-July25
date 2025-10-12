package Tree;

public class LCA {

    /*
    If both the left and right are not null --> current node will be LCA
     */
    public TreeNode lcaBT(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null)  return null;

        if(root==p || root==q)  return root;

        TreeNode leftLCA = lcaBT(root.left, p, q);
        TreeNode rightLCA = lcaBT(root.right, p, q);

        if(leftLCA!=null && rightLCA!=null) return root;

        return (leftLCA!=null) ? leftLCA : rightLCA;
    }

    public TreeNode lcaBST(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null) return null;

        //both lies on left side --> less than current node
        if(p.data < root.data && q.data < root.data)
            return lcaBST(root.left, p, q);
        if(p.data > root.data && q.data > root.data)
            return lcaBST(root.right, p, q);

        return root; //when either of both lies on different sides
    }
}
