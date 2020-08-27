package com.jvm.reference;

import java.lang.ref.SoftReference;

public class SoftReferenceTest {
    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        SoftReference<Object> softRef = new SoftReference<Object>(obj);
        System.out.println(softRef.get());// null

        // 清除强引用,触发GC
        obj = null;
        System.gc();

        System.out.println(softRef.get());

        Thread.sleep(200);
        System.out.println(softRef.get());


    }


}
