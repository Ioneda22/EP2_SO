package Solution;

import utils.ArchiveReader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private final static Path _filePath = Path.of("utils/bd.txt");
    private final static int _numOfProportions = 101;
    private final static int _numOfRuns = 50;

    public static void main(String[] args) {
        try {
            ArchiveReader archiveReader = new ArchiveReader();
            List<String> masterBase = archiveReader.readAllLines(_filePath);

            for (int i = 0; i < _numOfProportions; i++) {
                int numOfReaders = i;
                int numOfWriters = (_numOfProportions - 1) - numOfReaders;

                long totalTimeForProportion = 0;

                for (int j = 0; j < _numOfRuns; j++) {
                    List<String> baseForThisRun = new ArrayList<>(masterBase);

                    Solution sol = new Solution(numOfReaders, numOfWriters, baseForThisRun);
                    totalTimeForProportion += sol.solve();
                }

                double averageTime = totalTimeForProportion / (double) _numOfRuns;
                System.out.printf("Tempo MÉDIO para %dR/%dW: %.2f ms%n",
                        numOfReaders, numOfWriters, averageTime);
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo 'bd.txt': " + e.getMessage());
        }
    }
}