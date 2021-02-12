package com.ovnny.collections_comparators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorsExampleList {
    public static void main(String[] args) {

        List<Estudante> estudantes = new ArrayList<>();

        estudantes.add(new Estudante( nome: "Pedro", idade:19));
        estudantes.add(new Estudante( nome: "Carlos", idade:23));
        estudantes.add(new Estudante( nome: "Mariana", idade:21));
        estudantes.add(new Estudante( nome: "João", idade:18));
        estudantes.add(new Estudante( nome: "Thiago", idade:20));
        estudantes.add(new Estudante( nome: "George", idade:22));
        estudantes.add(new Estudante( nome: "Larissa", idade:21));

        System.out.println("----- ordem de inserção -----\n");
        System.out.println(estudantes);

        estudantes.sort((first, second) -> first.getIdade() - second.getIdade());
        System.out.println("------ ordem crescente_idade -------\n");
        System.out.println(estudantes);

        estudantes.sort((first, second) -> second.getIdade() - first.getIdade());

        System.out.println("------ ordem decrescente_idade ------\n");
        System.out.println(estudantes);

        estudantes.sort(Comparator.comparingInt(Estudante::getIdade));
        System.out.println("------ ordem crescente_idade (method reference) -----\n");
        System.out.println(estudantes);
        estudantes.sort(Comparator.comparingInt(Estudante::getIdade).reversed());
        System.out.println("------- ordem decrescente_idade (method reference) -----\n");
        System.out.println(estudantes);

        Collections.sort(estudantes);

        System.out.println("----- ordem crescente_idade (interface Comparable) -----\n");
        System.out.println(estudantes);

        Collections.sort(estudantes, new EstudanteOrdemIdadeReversaComparator());

        System.out.println("---- ordem decrescente_idade (interface Comparator) ----");
        System.out.println(estudantes);

    }
}
