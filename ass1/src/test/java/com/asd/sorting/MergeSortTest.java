package com.asd.sorting;


import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

import com.asd.Metrics.MetricsLogger;
import com.asd.Metrics.OperationCounter;
import com.asd.Metrics.RecursionTracker;
import com.asd.Metrics.TimeTrack;

public class MergeSortTest {


    private static final Random random = new Random();


    private int[] generateRandomArray(int size, int bound){

        int[] arr = new int[size];
        for(int i = 0; i < size; i++){

            arr[i] = random.nextInt(bound);

        }
        return arr;

    }

    @Test

    public void testRandomArrays(){

        for(int i = 0 ; i < 10; i++){
            TimeTrack timer = new TimeTrack();
            int size = random.nextInt(100) ;
            int[] arr = generateRandomArray(size, 1000);
            int[] expected = Arrays.copyOf(arr, arr.length);
            Arrays.sort(expected);

            OperationCounter counter = new OperationCounter();
            RecursionTracker tracker = new RecursionTracker();
            new MergeSort(counter,tracker);
            timer.start();
            MergeSort.sort(arr);
            timer.stop();
            MetricsLogger.LOGGER.log("MergeSort", size, (long) timer.getElapsedTimeNanos(), counter, tracker);

            assertArrayEquals(expected, arr);

        }

    }
}