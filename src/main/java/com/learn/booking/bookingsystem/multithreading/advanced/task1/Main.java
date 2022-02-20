package com.learn.booking.bookingsystem.multithreading.advanced.task1;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        //TODO add benchmarking
        long start = System.currentTimeMillis();
        factorialRecursiveTask(200);
        System.out.println("RecursiveTask took : " + (System.currentTimeMillis() - start));

        long start2 = System.currentTimeMillis();
        computeDirectly(200);
        System.out.println("Direct computing took : " + (System.currentTimeMillis() - start2));
    }

    private static void factorialRecursiveTask(int fact) {
        FactorialRecursiveTask recursiveTask = new FactorialRecursiveTask(BigInteger.ONE, BigInteger.valueOf(fact));
        ForkJoinPool pool = new ForkJoinPool();
        System.out.println(pool.invoke(recursiveTask));
    }

    private static void computeDirectly(int fact) {
        BigInteger result = BigInteger.ONE;
        for (BigInteger i = BigInteger.ONE; i.compareTo(BigInteger.valueOf(fact)) <= 0; i = i.add(BigInteger.ONE)) {
            result = result.multiply(i);
        }
        System.out.println(result);
    }

}
