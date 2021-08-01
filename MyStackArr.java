import java.util.Arrays;
import java.util.Objects;

public class MyStackArr<T> {
    private T[] arr = (T[]) new Object[1];
    private int N = 0;

    public boolean isEmpty() {
        return arr.length == 1;
    }

    public T pop() {
        T item = arr[--N];
        arr[N] = null;
        if (N > 0 && N == arr.length/4)
            resize(arr.length/2);
        return item;
    }

    public String printStack() {
        return Arrays.toString(arr);
    }

    public void push(T item) {
        if (N == arr.length){
            resize(2 * arr.length);
        }
        arr[N++] = item;
    }

    private void resize(int capacity) {
        T[] copy = (T[])new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = arr[i];
        }
        arr = copy;
    }
}
