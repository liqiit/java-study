package com.datastruct.generic;

import com.datastruct.custom.CacheUtil;
import com.datastruct.custom.CustomArrayList;
import com.datastruct.custom.CustomLinkedList;
import org.junit.Test;

import java.util.*;

public class GenericTest {
    @Test
    public void testCovariantArray() {
        Person[] people = new Employee[5];
        people[0] = new Student();
        people[1] = new Employee();
        System.out.println(people[0].getClass());
    }

    @Test
    public void testGenericClass() {
        GenericMemoryzCell<String> a = new GenericMemoryzCell<>();
        a.writeValue("s");
        System.out.println(a.readValue());
    }

    @Test
    public void testCovariantArrayCollection() {
        //数组具有类型协变性，参数父类数组声明，传入子类数组编译可以通过
        CollectionUtil.totalNum(new Student[]{});
        //集合没有协变性，参数父类集合声明，传入子类集合编译无法通过
        //集合可以通过通配符匹配 例如<? extends Person>来进行协变性
        List<Student> list = new ArrayList<>();
        CollectionUtil.totalNum(list);
        List<String> compareList = new ArrayList<>();
        compareList.add("a");

        //CollectionUtil.check(compareList,3);
    }

    @Test
    public void testLinkedList() {
        LinkedList<Integer> linkedList = new LinkedList();
        for (int i = 0; i < 10000; i++) {
            linkedList.add(i);
        }
        int j = 0;
        long start = System.currentTimeMillis();
        Iterator iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            Integer temp = (Integer) iterator.next();
            if ((temp % 2) == 0) {
                iterator.remove();
            }
        }
//        while (j < linkedList.size()) {
//            if ((linkedList.get(j) % 2) == 0) {//LinkedList通过索引获取元素很慢，需要通过迭代计算数量
//                linkedList.remove();
//            }
//        }
        long end = System.currentTimeMillis();
        linkedList.forEach(temp -> {
            System.out.println(temp);
        });
        System.out.println("耗时：" + (end - start));
    }

    @Test
    public void testArrayList() {
        ArrayList<Integer> linkedList = new ArrayList();
        for (int i = 0; i < 100; i++) {
            linkedList.add(i);
        }
        int j = 0;
        long start = System.currentTimeMillis();
//        Iterator iterator = linkedList.iterator();
//        while (iterator.hasNext()) {
//            Integer temp = (Integer) iterator.next();
//            if ((temp % 2) == 0) {
//                iterator.remove();
//            }
//        }
        while (j < linkedList.size()) {
            if ((linkedList.get(j) % 2) == 0) {// LinkedList通过索引获取元素很慢，需要迭代计算数量
                linkedList.remove(j);
            } else {
                j++;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
        linkedList.forEach(temp -> {
            System.out.println(temp);
        });
    }

    @Test
    public void testCustomArrayList() {
        CustomArrayList<String> list = new CustomArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.remove(4);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    public void testCustomLinkedList() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("a");
        list.add("b");
    }
    @Test
    public void testTimeoutCache(){
        try {
            CacheUtil.setCache("hello","hello");
            Object value = CacheUtil.getCache("hello");
            Thread.sleep(100000);
            value = CacheUtil.getCache("hello");
            System.out.println(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
