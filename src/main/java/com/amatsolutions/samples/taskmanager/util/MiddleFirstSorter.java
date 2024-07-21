package com.amatsolutions.samples.taskmanager.util;

import java.util.ArrayList;
import java.util.List;

public class MiddleFirstSorter {

    public static <T> List<T> sortMiddleFirst(List<T> list) {
        List<T> result = new ArrayList<>();
        int n = list.size();

        if (n == 0) {
            return result; // Return an empty list if the input is empty
        }

        int mid = (n - 1) / 2; // Middle index (choosing the lower middle for even-sized lists)

        result.add(list.get(mid)); // Add the middle element first

        int left = mid - 1;
        int right = mid + 1;

        while (left >= 0 || right < n) {
            if (left >= 0) {
                result.add(list.get(left));
                left--;
            }
            if (right < n) {
                result.add(list.get(right));
                right++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }

        List<Integer> sortedList = sortMiddleFirst(list);
        System.out.println(sortedList);
    }
}
