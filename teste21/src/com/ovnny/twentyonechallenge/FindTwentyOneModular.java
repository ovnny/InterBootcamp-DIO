package com.ovnny.twentyonechallenge;

import java.util.ArrayList;
import java.util.List;

public class FindTwentyOneModular {
    public static void main(String[] args) {
        Integer MAX_NUMBER = 6666;
        Integer MIN_NUMBER = 5556;

        List<List<Integer>> secondFilter = new ArrayList<>();

        for (int i = MAX_NUMBER; i > MIN_NUMBER; i--) {
            if (i % 9 == 3) {
                List<Integer> firstFilter;
                firstFilter = new ArrayList<>();
                firstFilter.add(i);
                secondFilter.add(firstFilter);
            }
        }

        secondFilter.stream().map(l -> l.toString().getChars(0, 3, secondFilter.add(), l.get(0)))
                .forEach(System.out::println);

    }
}
