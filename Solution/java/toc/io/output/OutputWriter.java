package toc.io.output;

import java.io.IOException;

public interface OutputWriter {
    void writeLine(String line) throws IOException;

    void cleanup() throws IOException;
}
