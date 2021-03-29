package print;

public class Node<T extends Comparable<?>> {
    public Node<T> left, right;
    public T e;

    public Node(T data) {
        this.e = data;
    }
}
