package com.atme.playcode.bst;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉搜素树
 *
 * @author JClearLove
 * @date 2021/05/07 09:44
 */

public class BstCodeSubTwo extends BstCode {
    /**
     * 存放节点值
     */
    private final List<Integer> list = new ArrayList<>();

    public boolean isValidBST(TreeNode root) {
        // 中序遍历是否是二叉搜素树，二叉搜索树节点的数值是有序序列
        traversalInOrder(root);
        // 判断是否是单调递增数列
        for (int i = 1; i < list.size(); i++) {
            // 注意是小于等于，搜素树中不能有相同元素
            if (list.get(i) <= list.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    private void traversalInOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        traversalInOrder(node.left);
        list.add(node.val);
        traversalInOrder(node.right);
    }

}

