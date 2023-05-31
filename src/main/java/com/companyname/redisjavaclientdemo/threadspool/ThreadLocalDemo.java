package com.companyname.redisjavaclientdemo.threadspool;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ThreadLocalDemo {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ThreadLocal<String> strHolder = new ThreadLocal<>();
        strHolder.set("hello world");


        // Q: 是否能够根据当前线程实例获取到当前线程有几个 threadlocal?
        ThreadLocal<Object> tempThreadLocal = new ThreadLocal<>();
        Method m = ThreadLocal.class.getDeclaredMethod("getMap", Thread.class);

        // Exception in thread "main" java.lang.reflect.InaccessibleObjectException:
        // Unable to make java.lang.ThreadLocal$ThreadLocalMap
        // java.lang.ThreadLocal.getMap(java.lang.Thread)
        // accessible: module java.base does not "opens java.lang" to unnamed module @76fb509a
        m.setAccessible(true);

//        ThreadLocal.ThreadLocalMap
        Thread currentThread = Thread.currentThread();

        Object obj = m.invoke(tempThreadLocal,currentThread);
        System.out.println(obj);

        // A: 不行。。。。。。。。


    }
}
