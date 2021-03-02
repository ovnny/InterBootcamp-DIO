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

        Set<String> hashIndexes = new HashSet<>();

        List<String> filteredWordList = wordsList.stream()
                .map(w -> w.name).collect(Collectors.toList());

        int nextAlphabetChar = (int)filteredWordList.get(0).charAt(0);

        Iterator<String> i = filteredWordList.iterator();
        while(i.hasNext()) {
            String word = i.next();

            if ((int)word.charAt(0) == nextAlphabetChar) {
                hashIndexes.add(word);
                nextAlphabetChar++;
                i.next();

            } else { i.forEachRemaining(hashIndexes::add); }
        }

        List<String> textToCompress = Arrays.stream(text.split(" "))
                .collect(Collectors.toList());

        for (Iterator<String> it = hashIndexes.iterator(); it.hasNext();) {
            WordCompresser<String> compresser = new WordCompresser();
            if(textToCompress.contains(it.next())) {

            }
        }

        WordCompresser<String> srt = new compress();



    }
}
class WordCompresser {
    List<String> hashTabledWord;

    public WordCompresser(List<String> hashTabledWord) {
        this.hashTabledWord = hashTabledWord;
    }
    public String compress(List<String> txt) {
        for (String w : txt)
            if (hashTabledWord.contains(w)) {
                return w.charAt(0) + ". ";
            } else {
                return w;
            }
    }


    @Override
    public boolean equals(Object o) {
       if (o == hashTabledWord){
           return true;
       }
       return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hashTabledWord);
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