package pro.sorokovsky.hard;

import pro.sorokovsky.common.container.Stack;

public class MultiBracketBalancer implements Converter {

    @Override
    public String convert(String input) {
        if (input == null || input.isEmpty()) {
            return "Помилка: порожній вираз";
        }

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (isOpeningBracket(c)) {
                stack.push(c);
            }
            else if (isClosingBracket(c)) {
                if (stack.isEmpty()) {
                    return "Помилка: зайва закриваюча дужка '" + c + "' на позиції " + i;
                }

                char lastOpen = stack.pop();
                if (!isMatchingPair(lastOpen, c)) {
                    return "Помилка: невідповідність дужок '" + lastOpen + "' та '" + c + "' на позиції " + i;
                }
            }
        }

        if (!stack.isEmpty()) {
            return "Помилка: не закриті дужки";
        }

        return "OK: дужки збалансовані";
    }

    private boolean isOpeningBracket(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    private boolean isClosingBracket(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    private boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '[' && close == ']') ||
                (open == '{' && close == '}');
    }
}