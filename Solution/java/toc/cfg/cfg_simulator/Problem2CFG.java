package toc.cfg.cfg_simulator;

public class Problem2CFG extends CFGSimulator{

    public Problem2CFG() {
        this.cfg = new CFG('S');

       this.cfg.addRule("S => aSbSaS | aSaSbS | bSaSaS | ε");

    }
}