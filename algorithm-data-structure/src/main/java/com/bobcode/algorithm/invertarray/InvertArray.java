package com.bobcode.algorithm.invertarray;

import java.util.Arrays;

public class InvertArray {
    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        System.out.println(Arrays.toString(data));
        invertRec(data, 0);
        // invertLoop(data);
        System.out.println(Arrays.toString(data));
    }

    public static void invertLoop(int[] data) {
        var n = data.length / 2;
        for (int i = 0; i < n; i++) {
            var index = data.length - i - 1;
            var temp = data[i];
            data[i] = data[index];
            data[index] = temp;
        }
    }

    public static void invertRec(int[] data, int i) {
        var n = data.length / 2;
        if (i < n) {
            var k = data.length - i - 1;
            var temp = data[i];
            data[i] = data[k];
            data[k] = temp;
            invertRec(data, i + 1);
        }
    }
}
