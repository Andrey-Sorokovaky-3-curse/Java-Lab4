package pro.sorokovsky.hard;

import pro.sorokovsky.common.container.Stack;

public class BracketReverseExtractor implements Converter {

    @Override
    public String convert(String input) {
        if (input == null || input.isEmpty()) {
            return "Помилка: порожній рядок";
        }

        StringBuilder outside = new StringBuilder();
        Stack<Character> inside = new Stack<>();
        Stack<Character> bracketStack = new Stack<>();
        boolean insideBrackets = false;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (isOpeningBracket(c)) {
                bracketStack.push(c);
                insideBrackets = true;
            }
            else if (isClosingBracket(c)) {
                if (!bracketStack.isEmpty()) {
                    bracketStack.pop();
                }
                if (bracketStack.isEmpty()) {
                    insideBrackets = false;
                }
            }
            else if (insideBrackets) {
                inside.push(c);
            }
            else {
                outside.append(c);
            }
        }

        StringBuilder result = new StringBuilder(outside);
        while (!inside.isEmpty()) {
            result.append(inside.pop());
        }

        return result.toString();
    }

    private boolean isOpeningBracket(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    private boolean isClosingBracket(char c) {
        return c == ')' || c == ']' || c == '}';
    }
}