public class Words_in_trie {

    // Alphabet size (# of symbols)
    static final int ALPHABET_SIZE = 26;

    // Trie node
    static class TrieNode {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];
        // isLeaf is true if the node represents
        // end of a word
        boolean isLeaf;

        TrieNode() {
            isLeaf = false;
            for (int i = 0; i < ALPHABET_SIZE; i++) {
                children[i] = null;
            }
        }
    }

    ;
    static TrieNode root;

    // If not present, inserts key into trie
    // If the key is prefix of trie node, just
    // marks leaf node
    static void insert(String key) {
        int length = key.length();

        TrieNode pCrawl = root;

        for (int level = 0; level < length; level++) {
            int index = key.charAt(level) - 'a';
            if (pCrawl.children[index] == null) {
                pCrawl.children[index] = new TrieNode();
            }

            pCrawl = pCrawl.children[index];
        }

        // mark last node as leaf
        pCrawl.isLeaf = true;
    }

    // Function to count number of words
    static int wordCount(TrieNode root) {
        int result = 0;

        // Leaf denotes end of a word
        if (root.isLeaf) {
            result++;
        }

        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (root.children[i] != null) {
                result += wordCount(root.children[i]);
            }
        }

        return result;
    }

    // Driver Program
    public static void main(String args[]) {
        // Input keys (use only 'a' through 'z'
        // and lower case)
        String keys[] = {"the", "a", "there", "answer",
                "any", "by", "bye", "their"};

        root = new TrieNode();

        // Construct Trie
        for (String key : keys) {
            insert(key);
        }

        System.out.println(wordCount(root));
    }
}
// This code is contributed by Sumit Ghosh
