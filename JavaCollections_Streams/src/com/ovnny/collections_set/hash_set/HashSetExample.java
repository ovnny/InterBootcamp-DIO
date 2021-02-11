package com.ovnny.collections_set.hash_set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashSetExample {
    public static void main(String[] args) {

        Set<Double> notasAlunos = new HashSet<>();

        // Adiciona as notas no set

        notasAlunos.add(5.9);
        notasAlunos.add(9.3);
        notasAlunos.add(7.7);
        notasAlunos.add(9.1);
        notasAlunos.add(5.9);
        notasAlunos.add(4.4);
        notasAlunos.add(6.8);
        notasAlunos.add(8.6);

        System.out.println(notasAlunos);
        // [9.1, 8.6, 7.7, 9.3, 6.8, 4.4, 5.9]

        notasAlunos.remove(5.9); // [9.1, 8.6, 7.7, 9.3, 6.8, 4.4]
        System.out.println(notasAlunos);
        System.out.println(notasAlunos.size()); // 6

        Iterator<Double> iterator = notasAlunos.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next()); /* 9.1
                                                    8.6
                                                    7.7
                                                    9.3
                                                    6.8
                                                    4.4*/
        }

        for (Double nota: notasAlunos) {
            System.out.println(nota);
        }

        notasAlunos.clear();

        System.out.println(notasAlunos.isEmpty()); // true
    }
}
