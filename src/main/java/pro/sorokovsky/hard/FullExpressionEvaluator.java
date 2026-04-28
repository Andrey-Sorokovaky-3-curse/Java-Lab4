package pro.sorokovsky.hard;

public class FullExpressionEvaluator implements Converter {

    private String expr;
    private int pos;

    @Override
    public String convert(String input) {
        if (input == null || input.isEmpty()) {
            return "Помилка: порожній вираз";
        }

        try {
            expr = input.replaceAll("\\s+", "");
            pos = 0;
            int result = parseExpression();

            if (pos != expr.length()) {
                return "Помилка: зайві символи в кінці виразу";
            }

            return String.valueOf(result);

        } catch (Exception e) {
            return "Помилка: " + e.getMessage();
        }
    }

    private int parseExpression() {
        int value = parseTerm();

        while (pos < expr.length()) {
            char op = expr.charAt(pos);
            if (op == '+') {
                pos++;
                value += parseTerm();
            } else if (op == '-') {
                pos++;
                value -= parseTerm();
            } else {
                break;
            }
        }

        return value;
    }

    private int parseTerm() {
        int value = parseElement();

        while (pos < expr.length() && expr.charAt(pos) == '*') {
            pos++;
            value *= parseElement();
        }

        return value;
    }

    private int parseElement() {
        if (pos >= expr.length()) {
            throw new RuntimeException("Очікується цифра або дужка");
        }

        char c = expr.charAt(pos);

        if (Character.isDigit(c)) {
            return parseNumber();
        }
        else if (c == '(') {
            pos++;
            int value = parseExpression();
            if (pos >= expr.length() || expr.charAt(pos) != ')') {
                throw new RuntimeException("Очікується ')'");
            }
            pos++;
            return value;
        }
        else {
            throw new RuntimeException("Невірний символ: " + c);
        }
    }

    private int parseNumber() {
        int start = pos;

        while (pos < expr.length() && Character.isDigit(expr.charAt(pos))) {
            pos++;
        }

        return Integer.parseInt(expr.substring(start, pos));
    }
}