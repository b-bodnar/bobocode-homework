package com.bobcode.algorithm;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;


public class Training implements Sorter {

    public static void main(String[] args) {
        Training t = new Training();
        var elements = IntStream.generate(() -> ThreadLocalRandom.current().nextInt(100))
                .limit(10)
                .toArray();
        // for test all algorithms
        var start = System.nanoTime();

        //t.bubbleSort(elements);
        //t.selectionSort(elements);
        //t.insertionSort(elements);
        t.mergeSort(elements, elements.length);
        //t.margeSortParallel(elements);

        //System.out.println(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start));
        System.out.println(Arrays.toString(elements));
    }

    @Override
    public void bubbleSort(int[] data) {

    }

    @Override
    public void selectionSort(int[] data) {

    }

    @Override
    public void insertionSort(int[] data) {

    }

    @Override
    public void mergeSort(int[] data, int n) {

    }


    @Override
    public void margeSortParallel(int[] data) {

    }
}
