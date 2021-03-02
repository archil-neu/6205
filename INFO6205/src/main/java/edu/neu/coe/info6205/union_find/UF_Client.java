package edu.neu.coe.info6205.union_find;

import java.util.Random;

public class UF_Client {

    int n;
    UF uf;

    public UF_Client(UF uf, int n){
        this.uf = uf;
        this.n = n;
    }

    public int count() {

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

    public void runCounts(UF newUF){

        this.uf = newUF;
        int c = count();
        //System.out.println("Union find n=" + String.valueOf(n) + ". Total of " + String.valueOf(c) + " steps.");

    }


    public static void main(String[] args) {

        if (args.length != 0) {
            int n = Integer.parseInt(args[0]);
            int avgCounter = 0;

            UF_Client uf_client = new UF_Client(new UF_HWQUPC(n ,false), n);
            for (int m = 0; m < 50; m++) {
                avgCounter += uf_client.count();
            }
            avgCounter = avgCounter / 50;
            System.out.println("Union find n=" + String.valueOf(n) + ". Total of " + String.valueOf(avgCounter) + " steps.");
        } else {
            int[] ns = {10, 100, 200, 1000, 5000, 10000, 20000, 30000};
            for (int n : ns) {
                int avgCounter = 0;
                UF_Client uf_client = new UF_Client(new UF_HWQUPC(n ,false), n);
                for (int m = 0; m < 50; m++) {
                    avgCounter += uf_client.count();
                }
                avgCounter = avgCounter / 50;
                System.out.println("Union find n=" + String.valueOf(n) + ". Total of " + String.valueOf(avgCounter) + " steps.");

            }

        }
    }


}
