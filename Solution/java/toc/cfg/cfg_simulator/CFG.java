package toc.cfg.cfg_simulator;

import java.util.*;

public class CFG {
    public final static char EPSILON = 'ε';

    private final char startSymbol;
    private final Map<Character, List<String>> rules;

    public CFG(char startSymbol) {
        if (!this.isNonTerminal(startSymbol)) {
            throw new IllegalArgumentException("Invalid start symbol. The start symbol must be uppercase");
        }

        this.startSymbol = startSymbol;

        this.rules = new HashMap<>();
    }

    private boolean isNonTerminal(char symbol) {
        return symbol >= 'A' && symbol <= 'Z';
    }

    /**
     * @param rule The rule must have only 2 sides (left and right) seperated by "=>". <br/>
     *             The left side must be non-terminal (uppercase characters). <br/>
     *             The right side can contain non-terminals (uppercase characters) and terminals (lowercase characters).
     */
    public void addRule(String rule) {
        var ruleSides = Arrays.stream(rule.split("=>")).map(String::trim).filter(s -> !s.isEmpty()).toArray(String[]::new);

        if (ruleSides.length != 2) {
            throw new IllegalArgumentException("Invalid rule. The rule must have only 2 side (left and right) seperated by \"=>\"");
        }

        var leftSide = ruleSides[0].trim();

        if (leftSide.length() != 1 || !this.isNonTerminal(leftSide.charAt(0))) {
            throw new IllegalArgumentException("Invalid left side. The left side must be a single uppercase character");
        }

        if (!this.rules.containsKey(leftSide.charAt(0))) {
            this.rules.put(leftSide.charAt(0), new ArrayList<>());
        }

        var rightSideParts = ruleSides[1].split("\\|");

        for (String rulePart : rightSideParts) {
            if (rulePart.trim().isEmpty()) {
                continue;
            }

            this.rules.get(leftSide.charAt(0)).add(rulePart.trim());
        }
    }

    private boolean isAcceptedRec(String input, int idx, Stack<Character> stack) {
        if (idx == input.length() && stack.isEmpty()) {
            return true;
        }

        if (stack.isEmpty()) {
            return false;
        }

        if (this.isNonTerminal(stack.peek())) {
            var ruleRightSideParts = this.rules.getOrDefault(stack.pop(), List.of());

            if (ruleRightSideParts.isEmpty()) {
                return false;
            }

            boolean isAccepted = false;

            for (var part : ruleRightSideParts) {
                if (isAccepted) {
                    break;
                }

                var copyStack = new Stack<Character>();
                copyStack.addAll(stack);

                for (int i = part.length() - 1; i >= 0; i--) {
                    if (part.charAt(i) == CFG.EPSILON) {
                        continue;
                    }

                    copyStack.push(part.charAt(i));
                }

                isAccepted = isAcceptedRec(input, idx, copyStack);
            }

            return isAccepted;
        }

        if (idx == input.length() || input.charAt(idx) != stack.peek()) {
            return false;
        }

        stack.pop();

        return isAcceptedRec(input, idx + 1, stack);
    }

    public boolean isAccepted(String input) {
        Stack<Character> stack = new Stack<>();
        stack.push(this.startSymbol);

        return this.isAcceptedRec(input, 0, stack);
    }
}
