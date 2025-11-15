package Trie;

import java.util.ArrayList;
import java.util.List;

public class WordSearchII {
    static int ALPHABET_SIZE = 26;
    static TrieNode root;

    static class TrieNode {
        TrieNode[] child = new TrieNode[ALPHABET_SIZE];
        String word;

        TrieNode() {
            for(int i=0;i<ALPHABET_SIZE;i++)    child[i] = null;
            word = null;
        }
    }


    public static void insert(String key) {
        TrieNode curr = root;
        int index;
        for(char ch : key.toCharArray()) {
            index = ch - 'a';
            //If the character not exist already in trie
            if(curr.child[index]==null) {
                //create a new Node
                curr.child[index] = new TrieNode();
            }
            curr = curr.child[index];
        }
        curr.word = key; //mark endOfWord
    }

    private void convertToTrie(String[] words) {
        root = new TrieNode();
        for(String key : words) {
            insert(key);
        }
    }

    int[] rowInc = {1,-1,0,0};
    int[] colInc = {0,0,1,-1};

    List<String > ans = new ArrayList<>();

    public void dfs(char[][] board, int i, int j, int m, int n, TrieNode curr, boolean[][] vis) {
       int index = board[i][j] - 'a';

        //If already visited
        //That character doesn't exist in trie
        if(vis[i][j] || curr.child[index]==null) return;

        curr = curr.child[index];

        if(curr.word!=null) {
            //found
            ans.add(curr.word);
            curr.word = null;
        }

        vis[i][j] = true;
        //Checking all 4 direction for next character
        for(int k=0;k<4;k++) {
            int nextRow = i + rowInc[k];
            int nextCol = j + colInc[k];

            if(isSafe(nextRow, nextCol, m, n, vis)) {
                dfs(board, nextRow, nextCol, m, n, curr, vis);
            }
        }
        vis[i][j] = false;
    }

    private boolean isSafe(int r, int c, int m, int n, boolean[][] vis) {
        return r>0 && r<m && c>0 && c<n && !vis[r][c];
    }

    public List<String> findWords(char[][] board, String[] words) {
        //create Trie for allWords
        convertToTrie(words);

        int m = board.length;
        int n = board[0].length;

        boolean[][] vis = new boolean[m][n];

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                dfs(board, i, j, m, n, root, vis);
            }
        }
        return ans;
    }
}
