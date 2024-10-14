package com.kabu.bif_eventdemo.utils;

public class ThreadLocalUtil {
    private static final ThreadLocal threadLocal =new ThreadLocal();


    public static void set(Object valule){
        threadLocal.set(valule);
    }
    public static <T> T get(){
        return (T) threadLocal.get();
    }
    public static void remove(){
        threadLocal.remove();
    }
}
