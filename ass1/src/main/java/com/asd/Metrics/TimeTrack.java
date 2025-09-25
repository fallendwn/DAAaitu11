package com.asd.Metrics;

public class TimeTrack {
    private long startTime;
    private long endTime;

    public void start() {
        startTime = System.nanoTime();
    }

    public void stop() {
        endTime = System.nanoTime();
    }


    public double getElapsedTimeMillis() {
        return (endTime - startTime) / 1_000_000.0;
    }
    
    public long getElapsedTimeNanos() {
        return endTime - startTime;
    }
}
