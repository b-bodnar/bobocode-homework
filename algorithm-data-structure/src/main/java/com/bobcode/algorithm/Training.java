package com.bobcode.algorithm;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Training implements Sorter {

    public static void main(String[] args) {
        Training t = new Training();
        var elements = IntStream.generate(() -> ThreadLocalRandom.current().nextInt(100))
                .limit(10)
                .toArray();
        //t.bubbleSort(elements);
        //t.selectionSort(elements);
        //t.mergeSort(elements, elements.length);
        t.insertionSort(elements);

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
}
