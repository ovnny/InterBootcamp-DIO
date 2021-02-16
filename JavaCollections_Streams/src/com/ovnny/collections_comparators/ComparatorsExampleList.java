package com.ovnny.collections_comparators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Implementa diversas formas da interface Comparator utilizando hora funções
* Lambda, hora sintatic sugar.
 *
 */

public class ComparatorsExampleList {
    public static void main(String[] args) {

        List<Estudante> estudantes = new ArrayList<>();

        estudantes.add(new Estudante( "Pedro", 19));
        estudantes.add(new Estudante( "Carlos", 23));
        estudantes.add(new Estudante( "Mariana", 21));
        estudantes.add(new Estudante( "João", 18));
        estudantes.add(new Estudante( "Thiago", 20));
        estudantes.add(new Estudante( "George", 22));
        estudantes.add(new Estudante( "Larissa", 21));

        System.out.println("----- ordem de inserção -----");
        System.out.println(estudantes + "\n");
        /*  ----- ordem de inserção -----
         *  [Pedro - 19, Carlos - 23, Mariana - 21, João - 18, Thiago - 20, George - 22, Larissa - 21]
         */

        estudantes.sort((first, second) -> first.getIdade() - second.getIdade());
        System.out.println("------ ordem crescente_idade -------");
        System.out.println(estudantes + "\n");
        /*  ------ ordem crescente_idade -------
         *  [João - 18, Pedro - 19, Thiago - 20, Mariana - 21, Larissa - 21, George - 22, Carlos - 23]
         */

        estudantes.sort((first, second) -> second.getIdade() - first.getIdade());
        // Utilização de sintaxe Lambda -> == arrow function(js)


        System.out.println("------ ordem decrescente_idade ------");
        System.out.println(estudantes + "\n");
        /*  ------ ordem decrescente_idade ------
         *  [Carlos - 23, George - 22, Mariana - 21, Larissa - 21, Thiago - 20, Pedro - 19, João - 18]
         */

        estudantes.sort(Comparator.comparingInt(Estudante::getIdade));
        System.out.println("------ ordem crescente_idade (method reference) -----");
        System.out.println(estudantes + "\n");
        /*  ------ ordem crescente_idade (method reference) -----
         *  [João - 18, Pedro - 19, Thiago - 20, Mariana - 21, Larissa - 21, George - 22, Carlos - 23]
         */
        // Estudante::getIdade é uma sintax sugar que tem a mesma propriedade
        // das primeiras duas lógicas. Só que em tempo de execução

        estudantes.sort(Comparator.comparingInt(Estudante::getIdade).reversed());
        System.out.println("------- ordem decrescente_idade (method reference) -----");
        System.out.println(estudantes + "\n");
        /*  ------- ordem decrescente_idade (method reference) -----
            [Carlos - 23, George - 22, Mariana - 21, Larissa - 21, Thiago - 20, Pedro - 19, João - 18]
         */

        Collections.sort(estudantes);

        System.out.println("----- ordem crescente_idade (interface Comparable) -----");
        System.out.println(estudantes + "\n");
        /*  ----- ordem crescente_idade (interface Comparable) -----
            [João - 18, Pedro - 19, Thiago - 20, Mariana - 21, Larissa - 21, George - 22, Carlos - 23]
         */

        Collections.sort(estudantes, new EstudanteOrdemIdadeReversaComparator());
        // Nessa implementação de sort não precisamos usar um objeto que implemente
        // a interface Comparator, mas passamos como segundo parâmetro um objeto
        // que implementa essa classe.

        System.out.println("---- ordem decrescente_idade (interface Comparator) ----");
        System.out.println(estudantes + "\n");
        /*  ---- ordem decrescente_idade (interface Comparator) ----
            [Carlos - 23, George - 22, Mariana - 21, Larissa - 21, Thiago - 20, Pedro - 19, João - 18]
         */

    }
}
