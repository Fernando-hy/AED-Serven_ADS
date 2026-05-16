package asd;

import as.ExceptionIsEmpty;

public class TestDeque {
    public static void main(String[] args) throws ExceptionIsEmpty {

        //Integer
        DequeLink<Integer> dq = new DequeLink<>();

        dq.addLast(10);
        dq.addLast(20);
        dq.addLast(30);
        dq.addFirst(5);
        System.out.println("Deque:       " + dq.toString());
        System.out.println("Primero:     " + dq.getFirst());
        System.out.println("Último:      " + dq.getLast());
        System.out.println("removeFirst: " + dq.removeFirst());
        System.out.println("removeLast:  " + dq.removeLast());
        System.out.println("Deque final: " + dq);

        //String
        DequeLink<String> dqs = new DequeLink<>();
        dqs.addLast("Ana");
        dqs.addLast("Luis");
        dqs.addFirst("Zoe");
        System.out.println("Deque:       " + dqs.toString());
        System.out.println("removeFirst: " + dqs.removeFirst());
        System.out.println("removeLast:  " + dqs.removeLast());
        System.out.println("Deque final: " + dqs);
    }
}