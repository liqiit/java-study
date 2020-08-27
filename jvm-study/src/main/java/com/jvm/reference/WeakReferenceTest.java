package com.jvm.reference;

import java.lang.ref.WeakReference;

public class WeakReferenceTest {
    public static void main(String[] args) {
        WeakReference<Object> o = new WeakReference<>(new Object());
        System.out.println(o.get());
        System.gc();
        System.out.println(o.get());
    }
}
