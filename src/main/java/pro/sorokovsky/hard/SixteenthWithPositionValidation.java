package pro.sorokovsky.hard;

public class SixteenthWithPositionValidation implements Converter {

    private String expr;
    private int pos;
    private int errorPos;

    @Override
    public String convert(String input) {
        if (input == null || input.isEmpty()) {
            return "1";
        }

        try {
            expr = input;
            pos = 0;
            errorPos = -1;

            skipWhitespace();
            boolean isValid = parseExpression();
            skipWhitespace();

            if (isValid && pos == expr.length()) {
                return "0";
            } else if (errorPos >= 0) {
                return String.valueOf(errorPos + 1);
            } else {
                return String.valueOf(pos + 1);
            }

        } catch (Exception e) {
            if (errorPos >= 0) {
                return String.valueOf(errorPos + 1);
            }
            return String.valueOf(pos + 1);
        }
    }

    private void skipWhitespace() {
        while (pos < expr.length() && Character.isWhitespace(expr.charAt(pos))) {
            pos++;
        }
    }

    private boolean parseExpression() {
        skipWhitespace();

        if (pos >= expr.length()) {
            errorPos = pos;
            return false;
        }

        char c = expr.charAt(pos);

        if (Character.isDigit(c)) {
            parseNumber();
            return true;
        }
        else if (c == '(') {
            pos++;
            skipWhitespace();

            if (!parseExpression()) return false;
            skipWhitespace();

            if (pos >= expr.length()) {
                errorPos = pos;
                return false;
            }

            char op = expr.charAt(pos);
            if (op != '+' && op != '-' && op != '*' && op != '^') {
                errorPos = pos;
                return false;
            }
            pos++;
            skipWhitespace();

            if (!parseExpression()) return false;
            skipWhitespace();

            if (pos >= expr.length() || expr.charAt(pos) != ')') {
                errorPos = pos;
                return false;
            }
            pos++;

            return true;
        }

        errorPos = pos;
        return false;
    }

    private void parseNumber() {
        while (pos < expr.length() && Character.isDigit(expr.charAt(pos))) {
            pos++;
        }
    }
}