package com.ovnny.desafiojavaavancado.desafio1.stringchallenge;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

enum TextMetadata {
    COMPRESSED_WORD_LENGTH(2),
    ALPHABET_SIZE(26);

    private final int value;
    TextMetadata(int value) { this.value = value; }
    public int getValue() { return value; }
}

public class StringChallenge {
    public static void main(String[] args) {

        String previousTokens = "abcdef abc abc abc beef beeef beeeeef beef beef beef cd cd cd cdcd dado dado dd dd    dddob  jklmnopq   klind  quioiuer quioiuer quioiuer zzzzzzzzzzzzzz. jkkkkkkpp juninho    juninho     nhinhoju.";
        String[] listTokens = previousTokens.split(" ");

        // Push tokens to StringBuilder then sanitize it and collect it to List<String>
        List<String> text = Arrays.stream(listTokens)
                .map(t -> new StringBuilder().append(t).toString())
                .map(t -> t.replaceAll("\\W", ""))
                .map(t -> t.replaceAll("[.]", ""))
                .filter(t -> t.length() > 1)
                .collect(Collectors.toList());

        Map<String, Integer> wordCollisions = text.stream()
                .filter(t -> t.length() > 2)
                .collect(Collectors.toMap(Function.identity(), t ->
                        (t.length() - TextMetadata.COMPRESSED_WORD_LENGTH.getValue()), Integer::sum));

        List<Word> wordsList = wordCollisions.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.naturalOrder()))
                .map(m -> new Word(m.getKey(), m.getValue()))
                .collect(Collectors.toList());

        Comparator<Word> byPotentialCompression = new CompareByKeyAndValue();
        wordsList.sort(byPotentialCompression);

        List<String> filteredWords = wordsList.stream()
                .map(f -> f.name).collect(Collectors.toList());

        // Building the hashtable's words
        Set<String> wordIndex = new HashSet<>();

        /*  TENTANDO UMA OUTRA ABORDAGEM:
            PROJETO: FINDER
              Classe que une duas estruturas de dados diferentes e um iterador observer
              que observa o gatilho que será baseado nos valores ASCII do alfabeto "toLower()"
              r"/[a-z*]" --> engatilhado por um "findfirst()" e um loop que ira iterar de
              n até n + AlPHABET_LENGHT pra cada procura na lista
          */





        TextCompresser compress = new TextCompresser(text, wordIndex);
        List<String> compressedText = compress.compressText(text);

        for (String w: compressedText) {
            System.out.print(w +" ");
        }
        System.out.println();
        System.out.println(wordIndex.size());
        wordIndex.stream()
                .sorted(Comparator.naturalOrder())
                .forEach(w -> System.out.println(w.charAt(0)+". = " + w));
    }
}

class TextCompresser {
    public List<String> text;
    public Set<String> hashIndexes;

    public TextCompresser(List<String> text, Set<String> hashIndexes) {
        this.text = text;
        this.hashIndexes = hashIndexes;
    }

    public List<String> compressText(List<String> text) {
        List<String> newText = new ArrayList<>();
        for (String word: text) {
            if( hashIndexes.contains(word) ) {
                word = word.charAt(0)+". ";
                newText.add(word);
            }
            else {newText.add(word);}
        }
        return newText;
    }
    @Override
    public String toString() {
        return "hashIndexes.toString().charAt(0)" +". =" + hashIndexes;
    }
}

class CompareByKeyAndValue implements Comparator<Word> {
    @Override
    public int compare(Word s1, Word s2) {
        if(     (int)s1.name.charAt(0) < (int)s2.name.charAt(0) &&
                s1.savedSpace > s2.savedSpace ||
                (int)s1.name.charAt(0) == (int)s2.name.charAt(0) &&
                s1.savedSpace >= s2.savedSpace) {
            return -1;
        }
        else if (   (int)s1.name.charAt(0) > (int) s2.name.charAt(0) &&
                    s1.savedSpace < s2.savedSpace  ||
                    (int)s1.name.charAt(0) == (int) s2.name.charAt(0) &&
                    s2.savedSpace <= s1.savedSpace ){
            return 1;

        } else { return 0; }
    }
}

class Word extends CompareByKeyAndValue{
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
