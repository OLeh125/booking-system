package com.learn.booking.bookingsystem.multithreading.task4;

import lombok.SneakyThrows;

public class Main {
    private static BlockingObjectPool pool = new BlockingObjectPool(10);

    public static void main(String[] args) {
        Thread thread1 = new Thread(Main::produce);
        Thread thread2 = new Thread(Main::consume);

        thread1.start();
        thread2.start();

    }

    public static void produce() {
        while (true) {
            pool.put(new Object());
        }
    }

    @SneakyThrows(InterruptedException.class)
    public static void consume() {
        while (true) {
            Thread.sleep(1);
            System.out.println("Element is " + pool.get());
            System.out.println("Pool size is " + pool.size());
        }
    }
}
