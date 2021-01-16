package com.datastruct.custom;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomLinkedList<T> implements Iterable {

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void clear() {

    }

    public void doClear() {
        beginMarker = new Node<>(null, null, null);
        endMarker = new Node<>(beginMarker, null, null);
        theSize = 0;
        modSize++;
    }

    public boolean add(T t) {
        add(size(), t);
        return true;
    }

    public void add(int idx, T t) {
        addBefore(getNode(idx, 0, size()), t);
    }

    private void addBefore(Node<T> node, T t) {
        Node<T> newNode = new Node<>(node.previous, node, t);
        node.previous.next = newNode;
        node.previous = newNode;
        theSize++;
        modSize++;
    }

    public T remove(int idx) {
        return remove(getNode(idx));
    }

    private T remove(Node<T> node) {
        node.previous.next = node.next;
        node.next.previous = node.previous;
        theSize--;
        modSize++;
        return node.value;
    }

    private Node<T> getNode(int idx) {
        return getNode(idx, size() - 1, 0);
    }

    /****
     * 查找指定位置节点
     * @param idx 节点所在位置
     * @param lower 最低有效索引
     * @param upper 最高有效索引
     * @return
     */
    private Node<T> getNode(int idx, int lower, int upper) {
        Node<T> p;
        if (idx > upper || idx < lower) {//判断当前索引是否越界
            throw new ArrayIndexOutOfBoundsException();
        }
        if (idx < size() / 2) {//如果索引在前半段，则从前向后查找
            p = beginMarker.next;
            for (int i = 0; i < idx; i++) {
                p = p.next;
            }
        } else {//如果索引在后半段，则从尾部向前查找
            p = endMarker.previous;
            for (int i = size(); i > idx; i--) {
                p = p.previous;
            }
        }
        return p;
    }

    public T set(int idx, T t) {
        Node<T> node = getNode(idx);
        T oldValue = node.value;
        node.value = t;
        return oldValue;
    }

    @Override
    public Iterator iterator() {
        return new LinkedListIterator();
    }

    private int theSize;
    private int modSize = 0;
    private Node<T> beginMarker;
    private Node<T> endMarker;

    private class LinkedListIterator implements Iterator<T> {

        private Node<T> currentNode = beginMarker.next;
        private int expectModCount = modSize;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return currentNode != endMarker;
        }

        @Override
        public T next() {
            if (modSize != expectModCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T itemValue = currentNode.value;
            currentNode = currentNode.next;
            okToRemove = true;
            return itemValue;
        }

        @Override
        public void remove() {
            if (modSize != expectModCount) {
                throw new ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new IllegalStateException();
            }
            CustomLinkedList.this.remove(currentNode.previous);
        }
    }

    private static class Node<T> {
        public Node<T> previous;
        public Node<T> next;
        public T value;

        public Node(Node<T> previous, Node<T> next, T value) {
            this.previous = previous;
            this.next = next;
            this.value = value;
        }
    }
}
