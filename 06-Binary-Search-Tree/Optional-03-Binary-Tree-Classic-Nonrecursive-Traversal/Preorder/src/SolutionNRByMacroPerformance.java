/// Source : https://leetcode.com/problems/binary-tree-preorder-traversal/description/
/// Author : liuyubobobo
/// Time   : 2018-05-30

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// Another Classic Non-Recursive algorithm for preorder traversal
// Time Complexity: O(n), n is the node number in the tree
// Space Complexity: O(h), h is the height of the tree
public class SolutionNRByMacroPerformance {

    public List<Integer> preorderTraversal(TreeNode root) {

        ArrayList<Integer> res = new ArrayList<Integer>();
        if(root == null) {
            return res;
        }

        // 从宏观上表现为：自顶向下依次访问左侧链，然后自底向上依次访问右侧链
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()){
            if(cur != null){
                res.add(cur.val);
                // 先把所有左子树压入栈
                stack.push(cur);
                // 自顶向下，一直找叶子结点，叶子节点左子树的为null
                cur = cur.left;
            } else{
                // 为null。说明已经查找了叶子结点，然后返回把站点元素弹出，访问其右子树
                cur = stack.pop();
                cur = cur.right;
            }
        }
        return res;
    }
}
