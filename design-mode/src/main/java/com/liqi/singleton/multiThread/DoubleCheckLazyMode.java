package com.liqi.singleton.multiThread;

public class DoubleCheckLazyMode {
    private DoubleCheckLazyMode() {//必须私有化构造方法，防止外部实例化
    }

    private static DoubleCheckLazyMode instance = null;

    public synchronized static DoubleCheckLazyMode getInstance() {//锁太粗，不需要在方法直接锁
        if (instance == null) {
            instance = new DoubleCheckLazyMode();
        }
        return instance;
    }

    public static DoubleCheckLazyMode getInstance2() {
        if (instance == null) {//可以省略，但是提高效率，减少拿锁的过程
            synchronized (DoubleCheckLazyMode.class) {
                if (instance == null) {//双重检查，方式多线程
                    //对象申请内存/初始化/内存地址赋值给引用变量，如果指令重排
                    instance = new DoubleCheckLazyMode();
                }
            }

        }

        return instance;
    }

    public static void main(String[] args) {

    }
}
