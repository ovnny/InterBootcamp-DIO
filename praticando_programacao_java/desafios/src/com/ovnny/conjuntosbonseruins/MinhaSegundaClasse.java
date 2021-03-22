package com.ovnny.conjuntosbonseruins;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class MinhaSegundaClasse {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.valueOf(br.readLine());

        while (N > 0) {
            List<String> wordsGroup = new ArrayList<>();

            for (int i = 0; i < N; i++)
                wordsGroup.add(br.readLine());

            List<String> sortedGroup = wordsGroup.stream().sorted().collect(Collectors.toList());

            String output = "";

            for (int i = 0; i < N - 1; i++) {
                if (sortedGroup.get(i+ 1).startsWith(sortedGroup.get(i))) {
                    output = "Conjunto Ruim";
                    break;
                } else {
                    output = "Conjunto Bom";
                }
            }

            System.out.println(output);
            N = Integer.valueOf(br.readLine());
        }
    }
}