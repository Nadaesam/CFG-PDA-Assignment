package toc.cfg;

import toc.cfg.cfg_simulator.CFGSimulator;
import toc.cfg.cfg_simulator.EqualNumberOfAsAndBsCFG;
import toc.cfg.cfg_simulator.PalindromeCFG;
import toc.cfg.cfg_simulator.ProblemFour;
import toc.cfg.cfg_simulator.Problem2CFG;
public class CFGSimulatorFactor {
    private final Integer cfgId;

    public CFGSimulatorFactor(Integer cfgId) {
        this.cfgId = cfgId;
    }

    public CFGSimulator getCFGSimulator() {
        return switch (this.cfgId) {
            case 1 -> new EqualNumberOfAsAndBsCFG();
            case 2 -> new Problem2CFG();
            case 3 -> new PalindromeCFG();
            case 4 -> new ProblemFour();
            default -> null;
        };
    }
}
