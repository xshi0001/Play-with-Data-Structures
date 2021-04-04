/**
 * 反转单链表
 *
 * @author JClearLove
 * @date 2021/03/30 19:49
 */

public class ReverseLinkList<E> {

    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public ReverseLinkList() {
        dummyHead = new Node();
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
    public void add(int index, E e) {

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        // 先新增元素的下个结点指引信息
        // 新增结点赋值给前一个元素的下一个结点信息
        prev.next = new Node(e, prev.next);
        size++;
    }

    // 在链表头添加新的元素e
    public void addFirst(E e) {
        add(0, e);
    }

    // 在链表末尾添加新的元素e
    public void addLast(E e) {
        add(size, e);
    }

    // 获得链表的第index(0-based)个位置的元素
    // 在链表中不是一个常用的操作，练习用：）
    public E get(int index) {

        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Illegal index.");
        }

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    // 获得链表的第一个元素
    public E getFirst() {
        return get(0);
    }

    // 获得链表的最后一个元素
    public E getLast() {
        return get(size - 1);
    }

    // 修改链表的第index(0-based)个位置的元素为e
    // 在链表中不是一个常用的操作，练习用：）
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Illegal index.");
        }

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    // 查找链表中是否有元素e
    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    // 从链表中删除index(0-based)位置的元素, 返回删除的元素
    // 在链表中不是一个常用的操作，练习用：）
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        }

        Node prev = dummyHead;
        // 查找的前一个元素
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        //查找到被删除元素
        Node retNode = prev.next;
        // 将被删除元素的指引赋值给前一个元素的下一个元素
        prev.next = retNode.next;
        //被删除元素为null
        retNode.next = null;
        size--;

        return retNode.e;
    }

    // 从链表中删除第一个元素, 返回删除的元素
    public E removeFirst() {
        return remove(0);
    }

    // 从链表中删除最后一个元素, 返回删除的元素
    public E removeLast() {
        return remove(size - 1);
    }

    // 从链表中删除元素e
    public void removeElement(E e) {

        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.e.equals(e)) {
                break;
            }
            prev = prev.next;
        }

        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
        }
    }


    /**
     * 带虚拟头节点的反转链表
     *
     * @return
     */
    public void postOrderReverseList() {
        dummyHead.next = postOrderReverseList(dummyHead.next);
    }

    /**
     * 后续遍历：反转以head节点为头节点的链表，先迭代后主逻辑（）
     * <p>
     * 理的不用管。相应地如果是后序遍历，那么你可以想象后面的链表都处理好了，怎么处理的不用管
     */
    private Node postOrderReverseList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node next = head.next;

        Node newHead = postOrderReverseList(next);

        // （改变指针）在进入后面的节点的后面，也就是递归返回的过程会执行到
        //    # 主逻辑（改变指针）在进入后面节点的前面（由于前面的都已经处理好了，因此不会有环）
        next.next = head;
        //  # 置空，防止环的产生
        head.next = null;
        return newHead;
    }

    /**
     * 前序遍历，将当前节点的下一个节点缓存后更改当前节点指针
     */
    public Node preOrderReverseListByRecursNR(Node head) {
        if (head == null) {
            return head;
        }
        // 上一节点
        Node pre = head;
        // 当前结点
        Node cur = head.next;
        // 临时结点，用于保存当前结点的指针域（即下一结点）
        Node tmp;
        while (cur != null) {
            // 当前结点为null，说明位于尾结点- 反转之前的留下联系方式
            tmp = cur.next;
            // 反转指针域的指向
            cur.next = pre;
            // 继续一下一次指针往下移动
            pre = cur;
            cur = tmp;
        }
        // 最后将原链表的头节点的指针域置为null，还回新链表的头结点，即原链表的尾结点
        head.next = null;
        return pre;
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        Node cur = dummyHead.next;
        while (cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");

        return res.toString();
    }

    public static void main(String[] args) {

        ReverseLinkList<Integer> linkedList = new ReverseLinkList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
        }
        System.out.println(linkedList);
        // 反转一个链表
        linkedList.postOrderReverseList();

        System.out.println(linkedList);
    }


}

