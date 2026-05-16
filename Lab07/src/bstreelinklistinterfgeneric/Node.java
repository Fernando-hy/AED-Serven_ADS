package bstreelinklistinterfgeneric;

public class Node<E> {
    private E data;
    private Node<E> left;
    private Node<E> right;

    public Node(E value) {
        this.data = value;
        this.left = null;
        this.right = null;
    }

    public E getData() { 
    	return data; 
    }
    public void setData(E d) { 
    	this.data=d; 
    }
    public Node<E> getLeft() { 
    	return left; 
    }
    public void setLeft(Node<E> left) { 
    	this.left = left; 
    }
    public Node<E> getRight() { 
    	return right; 
    }
    public void setRight(Node<E> right) { 
    	this.right = right; 
    }
}

