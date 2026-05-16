package as;

public class TestQueue {
    public static void main(String[] args) throws ExceptionIsEmpty {

        //Integer
        QueueArray<Integer> qi = new QueueArray<>(5);
        qi.enqueue(10);
        qi.enqueue(20);
        qi.enqueue(30);
        System.out.println("Cola:    " + qi);
        System.out.println("Frente:  " + qi.front());
        System.out.println("dequeue: " + qi.dequeue());
        System.out.println("Cola:    " + qi);

        QueueArray<String> qs = new QueueArray<>(3);
        qs.enqueue("Ana");
        qs.enqueue("Luis");
        qs.enqueue("Pedro");
        qs.enqueue("Maria"); // debe decir "Cola llena"
        System.out.println("Cola:    " + qs);
        System.out.println("Frente:  " + qs.front());
        System.out.println("dequeue: " + qs.dequeue());
        System.out.println("Cola:    " + qs);
    }
}