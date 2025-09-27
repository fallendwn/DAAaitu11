package com.asd.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.asd.Metrics.MetricsLogger;
import com.asd.Metrics.OperationCounter;
import com.asd.Metrics.RecursionTracker;
import com.asd.Metrics.TimeTrack;

public class ClosestPairTest {

    private static final Random random = new Random();

    private List<double[]> generateRandomPoints(int size, double bound){

        List<double[]> points = new ArrayList<>(size);
        for (int i = 0 ; i < size; i ++){

            double x = random.nextDouble() * bound;
            double y = random.nextDouble() * bound;
            points.add(new double[]{x,y});

        }
        return points;

    }

    @Test
    public void testRandomInputs(){

        for(int i = 0 ; i < 10; i++){
            TimeTrack timer = new TimeTrack();
            int size = 2 + random.nextInt(200);
            List<double[]> points = generateRandomPoints(size, 1000.0);

            OperationCounter counter = new OperationCounter();
            RecursionTracker tracker = new RecursionTracker();
            new ClosestPoint(counter,tracker);
            timer.start();
            double result = ClosestPoint.minDistUtil(points.toArray(new double[points.size()][]), 0, points.size());
            timer.stop();

            assertTrue(result > 0);
            assertTrue(Double.isFinite(result));
            MetricsLogger.LOGGER.log("ClosestPoint", size, (long) timer.getElapsedTimeNanos(), counter, tracker);

        }

    }

}
    
