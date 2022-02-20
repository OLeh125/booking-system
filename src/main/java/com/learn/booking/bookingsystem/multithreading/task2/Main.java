package com.learn.booking.bookingsystem.multithreading.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Main {

    private static List<Integer> list = new ArrayList<>();
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            while (true) {
                synchronized (lock) { // to avoid deadlock should use ReentrantLock instead. But not applicable for current case since only one intrinsic lock in use
                    addElement();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(50); // to guarantee the order
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true) {
                synchronized (lock) {
                    sumElements();
                }
            }
        });

        Thread thread3 = new Thread(() -> {
            try {
                Thread.sleep(100); // to guarantee the order
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true) {
                synchronized (lock) {
                    calculateSqrt();
                }
            }
        });

        System.out.println("Threads started working");
        thread1.start();
        thread2.start();
        thread3.start();

    }

    private static void calculateSqrt() {
        Optional<Integer> sum = sumElements();
        double sqrt = Math.sqrt(sum.orElseThrow());
        System.out.println("SQRT is " + sqrt);
    }

    private static Optional<Integer> sumElements() {
        Optional<Integer> sum = list.stream().reduce(Integer::sum);
        System.out.println("SUM is " + sum);
        return sum;
    }

    private static void addElement() {
        Random random = new Random();
        list.add(random.nextInt(10));
    }
}
