package pro.sorokovsky.hard;

public class BracketExpressionEvaluator implements Converter {

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
        if (pos >= expr.length()) {
            throw new RuntimeException("Очікується вираз");
        }

        char c = expr.charAt(pos);

        if (Character.isDigit(c)) {
            return parseNumber();
        }
        else if (c == '(') {
            pos++; // пропускаємо '('
            int left = parseExpression();

            if (pos >= expr.length()) {
                throw new RuntimeException("Очікується оператор");
            }

            char op = expr.charAt(pos);
            if (op != '+' && op != '-' && op != '*') {
                throw new RuntimeException("Очікується +, - або *, знайдено: " + op);
            }
            pos++;

            int right = parseExpression();

            if (pos >= expr.length() || expr.charAt(pos) != ')') {
                throw new RuntimeException("Очікується ')'");
            }
            pos++;

            return applyOperator(op, left, right);
        }
        else {
            throw new RuntimeException("Очікується цифра або '(', знайдено: " + c);
        }
    }

    private int parseNumber() {
        int start = pos;

        if (!Character.isDigit(expr.charAt(pos))) {
            throw new RuntimeException("Очікується цифра");
        }

        while (pos < expr.length() && Character.isDigit(expr.charAt(pos))) {
            pos++;
        }

        return Integer.parseInt(expr.substring(start, pos));
    }

    private int applyOperator(char op, int a, int b) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            default: throw new RuntimeException("Невідомий оператор: " + op);
        }
    }
}