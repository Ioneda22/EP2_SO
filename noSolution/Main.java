package noSolution;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import utils.ArchiveReader;

public class Main {

    private final static Path _filePath = Path.of("utils/bd.txt");
    private final static int _numOfProportions = 101;
    private final static int _numOfRuns = 50;
    private final static String _csvOutputPath = "output/results_noSolution.csv";

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        try (PrintWriter writer = new PrintWriter(new FileWriter(_csvOutputPath))) {

            ArchiveReader archiveReader = new ArchiveReader();
            List<String> masterBase = archiveReader.readAllLines(_filePath);

            writer.println("Readers,Writers,Time(ms)");

            System.out.println("Iniciando experimento 'NoSolution'. Salvando em: " + _csvOutputPath);

            for (int i = 0; i < _numOfProportions; i++) {
                int numOfReaders = i;
                int numOfWriters = (_numOfProportions - 1) - numOfReaders;

                long totalTimeForProportion = 0;

                for (int j = 0; j < _numOfRuns; j++) {
                    List<String> baseForThisRun = new ArrayList<>(masterBase);

                    NoSolution sol = new NoSolution(numOfReaders, numOfWriters, baseForThisRun);
                    totalTimeForProportion += sol.solve();
                }

                double averageTime = totalTimeForProportion / (double) _numOfRuns;

                System.out.printf("Tempo MÉDIO para %dR/%dW: %.2f ms%n",
                        numOfReaders, numOfWriters, averageTime);

                writer.printf("%d,%d,%.2f%n",
                        numOfReaders, numOfWriters, averageTime);

                writer.flush();
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler/escrever arquivo: " + e.getMessage());
        }
    }
}