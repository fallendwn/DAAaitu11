package com.asd.Metrics;

import java.util.function.Consumer;

public class Benchmark {
    
    private final Logger logger;
    private final OperationCounter counter;
    private final RecursionTracker tracker;
    private final TimeTrack timer;

    public Benchmark(Logger logger, OperationCounter counter, RecursionTracker tracker, TimeTrack timer){

        this.logger = logger;
        this.counter = counter;
        this.tracker = tracker;
        this.timer = timer;

    }


    public <T> void runBenchmark(String algorithmName, int n, Consumer<T> algorithm, T input){


        counter.reset();
        tracker.reset();
        timer.start();

        algorithm.accept(input);

        timer.stop();
        logger.log(algorithmName, n, (long) timer.getElapsedTimeMillis(), counter, tracker);


    }

}
