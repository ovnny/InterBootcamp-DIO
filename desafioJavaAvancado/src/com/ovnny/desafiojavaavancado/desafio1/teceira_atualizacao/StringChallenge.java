package com.ovnny.desafiojavaavancado.desafio1.teceira_atualizacao;

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

        String previousTokens = "banana banana abacate laranja uva uva uva uva ibirut jabuticabaabababa jabuti jabuti abracadabra";
        String[] listTokens = previousTokens.split(" ");

        // Push tokens to StringBuilder then sanitize it and collect it to List<String>
        List<String> text = Arrays.stream(listTokens)
                .map(t -> new StringBuilder().append(t).toString())
                .map(t -> t.replaceAll("\\W", ""))
                .map(t -> t.replaceAll("[.]", ""))
                .filter(t -> t.length() >= 1)
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

        // Building the hashtable's words
        Set<String> chosedWords = new HashSet<>();

        ListIterator<Word> it = wordsList.listIterator();
        while(it.hasNext()) {
            Word word = it.next();
            chosedWords.add(word.name);
            if ( it.next().name.charAt(0) > word.name.charAt(0) |
                word.name.charAt(0) != it.next().name.charAt(0) ) {
                chosedWords.add(word.name);
                chosedWords.add(it.next().name);

            } else { it.previous(); }
        }


        TextCompresser compress = new TextCompresser(text, chosedWords);
        List<String> compressedText = compress.compressText(text);

        for (String w: compressedText) {
            System.out.print(w +" ");
        }
        System.out.println();
        System.out.println(chosedWords.size());
        chosedWords.stream()
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
        int w1 = (int)s1.name.charAt(0);
        int w2 = (int)s2.name.charAt(0);
        int v1 = s1.savedSpace;
        int v2 = s2.savedSpace;

        if( (w2 > w1 & v1 > v2) | (w1 == w2 && v2 <= v1 )  ) {
            return -1; }

        else if(v1 > v2 & w2 < w1) {
            return 1; } else {  return 0 ;}
    }
}

class Word extends CompareByKeyAndValue{
    String name;
    Integer savedSpace;
    boolean hasMaxCompression = false;

    public Word(String name, Integer savedSpace) {
        this.name = name;
        this.savedSpace = savedSpace;
    }

    public void setHasMaxCompression(boolean hasMaxCompression) {
        this.hasMaxCompression = hasMaxCompression;
    }

    @Override
    public String toString() {
        return "(name='" + name + '\'' +
                ", savedSpace=" + savedSpace + ')';
    }
}
