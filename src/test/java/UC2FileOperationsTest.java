import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UC2FileOperationsTest {

    @TempDir
    Path temporaryDirectory;

    @Test
    void shouldCreateAndCheckAnEmptyFile() throws IOException {
        Path file = temporaryDirectory.resolve("empty.txt");

        UC2FileOperations.createEmptyFile(file);

        assertTrue(UC2FileOperations.fileExists(file));
        assertEquals(0, Files.size(file));
    }

    @Test
    void shouldDeleteFileAndReportItMissing() throws IOException {
        Path file = Files.createFile(temporaryDirectory.resolve("delete-me.txt"));

        assertTrue(UC2FileOperations.deleteFile(file));
        assertFalse(UC2FileOperations.fileExists(file));
    }

    @Test
    void shouldCreateDirectoryAndListItsEntries() throws IOException {
        Path directory = UC2FileOperations.createDirectory(temporaryDirectory.resolve("reports"));
        Files.createFile(directory.resolve("payroll.txt"));
        Files.createFile(directory.resolve("notes.md"));
        Files.createDirectory(directory.resolve("archive"));

        List<Path> entries = UC2FileOperations.listFilesAndDirectories(directory);
        List<Path> textFiles = UC2FileOperations.listFilesWithExtension(directory, ".txt");

        assertEquals(3, entries.size());
        assertEquals(1, textFiles.size());
        assertEquals("payroll.txt", textFiles.get(0).getFileName().toString());
    }
}
