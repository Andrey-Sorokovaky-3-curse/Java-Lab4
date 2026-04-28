package pro.sorokovsky.hard;

public class SixteenthValidator implements Converter {

    private String expr;
    private int pos;

    @Override
    public String convert(String input) {
        if (input == null || input.isEmpty()) {
            return "False";
        }

        try {
            expr = input.replaceAll("\\s+", "");
            pos = 0;
            boolean isValid = parseExpression();

            if (isValid && pos == expr.length()) {
                return "True";
            } else {
                return "False";
            }

        } catch (Exception e) {
            return "False";
        }
    }

    private boolean parseExpression() {
        if (pos >= expr.length()) {
            return false;
        }

        char c = expr.charAt(pos);

        if (Character.isDigit(c)) {
            parseNumber();
            return true;
        }
        else if (c == '(') {
            pos++; // пропускаємо '('

            if (!parseExpression()) return false;

            if (pos >= expr.length()) return false;
            char op = expr.charAt(pos);
            if (op != '+' && op != '-' && op != '*' && op != '^') {
                return false;
            }
            pos++;

            if (!parseExpression()) return false;

            if (pos >= expr.length() || expr.charAt(pos) != ')') {
                return false;
            }
            pos++;

            return true;
        }

        return false;
    }

    private void parseNumber() {
        while (pos < expr.length() && Character.isDigit(expr.charAt(pos))) {
            pos++;
        }
    }
}