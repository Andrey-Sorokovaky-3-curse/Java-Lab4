package pro.sorokovsky.hard;

import pro.sorokovsky.common.container.Stack;

public class PrefixToPostfixConverter implements Converter {

    @Override
    public String convert(String input) {
        if (input == null || input.isEmpty()) return "Помилка: порожній вираз";

        try {
            Stack<String> stack = new Stack<>();
            String expr = input.replaceAll("\\s+", "");

            for (int i = expr.length() - 1; i >= 0; i--) {
                char c = expr.charAt(i);

                if (Character.isLetterOrDigit(c)) {
                    stack.push(String.valueOf(c));
                } else if (isOperator(c)) {
                    String op1 = stack.pop();
                    String op2 = stack.pop();
                    stack.push(op1 + op2 + c);
                }
            }

            return stack.pop();
        } catch (Exception e) {
            return "Помилка: невірний формат";
        }
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }
}