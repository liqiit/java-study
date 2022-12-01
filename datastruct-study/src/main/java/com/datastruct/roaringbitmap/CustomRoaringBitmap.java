package com.datastruct.roaringbitmap;

import org.roaringbitmap.RoaringBitmap;

import java.util.function.Consumer;

/**
 * @ClassName RoaringBitmap
 * @Deacription 稀疏bitmap测试
 * @Author liqi
 * @Date 2021/8/13 11:11
 * @Version 1.0
 **/
public class CustomRoaringBitmap {
    public static void main(String[] args) {
        RoaringBitmap r1 = RoaringBitmap.bitmapOf(2, 4, 55, 10000);
        RoaringBitmap r2 = RoaringBitmap.bitmapOf(1, 4, 5, 1000);
        RoaringBitmap rr = RoaringBitmap.or(r1, r2);
        rr.forEach((Consumer<? super Integer>) r-> System.out.println(r));

    }
}
