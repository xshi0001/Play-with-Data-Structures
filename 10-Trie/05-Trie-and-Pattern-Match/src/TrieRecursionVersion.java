import java.util.TreeMap;

class TrieRecursionVersion {
        private class Node {
            public boolean isWord;
            public TreeMap<Character, Node> children;

            public Node(boolean isWord) {
                this.isWord = isWord;
                children = new TreeMap<>();
            }

            public Node() {
                this(false);
            }
        }
        private Node root;
        private int size;
        TrieRecursionVersion(){
            root=new Node();
            size=0;
        }
        public int getSize(){
            return  size;
        }
        public void insert(String word){
            insert(root,word,0);
        }
        //向以node为根的trie树中添加从index开始的子串(word[index...end])
        private void insert(Node node,String word,int index){
            if(index==word.length()){
                if(!node.isWord){
                    node.isWord=true;
                    size++;
                }
                return;
            }
             char c=word.charAt(index);
            if(node.children.get(c)==null) node.children.put(c,new Node());
            insert(node.children.get(c),word,index+1);
        }
        public boolean contains(String word){
            return contains(root,word,0);
        }
        //在以node为根的trie树汇总查询是否存在以index开始的子串(word[index...end])
        public boolean contains(Node node,String word,int index){
            if(index==word.length()) return node.isWord;
            char c=word.charAt(index);
            if(node.children.get(c)==null) return false;
            return contains(node.children.get(c),word,index+1);
        }
        public  boolean isPrefix(String word){
            return isPrefix(root,word,0);
        }
        //在以node为根的trie树中查询是否以index开始的子串(word[index...end])
        public boolean isPrefix(Node node,String word,int index){
            if(index==word.length()) return true;
            char c=word.charAt(index);
            if(node.children.get(c)==null) return  false;
            return isPrefix(node.children.get(c),word,index+1);
        }
    }
