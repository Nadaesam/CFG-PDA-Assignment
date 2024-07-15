package toc.cfg.cfg_simulator;

public class ProblemFour extends CFGSimulator {
    public ProblemFour() {
        this.cfg = new CFG('S');

        this.cfg.addRule("S => aaaE | ε");
        this.cfg.addRule("E => aaEb | ε");
    }
}
