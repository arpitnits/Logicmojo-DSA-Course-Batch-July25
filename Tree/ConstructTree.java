package Tree;

public class ConstructTree {

    private static TreeNode constructUsingInorderAndPreOrderUtil(int[] preOrder, int[] inOrder,
                                                             int inStart, int inEnd, int preIndex) {

        if(inStart>inEnd) return null;

        TreeNode newNode = new TreeNode(preOrder[preIndex]);

        if(inStart==inEnd)  return newNode; //left Node

        int inIndex = searchIndex(inOrder, newNode.data, inStart, inEnd);
        newNode.left = constructUsingInorderAndPreOrderUtil(preOrder, inOrder, inStart, inIndex-1, preIndex+1);
        newNode.right = constructUsingInorderAndPreOrderUtil(preOrder, inOrder, inIndex+1, inEnd, preIndex+1);

        return newNode;
    }

    public static int searchIndex(int[] arr, int key, int start, int end) {
        for(int i=start;i<=end;i++) {
            if(arr[i]==key) return i;
        }
        return -1;
    }

    public static TreeNode constructUsingInorderAndPreOrder(int[] preOrder, int[] inOrder) {
        return constructUsingInorderAndPreOrderUtil(preOrder, inOrder, 0, inOrder.length-1, 0);
    }
}
