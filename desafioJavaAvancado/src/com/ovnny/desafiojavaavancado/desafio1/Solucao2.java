package com.ovnny.desafiojavaavancado.desafio1;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Solucao2 {

    public static void main(String[] args) {

        String text = "abcdef abc abc abc beef beeef beeeeef beef beef beef cd cd cd cdcd";

        Map<String, Integer> tokensCollisions = Collections.list(
                new StringTokenizer(text, " ")).stream()
                .map(token -> (String) token)
                .filter(token -> token.length() > 2)
                .collect(Collectors.toMap(Function.identity(), token ->
                        (token.length() - TextData.ABREVIATION_COST.getValue()), Integer::sum));

        Set<Integer> Indexes = tokensCollisions.values().stream()
                .map(token -> token.intValue())
                .collect(Collectors.toSet());

        System.out.println(Indexes);


    }
}
