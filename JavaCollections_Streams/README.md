java.util Package

documentation at: <a>https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/doc-files/coll-index.html</a>

Collections Framework:

Unified architecture for manipulating collections
Part of Java Platform Standard Edition API Specification

Collection Interfaces are divided in two groups:
Basic Interface:

java.util.Set
java.util.SortedSet
java.util.NavigableSet
java.util.Queue
java.util.concurrent.BlockingQueue
java.util.concurrent.TransferQueue
java.util.Deque
java.util.concurrent.BlockingDeque

Map Interface - are not true collections but collection-view operations enables 
to manipulate them as that

java.util.Map

java.util.SortedMap
java.util.NavigableMap
java.util.concurrent.ConcurrentMap
java.util.concurrent.ConcurrentNavigableMap

_____________

Collection Implementations  - names in <Implementation-style> <Interface*>

Set -->  [ HashSet, TreeSet, LinkedHashSet ]
List ->  [ ArrayList, LinkedList ]
Deque->  [ ArrayDeque, LinkedList ]
Map -->  [ HashMap, TreeMap, LinkedHashMap ]


O método .add() adiciona elementos no fim da Queue(fila)


java.util.set não garante ordem de inserção
Hash Table

Foi criada para ser performática
GRANDE CONJUNTO DE DADOS
alternativa é a LinkedHashSet (com perda de performance)
mas permite a ordenação
não permite valores repetidos
permite adição e remoção
buscas específicas não são possíveis
pode iterar sobre objetos
não permite a reordenação a partir de quando o objeto é criado
Tree set permite a alteração da ordem depois
HashSet é a implementação mais performática da Colletion


LinkedHashSet 
Garante a ordem de inserção
Menos performático que o HashSet



TreeSet --> árvores binárias
organizado de acordo com a implementação do algoritmo do Collection
Toda vez que vc modifica o TreeSet ele reinicia o algoritmo de ordenação
q pode causar problemas de performance
busca - performático
modificação - caro

java.util.Map

Não extende Collection
java.util.HashMap
java.util.TreeMap
java.util.HashTable

Relação chave X valor
valores repetidos = true
chaves repetidas = false

Permite adição, busca de chave por valor, atualização, remoção e iteração
Pode ser ordenado.

HashMap<key, value> --> dicionários

HashMap é ideal para parsear queries ao banco de dados.
Bem utilizado quando não se conhece com clareza a estrutura de dados 
a ser recebida.
Pode-se também usar o HashMap com o segundo parâmetro como uma lista.
Assim, a chave será representada pelo nome de uma coluna de um csv file,
por exemplo, e o campo dos valores serão os elementos dentro daquela coluna.

    ex: Map<String, List<Integer>> = minhaTable = new HashMap<>();

HashMap + performático que o LinkedHashMap

java.util.Comparator e java.util.Comparable
Algoritmo de ordenação
Define uma regra de negócios destinado à ordenação de uma coleção.
Ordenação de objetos complexos criados pelo usuário