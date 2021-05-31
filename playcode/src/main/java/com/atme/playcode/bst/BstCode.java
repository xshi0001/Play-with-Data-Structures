package com.atme.playcode.bst;

import lombok.Data;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * 如何快速查hbase,优化方法
 *
 * @author JClearLove
 * @date 2021/04/16 10:03
 */
@Data
public class BstCode {

    private TreeNode root;
    private final Integer[] arr = new Integer[]{3, 9, 20, null, null, 15, 7};

    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public TreeNode(int val) {
            this.val = val;
        }
    }

    TreeNode createBinaryTreeByArray(Integer[] array, int index) {
        TreeNode tn = null;
        if (index < array.length) {
            Integer value = array[index];
            if (value == null) {
                return null;
            }
            tn = new TreeNode(value);
            tn.left = createBinaryTreeByArray(array, 2 * index + 1);
            tn.right = createBinaryTreeByArray(array, 2 * index + 2);
            return tn;
        }
        return tn;
    }

    public static class BTreePrinter {
        public static <T extends Comparable<?>> void printNode(TreeNode root) {
            int maxLevel = BTreePrinter.maxLevel(root);

            printNodeInternal(Collections.singletonList(root), 1, maxLevel);
        }

        private static <T extends Comparable<?>> void printNodeInternal(List<TreeNode> nodes, int level, int maxLevel) {
            if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes)) {
                return;
            }

            int floor = maxLevel - level;
            int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
            int firstSpaces = (int) Math.pow(2, (floor)) - 1;
            int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

            BTreePrinter.printWhitespaces(firstSpaces);

            List<TreeNode> newNodes = new ArrayList<TreeNode>();
            for (TreeNode node : nodes) {
                if (node != null) {
                    System.out.print(node.val);
                    newNodes.add(node.left);
                    newNodes.add(node.right);
                } else {
                    newNodes.add(null);
                    newNodes.add(null);
                    System.out.print(" ");
                }

                BTreePrinter.printWhitespaces(betweenSpaces);
            }
            System.out.println("");

            for (int i = 1; i <= endgeLines; i++) {
                for (int j = 0; j < nodes.size(); j++) {
                    BTreePrinter.printWhitespaces(firstSpaces - i);
                    if (nodes.get(j) == null) {
                        BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                        continue;
                    }

                    if (nodes.get(j).left != null) {
                        System.out.print("/");
                    } else {
                        BTreePrinter.printWhitespaces(1);
                    }

                    BTreePrinter.printWhitespaces(i + i - 1);

                    if (nodes.get(j).right != null) {
                        System.out.print("\\");
                    } else {
                        BTreePrinter.printWhitespaces(1);
                    }

                    BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
                }

                System.out.println("");
            }

            printNodeInternal(newNodes, level + 1, maxLevel);
        }

        private static void printWhitespaces(int count) {
            for (int i = 0; i < count; i++) {
                System.out.print(" ");
            }
        }

        private static <T extends Comparable<?>> int maxLevel(TreeNode node) {
            if (node == null) {
                return 0;
            }

            return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
        }

        private static <T> boolean isAllElementsNull(List<T> list) {
            for (Object object : list) {
                if (object != null) {
                    return false;
                }
            }

            return true;
        }

    }


    public List<List<Integer>> levelOrder(TreeNode root) {
        // 层次遍历
        LinkedList<TreeNode> nodes = new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();
        if (root != null) {
            nodes.add(root);
        }
        while (!nodes.isEmpty()) {
            LinkedList<Integer> list = new LinkedList<>();
            // 添加结点之前，临时存储当前队列长度
            int length = nodes.size();
            for (int i = 0; i < length; i++) {
                TreeNode node = nodes.poll();
                list.add(node.val);
                if (node.left != null) {
                    nodes.add(node.left);
                }
                if (node.right != null) {
                    nodes.add(node.right);
                }
            }
            res.add(list);
        }
        return res;
    }


    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> lists = levelOrder(root);
        Collections.reverse(lists);
        return lists;
    }

    public List<Integer> rightSideView(TreeNode root) {
        // 层次遍历-最后一个元素
        List<Integer> res = new ArrayList<>();
        LinkedList<TreeNode> nodes = new LinkedList<>();
        if (root != null) {
            nodes.add(root);
        }
        while (!nodes.isEmpty()) {
            // 记录一下没有放入队列的时候长度
            int curSize = nodes.size();
            for (int i = 0; i < curSize; i++) {
                TreeNode node = nodes.poll();
                if (i == curSize - 1) {
                    res.add(node.val);
                }
                if (node.left != null) {
                    nodes.add(node.left);
                }
                if (node.right != null) {
                    nodes.add(node.right);
                }
            }

        }
        return res;
    }

    public List<Double> averageOfLevels(TreeNode root) {
        // 队列-层次遍历
        ArrayList<Double> doubles = new ArrayList<>();
        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        if (root != null) {
            treeNodes.add(root);
        }
        while (!treeNodes.isEmpty()) {
            double temp = 0.0;
            int curSize = treeNodes.size();
            for (int i = 0; i < curSize; i++) {
                TreeNode node = treeNodes.poll();
                if (node != null) {
                    temp += node.val;
                    if (node.left != null) {
                        treeNodes.add(node.left);
                    }
                    if (node.right != null) {
                        treeNodes.add(node.right);
                    }
                }
            }

            doubles.add(temp / curSize);
        }
        return doubles;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        // 新建一个栈，实现前序遍历
        Stack<TreeNode> nodes = new Stack<>();
        // 存储结果
        ArrayList<Integer> res = new ArrayList<>();
        if (root != null) {
            nodes.push(root);
        }
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.pop();
            res.add(node.val);
            if (node.right != null) {
                nodes.push(node.right);
            }
            if (node.left != null) {
                nodes.push(node.left);
            }
        }
        return res;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> nodes = new Stack<>();
        // 存储结果
        ArrayList<Integer> res = new ArrayList<>();
        // 通过指针的遍历来帮助遍历结点
        TreeNode curNode = root;
        while (curNode != null || !nodes.isEmpty()) {
            // 一直把左结点遍历完
            if (curNode != null) {
                nodes.push(curNode);
                curNode = curNode.left;
            } else {
                // 到了第二次访问结点的时候
                TreeNode node = nodes.pop();
                res.add(node.val);
                if (node.left != null) {
                    nodes.push(node.right);
                }
            }
        }
        return res;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        // 新建一个栈，实现前序遍历
        Stack<TreeNode> nodes = new Stack<>();
        // 存储结果
        ArrayList<Integer> res = new ArrayList<>();
        if (root != null) {
            nodes.push(root);
        }

        while (!nodes.isEmpty()) {
            TreeNode node = nodes.pop();
            res.add(node.val);
            // 改变顺序
            if (node.left != null) {
                nodes.push(node.left);
            }
            if (node.right != null) {
                nodes.push(node.right);
            }

        }
        // 反转
        Collections.reverse(res);
        return res;
    }

    public TreeNode invertTree(TreeNode root) {
        // 新建一个栈，实现前序遍历
        Stack<TreeNode> nodes = new Stack<>();
        if (root != null) {
            nodes.push(root);
        }
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.pop();
            // 交换
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if (node.right != null) {
                nodes.push(node.right);
            }
            if (node.left != null) {
                nodes.push(node.left);
            }
        }
        return root;
    }

    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree2(root.left);
        invertTree2(root.right);
        return root;
    }


    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            // 比较最值的时候可以用一个标准，最大值就设置最小值。。。
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.pollFirst();
                max = Math.max(cur.val, max);

                if (cur.left != null)
                    queue.addLast(cur.left);
                if (cur.right != null)
                    queue.addLast(cur.right);
            }

            res.add(max);
        }

        return res;
    }

    // 以root为根结点的树，左右子树是否可以相互翻转，进而判别这两棵树是否是对称的
    // 所以我们要比较两棵树，参数自然而然是左子树结点和右子树结点
    // 返回类型为bool类型
    // 设计递归函数
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return compare(root.left, root.right);
    }

    // 左右子树
    private boolean compare(TreeNode left, TreeNode right) {
        // 首先排除空节点的情况
        if (left == null && right != null) {
            return false;
        } else if (left != null && right == null) {
            return false;
        } else if (left == null & right == null) {
            return true;
            //节点值不相等情况
        } else if (left.val != right.val) {
            return false;
        }

        // 左右结点都不为空，且左右节点值相等
        // 此时才做递归，做下一层判断
        return compare(left.left, right.right) & compare(left.right, right.left);
    }


    public int maxDepthNR(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepthNR(root.left);
        int rightDepth = maxDepthNR(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }


    /**
     * 记录遍历过程种最大深度
     */
    private int result = 0;

    public int maxDepthByPreOrder(TreeNode root) {
        if (root == null) {
            return result;
        }
        getDepth(root, 1);

        return result;
    }


    private void getDepth(TreeNode node, int depth) {
        result = Math.max(result, depth);

        if (node.left == null && node.right == null) {
            return;
        }

        if (node.left != null) {
            // 深度加1
            depth++;
            getDepth(node.left, depth);
            // 回溯，深度-1
            depth--;
        }
        if (node.right != null) {
            // 深度加1
            depth++;
            getDepth(node.right, depth);
            // 回溯，深度-1
            depth--;
        }
    }


    public int maxDepth(TreeNode root) {
        // 层序遍历方法
        LinkedList<TreeNode> nodes = new LinkedList<>();
        if (root != null) {
            nodes.add(root);
        }
        int depth = 0;
        while (!nodes.isEmpty()) {
            int curSize = nodes.size();
            for (int i = 0; i < curSize; i++) {
                TreeNode node = nodes.poll();
                if (node.left != null) {
                    nodes.add(node.left);
                }
                ;
                if (node.right != null) {
                    nodes.add(node.right);
                }
            }
            depth++;
        }
        return depth;
    }

    public int minDepthNR(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);

        if (root.left == null && root.right != null) {
            return rightDepth + 1;
        } else if (root.left != null && root.right == null) {
            return leftDepth + 1;
        }
        return Math.min(leftDepth, rightDepth) + 1;
    }

    public int minDepth(TreeNode root) {
        LinkedList<TreeNode> nodes = new LinkedList<>();
        if (root != null) {
            nodes.add(root);
        }
        // 纪录当前深度
        int depth = 0;
        while (!nodes.isEmpty()) {
            // 记录当前层队列大小
            int curSize = nodes.size();
            // 标记，当为true的时候说明已经找到左右子树均为空的叶子结点
            boolean flag = false;
            for (int i = 0; i < curSize; i++) {
                TreeNode node = nodes.remove();
                if (node.left != null) {
                    nodes.add(node.left);
                }
                if (node.right != null) {
                    nodes.add(node.right);
                }
                // 只有当左右孩子都为空的时候，才说明遍历的最低点了。如果其中一个孩子为空则不是最低点
                if (node.right == null && node.left == null) {
                    flag = true;
                }
            }
            depth++;
            if (flag) {
                break;
            }
        }

        return depth;

    }


    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftNodes = countNodes(root.left);
        int rightNodes = countNodes(root.right);
        return leftNodes + rightNodes + 1;
    }

    public boolean isBalanced(TreeNode root) {
        return depth(root) != -1;
    }

    @Test
    public void testDepth() {

        System.out.println("isBalanced(root) = " + isBalanced(root));
    }

    // 返回以该节点为根节点的二叉树的高度，如果不是二叉搜索树了则返回-1
    private int depth(TreeNode node) {

        if (node == null) {
            return 0;
        }

        int leftDepth = depth(node.left);
        if (leftDepth == -1) {
            return -1;
        }
        int rightDepth = depth(node.right);
        if (rightDepth == -1) {
            return -1;
        }

        int result = 0;
        if (Math.abs(leftDepth - rightDepth) > 1) {  // 中
            result = -1;
        } else {
            // 以当前节点为根节点的最大高度
            result = 1 + Math.max(leftDepth, rightDepth);
        }

        return result;
    }


    @Before
    public void init() {
        BstCode bstCode = new BstCode();
        root = bstCode.createBinaryTreeByArray(arr, 0);
        BTreePrinter.printNode(root);
    }

    @Test
    public void testCountNodes() {
        BTreePrinter.printNode(root);
        System.out.println("countNodes(root) = " + countNodes(root));
    }

    @Test
    public void testMaxDepthByPreOrder() {
        System.out.println("maxDepthByPreOrder(root) = " + maxDepthByPreOrder(root));
    }

    @Test
    public void testAverageOfLevels() {

        List<Double> doubles = averageOfLevels(root);
        System.out.println("doubles = " + doubles);
        for (Double aDouble : doubles) {


        }

    }

    @Test
    public void testRightSideView() {
        List<Integer> integers = rightSideView(root);
        System.out.println("integers = " + integers);

    }

    @Test
    public void testPreorderTraversal() {
        System.out.println(preorderTraversal(root));
    }

    @Test
    public void testInvertTree() {
        BTreePrinter.printNode(root);
        invertTree(root);
        BTreePrinter.printNode(root);
    }

    @Test
    public void testInvertTree2() {
        BTreePrinter.printNode(root);
        invertTree2(root);
        BTreePrinter.printNode(root);
    }

    @Test
    public void testLargestValues() {
        BTreePrinter.printNode(root);
        System.out.println("largestValues(root) = " + largestValues(root));
    }

    @Test
    public void testIsSymmetric() {
        BTreePrinter.printNode(root);
        System.out.println("isSymmetric(root) = " + isSymmetric(root));
    }

    @Test
    public void testMaxDepthNR() {
        BTreePrinter.printNode(root);
        System.out.println("maxDepthNR(root) = " + maxDepthNR(root));
    }

    @Test
    public void testMaxDepth() {
        BTreePrinter.printNode(root);
        System.out.println("maxDepthNR(root) = " + maxDepth(root));
    }


}

