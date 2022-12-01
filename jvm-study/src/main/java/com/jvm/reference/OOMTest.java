package com.jvm.reference;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OOMTest
 * @Deacription 内存溢出模拟
 * @Author liqi
 * @Date 2021/9/23 10:16
 * @Version 1.0
 **/
public class OOMTest {
    private final static Integer K = 1024;

    public static void main(String[] args) {
        int size = K * K * 8;
        List<byte[]>list=new ArrayList<>();
        for(int i=0;i<K;i++){
            System.out.println("写入数据");
            try {
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
            list.add(new byte[size]);
        }
    }
}
