package com.datastruct.custom;

public class CustomStackByArray<T> {
    int topofStack = -1;
    int capacity = 16;
    Object[] array = new Object[capacity];

    public boolean push(T t) {
        array[++topofStack] = t;
        return true;
    }

    public T pop() throws Exception {
        if (topofStack == -1) {
            throw new Exception();
        }
        return (T) array[topofStack--];
    }
}
