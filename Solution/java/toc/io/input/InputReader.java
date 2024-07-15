package toc.io.input;

import java.io.IOException;

public interface InputReader {
    String readLine() throws IOException;

    void cleanup() throws IOException;
}
