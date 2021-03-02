package edu.neu.coe.info6205.union_find;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.GenericSort;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.simple.InsertionSort;
import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.LazyLogger;
import edu.neu.coe.info6205.util.Utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

/**
 * Unit tests which are in fact benchmarks of the various sort methods.
 * Keep in mind that we are sorting objects here (Integers). not primitives.
 */



public class UF_Benchmarks {

    public void testUF_HWQUPC(int n, boolean pc) {
        String description = "Height-weighted Quick Union with Path Compression - " + ((pc) ? "with path compression" : "without path compression.");
        runBenchmarkHWQUPC(description, n, pc);
    }

    public void testWQUPC(int n) {
        String description = "Weighted Quick Union with depth size tracking";
        runBenchmarkWQUPC(description, n);
    }




    public void runBenchmarkWQUPC(String description, int n) {

        UF_Client uf = new UF_Client(new WQUPC(n), n);
        Supplier<UF> generator = () -> new WQUPC(n);
        final Benchmark<UF> benchmark = new Benchmark_Timer<UF>(description + " for " + n + " sites, ",null, uf::runCounts, null);
        logger.info(Utilities.formatDecimal3Places(benchmark.runFromSupplier(generator, 100)) + " ms");

    }

    public void runBenchmarkHWQUPC(String description, int n, boolean pc) {

        UF_Client uf = new UF_Client(new UF_HWQUPC(n,pc), n);
        Supplier<UF> generator = () -> new UF_HWQUPC(n, pc);
        final Benchmark<UF> benchmark = new Benchmark_Timer<UF>(description + " for " + n + " sites, ",null, uf::runCounts, null);
        logger.info(Utilities.formatDecimal3Places(benchmark.runFromSupplier(generator, 100)) + " ms");

    }





    public static void main(String[] args) {


        UF_Benchmarks benchmarks = new UF_Benchmarks();

        int[] ns = {10, 100, 200, 1000, 5000, 10000, 20000, 30000, 100000, 500000};

        // with union find without path compression
        for (int n : ns) {
            benchmarks.testWQUPC(n);
        }

        // with union find with path compression
        for (int n : ns) {
            benchmarks.testUF_HWQUPC(n, true);
        }

        for (int n : ns) {
            benchmarks.testUF_HWQUPC(n, false);
        }












    }


    final static LazyLogger logger = new LazyLogger(UF_Benchmarks.class);

}
