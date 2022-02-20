package com.learn.booking.bookingsystem.multithreading.task1;

import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    // private static Map<Integer, Integer> map = new HashMap<>(); // produce ConcurrentModificationException, all methods are not thread save
     private static Map<Integer, Integer> map = new ConcurrentHashMap<>(); // does not produce ConcurrentModificationException, all methods are not thread save
    // private static Map<Integer, Integer> map = Collections.synchronizedMap(new HashMap<>());// produce ConcurrentModificationException all methods are  thread save
    //TODO add benchmarking
    //TODO task 5 wasn't done since looks useless for me)

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            Random random = new Random();
            int index = 0;
            while (true) {
                map.put(index++, random.nextInt(10));
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                Optional<Integer> sum = map.values().stream().reduce(Integer::sum);
                System.out.println(sum);
            }
        });

        System.out.println("Threads started working");
        thread1.start();
        thread2.start();


    }

}
