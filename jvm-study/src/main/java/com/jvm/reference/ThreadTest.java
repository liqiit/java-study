package com.jvm.reference;

public class ThreadTest {
    static void testJoin() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("a" + i);
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            try {
                t1.join();
                for (int i = 0; i < 10; i++) {
                    System.out.println("b" + i);
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        t2.start();
    }

    public static void main(String[] args) {
        testJoin();
    }
}
