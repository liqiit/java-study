package com.jvm.reference;

import java.util.concurrent.TimeUnit;

public class Account {
    private int balance = 0;

    public synchronized void addBalance(int num) {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = num;

    }

    public synchronized int getBalance() {
        System.out.println(Thread.currentThread().getName());

        return balance;
    }

    volatile boolean running = true;

    void m() {
        System.out.println("start");

        while (running) {
//                try {
//                    TimeUnit.SECONDS.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
        }

        System.out.println("end");
    }

    public static void main(String[] args) {
//        Account account = new Account();
//        new Thread(() -> {
//            account.addBalance(100);
//        }).start();
////        try {
////            TimeUnit.SECONDS.sleep(10);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//        System.out.println(account.getBalance());
        Account t = new Account();
        new Thread(t::m).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.running = false;

    }
}
