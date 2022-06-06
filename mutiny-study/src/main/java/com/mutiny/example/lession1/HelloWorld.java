package com.mutiny.example.lession1;

import io.smallrye.mutiny.Uni;

import java.time.Duration;

/**
 * @Author Liqi
 * @Version 1.0.0
 * @Description TODO
 * @CreateTime 2022/4/24 16:50
 */
public class HelloWorld {
    public static void main(String[] args) {
        Uni<String> hello = Uni.createFrom().item("hello")
                .onItem().transform(String::toUpperCase);
               // .onItem().delayIt().by(Duration.ofMinutes(1));

//        hello.subscribe().with(
//                item-> System.out.println(item),
//                failure-> System.out.println(failure.getCause())
//        );

        hello.onItem().invoke(item -> System.out.println("received item:" + item));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
