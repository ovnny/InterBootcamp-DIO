package com.ovnny.list_example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ListExample {
    public static void main(String[] args) {
        List<String> nomes = new ArrayList<>();

        nomes.add("Carlos");
        nomes.add("Anderson");
        nomes.add("Pedro");
        nomes.add("Maria");
        nomes.add("Roberto");
        nomes.add("Vivian");

        System.out.println(nomes);
        Collections.sort(nomes);
        nomes.set(2, "Larissa"); //índice do array inicia no "0" --> item 3(array dinâmico)
        System.out.println(nomes);

        Collections.sort(nomes);
        nomes.set(2, "Wesley"); //set substitui o intem por outro
        System.out.println(nomes);

        nomes.remove(4);
        System.out.println(nomes);
        nomes.remove("Wesley");
        int tamanho = nomes.size(); //com atribuição à uma variável
        System.out.println(tamanho);
        System.out.println(nomes.size()); //sem atribuição à uma variável

        //contains() --> return boolean
        // isEmpty() --> return boolean
        // clear()  --> rm all
        // e outros --> openJDK docs

        for (String nomeDoItem: nomes) { //para iterar em uma lista (método mais comum)
            System.out.println("-->" + nomeDoItem); //outras formas na StreamAPI
        }

        Iterator<String> iterator = nomes.iterator();
        while (iterator.hasNext()) {
            System.out.println("----->" + iterator.next());
        }
    }

}