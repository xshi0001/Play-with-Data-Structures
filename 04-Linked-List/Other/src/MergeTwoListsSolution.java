class MergeTwoListsSolution {


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 如果有一个为null
        // 目标就是将通过cur去帮dummyHead找到他的下面的所有子节点
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                // find next node for cur node
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }

            // 寻找一个
            cur = cur.next;
        }
        // 只有有null就不需要继续往下面找了，只需要告诉cur下一个子节点在哪一个结点上
        cur.next = l1 == null ? l2 : l1;
        // 最后返回什么呢？-根据链表，知道一个结点，就可以知道这个结点的下面的所有结点，显然应该是dummyHead
        return dummyHead.next;
    }

    public String toString(ListNode l) {
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
        l1.addFirst(4);
        l1.addFirst(2);
        LinkedList<Integer> l2 = new LinkedList<>();
        l2.addFirst(3);
        l2.addFirst(1);

        MergeTwoListsSolution mergeTwoListsSolution = new MergeTwoListsSolution();
        ListNode listNode = mergeTwoListsSolution.mergeTwoLists(l1.dummyHead.next, l2.dummyHead.next);
        System.out.println(mergeTwoListsSolution.toString(listNode));
    }


    public static class LinkedList<E> {
        private ListNode dummyHead;
        private int size;

        LinkedList() {
            dummyHead = new ListNode();
            size = 0;
        }

        // 获取链表中的元素个数
        public int getSize() {
            return size;
        }

        // 返回链表是否为空
        public boolean isEmpty() {
            return size == 0;
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

        // 在链表末尾添加新的元素e
        public void addLast(int e) {
            add(size, e);
        }


    }

     // data structrue
    private static class ListNode {
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
