package toc.cfg.cfg_simulator;

public class PalindromeCFG extends CFGSimulator {
    public PalindromeCFG() {
        this.cfg = new CFG('S');

        this.cfg.addRule("S => aSa | bSb | a | b | ε");
    }
}
