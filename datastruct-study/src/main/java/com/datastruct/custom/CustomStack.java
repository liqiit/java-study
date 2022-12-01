package com.datastruct.custom;

import java.util.LinkedList;

public class CustomStack<T> {
    LinkedList list = new LinkedList();

    public boolean push(T t) {
        list.addFirst(t);
        return true;
    }

    public T pop() {
        T t = (T) list.removeFirst();
        return t;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
