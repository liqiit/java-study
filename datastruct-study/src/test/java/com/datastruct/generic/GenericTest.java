package com.datastruct.generic;

import com.datastruct.custom.CustomArrayList;
import com.datastruct.custom.CustomLinkedList;
import com.datastruct.custom.CustomStack;
import com.datastruct.custom.CustomStackByArray;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class GenericTest {
    @Test
    public void testCovariantArray() {
        ReentrantLock lock = new ReentrantLock(true);
        lock.lock();
        Person[] people = new Employee[5];
        people[0] = new Student();
        people[1] = new Employee();
        System.out.println(people[0].getClass());
        lock.unlock();
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
    public void testCustomStack() {
        CustomStack stack = new CustomStack();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        System.out.println(stack.pop());
    }

    @Test
    public void testCustomStackByArray() throws Exception {
        CustomStackByArray stack = new CustomStackByArray();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        System.out.println(stack.pop());
    }

    @Test
    public void testBalacnCompile() throws Exception {
        CustomStack stack = new CustomStack();
        String pushArray = "[({";
        String popArray = "])}";
        String testStr = "()[]{";
        for (char c : testStr.toCharArray()) {
            if (pushArray.contains(String.valueOf(c))) {
                stack.push(c);
            }
            if (popArray.contains(String.valueOf(c))) {
                if (stack.isEmpty()) {
                    throw new Exception();
                }
                stack.pop();
            }
        }
        if (!stack.isEmpty()) {
            throw new Exception();
        }
    }

    /***
     * 后缀表达式计算
     */
    @Test
    public void testMiddleSuffixCaculate() {
        String str = "6523+8*+3+*";
        String numberStr = "0123456789";
        String operatorStr = "+-*/()";
        CustomStack customStack = new CustomStack();
        for (char c : str.toCharArray()) {
            if (numberStr.contains(String.valueOf(c))) {
                customStack.push(c);
            }
            if (operatorStr.contains(String.valueOf(c))) {
                int num1 = Integer.valueOf(String.valueOf(customStack.pop()));
                int num2 = Integer.valueOf(String.valueOf(customStack.pop()));
                int result = 0;
                if (String.valueOf(c).equals("+")) {
                    result = num2 + num1;
                } else if (String.valueOf(c).equals("-")) {
                    result = num2 - num1;
                } else if (String.valueOf(c).equals("*")) {
                    result = num2 * num1;
                } else if (String.valueOf(c).equals("/")) {
                    result = num2 / num1;
                }
                customStack.push(result);
            }
        }
        System.out.println(customStack.pop());
    }

    @Test
    public void test() {
        Map<String, String> map = new HashMap<>();

        List<String> list1 = new ArrayList<>();
        LinkedList<String> list = new LinkedList<>();

        list.forEach(s -> {

        });
        list1.forEach(s -> {
            map.put(s, s);
        });
        List<String> list2 = new ArrayList<>();
        list2.forEach(s -> {
            if (map.containsKey(s)) {
                System.out.println("存在");
            }
        });


        list1.forEach(s -> {

        });
    }

}
