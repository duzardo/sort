package duzy.algoritimosort;
import java.util.Random;

public class Bubble {
    public static void main(String[] args) {
        int tamanho = 1000; // Defina o tamanho desejado aqui

        long tempoExecucaoTotal = 0;
        long trocas = 0; // Renomeado totalTrocas para trocas
        long iteracoes = 0; // Renomeado totalIteracoes para iteracoes
        int rodadas = 5;

        for (int rodada = 0; rodada < rodadas; rodada++) {
            int[] vetor = gerarVetorAleatorio(tamanho);

            long inicioTempo = System.nanoTime();
            long[] resultado = bubbleSort(vetor);
            long fimTempo = System.nanoTime();

            tempoExecucaoTotal += (fimTempo - inicioTempo);
            trocas += resultado[0]; 
            iteracoes += resultado[1]; 
        }

        double tempoExecucaoMedio = tempoExecucaoTotal / (rodadas * 1000000); 

        System.out.println("Tamanho do vetor: " + tamanho);
        System.out.println("Tempo Médio de Execução (ms): " + tempoExecucaoMedio);
        System.out.println("Trocas Médias: " + (double) trocas / rodadas); // Usando a nova variável "trocas"
        System.out.println("Iterações Médias: " + (double) iteracoes / rodadas); // Usando a nova variável "iteracoes"
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

    public static long[] bubbleSort(int[] vetor) {
        long trocas = 0;
        long iteracoes = 0;
        boolean trocado;
        int n = vetor.length;
        do {
            trocado = false;
            for (int i = 1; i < n; i++) {
                iteracoes++;
                if (vetor[i - 1] > vetor[i]) {
                    int x = vetor[i - 1];
                    vetor[i - 1] = vetor[i];
                    vetor[i] = x;
                    trocado = true;
                    trocas++;
                }
            }
            n--;
        } while (trocado);

        return new long[]{trocas, iteracoes}; 
    }
}
