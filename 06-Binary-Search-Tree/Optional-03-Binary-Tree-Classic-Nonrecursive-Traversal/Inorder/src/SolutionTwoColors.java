import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 我们知道垃圾回收算法中，有一种算法叫三色标记法。 即：
 * <p>
 * 用白色表示尚未访问
 * <p>
 * 灰色表示尚未完全访问子节点
 * <p>
 * 黑色表示子节点全部访问
 * <p>
 * 那么我们可以模仿其思想，使用双色标记法来统一三种遍历。
 * <p>
 * 其核心思想如下：
 * <p>
 * 使用颜色标记节点的状态，新节点为白色，已访问的节点为灰色。
 * <p>
 * 如果遇到的节点为白色，则将其标记为灰色，然后将其右子节点、自身、左子节点依次入栈。
 * <p>
 * 如果遇到的节点为灰色，则将节点的值输出。
 *
 * @author JClearLove
 * @date 2021/04/08 09:34
 */

public class SolutionTwoColors {
    private class TagNode {
        boolean color;
        TreeNode treeNode;

        private TagNode(boolean color, TreeNode treeNode) {
            this.color = color;
            this.treeNode = treeNode;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<TagNode> stack = new Stack<>();
        stack.push(new TagNode(false, root));
        while (!stack.isEmpty()) {
            TagNode node = stack.pop();
            TreeNode cur = node.treeNode;
            if (cur == null) {
                continue;
            }
            if (!node.color) {
                // 右
                stack.push(new TagNode(false, cur.right));
                // 根-在中间所以是中序遍历
                stack.push(new TagNode(true, cur));
                // 左
                stack.push(new TagNode(false, cur.left));
            } else {
                res.add(cur.val);
            }
        }
        return res;
    }

}

