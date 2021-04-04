//Trie (we pronounce "try") or prefix tree is a tree data structure used to retr
//ieve a key in a strings dataset. There are various applications of this very eff
//icient data structure, such as autocomplete and spellchecker.
//
// Implement the Trie class:
//
//
// Trie() initializes the trie object.
// void insert(String word) inserts the string word to the trie.
// boolean search(String word) returns true if the string word is in the trie (i
//.e., was inserted before), and false otherwise.
// boolean startsWith(String prefix) returns true if there is a previously inser
//ted string word that has the prefix prefix, and false otherwise.
//
//
//
// Example 1:
//
//
//Input
//["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
//[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
//Output
//[null, null, true, false, true, null, true]
//
//Explanation
//Trie trie = new Trie();
//trie.insert("apple");
//trie.search("apple");   // return True
//trie.search("app");     // return False
//trie.startsWith("app"); // return True
//trie.insert("app");
//trie.search("app");     // return True
//
//
//
// Constraints:
//
//
// 1 <= word.length, prefix.length <= 2000
// word and prefix consist of lowercase English letters.
// At most 3 * 104 calls will be made to insert, search, and startsWith.
//
// Related Topics 设计 字典树
// 👍 570 👎 0


import java.util.TreeMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Trie {
    public class Node {
        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root;
    private int size;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new Node();
        size = 0;
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        insert(root, word, 0);
    }

    // 向以node为根的trie树中添加从index开始的子串(word[index...end])
    private void insert(Node node, String word, int index) {
        if (index == word.length()) {
            if (!node.isWord) {
                node.isWord = true;
                size++;
            }
            return;
        }
        char c = word.charAt(index);
        if (node.next.get(c) == null) {
            node.next.put(c, new Node());
        }
        insert(node.next.get(c), word, index + 1);
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {

        return search(root, word, 0);
    }

    // 在以node为根的trie树汇总查询是否存在以index开始的子串(word[index...end])
    private boolean search(Node node, String word, int index) {
        if (index == word.length()) {
            return node.isWord;
        }
        char c = word.charAt(index);
        if (node.next.get(c) == null) {
            return false;
        } else {
            return search(node.next.get(c), word, index + 1);
        }
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        return startsWith(root, prefix, 0);
    }

    private boolean startsWith(Node node, String prefix, int index) {
        if (index == prefix.length()) {
            //一定是返回true，因为不一定是单词
            return true;
        }
        char c = prefix.charAt(index);
        if (node.next.get(c) == null) {
            return false;
        } else {

            return startsWith(node.next.get(c), prefix, index + 1);
        }

    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println("trie.search(\"apple\") = " + trie.search("apple"));
        System.out.println("trie.search(\"app\") = " + trie.search("app"));
        System.out.println("trie.startsWith(\"app\") = " + trie.startsWith("app"));
        trie.insert("app");
        System.out.println("trie.search(\"app\") = " + trie.search("app"));
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)
