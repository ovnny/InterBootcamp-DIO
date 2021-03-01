package com.ovnny.desafiojavaavancado.desafio1.lastchance;

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

        String text = "abcdef abc abc abc beef beeef beeeeef beef beef beef cd cd cd cdcd dado dado dd dd";

        Map<String, Integer> tokensCollisions = Collections.list(
                new StringTokenizer(text, " ")).stream()
                .map(token -> (String) token)
                .filter(token -> token.length() > 2)
                .collect(Collectors.toMap(Function.identity(), token ->
                        (token.length() - TextData.ABREVIATION_COST.getValue()), Integer::sum));

        List<Word> wordsList = tokensCollisions.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.naturalOrder()))
                .map(k -> new Word(k.getKey(), k.getValue()))
                .collect(Collectors.toList());

        Comparator<Word> sortWords = new CompareByNameAndSavedSpace();
        wordsList.sort(sortWords);
        /** wordsList:
         *
         * [(name='abcdef', savedSpace=4), (name='abc', savedSpace=3),
         *  (name='beef', savedSpace=8), (name='beeeeef', savedSpace=5),
         *  (name='beeef', savedSpace=3), (name='cdcd', savedSpace=2),
         *  (name='dado', savedSpace=4)]
         */
        Set<String> hashIndexes = new HashSet<>();

        List<String> filteredWordList = wordsList.stream()
                .map(w -> w.name).collect(Collectors.toList());

        System.out.println(filteredWordList+ "\n");

        Iterator<String> i = filteredWordList.iterator();
        while(i.hasNext()) {
            if (hashIndexes.isEmpty()) {
                hashIndexes.add(i.next());
                i.next();
            }
            for (:
                 ) {
                
            }
            else if (i.next().charAt(0) == i.next().charAt(0) + 1) {
                hashIndexes.add(i.next());
            } else {
                break;
            }
        }
        System.out.println(hashIndexes);
    }
}
class CompareByNameAndSavedSpace implements Comparator<Word> {
    @Override
    public int compare(Word s1, Word s2) {
        if(s1.name.charAt(0) == (s2.name.charAt(0))
                && s1.savedSpace > s2.savedSpace) {
            return -1;
        }
        if(s1.name.charAt(0) == s2.name.charAt(0)
                && s1.savedSpace < s2.savedSpace) {
            return 1;
        }
        return 0;
    }
}

class Word {
    String name;
    Integer savedSpace;

    public Word(String name, Integer savedSpace) {
        this.name = name;
        this.savedSpace = savedSpace;
    }

    @Override
    public String toString() {
        return "(name='" + name + '\'' +
                ", savedSpace=" + savedSpace + ')';
    }
}