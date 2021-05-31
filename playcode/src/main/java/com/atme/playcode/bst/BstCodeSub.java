package com.atme.playcode.bst;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.*;

/**
 * TODO
 *
 * @author JClearLove
 * @date 2021/04/26 09:40
 */

public class BstCodeSub extends BstCode {
    public boolean isSameTree(TreeNode p, TreeNode q) {

        return compareTree(p, q);
    }

    private boolean compareTree(TreeNode left, TreeNode right) {
        // 迭代终止条件
        if (left == null && right != null) {
            return false;
        } else if (left != null && right == null) {
            return false;
        } else if (left == null) {
            return true;
        } else if (left.val != right.val) {
            return false;
        }
        boolean leftRes = compareTree(left.left, right.left);
        boolean rightRes = compareTree(left.right, right.right);
        return leftRes && rightRes;
    }

    public boolean isSameTreeNr(TreeNode p, TreeNode q) {
        if (Objects.isNull(p) && Objects.isNull(q)) {
            return true;
        }
        // || 判断，如果前面的是true后面就不会执行
        if (Objects.isNull(p) || Objects.isNull(q)) {
            return false;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(p);
        queue.add(q);
        while (!queue.isEmpty()) {
            TreeNode pNode = queue.poll();
            TreeNode qNode = queue.poll();
            if (Objects.isNull(pNode) && Objects.isNull(qNode)) {
                continue;
            }

            if (Objects.isNull(pNode) || Objects.isNull(qNode) || pNode.val != qNode.val) {
                return false;
            }
            queue.add(pNode.left);
            queue.add(qNode.left);

            queue.add(pNode.right);
            queue.add(qNode.right);
        }
        return true;
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (t == null) {
            return true;   // t 为 null 一定都是 true
        }
        if (s == null) {
            return false;  // 这里 t 一定不为 null, 只要 s 为 null，肯定是 false
        }
        return isSubtree(s.left, t) || isSubtree(s.right, t) || isIfSameTree(s, t);
    }

    /**
     * 判断两棵树是否相同
     */
    public boolean isIfSameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        if (s.val != t.val) {
            return false;
        }
        return isIfSameTree(s.left, t.left) && isIfSameTree(s.right, t.right);
    }

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftValue = sumOfLeftLeaves(root.left);
        int rightValue = sumOfLeftLeaves(root.right);
        // 取值
        int midValue = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            midValue = root.left.val;
        }
        return leftValue + rightValue + midValue;

    }

    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            throw new IllegalArgumentException("树为空");
        }
        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        int result = 0;
        while (!nodes.isEmpty()) {
            int size = nodes.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = nodes.remove();
                // 记录每行最左边的元素
                if (i == 0) {
                    result = node.val;
                }
                if (node.left != null) {
                    nodes.add(node.left);
                }
                if (node.right != null) {
                    nodes.add(node.right);
                }

            }

        }
        return result;
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        return traversal(root, targetSum);
    }

    private boolean traversal(TreeNode cur, int count) {
        // 提前终止
        if (cur.right == null && cur.left == null && count == cur.val) {
            return true;
        }
        if (cur.right == null && cur.left == null) {

            return false;
        }

        // 左
        if (cur.left != null) {
            // 先减
            count -= cur.val;
            if (traversal(cur.left, count)) {
                return true;
            }

            // 在回来加
            count += cur.val;
        }
        // 右
        if (cur.right != null) {
            // 先减
            count -= cur.val;
            if (traversal(cur.right, count)) {
                return true;
            }
            // 在回来加
            count += cur.val;
        }

        return false;

    }

    // 把根节点放进路径  测试用例:[] 1 测试结果:null 期望结果:[] stdout:
    private final ArrayList<List<Integer>> paths = new ArrayList<>();
    private final LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (Objects.isNull(root)) {
            return paths;
        }
        path.add(root.val);
        pathTraversal(root, targetSum - root.val);
        return paths;
    }


    private void pathTraversal(TreeNode node, int count) {
        // 遇到叶子结点，并且叶子结点的值等于count
        if (node.right == null && node.left == null & count == 0) {
            //此处不能直接赋值
            ArrayList<Integer> res = new ArrayList<>();
            res.addAll(path);
            paths.add(res);
            return;
        }

        if (node.right == null && node.left == null) {
            //直接返回，什么也不做
            return;
        }

        if (node.left != null) {
            path.add(node.left.val);
            // 先减去
            count -= node.left.val;
            // 递归
            pathTraversal(node.left, count);
            // 回溯,count核path都要回溯
            count += node.left.val;
            path.removeLast();
        }

        if (node.right != null) {
            path.add(node.right.val);
            // 先减去
            count -= node.right.val;
            // 递归
            pathTraversal(node.right, count);
            // 回溯,count核path都要回溯
            count += node.right.val;
            path.removeLast();
        }
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        TreeNode node = new TreeNode(0);
        if (nums.length == 1) {
            node.val = nums[0];
            return node;
        }
        // 找到数组中最大的值核对应下来的下表

        int maxValue = 0;
        int maxValueIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > maxValue) {
                maxValue = nums[i];
                maxValueIndex = i;
            }
        }
        node.val = maxValue;
        // 最大值所在的下表左区间，构造左子树
        if (maxValueIndex > 0) {
            int[] left = Arrays.copyOfRange(nums, 0, maxValueIndex);
            node.left = constructMaximumBinaryTree(left);
        }
        // 最大值所在的下表右区间，构造右子树
        if (maxValueIndex < nums.length - 1) {
            int[] right = Arrays.copyOfRange(nums, maxValueIndex + 1, nums.length);
            node.right = constructMaximumBinaryTree(right);
        }
        return node;
    }

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return root1 == null ? root2 : root1;
        }

        root1.val += root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        return root.val > val ? searchBST(root.left, val) : searchBST(root.right, val);
    }

    // 遇到在二叉搜索树上求什么最值啊，差值之类的，就把它想成在一个有序数组上求最值，求差值，这样就简单多了。
    // 求最大值
    public int getMaximumDifference(TreeNode root) {
        // 求最小值和最大值
        return Math.abs(maximum(root) - minimum(root));
    }

    public int minimum(TreeNode node) {
        if (node == null) {
            throw new IllegalArgumentException("结点为空");
        }
        if (node.left == null) {
            return node.val;
        }
        return minimum(node.left);
    }

    public int maximum(TreeNode node) {
        if (node == null) {
            throw new IllegalArgumentException("结点为空");
        }
        if (node.right == null) {
            return node.val;
        }
        return maximum(node.right);
    }

    private final ArrayList<Integer> ints = new ArrayList<>();

    private void traversal(TreeNode node) {
        if (node == null) {
            return;
        }
        traversal(node.left);
        ints.add(node.val);
        traversal(node.right);
    }

    public int getMinimumDifference(TreeNode root) {
        if (ints.size() < 2) {
            return 0;
        }
        traversal(root);

        int res = Integer.MAX_VALUE;
        for (int i = 1; i < ints.size(); i++) {
            res = Math.min(res, Math.abs(ints.get(i) - ints.get(i - 1)));
        }
        return res;
    }


    @Test
    public void testGetMinimumDifference() {
        // [4,2,6,1,3]
        BstCode bstCode = new BstCode();
        TreeNode node = bstCode.createBinaryTreeByArray(new Integer[]{1, 2, 3, 4, 6}, 0);
        BTreePrinter.printNode(node);
        int minimumDifference = getMinimumDifference(node);
        System.out.println(minimumDifference);
    }


    @Test
    public void testSearchBST() {
        BstCode bstCode = new BstCode();
        TreeNode node = bstCode.createBinaryTreeByArray(new Integer[]{4, 2, 7, 1, 3}, 0);
        BTreePrinter.printNode(node);
        TreeNode bst = searchBST(node, 2);
        BTreePrinter.printNode(bst);

    }

    @Test
    public void testMergeTree() {
        BstCode bstCode = new BstCode();

        TreeNode root1 = bstCode.createBinaryTreeByArray(new Integer[]{1, 3, 2, 5}, 0);
        BTreePrinter.printNode(root1);
        TreeNode root2 = bstCode.createBinaryTreeByArray(new Integer[]{3, 4, 5, null, 4, null, 7}, 0);
        BTreePrinter.printNode(root2);
        TreeNode node = mergeTrees(root1, root2);

        BTreePrinter.printNode(node);
//        System.out.println();

        //        解答失败:
//        测试用例:[1,3,2,5]
//			[2,1,3,null,4,null,7]
//        测试结果:[3,4,5,null,4,null,7]
//        期望结果:[3,4,5,5,4,null,7]
//        stdout:

    }


    @Test
    public void testConstruct() {
        int[] ints = new int[]{3, 2, 1, 6, 0, 5};
        TreeNode node = constructMaximumBinaryTree(ints);
        BTreePrinter.printNode(node);

    }


    @Test
    public void testSumOfLeftLeaves() {

        int sum = sumOfLeftLeaves(getRoot());
        System.out.println(sum);
    }

    @Test
    public void testFindBottomLeftValue() {
        int bottomLeftValue = findBottomLeftValue(getRoot());
        TestCase.assertEquals(15, bottomLeftValue);
    }

    @Test
    public void testHasPathSum() {
        boolean b = hasPathSum(getRoot(), 12);
        System.out.println(b);
    }

    @Test
    public void testPathSum() {
        pathSum(getRoot(), 12);
        System.out.println(paths);
    }


}


