package Tree;

import java.util.*;

public class VerticalTraversal {

    /*
    col --> [value of all row in that col and if there are multiple values in same col, row then sort]

    Map<Col , Row <List<Integer>>

    Now we want to keep things

    Map --> HashMap, TreeMap, LinkedHashMap
     */
    /*
    row = 0, col=0, curr =1

    0 --> 0 --> [1]

    row = 1, col=-1, curr =1
    -1 --> 1 --> [2]

    2--> left
    row=2, col = -2, curr =4
    -2 --> 2 --> [4]

    2-->right
    row=2, col=0, curr=5
    0 --> 0 --> [1]
      --> 2 --> [5]

    3-->left
    0 --> 0 --> [1]
      --> 2 --> [5,6]
    */

    private static void traversalHelper(TreeNode curr, int row, int col, Map<Integer, TreeMap<Integer, List<Integer>>> traversalMap) {
        if(curr==null) return;

        traversalMap.putIfAbsent(col, new TreeMap<>()); //handle no key for that col
        traversalMap.get(col).putIfAbsent(row, new ArrayList<>());

        traversalMap.get(col).get(row).add(curr.data);

        traversalHelper(curr.left, row+1, col-1, traversalMap);
        traversalHelper(curr.right, row+1, col+1, traversalMap);
    }

    public static List<List<Integer>> verticalTraverse(TreeNode root) {
        // Col --> (Row, List<Integer>)
        Map<Integer, TreeMap<Integer, List<Integer>>> traversalMap = new TreeMap<>();
        List<List<Integer>> result = new ArrayList<>();

        traversalHelper(root, 0, 0, traversalMap);

        for(Map.Entry<Integer, TreeMap<Integer, List<Integer>>> entry : traversalMap.entrySet()) {

            //Traverse rows
            List<Integer> tempTraversal = new ArrayList<>();
            for(List<Integer> values  : entry.getValue().values()) {
                Collections.sort(values);
                tempTraversal.addAll(values);
            }
            result.add(tempTraversal);
        }
        return result;
    }
}
