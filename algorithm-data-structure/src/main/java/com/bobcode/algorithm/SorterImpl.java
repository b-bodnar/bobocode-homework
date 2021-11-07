package com.bobcode.algorithm;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class SorterImpl implements Sorter {

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
        if (n == 1) {
            return;
        }
        int mid = n / 2;
        int[] l = Arrays.copyOfRange(data, 0, mid);
        int[] r = Arrays.copyOfRange(data, mid, n);
        mergeSort(l, mid);
        mergeSort(r, n - mid);
        merge(data, l, r);

    }

    private void merge(int[] data, int[] l, int[] r) {
        int i = 0, j = 0;
        while (i < l.length && j < r.length) {
            if (l[i] < r[j]) {
                data[i + j] = l[i++];
            } else {
                data[i + j] = r[j++];
            }
        }
        System.arraycopy(l, i, data, i + j, l.length - i);
        System.arraycopy(r, j, data, i + j, r.length - j);
    }

    @Override
    public void margeSortParallel(int[] data) {
        ForkJoinPool.commonPool().invoke(new MargeSortTask(data));
    }
}

class MargeSortTask extends RecursiveAction {
    private int[] data;
    private int n;

    public MargeSortTask(int[] data) {
        this.data = data;
        this.n = data.length;
    }

    @Override
    protected void compute() {
        if (data.length == 1) {
            return;
        }
        var left = Arrays.copyOfRange(data, 0, n / 2);
        var right = Arrays.copyOfRange(data, n / 2, n);

        var leftTask = new MargeSortTask(left);
        var rightTask = new MargeSortTask(right);

        leftTask.fork();
        rightTask.compute();
        leftTask.join();

        merge(data, left, right);
    }

    private void merge(int[] data, int[] left, int[] right) {
        var i = 0;
        var j = 0;
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                data[i + j] = left[i++];
            } else {
                data[i + j] = right[j++];
            }
        }
        System.arraycopy(left, i, data, i + j, left.length - i);
        System.arraycopy(right, j, data, i + j, right.length - j);
    }
}
