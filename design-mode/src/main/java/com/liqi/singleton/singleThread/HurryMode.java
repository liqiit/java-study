package com.liqi.singleton.singleThread;

public class HurryMode {
    //天生线程安全，静态属性在类加载的过程就有JVM进行初始化
    private static final HurryMode instance = new HurryMode();

    public static HurryMode getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        HurryMode h1 = HurryMode.getInstance();
        HurryMode h2 = HurryMode.getInstance();
        System.out.println(h1);
        System.out.println(h2);
    }
}
