package Trie;

public class Trie {
    static int ALPHABET_SIZE = 26;
    static TrieNode root;

    static class TrieNode {
        TrieNode[] child = new TrieNode[ALPHABET_SIZE];
        boolean isEnd;

        TrieNode() {
            for(int i=0;i<ALPHABET_SIZE;i++)    child[i] = null;
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
        curr.isEnd = true; //mark endOfWord
    }

    public static boolean search(String key) {
        TrieNode curr = root;
        int index;
        for(char ch : key.toCharArray()) {
            index = ch - 'a';
            if(curr.child[index]==null) return false;

            curr = curr.child[index];
        }
        return curr.isEnd; //last character of string is endOfWord
    }


    public static void main(String[] args) {
        String[] keys = {"and", "ant", "dad", "do"};

        root = new TrieNode();
        for(String key : keys) insert(key);

        System.out.println("an exist:" + search("an"));
        System.out.println("and exist:"  + search("and"));
    }
}
