package com.asd.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ClosestPairTest {

    private static final Random random = new Random();

    private static double distance(double[] p1, double[] p2){

        return Math.sqrt((p1[0]) - p2[0] * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]));


    }
    private static double minDistance (List<double[]> points){

        int n = points.size();
        double minDist = Double.MAX_VALUE;

        for (int i = 0; i <n ; ++i){

            for (int j = i + 1; j<n; ++j){

                double dist = distance(points.get(i), points.get(j));
                if (dist < minDist){

                    minDist = dist;

                }

            }

        }
        return minDist;
    }


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

            int size = 2 + random.nextInt(200);
            List<double[]> points = generateRandomPoints(size, 1000.0);


            double result = minDistance(points);

            assertTrue(result > 0);
            assertTrue(Double.isFinite(result));

        }

    }

}
    
