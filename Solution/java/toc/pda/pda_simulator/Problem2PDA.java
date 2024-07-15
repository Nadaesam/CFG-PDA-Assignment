package toc.pda.pda_simulator;

public class Problem2PDA extends PDASimulator {
    public Problem2PDA() {
        this.pda = new PDA(6, 0, new int[]{5});

        this.pda.addTransition(0, 1, PDA.EPSILON, PDA.EPSILON, '$');

        this.pda.addTransition(1, 1, 'a', PDA.EPSILON, 'a');
        this.pda.addTransition(1, 2, 'b', PDA.EPSILON, PDA.EPSILON);

        this.pda.addTransition(2, 3, 'b', 'a', PDA.EPSILON);

        this.pda.addTransition(3, 4, 'b', 'a', PDA.EPSILON);

        this.pda.addTransition(4, 2, 'b', PDA.EPSILON, PDA.EPSILON);
        this.pda.addTransition(4, 5, PDA.EPSILON, '$', PDA.EPSILON);

    }
}


