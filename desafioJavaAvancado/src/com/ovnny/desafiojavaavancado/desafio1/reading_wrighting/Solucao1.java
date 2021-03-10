package com.ovnny.desafiojavaavancado.desafio1.reading_wrighting;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

enum TextData {
    ABREVIATION_COST(2), ALPHABET_SIZE(26);
    private final int value;

    TextData(int value) { this.value = value; }
    public int getValue() { return value; }
}

public class Solucao1 {

    public static void main(String[] args) {

        String text = "abcdef abc abc abc beef beeef beeeeef beef beef beef cd cd cd cdcd";

        Map<String, Integer> tokensCollisions = Collections.list(
                new StringTokenizer(text, " ")).stream()
                .map(token -> (String) token)
                .filter(token -> token.length() > 2)
                .collect(Collectors.toMap(Function.identity(), token ->
                        (token.length() - TextData.ABREVIATION_COST.getValue()), Integer::sum));

        List<Word> wordsList = tokensCollisions.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(keyValue -> new Word(keyValue.getKey(), keyValue.getValue()))
                .collect(Collectors.toList());

        List<String> chosedWords = wordsList.stream()
                .map(Word::getName)
                .collect(Collectors.toList());

    }
}

class Word {
    String name;
    Integer SavedChars;

    public Word(String name, Integer savedChars) {
        this.name = name;
        SavedChars = savedChars;
    }

    public String getName() {
        return name;
    }

    public Integer getSavedChars() {
        return SavedChars;
    }

    public char getFirstChar() {
        return this.getName().charAt(0);
    }

    @Override
    public String toString() {
        return "Word{" +
                "name='" + name + '\'' +
                ", SavedChars=" + SavedChars +
                '}';
    }
}

