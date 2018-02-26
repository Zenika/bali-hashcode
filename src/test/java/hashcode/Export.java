package hashcode;

import org.junit.Test;

import java.io.IOException;

public class Export {

    @Test
    public void testExample() throws IOException {
        String input = "input/example.in";
        String output = "output/example.out";
        FileUtils.writeFilesToTarget(output, "hello world".getBytes());
    }
}
