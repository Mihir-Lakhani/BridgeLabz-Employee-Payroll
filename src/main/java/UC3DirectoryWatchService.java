import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

public class UC3DirectoryWatchService {

    public static long countEntries(Path file) throws IOException {
        try (Stream<String> lines = Files.lines(file)) {
            return lines.count();
        }
    }

    public static void watchDirectory(Path directory) throws IOException, InterruptedException {
        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
            Map<WatchKey, Path> watchedDirectories = new HashMap<>();
            registerAll(directory, watchService, watchedDirectories);

            System.out.println("Watching " + directory.toAbsolutePath());
            System.out.println("Press Ctrl+C to stop.");

            while (!watchedDirectories.isEmpty()) {
                WatchKey key = watchService.take();
                Path watchedDirectory = watchedDirectories.get(key);

                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() == StandardWatchEventKinds.OVERFLOW) {
                        continue;
                    }

                    Path changedPath = watchedDirectory.resolve((Path) event.context());
                    System.out.println(event.kind().name() + ": " + changedPath);

                    if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE
                            && Files.isDirectory(changedPath)) {
                        registerAll(changedPath, watchService, watchedDirectories);
                    }
                }

                if (!key.reset()) {
                    watchedDirectories.remove(key);
                }
            }
        }
    }

    private static void registerAll(Path directory, WatchService watchService,
                                    Map<WatchKey, Path> watchedDirectories) throws IOException {
        try (Stream<Path> paths = Files.walk(directory)) {
            Iterator<Path> pathIterator = paths.iterator();
            while (pathIterator.hasNext()) {
                Path path = pathIterator.next();
                if (Files.isDirectory(path)) {
                    WatchKey key = path.register(watchService,
                            StandardWatchEventKinds.ENTRY_CREATE,
                            StandardWatchEventKinds.ENTRY_DELETE,
                            StandardWatchEventKinds.ENTRY_MODIFY);
                    watchedDirectories.put(key, path);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Path directory = args.length == 0 ? Paths.get("src/main/java") : Paths.get(args[0]);
        watchDirectory(directory);
    }
}
