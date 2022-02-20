package com.learn.booking.bookingsystem.multithreading.advanced.task2;

import java.util.concurrent.RecursiveAction;
import lombok.Getter;

@Getter
public class QuickSortAction extends RecursiveAction {

    private Integer[] arr;
    private int left;
    private int right;

    public QuickSortAction(Integer[] arr) {
        this(arr, 0, arr.length - 1);
    }

    public QuickSortAction(Integer[] arr, int left, int right) {
        this.arr = arr;
        this.left = left;
        this.right = right;
    }


    @Override
    protected void compute() {
        if (left < right) {
            int pivot = partition(arr, left, right);
            invokeAll(new QuickSortAction(arr, left, pivot), new QuickSortAction(arr, pivot + 1, right));
        }
    }

    private int partition(Integer[] array, int begin, int end) {
        int pivot = array[begin];
        int i = begin - 1;
        int j = end + 1;
        while (true) {
            do {
                i++;
            }
            while (array[i] < pivot);

            do {
                j--;
            }
            while (array[j] > pivot);
            if (i >= j) {
                return j;
            }

            swap(array, i, j);
        }
    }

    private void swap(Integer[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
