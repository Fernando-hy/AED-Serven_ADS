package as;

public class QueueArray<E> implements Queue<E> {
    private E[] array;
    private int front;
    private int rear;
    private int size;
    private int capacidad;

    @SuppressWarnings("unchecked")
	public QueueArray(int n) {
    	capacidad = n;
        array = (E[]) new Object[n];
        front = 0;
        rear  = -1;
        size  = 0;
    }

    @Override
    public void enqueue(E x) {
        if (isFull()) {
            System.out.println("Cola llena");
            return;
        }
        rear = (rear + 1) % capacidad;  // avanza circular
        array[rear] = x;
        size++;
    }

    @Override
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Cola vacía");
        E element = array[front];
        front = (front + 1) % capacidad; // avanza circular
        size--;
        return element;
    }

    @Override
    public E front() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Cola vacía");
        return array[front];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacidad;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[(front + i) % capacidad]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
