package com.jvm.reference;

import org.openjdk.jol.info.ClassLayout;

public class ObjectTest {
    public static void main(String[] args) {
        Object object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
        synchronized (object) {
            System.out.println(ClassLayout.parseInstance(object).toPrintable());

        }

//        User o=new User();
//        o.setName("liqi");
//        o.setAge(10);
//        o.setBalance(1000);
//        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}

class User {
    private String name;
    private int age;
    private long balance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}