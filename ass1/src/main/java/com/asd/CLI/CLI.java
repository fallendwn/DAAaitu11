package com.asd.CLI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.asd.Metrics.OperationCounter;
import com.asd.Metrics.RecursionTracker;
import com.asd.sorting.ClosestPoint;
import com.asd.sorting.DeterministicSelect;
import com.asd.sorting.MergeSort;
import com.asd.sorting.QuickSort;

public class CLI {
    public static RecursionTracker tracker = new RecursionTracker();

    private static Random random = new Random();
    public static void main(String[] args) {
        
        // merge-sort, quick-sort, deterministic-select, closest-pair;
        tracker = new RecursionTracker();
        String algorithmType = args[0];
        int numberOfTests = Integer.parseInt(args[1]);

        if (algorithmType.equals("merge-sort")){
            MergeSort.tracker = tracker;
            MergeSort.counter = new OperationCounter();
            int[] array_ = randomArray(numberOfTests);
            MergeSort.sort(array_);
            System.out.println(Arrays.toString(array_));

        }
        else if(algorithmType.equals("quick-sort")){
            QuickSort.counter = new OperationCounter();
            QuickSort.tracker = tracker;
            int[] array_ = randomArray(numberOfTests);
            QuickSort.quickSort(array_, 0, numberOfTests-1);
            System.out.println(Arrays.toString(array_));

        }
        else if(algorithmType.equals("deterministic-select")){
            DeterministicSelect.counter = new OperationCounter();
            DeterministicSelect.tracker = tracker;
            System.out.println(DeterministicSelect.deterministicSelect(randomArrayList(numberOfTests), 5));

        }
        else if (algorithmType.equals("closest-pair")){
            ClosestPoint.counter = new OperationCounter();
            ClosestPoint.tracker = tracker;
            List<double[]> points = generateRandomPoints(numberOfTests);
            double result = ClosestPoint.minDistUtil(points.toArray(new double[points.size()][]), 0, points.size());
            System.out.println(result);

        }
        


    }

    public static int[] randomArray(int size){

        int[] arr_ = new int[size];

        for(int i = 0 ; i < size ; i ++){

            arr_[i] = random.nextInt(1000);

        }
        return arr_;


    }

    public static ArrayList<Integer> randomArrayList(int size){

        ArrayList<Integer> array_ = new ArrayList<>();

        for (int i = 0 ; i < size ; i ++){

            array_.add(random.nextInt(1000));

        }
        return array_;
    }

    private static List<double[]> generateRandomPoints(int size){

        List<double[]> points = new ArrayList<>(size);
        for (int i = 0 ; i < size; i ++){

            double x = random.nextDouble() * 1000;
            double y = random.nextDouble() * 1000;
            points.add(new double[]{x,y});

        }
        return points;

    }
    
}
