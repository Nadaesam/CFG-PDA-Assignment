package toc.pda.pda_simulator;

public class CurlyBracketsPDA extends PDASimulator {
    public CurlyBracketsPDA() {
        this.pda = new PDA(3, 0, new int[]{2});

        this.pda.addTransition(0, 1, PDA.EPSILON, PDA.EPSILON, '$');

        this.pda.addTransition(1, 1, '{', PDA.EPSILON, '{');
        this.pda.addTransition(1, 1, '}', '{', PDA.EPSILON);

        this.pda.addTransition(1, 2, PDA.EPSILON, '$', PDA.EPSILON);
    }
}
