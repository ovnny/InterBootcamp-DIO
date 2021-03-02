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

        String text =   "abcdef abc abc abc beef beeef beeeeef beef beef beef cd cd cd cdcd dado dado dd dd    dddob  jklmnopq   klind  quioiuer quioiuer quioiuer zzzzzzzzzzzzzz. jkkkkkkpp juninho    juninho     nhinhoju";

        text = text.replaceAll("\\W", " ");
        text = text.trim();

        Map<String, Integer> tokensCollisions = Collections.list(
                new StringTokenizer(text, " ")).stream()
                .map(token -> (String) token)
                .filter(token -> token.length() > 2)
                .collect(Collectors.toMap(Function.identity(), token ->
                        ((token.length() - TextData.ABREVIATION_COST.getValue())), Integer::sum));

        List<Word> wordsList = tokensCollisions.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.naturalOrder()))
                .map(k -> new Word(k.getKey(), k.getValue()))
                .collect(Collectors.toList());

        System.out.println("Esses são os valores de economia de cada palavra "+wordsList);

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

        TextCompresser compress = new TextCompresser(textToCompress, hashIndexes);
        List<String> newText = compress.compress(textToCompress);

        StringBuilder ultimateString = new StringBuilder();

        for (String w: newText) {
            ultimateString.append(w);
        }
        String ultimateString2 = ultimateString.toString();
        ultimateString2 = ultimateString2.trim();

    System.out.println("String Certa: "+ultimateString2);
        System.out.println(hashIndexes.size());
        hashIndexes.stream()
                .sorted(Comparator.naturalOrder())
                .forEach(w -> System.out.println(w.charAt(0)+". = "+w));
    }
}

class TextCompresser {
    public List<String> text;
    public Set<String> hashIndexes;

    public TextCompresser(List<String> text, Set<String> hashIndexes) {
        this.text = text;
        this.hashIndexes = hashIndexes;
    }

    public List<String> compress(List<String> text) {

        List<String> newText = new ArrayList<>();
        for (String word: text) {
            if( hashIndexes.contains(word) ) {
                word = word.charAt(0)+". ";
                newText.add(word);
            }
            else {newText.add(word+" ");}
        }
        return newText;
    }

    @Override
    public String toString() {
        return "hashIndexes.toString().charAt(0)" +". =" + hashIndexes;
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