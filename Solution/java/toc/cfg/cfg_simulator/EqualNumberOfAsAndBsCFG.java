package toc.cfg.cfg_simulator;

public class EqualNumberOfAsAndBsCFG extends CFGSimulator {
    public EqualNumberOfAsAndBsCFG() {
        this.cfg = new CFG('S');

        this.cfg.addRule("S => aSbS | bSaS | ε");
    }
}
