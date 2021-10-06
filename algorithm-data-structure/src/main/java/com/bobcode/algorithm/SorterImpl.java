package com.bobcode.algorithm;

public class SorterImpl implements Sorter{

    /**
     * @param data input array for sorting
     * @author B.Bodnar
     */

    @Override
    public void bubbleSort(int[] data) {
        for (int i = data.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (data[j] > data[j + 1]) {
                    var temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }

    /**
     * @param data input array for sorting
     * @author B.Bodnar
     */
    @Override
    public void selectionSort(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = i + 1; j < data.length; j++) {
                if (data[i] > data[j]) {
                    var temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                }
            }
        }
    }

    /**
     * @param data input array for sorting
     * @author B.Bodnar
     */
    @Override
    public void insertionSort(int[] data) {
        var n = data.length;
        for (int i = 1; i < n; i++) {
            var current = data[i];
            var j = i - 1;
            while (j >= 0 && current < data[j]) {
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = current;
        }
    }

    /**
     * @param data input data for sorting
     * @param n    length of input data array
     * @author B.Bodnar
     */
    @Override
    public void mergeSort(int[] data, int n) {
        if (n < 2) {
            return;
        }
        var mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = data[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = data[i];
        }

        mergeSort(l, mid);
        mergeSort(r, n - mid);
        merge(data, l, r, mid, n - mid);
    }

    private static void merge(int[] data, int[] l, int[] r, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                data[k++] = l[i++];
            } else {
                data[k++] = r[j++];
            }
        }
        while (i < left) {
            data[k++] = l[i++];
        }
        while (j < right) {
            data[k++] = r[j++];
        }

    }
}
