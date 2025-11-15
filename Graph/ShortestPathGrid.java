package Graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class ShortestPathGrid {

    int[] rowInc = {-1,-1,-1, 0,0, 1,1,1};
    int[] colInc = {-1, 0, 1,-1,1,-1,0,1};

    class Block {
        int row;
        int col;

        Block(int r, int c) {
            this.row = r;
            this.col = c;
        }
    }

    private boolean isSafe(int row, int col, int n, int m, int[][] grid) {
        return row>=0 && row<n && col>=0 && col<m && grid[row][col]==0;
    }
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] dis = new int[n][m];
        for(int[] d : dis) Arrays.fill(d, Integer.MAX_VALUE);

        Queue<Block> q = new ArrayDeque<>();

        if(grid[0][0] == 0) {
            dis[0][0] = 0;
            q.add(new Block(0,0));
        }

        while(!q.isEmpty()) {
            Block block = q.poll();

            //Explore all 8 directions
            for(int k=0;k<8;k++) {
                int nextRow = block.row + rowInc[k];
                int nextCol = block.col + colInc[k];


                if(isSafe(nextRow, nextCol, n, m, grid)) {
                    int newDis = dis[block.row][block.col] + 1;
                    int oldDis = dis[nextRow][nextCol];

                    if(newDis < oldDis) {
                        q.add(new Block(nextRow, nextCol));
                        dis[nextRow][nextCol] = newDis;
                    }
                }
            }
        }

        return dis[n-1][m-1];
    }
}
