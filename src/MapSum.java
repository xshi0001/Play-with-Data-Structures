//Implement the MapSum class:
//
//
// MapSum() Initializes the MapSum object.
// void insert(String key, int val) Inserts the key-val pair into the map. If th
//e key already existed, the original key-value pair will be overridden to the new
// one.
// int sum(string prefix) Returns the sum of all the pairs' value whose key star
//ts with the prefix.
//
//
//
// Example 1:
//
//
//Input
//["MapSum", "insert", "sum", "insert", "sum"]
//[[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
//Output
//[null, null, 3, null, 5]
//
//Explanation
//MapSum mapSum = new MapSum();
//mapSum.insert("apple", 3);
//mapSum.sum("ap");           // return 3 (apple = 3)
//mapSum.insert("app", 2);
//mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
//
//
//
// Constraints:
//
//
// 1 <= key.length, prefix.length <= 50
// key and prefix consist of only lowercase English letters.
// 1 <= val <= 1000
// At most 50 calls will be made to insert and sum.
//
// Related Topics 字典树
// 👍 84 👎 0


import java.util.TreeMap;

//leetcode submit region begin(Prohibit modification and deletion)
class MapSum {

    private class TrieNode {
        /**
         * weight: the word at the Node
         */
        private int weight;
        private TreeMap<Character, TrieNode> next;

        public TrieNode() {
            this(0);
        }

        public TrieNode(int weight) {
            this.weight = weight;
            this.next = new TreeMap<>();
        }
    }

    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public MapSum() {
        this.root = new TrieNode();
    }

    public void insert(String key, int val) {
        TrieNode cur = root;
        insert(root, 0, key, val);
    }

    // 以node为根节点插入索引范围[l...r]的val中的单词
    private void insert(TrieNode node, int l, String word, int val) {
        if (l == word.length()) {
            node.weight = val;
            return;
        }

        char c = word.charAt(l);
        if (node.next.get(c) == null) {
            node.next.put(c, new TrieNode());
        }
        insert(node.next.get(c), l + 1, word, val);
    }

    public int sum(String prefix) {

        TrieNode cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return 0;
            }
            cur = cur.next.get(c);
        }

        return sum(cur);
    }

    private int sum(TrieNode node) {
        if (node.next.size() == 0) {
            return node.weight;
        }
        int res = node.weight;
        for (char c : node.next.keySet()) {
            res += sum(node.next.get(c));
        }
        return res;
    }

    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        System.out.println("mapSum.sum(\"ap\") = " + mapSum.sum("ap"));
        mapSum.insert("app", 2);
        System.out.println("mapSum.sum(\"ap\") = " + mapSum.sum("ap"));
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)
