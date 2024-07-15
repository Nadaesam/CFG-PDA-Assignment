package toc.pda;

import toc.pda.pda_simulator.AnBnPDA;
import toc.pda.pda_simulator.AnPlusmBnCm;
import toc.pda.pda_simulator.CurlyBracketsPDA;
import toc.pda.pda_simulator.PDASimulator;
import toc.pda.pda_simulator.Problem2PDA;
public class PDASimulatorFactor {
    private final Integer pdaId;

    public PDASimulatorFactor(Integer pdaId) {
        this.pdaId = pdaId;
    }

    public PDASimulator getPDASimulator() {
        return switch (this.pdaId) {
            case 1 -> new AnBnPDA();
            case 2 -> new Problem2PDA();
            case 3 -> new CurlyBracketsPDA();
            case 4 -> new AnPlusmBnCm();
            default -> null;
        };
    }
}
