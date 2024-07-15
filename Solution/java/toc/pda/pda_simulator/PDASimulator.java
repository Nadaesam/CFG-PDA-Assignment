package toc.pda.pda_simulator;

public abstract class PDASimulator {
    protected PDA pda;

    public boolean isAccepted(String input) {
        if (this.pda == null) {
            throw new NullPointerException("PDA is null, Please assign PDA first!");
        }

        return this.pda.isAccepted(input);
    }
}
