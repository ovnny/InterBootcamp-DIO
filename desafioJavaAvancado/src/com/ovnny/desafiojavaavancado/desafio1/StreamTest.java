package com.ovnny.desafiojavaavancado.desafio1;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StreamTest {
    public static void main(String[] args) {

        List<Words> wordsList = new ArrayList<>();

        String text = "abcdef abc abc abc";

        StringTokenizer st = new StringTokenizer(text, " ");
        while(st.hasMoreElements()) {
            Words word = new Words(st.nextToken());
            wordsList.add(word);
        }
        wordsList.forEach(System.out::println);
    }
    static class Words {
        String name;
        int savedWords;

        Words(String name) {
            this.name = name;
            savedWords = this.name.length() - 2;
        }
    }
}
