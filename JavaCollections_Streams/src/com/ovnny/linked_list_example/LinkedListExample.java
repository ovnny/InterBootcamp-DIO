package com.ovnny.linked_list_example;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class LinkedListExample {
    public static void main(String[] args) {
        Queue<String> filaBanco = new LinkedList<>();

        filaBanco.add("Patrícia");
        filaBanco.add("Roberto");
        filaBanco.add("Flávio");
        filaBanco.add("Pamela");
        filaBanco.add("Anderson");

        System.out.println(filaBanco); // [Patrícia, Roberto, Flávio, Pamela, Anderson]

        String primeiroDaFila = filaBanco.poll();
        System.out.println(primeiroDaFila); // Patrícia

        System.out.println(filaBanco); // [Roberto, Flávio, Pamela, Anderson]
        String primeiroCliente = filaBanco.peek();

        System.out.println(primeiroCliente); // Roberto
        System.out.println(filaBanco); // [Roberto, Flávio, Pamela, Anderson]

        // filaBanco.clear(); // induzindo o .element() ao erro

        /*
          Exception in thread "main" java.util.NoSuchElementException
         */

        String primeiroCLienteOuErro = filaBanco.element();
        System.out.println(primeiroCLienteOuErro); // Roberto
        System.out.println(filaBanco); // [Roberto, Flávio, Pamela, Anderson]

        for (String client: filaBanco) {
            System.out.println(client);
        } /*
            Roberto
            Flávio
            Pamela
            Anderson
        */

        Iterator<String> iteratorFilaBanco = filaBanco.iterator();
        while (iteratorFilaBanco.hasNext()) {
            System.out.println("---> " + iteratorFilaBanco.next());
        }

        System.out.println((filaBanco.size()));
        System.out.println(filaBanco.isEmpty());
    }
}

// /home/ovnny/Desktop/github/InterBootcamp-DIO/JavaCollections_Streams/src/com/ovnny