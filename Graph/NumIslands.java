package Graph;

import java.util.ArrayList;

public class NumIslands {

    static int[] rowInc = {0,0,-1,1};
    static int[] colInc = {-1,1,0,0};

    private static boolean isSafe(int row, int col, int n, int m, char[][] grid) {
        return row>=0 && col>=0 && row<n && col<m && grid[row][col]=='1';
    }

    private static void dfsRecursive(int row, int col, int n, int m, char[][] grid, boolean[][] vis) {
        vis[row][col] = true;

        //Visit all 4 neighbours
        for(int k=0;k<4;k++) {
            int nextRow = row + rowInc[k];
            int nextCol = col + colInc[k];

            if(!vis[nextRow][nextCol] &&
                    isSafe(nextRow, nextCol, n, m, grid)) {
                dfsRecursive(nextRow, nextCol, n, m, grid, vis);
            }
        }
    }

    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        boolean[][] vis = new boolean[n][m];

        int component =0;

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(!vis[i][j] && grid[i][j]=='1') {
                    component++;
                    dfsRecursive(i, j, n, m, grid, vis);
                }
            }
        }
        return component;
    }

    private static void dfsRecursiveWithoutExtraSpace(int row, int col, int n, int m, char[][] grid) {
        grid[row][col] = '2';

        //Visit all 4 neighbours
        for(int k=0;k<4;k++) {
            int nextRow = row + rowInc[k];
            int nextCol = col + colInc[k];

            if(isSafe(nextRow, nextCol, n, m, grid)) {
                dfsRecursiveWithoutExtraSpace(nextRow, nextCol, n, m, grid);
            }
        }
    }

    public int numIslandsWithoutExtraSpace(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int component =0;

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(grid[i][j]=='1') {
                    component++;
                    dfsRecursiveWithoutExtraSpace(i, j, n, m, grid);
                }
            }
        }
        return component;
    }
}

