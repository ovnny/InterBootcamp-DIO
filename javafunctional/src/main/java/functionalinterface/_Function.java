package functionalinterface;

import java.util.function.BiFunction;
import java.util.function.Function;

public class _Function {
    public static void main(String[] args) {

        //Functions takes 1 argument and produces 1 result
        int increment = incrementByOne(1);
        System.out.println(increment);

        int increment2 = incrementByOneFunction.apply(1);
        System.out.println(increment2);

        int product = multiplyBy10Function.apply(increment2);
        System.out.println(product);

        Function<Integer, Integer> addBy1AndMultiplyBy10 =
                incrementByOneFunction.andThen(multiplyBy10Function);
        System.out.println( addBy1AndMultiplyBy10.apply(10));

        // Simple Function approach
        System.out.println(add1AndMultiply(4, 100));
        System.out.println(add1AndMultiply(7, 99));

        // BiFunction takes 2 arguments and produces 1 result
        System.out.println(add1AndMultiplyBiFunction.apply(5 , 100));
    }

    static Function<Integer, Integer> incrementByOneFunction =
            num -> num + 1;

    static Function<Integer, Integer> multiplyBy10Function =
            num -> num * 10;

    static int incrementByOne(int num) {
        return num + 1;
    }

    // BIFunction
    static BiFunction<Integer, Integer, Integer> add1AndMultiplyBiFunction =
            (numToAdd1, numToMultiply)
                    -> (numToAdd1 + 1) * numToMultiply;

    //Normal Function
    static int add1AndMultiply(int num, int numToMultiplyBy) {
        return (num + 1) * numToMultiplyBy;
    }

}
