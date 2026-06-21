package listlinked;

public class ListLinked<T> {  // quitamos extends Comparable<T>
    private Node<T> first;

    public ListLinked() {
        this.first = null;
    }

    public boolean isEmptyList() {
        return this.first == null;
    }

    public void insertFirst(T value) {
        Node<T> newNode = new Node<>(value);
        if (!isEmptyList())
            newNode.setNext(this.first);
        this.first = newNode;
    }

    public void insertLast(T value) {
        Node<T> newNode = new Node<>(value);
        if (isEmptyList()) {
            this.first = newNode;
        } else {
            Node<T> actual = this.first;
            while (actual.getNext() != null)
                actual = actual.getNext();
            actual.setNext(newNode);
        }
    }

    public boolean removeNode(T value) {
        if (isEmptyList()) return false;

        if (this.first.getValue().equals(value)) {  // compareTo → equals
            this.first = this.first.getNext();
            return true;
        }
        Node<T> actual = this.first;
        while (actual.getNext() != null && !actual.getNext().getValue().equals(value))
            actual = actual.getNext();

        if (actual.getNext() == null) return false;
        actual.setNext(actual.getNext().getNext());
        return true;
    }

    public boolean search(T value) {
        if (isEmptyList()) return false;
        Node<T> actual = this.first;
        while (actual != null) {
            if (actual.getValue().equals(value)) return true;
            actual = actual.getNext();
        }
        return false;
    }

    public int size() {
        int count = 0;
        Node<T> actual = this.first;
        while (actual != null) { count++; actual = actual.getNext(); }
        return count;
    }

    // get por índice — necesario para recorrer en GraphLink
    public T get(int index) {
        Node<T> actual = this.first;
        for (int i = 0; i < index; i++)
            actual = actual.getNext();
        return actual.getValue();
    }
    
    public void removeAt(int index) {
        if (isEmptyList()) return;
        if (index == 0) {
            first = first.getNext();
            return;
        }
        Node<T> actual = first;
        for (int i = 0; i < index - 1; i++)
            actual = actual.getNext();
        actual.setNext(actual.getNext().getNext());
    }

    public void print() {
        if (isEmptyList()) {
            System.out.println("La lista no tiene elementos");
        } else {
            Node<T> actual = this.first;
            while (actual != null) {
                System.out.print(actual.getValue() + " -> ");
                actual = actual.getNext();
            }
            System.out.println("null");
        }
    }

    public Node<T> getFirst() { return this.first; }

    public static class Node<T> {  // quitamos extends Comparable<T>
        private T value;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
            this.next = null;
        }

        public T getValue()               { return value; }
        public void setValue(T value)     { this.value = value; }
        public Node<T> getNext()          { return next; }
        public void setNext(Node<T> next) { this.next = next; }
    }
}