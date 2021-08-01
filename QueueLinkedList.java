public class QueueLinkedList<T> {
private Node head,last = null;
    private class Node{
        T data;
        Node next;
    }

    public boolean isEmpty()
    { return head == null; }

    public void enqueue(T elem) {
        Node oldLast = last;
        last = new Node();
        last.data = elem;
        last.next =  null;
        if (isEmpty())
            head = last;
        else
            oldLast.next = last;
    }

    public T dequeue() {
        T item = head.data;
        head = head.next;
        if (isEmpty())
            last = null;
        return item;
    }

    public void printQueue(QueueLinkedList queueLinkedList) {
        Node current = head;
        while (current != null){
            System.out.print(current.data + " ");
            current = current.next;
        }
    }
}
