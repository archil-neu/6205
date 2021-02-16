package edu.neu.coe.info6205.union_find;

import java.util.Random;

public class UF_Client {

    public static int count(int n) {

        UF_HWQUPC uf = new UF_HWQUPC(n, false);
        Random r = new Random();
        int stepCounter = 0;

        while (uf.components() > 1) {
            int i = r.nextInt(n);
            int j = r.nextInt(n);
            stepCounter++;
            if (!uf.connected(i, j)) {
                uf.union(i, j);
            }
        }
        return stepCounter;
    }


    public static void main(String[] args) {
        if (args.length != 0) {
            int n = Integer.parseInt(args[0]);
            int avgCounter = 0;
            for (int m = 0; m < 50; m++) {
                avgCounter += count(n);
            }
            avgCounter = avgCounter / 50;
            System.out.println("Union find n=" + String.valueOf(n) + ". Total of " + String.valueOf(avgCounter) + " steps.");
        } else {
            int[] ns = {10, 100, 200, 1000, 5000, 10000, 20000, 30000};
            for (int n : ns) {
                int avgCounter = 0;
                for (int m = 0; m < 50; m++) {
                    avgCounter += count(n);
                }
                avgCounter = avgCounter / 50;
                System.out.println("Union find n=" + String.valueOf(n) + ". Total of " + String.valueOf(avgCounter) + " steps.");

            }

        }
    }
}
