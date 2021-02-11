package com.ovnny.collections_set.tree_set;

import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetExample {

    public static void main(String[] args) {

        TreeSet<String> treeCapitais = new TreeSet<>();

        // Monta a árvore com as capitais

        treeCapitais.add("Porto Alegre");
        treeCapitais.add("Florianópolis");
        treeCapitais.add("Curitiba");
        treeCapitais.add("São Paulo");
        treeCapitais.add("Rio de Janeiro");
        treeCapitais.add("Belo Horizonte");

        System.out.println(treeCapitais);
        // [Belo Horizonte, Curitiba, Florianópolis, Porto Alegre, Rio de Janeiro, São Paulo]

        // Retorn a primeira capital no topo da árvore
        System.out.println(treeCapitais.first()); // Belo Horizonte

        System.out.println(treeCapitais.last()); // São Paulo

        System.out.println(treeCapitais.lower("Florianópolis")); // Curitiba

        System.out.println(treeCapitais); // [Belo Horizonte, Curitiba, Florianópolis, Porto Alegre, Rio de Janeiro, São Paulo]

        System.out.println(treeCapitais.pollFirst()); // Belo Horizonte

        System.out.println(treeCapitais.pollLast()); // São Paulo

        System.out.println(treeCapitais); // // [Curitiba, Florianópolis, Porto Alegre, Rio de Janeiro]

        Iterator<String> iterator = treeCapitais.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        } /*Curitiba
            Florianópolis
            Porto Alegre
            Rio de Janeiro
            Curitiba
            Florianópolis
*/

        for (String capital: treeCapitais) {
            System.out.println(capital);
        } /*Curitiba
            Florianópolis
            Porto Alegre
            Rio de Janeiro
            Curitiba
            Florianópolis
*/
    }
}
