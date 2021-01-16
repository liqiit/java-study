package com.datastruct.generic;

import java.util.Collection;
import java.util.List;

public class CollectionUtil<A> {

    public static void totalNum(Person[] people) {

    }

    public static void totalNum(Collection<? extends Person> employees) {

    }

    public static <CompareType> boolean check(List<CompareType> list, CompareType compareType) {
        return list.contains(compareType);
    }
    //静态方法和静态域不能引用类泛型类型
//    public static A get(A a){
//        return a;
//    }
}
