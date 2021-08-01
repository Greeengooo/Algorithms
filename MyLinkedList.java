import org.w3c.dom.Node;

import java.util.Iterator;
import java.util.LinkedList;

public class MyLinkedList<T> {
    Node head = null, tail = null;
    private class Node {
        T item;
        Node next;

        private Node(T element) {
            this.item = element;
        }
    }

    public void add(T elem) {
        Node newItem = new Node(elem);
        if (head == null){
           head = newItem;
           tail = newItem;
        }else{
            tail.next = newItem;
            tail = tail.next;
        }
    }

    public void remove() {
        Node temp = head;
        while (temp.next!=null && temp.next.next != null){
            temp = temp.next;
        }
        temp.next = null;
        tail = temp;
    }

    public void printList(MyLinkedList list) {
        Node current = list.head;
        while(current != null){
            System.out.print(current.item + " ");
            current = current.next;
        }
    }

}
