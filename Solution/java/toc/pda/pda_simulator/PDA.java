package toc.pda.pda_simulator;

import java.util.*;

public class PDA {
    public static char EPSILON = 'ε';

    private final int statesLen;
    private final int startState;
    private final Set<Integer> finalStates;
    /*
     *  src_state -> {
     *    char1 -> {
     *      dest_state1 -> [toPop, toPush],
     *      dest_state2 -> [toPop, toPush],
     *      ...
     *    }
     *    char2 -> {
     *      dest_state1 -> [toPop, toPush],
     *      ...
     *    }
     *  }
     * */
    private final Map<Integer, Map<Character, Map<Integer, Character[]>>> transitions;


    public PDA(int statesLen, int startState, int[] finalStates) {
        this.statesLen = statesLen;

        if (startState < 0 || startState >= statesLen) {
            throw new IllegalArgumentException("Invalid start state. It must be between 0 and " + (statesLen - 1));
        }

        this.startState = startState;

        if (finalStates == null || finalStates.length == 0) {
            throw new IllegalArgumentException("The \"finalStates\" array cannot be null or empty");
        }

        this.finalStates = new HashSet<>();

        for (int finalState : finalStates) {
            if (finalState < 0 || finalState >= statesLen) {
                throw new IllegalArgumentException("Invalid final state. It must be between 0 and " + (statesLen - 1));
            }

            this.finalStates.add(finalState);
        }

        this.transitions = new HashMap<>();
    }

    public void addTransition(int src, int dest, char toRead, char toPop, char toPush) {
        if (src < 0 || src >= this.statesLen) {
            throw new IllegalArgumentException("Invalid src state. It must be between 0 and " + (statesLen - 1));
        }

        if (dest < 0 || dest >= this.statesLen) {
            throw new IllegalArgumentException("Invalid dest state. It must be between 0 and " + (statesLen - 1));
        }

        if (!this.transitions.containsKey(src)) {
            this.transitions.put(src, new HashMap<>());
        }

        if (!this.transitions.get(src).containsKey(toRead)) {
            this.transitions.get(src).put(toRead, new HashMap<>());
        }

        this.transitions.get(src).get(toRead).put(dest, new Character[]{toPop, toPush});
    }

    private boolean isAcceptedRec(String input, int idx, int currState, Stack<Character> stack) {
        boolean accepted = idx == input.length() && this.finalStates.contains(currState) && stack.isEmpty();

        List<Character> possibleChars = new ArrayList<>(List.of(PDA.EPSILON));

        if (idx < input.length()) {
            possibleChars.add(input.charAt(idx));
        }

        for (int i = 0; i < possibleChars.size(); i++) {
            var transitions = this.transitions.getOrDefault(currState, Map.of()).getOrDefault(possibleChars.get(i), Map.of());

            for (var state : transitions.entrySet()) {
                if (accepted) {
                    break;
                }

                var dest = state.getKey();
                var toPop = state.getValue()[0];
                var toPush = state.getValue()[1];

                Stack<Character> updatedStack = new Stack<>();
                updatedStack.addAll(stack);

                if (toPop != PDA.EPSILON) {
                    if (updatedStack.isEmpty() || updatedStack.peek() != toPop) {
                        continue;
                    } else {
                        updatedStack.pop();
                    }
                }

                if (toPush != PDA.EPSILON) {
                    updatedStack.add(toPush);
                }

                // when i = 0, it's epsilon transition. so, in this case we don't increment "idx"
                // when i = 1, it's a char but epsilon. so, in this case we increment "idx"
                accepted = isAcceptedRec(input, idx + i, dest, updatedStack);
            }
        }

        return accepted;
    }

    public boolean isAccepted(String input) {
        return this.isAcceptedRec(input, 0, this.startState, new Stack<>());
    }
}
