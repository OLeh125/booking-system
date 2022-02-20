package com.learn.booking.bookingsystem.multithreading.advanced.task3;

import java.io.File;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        //TODO add benchmarking
        File directory = new File("C:\\Users\\Oleh_Artymyak");

        long start = System.currentTimeMillis();
        fileScannerAction(directory);
        System.out.println("FileScannerAction scanning took : " + (System.currentTimeMillis() - start));

        long start2 = System.currentTimeMillis();
        scanDirectly(directory);
        System.out.println("Direct scanning took : " + (System.currentTimeMillis() - start2));
    }

    private static void fileScannerAction(File file) {
        ScannerAction scannerAction = new ScannerAction(file);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(scannerAction);
        System.out.println(List.of(scannerAction.getFileCount()));
        System.out.println(List.of(scannerAction.getDirCount()));
    }

    private static void scan(File file, FileDirCounter counter) {
        if (file.isFile()) {
            counter.setFileCount(counter.getFileCount() + 1);
        } else {
            counter.setDirCount(counter.getDirCount() + 1);
            final File[] children = file.listFiles();
            if (children != null) {
                for (final File child : children) {
                    scan(child, counter);
                }
            }
        }
    }

    private static void scanDirectly(File file) {
        FileDirCounter fileDirCounter = new FileDirCounter();
        scan(file, fileDirCounter);
        System.out.println("file count is " + fileDirCounter.getFileCount());
        System.out.println("dir count is " + fileDirCounter.getDirCount());
    }
}
