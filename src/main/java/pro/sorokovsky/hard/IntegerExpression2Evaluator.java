package pro.sorokovsky.hard;

import pro.sorokovsky.common.container.Stack;

public class IntegerExpression2Evaluator implements Converter {

    @Override
    public String convert(String input) {
        if (input == null || input.isEmpty()) {
            return "Помилка: порожній вираз";
        }

        try {
            String expr = input.replaceAll("\\s+", "");

            Stack<Integer> values = new Stack<>();
            Stack<Character> operators = new Stack<>();

            int num = 0;
            char lastOp = '+';

            for (int i = 0; i < expr.length(); i++) {
                char c = expr.charAt(i);

                if (Character.isDigit(c)) {
                    num = num * 10 + (c - '0');
                }

                if (!Character.isDigit(c) || i == expr.length() - 1) {
                    if (lastOp == '*') {
                        values.push(values.pop() * num);
                    } else if (lastOp == '+') {
                        values.push(num);
                    } else if (lastOp == '-') {
                        values.push(-num);
                    }

                    num = 0;
                    lastOp = c;
                }
            }

            int result = 0;
            while (!values.isEmpty()) {
                result += values.pop();
            }

            return String.valueOf(result);

        } catch (Exception e) {
            return "Помилка: невірний формат";
        }
    }
}