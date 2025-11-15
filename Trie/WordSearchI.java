package Trie;

public class WordSearchI {

    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;

        for(int i=0;i<rows;i++) {
            for(int j=0;j<cols;j++) {
                if(dfs(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    int[] rowInc = {1,-1,0,0};
    int[] colInc = {0,0,1,-1};

    private boolean dfs(char[][] board, int i, int j, String word, int index) {
        //terminating condition --> word is over
        if (index == word.length()) return true;
        if(board[i][j] != word.charAt(index))  return false;
        boolean found = false;
        char temp = board[i][j];
        board[i][j] = '#'; //to mark this block as visited

        for(int k=0;k<4;k++) {
            if(isSafe(i+rowInc[k], j+colInc[k], board.length, board[0].length, board) &&
                    dfs(board, i+rowInc[k], j+colInc[k], word, index+1)) {
                return true;
            }
        }
        board[i][j] = temp;
        return found;
    }

    private boolean isSafe(int r, int c, int m, int n, char[][] board) {
        return r>0 && r<m && c>0 && c<n && board[r][c]!='#';
    }
}
