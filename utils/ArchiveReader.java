package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ArchiveReader {

    public List<String> readAllLines(Path filePath) throws IOException {
        return Files.readAllLines(filePath);
    }
}
