package listlinked;

import actividad1.ExceptionIsEmpty;

public class StackLink<E> implements Stack<E> {
    private Node<E> top;

    public StackLink() {
        this.top = null;
    }

    public void push(E x) {
        Node<E> newNode = new Node<>(x);
        newNode.setNext(top); // apunta al anterior tope
        top = newNode;        // el nuevo nodo es el tope
    }

    public E pop() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Pila vacía");
        E data = top.getData();
        top = top.getNext(); // el tope baja un nivel
        return data;
    }

    public E top() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Pila vacía");
        return top.getData();
    }

    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("tope -> [");
        Node<E> actual = top;
        while (actual != null) {
            sb.append(actual.getData());
            if (actual.getNext() != null) sb.append(", ");
            actual = actual.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
}