package com.asd.sorting;

import com.asd.Metrics.OperationCounter;
import com.asd.Metrics.RecursionTracker;

import java.util.*;

public class ClosestPoint {
    private static OperationCounter counter = null;
    private static RecursionTracker tracker = null;

    public ClosestPoint(OperationCounter c,RecursionTracker t) {
        ClosestPoint.counter = c;
        ClosestPoint.tracker = t;
    }

    static double distance(double[] p1, double[] p2) {
        if (counter !=null) {
            counter.increaseComparisons();
        } 
        return Math.sqrt((p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]));
    }


    static Comparator<double[]> compareX = new Comparator<double[]>() {
        public int compare(double[] p1, double[] p2) {
            return Double.compare(p1[0], p2[0]);
        }
    };

    static Comparator<double[]> compareY = new Comparator<double[]>() {
        public int compare(double[] p1, double[] p2) {
            return Double.compare(p1[1], p2[1]);
        }
    };


    static double stripClosest(double[][] strip, double d) {
        double minDist = d;
        Arrays.sort(strip, compareY);
        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < strip.length && (strip[j][1] - strip[i][1]) < minDist; j++) {
                if (counter != null) counter.increaseComparisons();
                minDist = Math.min(minDist, distance(strip[i], strip[j]));
            }
        }
        return minDist;
    }

    static double minDistUtil(double[][] points, int left, int right) {
        if (tracker != null) tracker.enter();

        if (right - left <= 2) {
            double minDist = Double.MAX_VALUE;
            for (int i = left; i < right; i++) {
                for (int j = i + 1; j < right; j++) {
                    if (counter != null) counter.increaseComparisons();
                    minDist = Math.min(minDist, distance(points[i], points[j]));
                }
            }
            if (tracker != null) tracker.exit();
            return minDist;
        }

        int mid = (left + right) / 2;
        double midX = points[mid][0];
        double dl = minDistUtil(points, left, mid);
        double dr = minDistUtil(points, mid, right);
        double d = Math.min(dl, dr);

        List<double[]> strip = new ArrayList<>();
        for (int i = left; i < right; i++) {
            if (Math.abs(points[i][0] - midX) < d) {
                strip.add(points[i]);
                if (counter != null) counter.increaseAssignments();
            }
        }

        double stripDist = stripClosest(strip.toArray(new double[strip.size()][]), d);
        if (tracker != null) tracker.exit();
        return Math.min(d, stripDist);
    }
}
