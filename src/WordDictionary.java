//Design a data structure that supports adding new words and finding if a string
// matches any previously added string.
//
// Implement the WordDictionary class:
//
//
// WordDictionary() Initializes the object.
// void addWord(word) Adds word to the data structure, it can be matched later.
//
// bool search(word) Returns true if there is any string in the data structure t
//hat matches word or false otherwise. word may contain dots '.' where dots can be
// matched with any letter.
//
//
//
// Example:
//
//
//Input
//["WordDictionary","addWord","addWord","addWord","search","search","search","se
//arch"]
//[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
//Output
//[null,null,null,null,false,true,true,true]
//
//Explanation
//WordDictionary wordDictionary = new WordDictionary();
//wordDictionary.addWord("bad");
//wordDictionary.addWord("dad");
//wordDictionary.addWord("mad");
//wordDictionary.search("pad"); // return False
//wordDictionary.search("bad"); // return True
//wordDictionary.search(".ad"); // return True
//wordDictionary.search("b.."); // return True
//
//
//
// Constraints:
//
//
// 1 <= word.length <= 500
// word in addWord consists lower-case English letters.
// word in search consist of '.' or lower-case English letters.
// At most 50000 calls will be made to addWord and search.
//
// Related Topics 深度优先搜索 设计 字典树 回溯算法
// 👍 226 👎 0


import java.util.TreeMap;

//leetcode submit region begin(Prohibit modification and deletion)
class WordDictionary {

    private class Node {
        private boolean isWord;
        private TreeMap<Character, Node> next;

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
    public WordDictionary() {
        root = new Node();
        size = 0;
    }

    public void addWord(String word) {
        addWord(root, word, 0);
    }

    private void addWord(Node node, String word, int index) {
        // 一直迭代完全word，并且检查是否之前已经设置isWord
        if (index == word.length()) {
            if (!node.isWord) {
                node.isWord = true;
                size++;
            }
            // 不能少！
            return;
        }
        char c = word.charAt(index);
        if (node.next.get(c) == null) {
            node.next.put(c, new Node());
        }
        // 不是放在else中哦
        addWord(node.next.get(c), word, index + 1);
    }


    public boolean search(String word) {
        return search(root, word, 0);
    }

    private boolean search(Node node, String word, int index) {
        if (index == word.length()) {
            return node.isWord;
        }
        char c = word.charAt(index);
        if (c != '.') {
            if (node.next.get(c) == null) {
                return false;
            }
            return search(node.next.get(c), word, index + 1);
        } else {
            for (char nextChar : node.next.keySet()) {
                if (search(node.next.get(nextChar), word, index + 1)) {
                    return true;
                }
            }
            return false;
        }

    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println("wordDictionary.search(\"pad\") = " + wordDictionary.search("pad"));
        System.out.println("wordDictionary.search(\"bad\") = " + wordDictionary.search("bad"));
        System.out.println("wordDictionary.search(\".ad\") = " + wordDictionary.search(".ad"));
        System.out.println("wordDictionary.search(\"b..\") = " + wordDictionary.search("b.."));
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
//leetcode submit region end(Prohibit modification and deletion)
