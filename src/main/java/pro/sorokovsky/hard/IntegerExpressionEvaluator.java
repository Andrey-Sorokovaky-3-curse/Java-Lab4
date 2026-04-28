package pro.sorokovsky.hard;

public class IntegerExpressionEvaluator implements Converter {

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
            return "Помилка: невірний формат виразу";
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
        if (pos >= expr.length()) {
            throw new RuntimeException("Очікується число");
        }

        if (expr.charAt(pos) == '(') {
            pos++;
            int value = parseExpression();
            if (pos >= expr.length() || expr.charAt(pos) != ')') {
                throw new RuntimeException("Очікується ')'");
            }
            pos++;
            return value;
        }

        return parseNumber();
    }

    private int parseNumber() {
        int start = pos;
        while (pos < expr.length() && Character.isDigit(expr.charAt(pos))) {
            pos++;
        }

        if (start == pos) {
            throw new RuntimeException("Очікується число");
        }

        return Integer.parseInt(expr.substring(start, pos));
    }
}