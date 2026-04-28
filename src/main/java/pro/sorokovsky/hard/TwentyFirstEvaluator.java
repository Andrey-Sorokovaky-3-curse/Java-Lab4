package pro.sorokovsky.hard;

import java.util.ArrayList;
import java.util.List;

public class TwentyFirstEvaluator implements Converter {

    @Override
    public String convert(String input) {
        if (input == null || input.isEmpty()) {
            return "Помилка: порожній вираз";
        }

        try {
            String expr = input.replaceAll("\\s+", "");
            boolean result = evaluate(expr);
            return result ? "T" : "F";
        } catch (Exception e) {
            return "Помилка: " + e.getMessage();
        }
    }

    private boolean evaluate(String expr) {
        if (expr.equals("T")) return true;
        if (expr.equals("F")) return false;

        if (expr.startsWith("Not(") && expr.endsWith(")")) {
            String inner = expr.substring(4, expr.length() - 1);
            return !evaluate(inner);
        }

        if (expr.startsWith("And(") && expr.endsWith(")")) {
            String inner = expr.substring(4, expr.length() - 1);
            List<Boolean> operands = parseOperands(inner);
            for (Boolean op : operands) {
                if (!op) return false;
            }
            return true;
        }

        if (expr.startsWith("Or(") && expr.endsWith(")")) {
            String inner = expr.substring(3, expr.length() - 1);
            List<Boolean> operands = parseOperands(inner);
            for (Boolean op : operands) {
                if (op) return true;
            }
            return false;
        }

        throw new RuntimeException("Невірний формат: " + expr);
    }

    private List<Boolean> parseOperands(String expr) {
        List<Boolean> operands = new ArrayList<>();
        int balance = 0;
        int start = 0;

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (c == '(') balance++;
            if (c == ')') balance--;
            if (c == ',' && balance == 0) {
                operands.add(evaluate(expr.substring(start, i)));
                start = i + 1;
            }
        }
        operands.add(evaluate(expr.substring(start)));

        return operands;
    }
}