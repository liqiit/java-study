package com.liqi.singleton.singleThread;

public class LazyMode {
    private static LazyMode instance = null;

    public static LazyMode getInstance() {
        if (instance == null) {
            instance = new LazyMode();
        }
        return instance;
    }

    public static void main(String[] args) {
        LazyMode l1 = LazyMode.getInstance();
        LazyMode l2 = LazyMode.getInstance();
        System.out.println(l1);
        System.out.println(l2);
    }
}
