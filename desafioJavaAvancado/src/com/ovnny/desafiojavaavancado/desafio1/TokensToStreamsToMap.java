package com.ovnny.desafiojavaavancado.desafio1;

import java.util.Collections;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TokensToStreamsToMap {

    public static void main(String[] args) {

        String text = "abcdef abc abc abc";

        Map<String, Integer> tokensCollisions = Collections.list(
                new StringTokenizer(text, " ")).stream()
                .map(token -> (String) token)
                .filter(token -> token.length() > 2)
                .collect(Collectors.toMap(Function.identity(), token ->
                        (token.length() - AbreviationCost.ABREVIATION.getValue()), Integer::sum));

        System.out.println(tokensCollisions);

    }

    public enum AbreviationCost {
        ABREVIATION(2), ALPHABETLENGHT(26);
        private final int value;

        AbreviationCost(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
    }
}
