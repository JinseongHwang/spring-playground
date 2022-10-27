package me.study.section02;

import java.lang.Thread.UncaughtExceptionHandler;

public class Test2 {

    public static void main(String[] args) {

        final Thread thread = new Thread(() -> {
            throw new RuntimeException("runtime exception");
        });

        thread.setName("Misbehaving thread");

        thread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("A critical error happened in thread " + t.getName()
                                   + " the error is " + e.getMessage());
            }
        });
        thread.start();
    }
}
