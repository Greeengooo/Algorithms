

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyBag<T> implements Iterable<T>{
    private Node<T> first, last;
    private int N;
    private static class Node<T> {
        T data;
        Node next;
    }

    public MyBag() {
        first = null;
        N = 0;
    }


    public boolean isEmpty() {
        return first==null;
    }
    public int size() {
        return N;
    }
    public void add(T elem){
        Node<T> oldFirst = first;
        if (isEmpty())
             last = first;
        first = new Node<>();
        first.data = elem;
        first.next = oldFirst;
        N++;
    }


    @Override
    public Iterator<T> iterator() {
        return new ListIterator(first);
    }

    private class ListIterator implements Iterator<T>{
        private Node<T> current;

        public ListIterator(Node<T> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            T item = current.data;
            current = current.next;
            return item;
        }
    }

}
