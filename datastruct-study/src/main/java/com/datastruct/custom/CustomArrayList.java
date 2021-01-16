package com.datastruct.custom;

import java.util.Iterator;
import java.util.Objects;

public class CustomArrayList<E> implements Iterable<E> {
    private final static int DEFAULT_CAPACITY = 10;
    private int size;
    private E[] array = null;

    public CustomArrayList() {
        array = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public boolean add(E obj) {
        array[size++] = obj;
        return true;
    }

    public boolean add(int index, E obj) {
        //先判断是否需要扩容
        if (index > size()) {
            //ensureCapacity();
        }
        return true;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public E get(int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (E) array[index];
    }

    public E set(int index, E newVal) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        E oldVal = array[index];
        array[index] = newVal;
        return oldVal;
    }

    public E remove(int index) {
        //判断当前是否越界
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        E oldValue = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return oldValue;
    }

    public void ensureCapacity(int newCapacity) {
        if (newCapacity < size()) return;
        E[] oldArray = array;
        array = (E[]) new Object[newCapacity];
        for (int i = 0; i < size(); i++) {
            array[i] = oldArray[i];
        }
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
