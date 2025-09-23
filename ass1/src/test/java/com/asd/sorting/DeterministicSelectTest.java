package com.asd.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.RepeatedTest;

public class DeterministicSelectTest {

    private final Random random=new Random();
    private ArrayList<Integer> generateRandomArray(int size, int bound) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(bound)); 
        }
        return list;
    }

    @RepeatedTest(20)
    void testDeterministicSelectRandom() {
        ArrayList<Integer> arr = generateRandomArray(30, 100);
        int k = random.nextInt(arr.size())+1;

        ArrayList<Integer> sorted = new ArrayList<>(arr);
        Collections.sort(sorted);

        int expected = sorted.get(k-1); 
        int actual = DeterministicSelect.deterministicSelect(new ArrayList<>(arr), k);

        assertEquals(expected, actual);
    }
}