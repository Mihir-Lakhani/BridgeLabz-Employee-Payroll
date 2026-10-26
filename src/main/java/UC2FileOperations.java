import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class UC2FileOperations {

    public static boolean fileExists(Path file) {
        return Files.exists(file);
    }

    public static boolean deleteFile(Path file) throws IOException {
        return Files.deleteIfExists(file);
    }

    public static Path createDirectory(Path directory) throws IOException {
        return Files.createDirectories(directory);
    }

    public static Path createEmptyFile(Path file) throws IOException {
        return Files.createFile(file);
    }

    public static List<Path> listFilesAndDirectories(Path directory) throws IOException {
        List<Path> entries = new ArrayList<>();
        try (Stream<Path> paths = Files.list(directory)) {
            paths.forEach(entries::add);
        }
        return entries;
    }

    public static List<Path> listFilesWithExtension(Path directory, String extension) throws IOException {
        List<Path> matches = new ArrayList<>();
        try (Stream<Path> paths = Files.list(directory)) {
            paths.filter(path -> path.getFileName().toString().endsWith(extension))
                    .forEach(matches::add);
        }
        return matches;
    }
}
