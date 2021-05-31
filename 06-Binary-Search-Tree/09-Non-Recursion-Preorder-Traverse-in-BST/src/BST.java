import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
        root = add(root, e);
    }

    // 向以node为根的二分搜索树中插入元素e，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }

        return node;
    }

    // 看二分搜索树中是否包含元素e
    public boolean contains(E e) {
        return contains(root, e);
    }

    // 看以node为根的二分搜索树中是否包含元素e, 递归算法
    private boolean contains(Node node, E e) {

        if (node == null) {
            return false;
        }

        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else // e.compareTo(node.e) > 0
        {
            return contains(node.right, e);
        }
    }

    // 二分搜索树的前序遍历
    public void preOrder() {
        preOrder(root);
    }

    // 前序遍历以node为根的二分搜索树, 递归算法
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    public List<E> preOrderTraversal(Node root) {
        List<E> res = new ArrayList<>();
        res = preOrderTraversal(root, res);
        return res;
    }

    private List<E> preOrderTraversal(Node node, List<E> res) {
        if (node == null) {
            return res;
        }
        res.add(node.e);
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
        return res;
    }


    /**
     * 本质上是在模拟递归，因为在递归的过程中使用了系统栈，所以在迭代的解法中常用Stack来模拟系统栈。
     */
    // 二分搜索树的非递归前序遍历
    public void preOrderNR() {

        if (root == null) {
            return;
        }
        // 应该创建一个Stack用来存放节点
        Stack<Node> stack = new Stack<>();
        // 从根结点开始
        stack.push(root);
        while (!stack.isEmpty()) {
            // 访问
            Node cur = stack.pop();
            System.out.println(cur.e);
            // 入栈-右 我们应该先打印左子树，然后右子树。所以先加入Stack的就是右子树，然后左子树。
            //此时你能得到的流程如下
            if (cur.right != null) {
                stack.push(cur.right);
            }
            // 入栈-左
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateString(root, 0, res);
        return res.toString();
    }

    // 生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateString(Node node, int depth, StringBuilder res) {

        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateString(node.left, depth + 1, res);
        generateString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }
}
