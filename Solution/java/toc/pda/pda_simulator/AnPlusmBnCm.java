package toc.pda.pda_simulator;

public class AnPlusmBnCm extends PDASimulator {

    public AnPlusmBnCm() {
        this.pda = new PDA(5, 0, new int[]{4});

        this.pda.addTransition(0, 1, PDA.EPSILON, PDA.EPSILON, '$');
        this.pda.addTransition(1, 1, 'a', PDA.EPSILON, 'a');

        this.pda.addTransition(1, 2, 'b', 'a', PDA.EPSILON);
        this.pda.addTransition(2, 2, 'b', 'a', PDA.EPSILON);

        this.pda.addTransition(2, 3, 'c', 'a', PDA.EPSILON);
        this.pda.addTransition(3, 3, 'c', 'a', PDA.EPSILON);

        this.pda.addTransition(3, 4, PDA.EPSILON, '$', PDA.EPSILON);
    }
}
