import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Tester {


    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(25);
        list.add(26);
        list.add(27);
        list.add(28);
        list.remove();
        list.printList(list);

        System.out.println();
        System.out.println("-----------Stack----LinkedList------");

        MyStackLinkedList<Integer> stackList = new MyStackLinkedList<>();
        stackList.push(2);
        stackList.push(3);
        stackList.push(4);
        stackList.push(5);
        System.out.println("pop: " + stackList.pop());
        System.out.print("Print stack: ");stackList.printStack(stackList);

        System.out.println();
        System.out.println("-----------Stack----Array------");
        MyStackArr<Integer> stackArr = new MyStackArr<>();
        stackArr.push(2);
        stackArr.push(3);
        stackArr.push(4);
        stackArr.push(5);
        System.out.println("pop: " + stackArr.pop());
        System.out.print("Print stack: " + stackArr.printStack());


        System.out.println();
        System.out.println("-----------Queue----LinkedList------");
       QueueLinkedList<Integer> queueList = new QueueLinkedList<>();
        queueList.enqueue(2);
        queueList.enqueue(3);
        queueList.enqueue(4);
        queueList.enqueue(5);
        System.out.println("pop: " + queueList.dequeue());
        System.out.print("Print queue: "); queueList.printQueue(queueList);

        System.out.println();
        System.out.println("-----------Bag----------");
        MyBag<Integer> myBag = new MyBag<>();
        System.out.println(myBag.isEmpty());
        myBag.add(1);
        myBag.add(2);
        myBag.add(3);
        myBag.add(4);

        System.out.println("Size: " + myBag.size());
        for (Integer i: myBag) {
            System.out.print(i + " ");
        }


        System.out.println();
        System.out.println("With Iterator");
        Iterator<Integer> i = myBag.iterator();
        while(i.hasNext()) {
            Integer element = i.next();
            System.out.print(element + " ");
        }
    }
}
