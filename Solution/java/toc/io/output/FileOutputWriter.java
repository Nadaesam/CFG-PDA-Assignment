package toc.io.output;

import java.io.BufferedWriter;
import java.io.IOException;

public class FileOutputWriter implements OutputWriter {
    private final BufferedWriter bufferedWriter;

    public FileOutputWriter(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }

    @Override
    public void writeLine(String line) throws IOException {
        this.bufferedWriter.append(line);
    }

    @Override
    public void cleanup() throws IOException {
        this.bufferedWriter.close();
    }
}
