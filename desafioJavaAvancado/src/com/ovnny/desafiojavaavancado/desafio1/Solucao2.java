package com.ovnny.desafiojavaavancado.desafio1;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Solucao2 {

    public static void main(String[] args) {

        String text = "abcdef abc abc abc beef beeef beeeeef beef beef beef cd cd cd cdcd dado dado dd dd";

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


        List<String> allWords = wordsList.stream().map(Word::getName)
                .collect(Collectors.toList());

        // [beef, beeeeef, abcdef, abc, beeef, cdcd]
        System.out.println(allWords);

        Set<String> filteredWords = new HashSet<>();

        Iterator<String> i = allWords.iterator();
        while(i.hasNext()){
            String word = i.next();
            filteredWords.add(word);
            if((int) word.charAt(0) == (int) i.next().charAt(0)) {
                i.remove();
            } else if ((int)word.charAt(0) > (int) i.next().charAt(0)) {
                filteredWords.add(word);
            } else {
                continue;
            }
        }
        System.out.println(filteredWords);

    }
}
