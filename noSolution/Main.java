package noSolution;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import utils.ArchiveReader;

public class Main {

    private final static Path _filePath  = Path.of("utils/bd.txt");

    public static void main(String[] args) {
        try {
            ArchiveReader archiveReader = new ArchiveReader();
            List<String> masterBase = archiveReader.readAllLines(_filePath);

            for (int i = 0; i <= 100; i++) {
                int numOfReaders = i;
                int numOfWriters = 100 - numOfReaders;

                long totalTimeForProportion = 0;

                for (int j = 0; j < 50; j++) {
                    List<String> baseForThisRun = new ArrayList<>(masterBase);

                    NoSolution sol = new NoSolution(numOfReaders, numOfWriters, baseForThisRun);
                    totalTimeForProportion += sol.solve();
                }

                double averageTime = totalTimeForProportion / 50.0;
                System.out.printf("Tempo MÉDIO para %dR/%dW: %.2f ms%n",
                        numOfReaders, numOfWriters, averageTime);
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo 'bd.txt': " + e.getMessage());
        }
    }
}