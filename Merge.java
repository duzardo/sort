package duzy.algoritimosort;

import java.util.Random;

public class Merge {
    public static void main(String[] args) {
        int tamanho = 1000;
        int rodadas = 5;

        long tempoExecucaoTotal = 0;
        long totalTrocas = 0;
        long totalIteracoes = 0;

        for (int rodada = 0; rodada < rodadas; rodada++) {
            int[] vetor = gerarVetorAleatorio(tamanho);
            int[] copiaVetor = vetor.clone();

            long inicioTempo = System.nanoTime();
            long[] resultado = mergeSort(copiaVetor, 0, copiaVetor.length - 1);
            long fimTempo = System.nanoTime();

            tempoExecucaoTotal += (fimTempo - inicioTempo);
            totalTrocas += resultado[0];
            totalIteracoes += resultado[1];
        }

        double tempoExecucaoMedio = tempoExecucaoTotal / (rodadas * 1000000); 

        System.out.println("Tamanho do vetor: " + tamanho);
        System.out.println("Tempo Médio de Execução (ms): " + tempoExecucaoMedio);
        System.out.println("Trocas Médias: " + (double) totalTrocas / rodadas);
        System.out.println("Iterações Médias: " + (double) totalIteracoes / rodadas);
        System.out.println("=================================");
    }

    public static int[] gerarVetorAleatorio(int tamanho) {
        int[] vetor = new int[tamanho];
        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = random.nextInt(10000); 
        }
        return vetor;
    }

    public static long[] mergeSort(int[] vetor, int esquerda, int direita) {
        long trocas = 0;
        long iteracoes = 0;
        if (esquerda < direita) {
            int meio = (esquerda + direita) / 2;
            long[] metadeEsquerda = mergeSort(vetor, esquerda, meio);
            long[] metadeDireita = mergeSort(vetor, meio + 1, direita);
            long[] resultado = merge(vetor, esquerda, meio, direita);
            trocas = metadeEsquerda[0] + metadeDireita[0] + resultado[0];
            iteracoes = metadeEsquerda[1] + metadeDireita[1] + resultado[1];
            return new long[]{trocas, iteracoes};
        } else {
            return new long[]{0, 0};
        }
    }

    public static long[] merge(int[] vetor, int esquerda, int meio, int direita) {
    int[] tempArray = new int[direita - esquerda + 1];
    int i = esquerda;
    int j = meio + 1;
    int k = 0;
    long trocas = 0;
    long iteracoes = 0;

    while (i <= meio && j <= direita) {
        iteracoes++;
        if (vetor[i] <= vetor[j]) {
            tempArray[k++] = vetor[i++];
        } else {
            tempArray[k++] = vetor[j++];
            trocas += meio - i + 1;
        }
    }

    while (i <= meio) {
        iteracoes++;
        tempArray[k++] = vetor[i++];
    }

    while (j <= direita) {
        iteracoes++;
        tempArray[k++] = vetor[j++];
    }

    for (i = 0; i < k; i++) {
        vetor[esquerda + i] = tempArray[i];
    }

    return new long[]{trocas, iteracoes};
}

}
