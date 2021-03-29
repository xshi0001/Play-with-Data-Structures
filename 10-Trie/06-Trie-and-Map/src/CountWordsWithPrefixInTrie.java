import java.util.TreeMap;

/**
 * TODO
 *
 * @author JClearLove
 * @date 2021/03/25 22:22
 */

public class CountWordsWithPrefixInTrie {

    private class TrieNode {
        // Using map to store the pointers
        // of children nodes for dynamic
        // implementation, for making the
        // program space efiicient
        TreeMap<Character, TrieNode> next;
        // if isEndOfWord is true,then node represents end of  word
        boolean isEndOfWord;
        // num represents number of times a character has appeared during insertion
        // of the words in the trie
        TreeMap<Character, Integer> num;

        public TrieNode() {
            this.next = new TreeMap<>();
            this.isEndOfWord = false;
            this.num = new TreeMap<>();
        }
    }

    private TrieNode root;

    public CountWordsWithPrefixInTrie() {
        this.root = new TrieNode();
    }

    public void insertWord(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new TrieNode());
                cur.num.put(c, 1);
            } else {
                cur.num.put(c, cur.num.get(c) + 1);
            }
            cur = cur.next.get(c);
        }
        cur.isEndOfWord = true;

    }

    public int countWords(String[] words, String prefix) {
        for (int i = 0; i < words.length; i++) {
            insertWord(words[i]);
        }
        TrieNode cur = root;
        int wordCount = 0;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) != null) {
                wordCount = cur.num.get(c);
            }
            cur = cur.next.get(c);
        }
        return wordCount;
    }

    public static void main(String[] args) {
        CountWordsWithPrefixInTrie countWordsWithPrefixInTrie = new CountWordsWithPrefixInTrie();
        String[] strings = {"apk", "app", "apple",
                "arp", "array"};

        int i = countWordsWithPrefixInTrie.countWords(strings, "ap");
        System.out.println("i = " + i);
    }
}

