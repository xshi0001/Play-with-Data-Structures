/**
 * TODO
 *
 * @author JClearLove
 * @date 2021/03/22 20:05
 */

public class Solution206RemoveElements {



    public static class LinkedList<E> {
        private ListNode dummyHead;
        private int size;

        LinkedList() {
            dummyHead = new ListNode();
            size = 0;
        }

        // 在链表的index(0-based)位置添加新的元素e
        // 在链表中不是一个常用的操作，练习用：）
        void add(int index, int e) {

            if (index < 0 || index > size) {
                throw new IllegalArgumentException("Add failed. Illegal index.");
            }

            ListNode prev = dummyHead;
            for (int i = 0; i < index; i++) {
                prev = prev.next;
            }

            prev.next = new ListNode(e, prev.next);
            size++;
        }

        // 在链表头添加新的元素e
        void addFirst(int e) {
            add(0, e);
        }

    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(0, head);
        ListNode prev = dummyHead;
        while (prev.next != null) {
            //如果下一个结点value等于删除值
            if (prev.next.val == val) {
                //缓存prev.next值，并将删除元素的值下一个结点赋值给prev.next
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
            }else {
                // 如果不相等，则迭代prev
                prev = prev.next;
            }
        }
        return dummyHead.next;
    }

    private String toString(ListNode l) {
        StringBuilder res = new StringBuilder();

        ListNode cur = l;
        while (cur != null) {
            res.append(cur.val).append("->");
            cur = cur.next;
        }
        res.append("NULL");

        return res.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> l1 = new LinkedList<>();
        l1.addFirst(2);
        l1.addFirst(1);
        l1.addFirst(2);
        l1.addFirst(2);
        Solution206RemoveElements solution206RemoveElements = new Solution206RemoveElements();
        ListNode listNode = solution206RemoveElements.removeElements(l1.dummyHead.next, 2);
        System.out.println("listNode.toString() = " + solution206RemoveElements.toString(listNode));
    }

        public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}

