package com.asd.sorting;

import com.asd.Metrics.OperationCounter;
import com.asd.Metrics.RecursionTracker;

public class MergeSort {
    private static final int CUTOFF = 10;
    private static OperationCounter counter = null;
    private static RecursionTracker tracker = null;

    public MergeSort(OperationCounter counter, RecursionTracker tracker){

        MergeSort.counter = counter;
        MergeSort.tracker = tracker;
        

    }

    public static void sort(int[] arr){

        if(arr == null || arr.length <= 1){
            return;
        }
        int[] buffer = new int[arr.length];
        mergeSort(arr, buffer,0, arr.length-1);

    }

    private static void merge(int arr[], int buffer[], int l, int m, int r){

        for (int i = l ; i <= r ; i++) {
            counter.increaseAssignments();
            buffer[i] = arr[i];

        }


        int i = l;
        int j = m+1;
        int k = l;

        while (i<=m && j<=r){
            counter.increaseComparisons();
            if(buffer[i] <= buffer[j]){
                counter.increaseAssignments();
                arr[k++] = buffer[i++];

            }else{
                counter.increaseAssignments();
                arr[k++] = buffer[j++];

            }

        }
        while(i<=m){
            counter.increaseAssignments();
            arr[k++] = buffer[i++];

        }

    }

    private static void mergeSort(int arr[],int[] buffer, int l, int r){
        tracker.enter();
        if (r-l <= CUTOFF){

            insertionSort(arr, l, r);
            return;

        }
        int m = l + (r-l) / 2;
        mergeSort(arr, buffer, l, m);
        mergeSort(arr, buffer, m+1, r); 
        merge(arr, buffer, l, m, r);
        tracker.exit();
    }

    private static void insertionSort(int[] arr, int l, int r){

        for(int i = l + 1; i <= r; i++){

            int key = arr[i];
            int j = i-1;
            while(j >= l && arr[j] > key){
                counter.increaseComparisons();
                arr[j+1] = arr[j];
                j--;

            }
            counter.increaseAssignments();
            arr[j+1] = key;

        }

    }
    
}
