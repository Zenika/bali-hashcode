package hashcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {
    public static void writeFilesToTarget(String filename, byte[] data) throws IOException {
        Files.write(Paths.get(filename), data);
    }
}
