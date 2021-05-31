public class BST<E extends Comparable<E>> {

    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 向二分搜索树中添加新的元素e
    public void add(E e) {

        if (root == null) {
            root = new Node(e);
            size++;
        } else {
            add(root, e);
        }
    }

    // 向以node为根的二分搜索树中插入元素e，递归算法
    private void add(Node node, E e) {

        // 终止条件这么多！


        // 如果当前结点值与被添加结点值相同，那么就直接返回
        if (e.equals(node.e)) {
            return;
            // 否则，如果，判断如果结点值小于当前结点，且左边的左子树为空，就直接添加结点
        } else if (e.compareTo(node.e) < 0 && node.left == null) {
            node.left = new Node(e);
            size++;
            return;
            // 否则如果， 当前节点值小于被添加节点值，且右子树为空，就添加到右子树中
        } else if (e.compareTo(node.e) > 0 && node.right == null) {
            node.right = new Node(e);
            size++;
            return;
        }



        // 如果上面条件都不符合要求，那么就递归的判断左右子树
        if (e.compareTo(node.e) < 0) {
            add(node.left, e);
        } else //e.compareTo(node.e) > 0
        {
            add(node.right, e);
        }
    }
}
