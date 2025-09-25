package com.asd.sorting;

import java.util.Random;

import com.asd.Metrics.OperationCounter;
import com.asd.Metrics.RecursionTracker;

public class QuickSort {

    private static OperationCounter counter = null;
    private static RecursionTracker tracker = null;
    public QuickSort(OperationCounter counter, RecursionTracker tracker){

        QuickSort.counter = counter;
        QuickSort.tracker = tracker;

    }
    public static void quickSort(int[] arr, int low, int high){
        tracker.enter();
        if (low<high){
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi-1);
            quickSort(arr, pi+1, high);

        }
        tracker.exit();

    }

    private static int partition(int[] arr, int low, int high){

        int pivotPos = new Random().nextInt(high - low + 1) + low;
        int pivot = arr[pivotPos];

        swap(arr, high, pivotPos);

        int i = low - 1;

        for (int j = low; j < high ; j++){
            counter.increaseComparisons();
            if (arr[j] <= pivot){

                i++;
                swap(arr, i, j);

            }

        }
        swap(arr,i+1,high);
        return i+1;

    }

    public static void swap(int[] arr, int i, int j){
        counter.increaseAssignments();
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;


    }
}
