import java.io.IOException;
import java.util.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class java_nio_ops {
    public static void main(String[] args) throws IOException {

        Path folder = Paths.get("src/test/java");
        Path file = folder.resolve("NIOTestfile.java");

        if (Files.notExists(folder)) {
            Files.createDirectories(folder);
        }

        if (Files.notExists(file)) {
            Files.createFile(file);
        }

        System.out.println("File exists: " + Files.exists(file));

        //Files.deleteIfExists(file);
        //Files.deleteIfExists(folder);
        System.out.println("File exists after delete: " + Files.exists(file));

    }
}