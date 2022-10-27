package me.study.section02;

public class Test3 {

    public static void main(String[] args) {
        final NewThread thread = new NewThread();
        thread.start();
    }

    private static class NewThread extends Thread {

        @Override
        public void run() {
            System.out.println("Hello from " + Thread.currentThread().getName());
        }
    }
}
