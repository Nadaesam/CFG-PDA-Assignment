package toc.cfg.cfg_simulator;

public abstract class CFGSimulator {
    protected CFG cfg;

    public boolean isAccepted(String input) {
        if (this.cfg == null) {
            throw new NullPointerException("CFG is null, Please assign CFG first!");
        }

        return this.cfg.isAccepted(input);
    }
}
