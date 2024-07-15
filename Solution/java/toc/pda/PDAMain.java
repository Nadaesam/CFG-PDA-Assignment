package toc.pda;

import toc.io.input.FileInputReader;
import toc.io.input.InputReader;
import toc.io.output.FileOutputWriter;
import toc.io.output.OutputWriter;
import toc.pda.pda_simulator.PDASimulator;

import java.io.*;

public class PDAMain {
    private static final String inputFilePath = "src/main/resources/input_pda.txt";
    private static final String outputFilePath = "src/main/resources/output_pda.txt";

    public static void start() {
        try {
            InputReader inputReader = new FileInputReader(new BufferedReader(new FileReader(inputFilePath)));
            OutputWriter outputWriter = new FileOutputWriter(new BufferedWriter(new FileWriter(outputFilePath)));

            String line;

            while ((line = inputReader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }

                Integer pdaId = Integer.parseInt(line);

                outputWriter.writeLine(pdaId + "\n");

                PDASimulator pdaSimulator = new PDASimulatorFactor(pdaId).getPDASimulator();

                while (!(line = inputReader.readLine()).equalsIgnoreCase("end")) {
                    if (pdaSimulator == null) {
                        outputWriter.writeLine("null\n");
                        continue;
                    }

                    var isAccepted = pdaSimulator.isAccepted(line);
                    outputWriter.writeLine((isAccepted ? "accepted" : "not accepted") + "\n");
                }

                outputWriter.writeLine("end\n");
            }

            inputReader.cleanup();
            outputWriter.cleanup();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
