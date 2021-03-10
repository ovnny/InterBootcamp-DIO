package com.ovnny.desafiojavaavancado.desafio1.reading_wrighting;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

enum Abreviations {
    ABREVIATION_COST(2), ALPHABETSIZE(26);
    private final int value;

    Abreviations(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}

public class TokensToStreamsToMap {

    public static void main(String[] args) {

        String text = "abcdef abc abc abc";

        Map<String, Integer> tokensCollisions = Collections.list(
                new StringTokenizer(text, " ")).stream()
                .map(token -> (String) token)
                .filter(token -> token.length() > 2)
                .collect(Collectors.toMap(Function.identity(), token ->
                        (token.length() - Abreviations.ABREVIATION_COST.getValue()), Integer::sum));

    }

    private static class Word {
        String name;
        Integer savedChars;

        public Word(String name, Integer savedChars) {
            this.name = name;
            this.savedChars = savedChars;
        }

        public String getName() {
            return name;
        }

        private Integer getSavedChars() {
            return savedChars;
        }

//        BiPredicate<Word, Word> savesMoreChars = (x, y) -> {
//            return y.startsWith != x.getName().charAt(0) &&
//                    y.getSavedChars() - x.getSavedChars() > 0;
//
//        };
    }
}
