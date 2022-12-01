package com.javaRx.demo;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

/***
 * @Author :liqi
 * @CreateTime: 2022-05-18 17:08
 * @Description: TODO
 */
public class ObservableDemo {
    /***
     *
     * @param names
     */
    public static void hello(String... names) {
        Observable.from(names).subscribe(s -> System.out.println("Hello " + s + "!"));
    }

    public static void just() {
        Observable.just(1, 2, 3, 4, 5).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("completed");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("error");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext:" + integer);
            }


        });
    }

    public static void schedule() {

    }

    public static void main(String[] args) {
        //hello("liqi");
        just();
    }
}
