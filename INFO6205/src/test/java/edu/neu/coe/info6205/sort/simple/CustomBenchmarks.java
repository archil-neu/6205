package edu.neu.coe.info6205.sort.simple;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.GenericSort;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.LazyLogger;
import edu.neu.coe.info6205.util.Utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Unit tests which are in fact benchmarks of the various sort methods.
 * Keep in mind that we are sorting objects here (Integers). not primitives.
 */
public class CustomBenchmarks {

    public void testInsertionSortBenchmark(int arrayLength, int orderType) {
        String description = "Insertion sort";
        Helper<Integer> helper = new BaseHelper<>(description, arrayLength);
        final GenericSort<Integer> sort = new InsertionSort<>(helper);
        runBenchmark(description, sort, arrayLength, orderType);
    }


//    public X[] random(Class<X> clazz, Function<Random, X> f) {
//        if (n <= 0) throw new BaseHelper.HelperException("Helper.random: not initialized");
//        return Utilities.fillRandomArray(clazz, random, n, f);
//    }

    public Integer[] generateArray(int n, int orderType) {
        Integer[] arr = new Integer[n];
        // 0 is already ordered, numbers from 1 to n
        if (orderType == 1) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = i + 1;
            }
        }
        // 1 is ordered backwards, numbers from n to 1
        if (orderType == -1) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = n - i;
            }
        }
        // 0 is randomly ordered, random numbers from 0 to n
        if (orderType == 0) {
            Random r = new Random();
            for (int i = 0; i < arr.length; i++) {
                arr[i] = r.nextInt(n);
            }
        }
        // 2 partially ordered
        if (orderType == 2) {
            Random r = new Random();
            for (int i = 0; i < arr.length/2; i++) {
                arr[i] = r.nextInt(n);
            }
            for (int i = arr.length/2; i < arr.length; i++) {
                arr[i] = i;
            }
        }
        return arr;
    }

    public void runBenchmark(String description, GenericSort<Integer> sort, int n, int orderType) {

        Supplier<Integer[]> generator = () -> generateArray(n, orderType);

        String orderDescr = "";
        if (orderType == -1) orderDescr = "Backward ordered";
        if (orderType == 0) orderDescr = "Randomly ordered";
        if (orderType == 1) orderDescr = "Already ordered";
        if (orderType == 2) orderDescr = "Partially ordered";


        final Benchmark<Integer[]> benchmark = new Benchmark_Timer<>(
                description + " for " + n + " Integers, " + orderDescr,
                (xs) -> Arrays.copyOf(xs, xs.length),
                sort::sort,
                null
        );
        logger.info(Utilities.formatDecimal3Places(benchmark.runFromSupplier(generator, 100)) + " ms");

    }


    public static void main(String[] args) {

        List<Integer> arrLen = new ArrayList<>();
        for (int i = 4; i < 16; i++) {
            int p = (int) Math.pow(2, i);
            arrLen.add(p);
        }

        CustomBenchmarks benchmarks = new CustomBenchmarks();

        for (int i : arrLen)
            for (int j = -1; j <= 2; j++)
                benchmarks.testInsertionSortBenchmark(i, j);

    }


    final static LazyLogger logger = new LazyLogger(CustomBenchmarks.class);

}
