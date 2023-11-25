package me.study.blocking;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {
    static final int NUMBER_OF_TASKS = 1_000;
    public static void main(String[] args) {
        System.out.printf("%d개의 태스크를 실행합니다.\n", NUMBER_OF_TASKS);

        long start = System.currentTimeMillis();
        run();
        System.out.printf("=== %dms 소요됨 ===", System.currentTimeMillis() - start);
    }

    private static void run() {
        try (ExecutorService executorService = Executors.newCachedThreadPool()) {
            for (int i = 0; i < NUMBER_OF_TASKS; i++) {
                executorService.submit(CachedThreadPool::blockingIoOperation);
            }
        } catch (RuntimeException ignored) {}
    }

    private static void blockingIoOperation() {
        System.out.println("현재 스레드 = " + Thread.currentThread());
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException ignored) {}
    }
}
