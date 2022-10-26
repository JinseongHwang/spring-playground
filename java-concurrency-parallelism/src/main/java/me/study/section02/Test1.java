package me.study.section02;

public class Test1 {

    public static void main(String[] args) throws InterruptedException {

        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("We are now in thread : [" + Thread.currentThread().getName() + "]"); // 3. Thread-0 thread
            }
        });

        System.out.println("We are in thread : [" + Thread.currentThread().getName() + "] before starting a new thread"); // 1. main thread
        thread.start();
        System.out.println("We are in thread : [" + Thread.currentThread().getName() + "] after starting a new thread"); // 2. main thread

        Thread.sleep(10_000L); // main thread 반납 후 Thread-0 실행 됨.
    }
}
