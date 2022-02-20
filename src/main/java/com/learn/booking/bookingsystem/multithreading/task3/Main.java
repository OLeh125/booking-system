package com.learn.booking.bookingsystem.multithreading.task3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import lombok.SneakyThrows;

public class Main {

    public static void main(String[] args) {
        ProduceConsumer produceConsumer = new ProduceConsumer();
        Thread thread1 = new Thread(produceConsumer::produce);
        Thread thread2 = new Thread(produceConsumer::consume);

        thread1.start();
        thread2.start();
    }

}

class ProduceConsumer {

    private Queue<Integer> queue = new LinkedList<>();
    private final int LIMIT = 10;
    private final Object lock = new Object();

    @SneakyThrows(InterruptedException.class)
    public void produce() {
        Random random = new Random();
        while (true) {
            synchronized (lock) {
                while (queue.size() == LIMIT) {
                    lock.wait();
                }
                queue.offer(random.nextInt(100));
                lock.notify();
            }
        }
    }

    @SneakyThrows(InterruptedException.class)
    public void consume() {
        while (true) {
            synchronized (lock) {
                while (queue.isEmpty()) {
                    lock.wait();
                }
                System.out.println("Element is " + queue.poll());
                System.out.println("Queue size is " + queue.size());
                lock.notify();
            }
            Thread.sleep(1);
        }
    }

}
