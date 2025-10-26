package Graph;

import java.util.ArrayDeque;
import java.util.Queue;

public class RottenOranges {


    class Block {
        int row;
        int col;

        Block(int r, int c) {
            this.row = r;
            this.col = c;
        }
    }

    int[] rowInc = {0,0,1,-1};
    int[] colInc = {1,-1,0,0};

    private boolean isSafe(int r, int c, int m, int n, int[][] grid) {
        return r>=0 && r<m && c>=0 && c<n && grid[r][c]==1;
    }

    public int orangesRotting(int[][] grid) {

        Queue<Block> q = new ArrayDeque<>();

        int m = grid.length;
        int n = grid[0].length;

        //boolean[][] vis = new boolean[m][n];

        //Fill all rotten oranges into queue
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j]==2) {
                    q.add(new Block(i, j));
                    //vis[i][j] = true;
                }
            }
        }

        //Process each and every rotten oranges
        while(!q.isEmpty()) {
            Block curr = q.poll();

            //check all neighbour -- 4 directions
            for(int k=0;k<4;k++) {
                int nextRow = curr.row + rowInc[k];
                int nextCol = curr.col + colInc[k];

                if(isSafe(nextRow, nextCol, m, n, grid)) {
                    q.add(new Block(nextRow, nextCol));
                    //vis[nextRow][nextCol] = true;
                    grid[nextRow][nextCol] = grid[curr.row][curr.col] + 1;
                }
            }
        }

        int maxMinutes = 0;

        //Verify whether any fresh oranges is remaining
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j]==1)   return -1; //Fresh Oranges exist

                maxMinutes = Math.max(maxMinutes, grid[i][j]);
            }
        }

        return maxMinutes>0 ? maxMinutes-2 : 0;
    }
}
