package com.bobcode.concurrency;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {
    private final long[] numbers;
    private final int start;
    private final int end;
    private final long THRESHOLD = 10_000;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        var length = end - start;
        if (length < THRESHOLD) {
            return computeSequentially();
        }
        var leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        leftTask.fork();
        var rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        var rightResult = rightTask.compute();
        var leftResult = leftTask.join();
        return leftResult + rightResult;
    }

    private Long computeSequentially() {
        var sum = 0L;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        long[] numbers = LongStream.range(0, 20_000_000).toArray();
        var task = new ForkJoinSumCalculator(numbers);
        var pool = ForkJoinPool.commonPool();
        var start = System.nanoTime();
        var res = pool.invoke(task);
        System.out.println("time = " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start));
        System.out.println(res);
    }
}
