package toc.io.input;

import java.io.BufferedReader;
import java.io.IOException;

public class FileInputReader implements InputReader {
    private final BufferedReader bufferedReader;

    public FileInputReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    @Override
    public String readLine() throws IOException {
        return this.bufferedReader.readLine();
    }

    @Override
    public void cleanup() throws IOException {
        this.bufferedReader.close();
    }
}
