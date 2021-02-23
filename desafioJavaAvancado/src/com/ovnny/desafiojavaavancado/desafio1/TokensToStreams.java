package com.ovnny.desafiojavaavancado.desafio1;

import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class TokensToStreams {

    public static void main(String[] args) {
        String text = "abcdef abc abc abc";

        List<String> tokens = Collections.list(new StringTokenizer(text, " ")).stream()
                .map(token -> (String) token)
                .filter(t -> t.length() > 2)
                .collect(Collectors.toList());

        tokens.forEach(System.out::println);
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
