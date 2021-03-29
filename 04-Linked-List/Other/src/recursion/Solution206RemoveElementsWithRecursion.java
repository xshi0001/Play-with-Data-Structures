package recursion;

/**
 * TODO
 *
 * @author JClearLove
 * @date 2021/03/22 22:08
 */

public class Solution206RemoveElementsWithRecursion {
    /**
     * 宏观语义：求解链表[head，val]中存在val并删除该元素
     * 问题的子问题是求解链表[head.next,val]中存在val并删除该元素
     *
     * @param head 头结点
     * @param val  计算规则
     * @return 返回处理好的链表头结点
     */
    public ListNode removeElements(ListNode head, int val) {
        // 求解最基本问题
        if (head == null) {
            return null;
        }
        if (head.val == val) {
            // 需要删除head，则只需要计算子问题就可以了
            return removeElements(head.next, val);
        } else {
            // 需要保留head,怎么保留呢？
            head.next = removeElements(head.next, val);
            return head;
        }
    }


    public static class ListNode {

        public int val;
        public ListNode next;

        public ListNode(int x) {
            val = x;
        }

        // 链表节点的构造函数
        // 使用arr为参数，创建一个链表，当前的ListNode为链表头结点
        public ListNode(int[] arr) {

            if (arr == null || arr.length == 0) {
                throw new IllegalArgumentException("arr can not be empty");
            }
            this.val = arr[0];
            // 将当前结点的作为头结点-head
            ListNode cur = this;
            // 迭代赋值
            for (int i = 1; i < arr.length; i++) {
                cur.next = new ListNode(arr[i]);
                cur = cur.next;
            }
        }

        // 以当前节点为头结点的链表信息字符串
        @Override
        public String toString() {

            StringBuilder s = new StringBuilder();
            ListNode cur = this;
            while (cur != null) {
                s.append(cur.val).append("->");
                cur = cur.next;
            }
            s.append("NULL");
            return s.toString();
        }
    }

    public static void main(String[] args) {

        ListNode head = new ListNode(new int[]{1, 3, 6, 4, 6});
        Solution206RemoveElementsWithRecursion solution = new Solution206RemoveElementsWithRecursion();
        ListNode listNode = solution.removeElements(head, 6);
        System.out.println("listNode.toString() = " + listNode.toString());
    }

}

