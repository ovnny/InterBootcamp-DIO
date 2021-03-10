import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

public class ComoNaPlataforma {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {

            String text = br.readLine();
            text.trim()
                .toLowerCase()
                .replaceAll("\t", " ")
                .replaceAll("\n", " ");

            if(text.equals(".")) break;
            if(text.length() <= 0) continue;

            List<String> sanitizedText = new ArrayList(Arrays.asList(text.split(" ")));

            Map<String, Integer> wordCollisions = sanitizedText.stream()
                    .map(t -> t.replaceAll("\\W", ""))
                    .map(t -> t.replaceAll("[.]", ""))
                    .filter(t -> t.length() > 2)
                    .collect(Collectors.toMap(Function.identity(), t ->
                            (t.length() - TextMetadata.COMPRESSED_WORD_LENGTH.getValue()), Integer::sum));

            // Instantiate new Word objects
            List<Word> wordsList = wordCollisions.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey(Comparator.naturalOrder()))
                    .map(m -> new Word(m.getKey(), m.getValue()))
                    .collect(Collectors.toList());

            Comparator<Word> byPotentialCompression = new CompareByKeyAndValue();
            wordsList.sort(byPotentialCompression);

            List<Character> wordsIndexes = wordsList.stream()
                    .map(w -> w.name.charAt(0)).distinct()
                    .collect(Collectors.toList());

            Set<String> chosedWords = new HashSet<>();

            if (wordsIndexes.size() >= 2) {
                for (int i = 0; i < wordsIndexes.size(); i++) {
                    for (int j = 0; j < wordsList.size(); j++) {
                        if (wordsList.get(j).name.charAt(0) == wordsIndexes.get(i)) {
                            chosedWords.add(wordsList.get(j).name);
                            i++ ; }}}
            } else { chosedWords.add(wordsList.get(0).name);  }


            TextCompresser compress = new TextCompresser(sanitizedText, chosedWords);
            List<String> compressedText = compress.compressText(sanitizedText);

            for (String w : compressedText) {
                System.out.print(w + " "); }
            System.out.println();
            System.out.println(chosedWords.size());
            chosedWords.stream()
                    .sorted(Comparator.naturalOrder())
                    .forEach(w -> System.out.println(w.charAt(0) + ". = " + w));
        }
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