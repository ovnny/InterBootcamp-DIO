package com.ovnny.desafiojavaavancado.desafio1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class StreamTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
    }

    public List<String> tokensToStream(String srt) {
        return Collections.list(new StringTokenizer(srt, " ")).stream()
                .map(token -> (String)token)
                .collect(Collectors.toList());

    }
} 
