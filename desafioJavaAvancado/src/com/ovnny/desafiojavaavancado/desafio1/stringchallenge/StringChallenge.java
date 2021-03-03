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
        List<String> text =  Arrays.stream(listTokens)
                .map(t -> new StringBuilder().append(t).toString())
                .map(t -> t.replaceAll("\\W",""))
                .map(t -> t.replaceAll("[.]", "" ))
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

        Comparator<Word> byPotententialCompression = new CompareByKeyAndValue();
        wordsList.sort(byPotententialCompression);






    }
}

class CompareByKeyAndValue implements Comparator<Word> {
    @Override
    public int compare(Word s1, Word s2) {
        if(     (int)s1.name.charAt(0) == (int)s2.name.charAt(0) &&
                s1.savedSpace > s2.savedSpace ||
                (int)s1.name.charAt(0) < (int)s2.name.charAt(0) &&
                s1.savedSpace > s2.savedSpace ) {
            return -1;
        }
        else if ((int)s1.name.charAt(0) < (int)s2.name.charAt(0) &&
                s1.savedSpace <= s2.savedSpace ) {
            return 1;
        }   else { return 0; }
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
