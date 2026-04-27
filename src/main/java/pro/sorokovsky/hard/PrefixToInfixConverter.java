package pro.sorokovsky.hard;

import pro.sorokovsky.common.container.Stack;

public class PrefixToInfixConverter implements Converter {

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private boolean isOperand(char c) {
        return Character.isLetterOrDigit(c);
    }

    private int getPrecedence(char op) {
        switch (op) {
            case '+': case '-': return 1;
            case '*': case '/': return 2;
            case '^': return 3;
            default: return 0;
        }
    }

    private boolean needsParentheses(String expr, char parentOp, boolean isRight) {
        if (expr.length() == 1 && isOperand(expr.charAt(0))) {
            return false;
        }

        char exprOp = findMainOperator(expr);
        if (exprOp == 0) return false;

        int parentPrec = getPrecedence(parentOp);
        int exprPrec = getPrecedence(exprOp);

        if (exprPrec > parentPrec) {
            return false;
        }

        if (exprPrec < parentPrec) {
            return true;
        }

        // Однаковий пріоритет - перевіряємо асоціативність
        if (parentOp == '^') {
            return isRight; // Право-асоціативний
        }

        return isRight; // Ліво-асоціативні оператори
    }

    private char findMainOperator(String expr) {
        if (expr.startsWith("(") && expr.endsWith(")")) {
            expr = expr.substring(1, expr.length() - 1);
        }

        int balance = 0;
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (c == '(') balance++;
            if (c == ')') balance--;
            if (balance == 0 && isOperator(c)) {
                return c;
            }
        }
        return 0;
    }

    @Override
    public String convert(String input) {
        if (input == null || input.isEmpty()) {
            return "Помилка: порожній вираз";
        }

        try {
            Stack<String> stack = new Stack<>();
            String expression = input.replaceAll("\\s+", ""); // Видаляємо пробіли

            for (int i = expression.length() - 1; i >= 0; i--) {
                char c = expression.charAt(i);

                if (isOperand(c)) {
                    stack.push(String.valueOf(c));
                }
                else if (isOperator(c)) {
                    String operand1 = stack.pop();
                    String operand2 = stack.pop();
                    String left = needsParentheses(operand1, c, false) ? "(" + operand1 + ")" : operand1;
                    String right = needsParentheses(operand2, c, true) ? "(" + operand2 + ")" : operand2;

                    String infix = left + c + right;
                    stack.push(infix);
                }
            }

            return stack.pop();

        } catch (Exception e) {
            return "Помилка: невірний формат префіксного виразу";
        }
    }
}