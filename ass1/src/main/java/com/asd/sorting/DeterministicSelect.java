package com.asd.sorting;

import java.util.ArrayList;
import java.util.Collections;

import com.asd.Metrics.OperationCounter;
import com.asd.Metrics.RecursionTracker;
public class DeterministicSelect {

    private static OperationCounter counter = null;
    private static RecursionTracker tracker = null;


    public DeterministicSelect(OperationCounter c, RecursionTracker t) {
        DeterministicSelect.counter = c;
        DeterministicSelect.tracker = t;
    }


    public static Integer deterministicSelect(ArrayList<Integer> arr, int k) {
        tracker.enter();
        if (arr.size() == 1){
            tracker.exit();
            return arr.get(0);
        }
        else if(arr.size() == 0 ){

            tracker.exit();
            return 0;

        }

        ArrayList<Integer> list_of_medians = new ArrayList<>();
        ArrayList<Integer> temp_array = new ArrayList<>();
        for (int i = 0 ; i < arr.size(); i++){
            counter.increaseAssignments();
            temp_array.add(arr.get(i));
            if( ( i + 1 ) % 5 == 0){

                Collections.sort(temp_array);
                list_of_medians.add(temp_array.get((int) Math.floor(temp_array.size()/2)));
                temp_array = new ArrayList<>();
            }

        }
        if (!temp_array.isEmpty()){

            Collections.sort(temp_array);
            list_of_medians.add(temp_array.get((int) Math.floor(temp_array.size()/2)));

        }

        int pivotIndex=(int) Math.ceil(list_of_medians.size()/2.0);
        int pivot=deterministicSelect(list_of_medians, pivotIndex);
        ArrayList<Integer> L = new ArrayList<>();
        ArrayList<Integer> E = new ArrayList<>();
        ArrayList<Integer> R = new ArrayList<>();

        for(int i = 0 ; i < arr.size();i++){
            counter.increaseComparisons();
            if (arr.get(i) < pivot){

                L.add(arr.get(i));

            }
            else if (arr.get(i) == pivot){

                E.add(arr.get(i));

            }
            else{

                R.add(arr.get(i));

            }

        }
        if (L.size() >= k){

            return deterministicSelect(L, k);

        }
        else if (k - L.size() <= E.size()){
            tracker.exit();
            return pivot;

        }
        else{

            return deterministicSelect(R, k - L.size() - E.size());

        }

    }
    
}
