package com.asd.Metrics;

public class OperationCounter {
    


    private long comparisons = 0;
    private long assignments = 0;


    
    public void increaseAssignments() {
        assignments++;
    }

    public void increaseComparisons() {
        comparisons++;
    }

    public long getAssignments() {
        return assignments;
    }

    public long getComparisons() {
        return comparisons;
    }


    public void reset() {
        comparisons = 0;
        assignments = 0;
    }
}

