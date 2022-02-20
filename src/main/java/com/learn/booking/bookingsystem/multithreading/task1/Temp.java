package com.learn.booking.bookingsystem.multithreading.task1;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;

public class Temp {

    @SneakyThrows
    public static void main(String[] args) {

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = loader.getResourceAsStream("application.properties");
        System.out.println(inputStream);

    }

}

class MyRunnable implements Callable<Integer> {

    @SneakyThrows
    @Override
    public Integer call() {
        System.out.println("started - " + Thread.currentThread().getName());
        Thread.sleep(5000);
        System.out.println("finished - " + Thread.currentThread().getName());
        return 10;
    }
}