package com.asd.Metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {

    private final String fileName;

    public Logger(String fileName) {
        this.fileName = fileName;
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            pw.println("Algorithm,N,Time,Comparisons,Assignments,maxDepth");
        } catch (IOException e) {
            throw new RuntimeException("csv not initialized", e);
        }
    }

    public void log(String algorithm, int n, long timeMillis,
        OperationCounter counter, RecursionTracker tracker) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName, true))) {
            pw.printf("%s,%d,%d,%d,%d,%d%n",
                    algorithm, n, timeMillis,
                    counter.getComparisons(),
                    counter.getAssignments(),
                    tracker.getMaxDepth());
        } catch (IOException e) {
            throw new RuntimeException("cannot write line in csv", e);
        }
    }
    
}
