package com.bobcode.algorithm;

public interface Sorter {

    /**
     * timecomplexsity O(n2)
     *
     * @param data input data for sorting
     */
    void bubbleSort(int[] data);

    /**
     * timecomplexsity O(n2)
     *
     * @param data input data for sorting
     */
    void selectionSort(int[] data);

    /**
     * timecomplexsity O(n2)
     *
     * @param data input data for sorting
     */
    void insertionSort(int[] data);

    /**
     * timecomplexsity O(n * log(n))
     *
     * @param data input data for sorting
     */
    void mergeSort(int[] data);

    void margeSortParallel(int[] data);
}
