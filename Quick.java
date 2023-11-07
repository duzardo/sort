package duzy.algoritimosort;

import java.util.Random;

public class Quick {
    public static void main(String[] args) {
        int tamanho = 1000; // Defina o tamanho desejado aqui
        int rodadas = 5;

        long tempoExecucaoTotal = 0;
        long trocas = 0;
        long iteracoes = 0;

        for (int rodada = 0; rodada < rodadas; rodada++) {
            int[] vetor = gerarVetorAleatorio(tamanho);
            int[] copiaVetor = vetor.clone();

            long inicioTempo = System.nanoTime();
            long[] resultado = quickSort(copiaVetor, 0, copiaVetor.length - 1);
            long fimTempo = System.nanoTime();

            tempoExecucaoTotal += (fimTempo - inicioTempo);
            trocas += resultado[0];
            iteracoes += resultado[1];
        }

        double tempoExecucaoMedio = tempoExecucaoTotal / (rodadas * 1000000); // em milissegundos

        System.out.println("Tamanho do vetor: " + tamanho);
        System.out.println("Tempo Médio de Execução (ms): " + tempoExecucaoMedio);
        System.out.println("Trocas Médias: " + (double) trocas / rodadas);
        System.out.println("Iterações Médias: " + (double) iteracoes);
        System.out.println("=================================");
    }

    public static int[] gerarVetorAleatorio(int tamanho) {
        int[] vetor = new int[tamanho];
        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = random.nextInt(10000); // Você pode ajustar o intervalo conforme necessário
        }
        return vetor;
    }

    public static long[] quickSort(int[] vetor, int esquerda, int direita) {
        long trocas = 0;
        long iteracoes = 0;

        if (esquerda < direita) {
            int[] indices = partition(vetor, esquerda, direita);
            int indexPivot = indices[0];
            int indexNewPivot = indices[1];

            trocas = indexNewPivot - indexPivot;
            iteracoes = (direita - esquerda);

            long[] metadeEsquerda = quickSort(vetor, esquerda, indexNewPivot - 1);
            long[] metadeDireita = quickSort(vetor, indexNewPivot + 1, direita);

            trocas += metadeEsquerda[0] + metadeDireita[0];
            iteracoes += metadeEsquerda[1] + metadeDireita[1];
        }

        return new long[]{trocas, iteracoes};
    }

    public static int[] partition(int[] vetor, int esquerda, int direita) {
        int pivot = vetor[direita];
        int indexPivot = esquerda - 1;
        int trocas = 0;
        int iteracoes = 0;

        for (int j = esquerda; j < direita; j++) {
            iteracoes++;
            if (vetor[j] < pivot) {
                indexPivot++;
                int temp = vetor[indexPivot];
                vetor[indexPivot] = vetor[j];
                vetor[j] = temp;
                trocas++;
            }
        }

        int temp = vetor[indexPivot + 1];
        vetor[indexPivot + 1] = vetor[direita];
        vetor[direita] = temp;
        trocas++;

        return new int[]{indexPivot, indexPivot + 1, trocas, iteracoes};
    }
}
