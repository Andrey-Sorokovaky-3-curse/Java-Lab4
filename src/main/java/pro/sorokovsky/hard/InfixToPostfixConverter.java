package pro.sorokovsky.hard;

import org.jetbrains.annotations.NotNull;
import pro.sorokovsky.common.container.Stack;

public class InfixToPostfixConverter {
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private static int getPrecedence(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

    private static boolean isOperand(char c) {
        return Character.isLetterOrDigit(c);
    }

    public static @NotNull String infixToPostfix(@NotNull String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (isOperand(c)) {
                result.append(c);
            }

            else if (c == '(') {
                stack.push(c);
            }

            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop(); // Видаляємо '(' зі стеку
            }
            // Якщо це оператор
            else if (isOperator(c)) {
                while (!stack.isEmpty() && getPrecedence(c) <= getPrecedence(stack.peek())) {
                    if (c == '^' && stack.peek() == '^') {
                        break;
                    }
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    public static boolean isValidInfix(String expression) {
        int balance = 0;
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '(') balance++;
            if (c == ')') balance--;
            if (balance < 0) return false;
        }
        return balance == 0;
    }
}