package toc.cfg;

import toc.cfg.cfg_simulator.CFGSimulator;
import toc.io.input.FileInputReader;
import toc.io.input.InputReader;
import toc.io.output.FileOutputWriter;
import toc.io.output.OutputWriter;

import java.io.*;

public class CFGMain {
    private static final String inputFilePath = "src/main/resources/input_cfg.txt";
    private static final String outputFilePath = "src/main/resources/output_cfg.txt";

    public static void start() {
        try {
            InputReader inputReader = new FileInputReader(new BufferedReader(new FileReader(inputFilePath)));
            OutputWriter outputWriter = new FileOutputWriter(new BufferedWriter(new FileWriter(outputFilePath)));

            String line;

            while ((line = inputReader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }

                Integer cfgId = Integer.parseInt(line);

                outputWriter.writeLine(cfgId + "\n");

                CFGSimulator cfgSimulator = new CFGSimulatorFactor(cfgId).getCFGSimulator();

                while (!(line = inputReader.readLine()).equalsIgnoreCase("end")) {
                    if (cfgSimulator == null) {
                        outputWriter.writeLine("null\n");
                        continue;
                    }

                    var isAccepted = cfgSimulator.isAccepted(line);
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
