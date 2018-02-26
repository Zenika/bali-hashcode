package hashcode;

import java.io.IOException;
import java.util.List;

public class HelloWorld {
    public static void main(String[] args) throws IOException {
        List.of("toto", "tata").stream().forEach(System.out::println);
        FileUtils.writeFilesToTarget("out/test.txt", "toto".getBytes());
    }
}
