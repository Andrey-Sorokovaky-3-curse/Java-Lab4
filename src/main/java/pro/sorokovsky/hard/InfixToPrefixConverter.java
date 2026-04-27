package pro.sorokovsky.hard;

import org.jetbrains.annotations.NotNull;
import pro.sorokovsky.common.container.Stack;

public class InfixToPrefixConverter {

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private static int getPrecedence(char c) {
        return switch (c) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            default -> -1;
        };
    }

    public static @NotNull String infixToPrefix(String expression) {
        String reversed = new StringBuilder(expression).reverse().toString();
        StringBuilder modified = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            if (c == '(') {
                modified.append(')');
            } else if (c == ')') {
                modified.append('(');
            } else {
                modified.append(c);
            }
        }
        String postfix = infixToPostfix(modified.toString());
        return new StringBuilder(postfix).reverse().toString();
    }

    private static @NotNull String infixToPostfix(String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            }
            else if (c == '(') {
                stack.push(c);
            }
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop(); // Видаляємо '('
                }
            }
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
}