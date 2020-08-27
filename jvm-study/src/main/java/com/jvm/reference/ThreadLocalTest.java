package com.jvm.reference;

public class ThreadLocalTest {
    static ThreadLocal<String> a = new ThreadLocal<>();
    static ThreadLocal<String> b = new ThreadLocal<>();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(a.get());
                a.set("a");
                System.out.println(a.get());
            }
        });
        System.out.println(a.get());
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(a.get());
                a.set("b");
                System.out.println(a.get());
            }
        });
        t1.start();
        t2.start();
    }
}
