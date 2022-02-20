package com.learn.booking.bookingsystem.multithreading.advanced.task2;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        //TODO add benchmarking
        Integer[] arr = fillArray();

        long start = System.currentTimeMillis();
        quickSortAction(arr);
        System.out.println("QuickSortAction took : " + (System.currentTimeMillis() - start));

        long start2 = System.currentTimeMillis();
        sortDirectly(arr);
        System.out.println("Direct computing took : " + (System.currentTimeMillis() - start2));
    }

    private static Integer[] fillArray() {
        Integer[] arr = new Integer[10000];
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            int randomNum = rand.nextInt(10000);
            arr[i] = randomNum;
        }
        return arr;
    }

    private static void quickSortAction(Integer[] arr) {
        QuickSortAction recursiveTask = new QuickSortAction(arr);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(recursiveTask);
        System.out.println(List.of(recursiveTask.getArr()));
    }

    private static void sortDirectly(Integer[] arr) {
        quickSort(arr, 0, arr.length - 1);
        System.out.println(List.of(arr));
    }


    public static void quickSort(Integer arr[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(Integer arr[], int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        int swapTemp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = swapTemp;

        return i + 1;
    }


}
