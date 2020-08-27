package com.jvm.reference;

import java.io.IOException;

public class StrongReferenceTest {
    public static void main(String[] args) throws IOException {
        M s = new M();
        s = null;
        System.gc();
        System.out.println(s);
    }
}

class M {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}