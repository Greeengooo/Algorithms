public class MyStackLinkedList<T> {
    private Node head = null;
    private class Node{
        T data;
        Node next;
    }

    public void push(T elem) {
        Node oldFirst = head;
        head = new Node();
        head.data = elem;
        head.next = oldFirst;
    }

    public T pop() {
        T elem = head.data;
        head = head.next;
        return elem;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void printStack(MyStackLinkedList stack) {
        Node current = head;
        while (current != null){
            System.out.print(current.data + " ");
            current = current.next;
        }
    }
}
