package toc.pda.pda_simulator;

public class AnBnPDA extends PDASimulator {
    public AnBnPDA() {
        this.pda = new PDA(4, 0, new int[]{0, 3});

        this.pda.addTransition(0, 1, PDA.EPSILON, PDA.EPSILON, '$');
        this.pda.addTransition(1, 1, 'a', PDA.EPSILON, 'a');
        this.pda.addTransition(1, 2, 'b', 'a', PDA.EPSILON);
        this.pda.addTransition(2, 2, 'b', 'a', PDA.EPSILON);
        this.pda.addTransition(2, 3, PDA.EPSILON, '$', PDA.EPSILON);
    }
}
