package asd;

import as.ExceptionIsEmpty;

public class DequeLink<E> implements Deque<E> {
    private Node<E> first;
    private Node<E> last;

    public DequeLink() {
        first = null;
        last  = null;
    }

    public void addFirst(E x) {
        Node<E> newNode = new Node<>(x);
        if (isEmpty()) {
            first = newNode;
            last  = newNode;
        } else {
            newNode.setNext(first);
            first = newNode;
        }
    }

    public void addLast(E x) {
        Node<E> newNode = new Node<>(x);
        if (isEmpty()) {
            first = newNode;
            last  = newNode;
        } else {
            last.setNext(newNode);
            last = newNode;
        }
    }

    public E removeFirst() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Deque vacío");
        E data = first.getData();
        first = first.getNext();
        if (first == null) last = null; // quedó vacío
        return data;
    }

    public E removeLast() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Deque vacío");
        E data = last.getData();
        if (first == last) {
            // solo había un nodo
            first = null;
            last  = null;
        } else {
            // recorrer hasta el penúltimo nodo
            Node<E> actual = first;
            while (actual.getNext() != last) {
                actual = actual.getNext();
            }
            actual.setNext(null);
            last = actual;
        }
        return data;
    }

    public E getFirst() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Deque vacío");
        return first.getData();
    }

    public E getLast() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Deque vacío");
        return last.getData();
    }
    
    public int size() {
        int count = 0;
        Node<E> actual = first;
        while (actual != null) {
            count++;
            actual = actual.getNext();
        }
        return count;
    }

    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Node<E> actual = first;
        while (actual != null) {
            sb.append(actual.getData());
            if (actual.getNext() != null) sb.append(" <-> ");
            actual = actual.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
}