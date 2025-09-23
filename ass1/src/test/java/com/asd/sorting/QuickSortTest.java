package com.asd.sorting;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;



public class QuickSortTest {


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

            int size = random.nextInt(100) ;
            int[] arr = generateRandomArray(size, 1000);
            int[] expected = Arrays.copyOf(arr, arr.length);
            Arrays.sort(expected);
            QuickSort.quickSort(arr,0,arr.length-1);
            assertArrayEquals(expected, arr);

        }

    }


}
