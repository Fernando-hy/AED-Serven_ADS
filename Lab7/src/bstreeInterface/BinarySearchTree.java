package bstreeInterface;

import exceptions.ItemDuplicated;
import exceptions.ExceptionIsEmpty;
import exceptions.ItemNotFound;

public interface BinarySearchTree<E> {
    void insert(E data) throws ItemDuplicated;
    boolean search(E data) throws ItemNotFound;
    void delete(E data) throws ExceptionIsEmpty, ItemNotFound;
    boolean isEmpty();
}
