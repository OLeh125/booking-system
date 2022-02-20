package com.learn.booking.bookingsystem.multithreading.task4;

import java.util.concurrent.ArrayBlockingQueue;

public class BlockingObjectPool {

    private ArrayBlockingQueue<Object> queue; // without ArrayBlockingQueue I would use approach from task 3

    public BlockingObjectPool(int size) {
        queue = new ArrayBlockingQueue<>(size);
        for (int i = 0; i < size; i++) {
            try {
                queue.put(new Object());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Object get() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException("Thread was Interrupted");
        }
    }

    public void put(Object o) {
        try {
            queue.put(o);
        } catch (InterruptedException e) {
            throw new RuntimeException("Thread was Interrupted");
        }
    }

    public int size() {
        return queue.size();
    }
}
