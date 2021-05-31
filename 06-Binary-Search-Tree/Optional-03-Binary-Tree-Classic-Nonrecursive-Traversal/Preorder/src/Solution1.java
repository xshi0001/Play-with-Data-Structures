/// Source : https://leetcode.com/problems/binary-tree-preorder-traversal/description/
/// Author : liuyubobobo
/// Time   : 2017-11-17

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// Classic Non-Recursive algorithm for preorder traversal
// Time Complexity: O(n), n is the node number in the tree
// Space Complexity: O(h), h is the height of the tree
public class Solution1 {

    public List<Integer> preorderTraversal(TreeNode root) {

        ArrayList<Integer> res = new ArrayList<Integer>();
        if(root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        // 1.先将跟结点入栈
        stack.push(root);
        while(!stack.empty()){
            //2. 出一个元素，即访问栈顶元素
            TreeNode curNode = stack.pop();
            res.add(curNode.val);
            //3. 左右子树判断是否为空，根据栈的特性，先进后出，所以先放右子树
            if(curNode.right != null) {
                stack.push(curNode.right);
            }
            if(curNode.left != null) {
                stack.push(curNode.left);
            }
        }
        return res;
    }

}
