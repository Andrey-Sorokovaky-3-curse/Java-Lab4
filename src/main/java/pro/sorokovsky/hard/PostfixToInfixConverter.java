package pro.sorokovsky.hard;

import pro.sorokovsky.common.container.Stack;

public class PostfixToInfixConverter implements Converter{
    @Override
    public String convert(String input) {
        if (input == null || input.isEmpty()) return "Помилка: порожній вираз";

        try {
            Stack<String> stack = new Stack<>();
            String expr = input.replaceAll("\\s+", "");

            for (int i = 0; i < expr.length(); i++) {
                char c = expr.charAt(i);

                if (Character.isLetterOrDigit(c)) {
                    stack.push(String.valueOf(c));
                } else if (isOperator(c)) {
                    String op2 = stack.pop();
                    String op1 = stack.pop();
                    String infix = "(" + op1 + c + op2 + ")";
                    stack.push(infix);
                }
            }

            String result = stack.pop();
            if (result.startsWith("(") && result.endsWith(")")) {
                result = result.substring(1, result.length() - 1);
            }
            return result;

        } catch (Exception e) {
            return "Помилка: невірний формат";
        }
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }
}
