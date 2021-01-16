package com.datastruct.generic;

/***
 * 泛型类 声明自定义类型,用尖括号包含一个或多个参数类型  <T1,T2,t3>
 * @param <Anytype>
 */
public class GenericMemoryzCell<Anytype> {
    private Anytype storedValue;

    public Anytype readValue() {
        return storedValue;
    }

    public void writeValue(Anytype x) {
        this.storedValue = x;
    }
}
