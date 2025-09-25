package com.asd.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.RepeatedTest;

import com.asd.Metrics.MetricsLogger;
import com.asd.Metrics.OperationCounter;
import com.asd.Metrics.RecursionTracker;
import com.asd.Metrics.TimeTrack;

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
        TimeTrack timer = new TimeTrack();
        ArrayList<Integer> arr = generateRandomArray(30, 100);
        int k = random.nextInt(arr.size())+1;

        ArrayList<Integer> sorted = new ArrayList<>(arr);
        Collections.sort(sorted);
        OperationCounter counter = new OperationCounter();
        RecursionTracker tracker = new RecursionTracker();
        new DeterministicSelect(counter,tracker);
        int expected = sorted.get(k-1); 
        timer.start();
        int actual = DeterministicSelect.deterministicSelect(new ArrayList<>(arr), k);
        timer.stop();
        assertEquals(expected, actual);
        MetricsLogger.LOGGER.log("DeterministicSelect", arr.size(), (long) timer.getElapsedTimeNanos(), counter, tracker);
    }
}