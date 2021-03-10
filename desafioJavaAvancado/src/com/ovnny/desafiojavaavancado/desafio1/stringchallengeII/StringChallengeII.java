package com.ovnny.desafiojavaavancado.desafio1.stringchallengeII;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


// Value of word compression since each compressed word has shape "w.". A char and a period.
enum TextMetadata {
    COMPRESSED_WORD_LENGTH(2);

    private final int value;
    TextMetadata(int value) { this.value = value; }
    public int getValue() { return value; }
}

public class StringChallengeII {

    public static void main(String[] args) {
        // Simulating Dio Platform
        String previousTokens = "abcdef abc abc abc";

        String[] listTokens = previousTokens.split(" ");

        // Real Beginning: Push tokens to StringBuilder then sanitize then and collect it to List<String>
        while(true) {
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

            // Instantiate new Word objects
            List<Word> wordsList = wordCollisions.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey(Comparator.naturalOrder()))
                    .map(m -> new Word(m.getKey(), m.getValue()))
                    .collect(Collectors.toList());

            // Word extends ComparingByKeyAndValue class who implements the Comparator class and overyde their method
            // Object byPotentialCompression sort the words in reverse order, bringing the words with bigger occurrences
            // and more char to compress to front the others
            Comparator<Word> byPotentialCompression = new CompareByKeyAndValue();
            wordsList.sort(byPotentialCompression);

            // Lists different firsts possible chars within the text to use as refference latter
            List<Character> wordsIndexes = wordsList.stream()
                    .map(w -> w.name.charAt(0)).distinct()
                    .collect(Collectors.toList());

            // Building the hashtable's of words to seek and compress latter
            Set<String> chosedWords = new HashSet<>();


            // This bad boy uses the list of possible chars as an anchor to iterate over
            // the sorted list of words. If the list have three words, it will be iterated  3 times
            // and so on. As the list was sorted by natural order and potential of compression at glance,
            // each first occurrence will be, naturally, the better choice and so on, when
            // the word is founded we increment the anchor by one to the next possible char of the list.
            // Than, push then to the chosedWord's Set

            if (wordsIndexes.size() > 1) {
                for (int i = 0; i < wordsIndexes.size(); i++) {
                    for (int j = 0; j < wordsList.size(); j++) {
                        if (wordsIndexes.get(i) == wordsList.get(j).name.charAt(0)) {
                            chosedWords.add(wordsList.get(j).name);
                            i++;

                        }
                    }
                }
            } else {
                chosedWords.add(wordsList.get(0).name);
            }


            // Iterate through the initial stream of strings and hash every word
            // if any matches with the Set, we compress it

            TextCompresser compress = new TextCompresser(text, chosedWords);
            List<String> compressedText = compress.compressText(text);

            // printing the result as requested on the problem set
            for (String w : compressedText) {
                System.out.print(w + " ");
            }
            System.out.println();
            System.out.println(chosedWords.size());
            chosedWords.stream()
                    .sorted(Comparator.naturalOrder())
                    .forEach(w -> System.out.println(w.charAt(0) + ". = " + w));

            wordsIndexes.clear();
            chosedWords.clear();
            wordsList.clear();
        }
    }
}
// Below, the helpers: formaters, classes and methods

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
            }
            newText.add(word);
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
        int w1 = s1.name.charAt(0);
        int w2 = s2.name.charAt(0);
        int v1 = s1.savedSpace;
        int v2 = s2.savedSpace;

        if( (w2 > w1 & v1 > v2) | (w1 == w2 && v2 <= v1 )  ) {
            return -1; }

        else if(v1 > v2 & w2 < w1) {
            return 1; } else {  return 0 ;}
    }
}

class Word extends CompareByKeyAndValue {
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