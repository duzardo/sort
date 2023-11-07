package duzy.algoritimosort;

import java.util.Random;

public class InsertionSortDemo {

    public static void main(String[] args) {
        int size = 1000; 
        int numRounds = 5;

        long totalExecutionTime = 0;
        long trocas = 0;
        long iteracoes = 0;

        for (int round = 0; round < numRounds; round++) {
            int[] arr = gerarVetorAleatorio(size);

            long startTime = System.nanoTime();
            long[] metrics = insertionSort(arr);
            long endTime = System.nanoTime();

            totalExecutionTime += (endTime - startTime);
            trocas += metrics[0];
            iteracoes += metrics[1];
        }

        long tempo = totalExecutionTime / numRounds;
        long avTrocas = trocas / numRounds;
        long avIteracoes = iteracoes / numRounds;

        System.out.println("Tamanho: " + size);
        System.out.println("Average Execution Time (ns): " + tempo);
        System.out.println(" Trocas: " + avTrocas);
        System.out.println(" Iteracoes: " + avIteracoes);
        System.out.println();
    }

    public static int[] gerarVetorAleatorio(int size) {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(1000); // Altere o intervalo conforme necessário
        }
        return arr;
    }

    public static long[] insertionSort(int[] arr) {
        long trocas = 0;
        long iteracoes = 0;
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            iteracoes++;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
                trocas++;
                iteracoes++;
            }
            arr[j + 1] = key;
        }
        return new long[]{trocas, iteracoes};
    }
}
