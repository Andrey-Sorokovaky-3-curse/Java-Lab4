package pro.sorokovsky.hard;

import pro.sorokovsky.common.container.Stack;

public class BracketBalancer implements Converter {
    @Override
    public String convert(String input) {
        if (input == null || input.isEmpty()) {
            return "Помилка: порожній вираз";
        }

        int balance = 0;
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '(') {
                balance++;
                stack.push(c);
            }
            else if (c == ')') {
                balance--;
                if (balance < 0) {
                    return "Помилка: зайва закриваюча дужка на позиції " + i;
                }
                stack.pop();
            }
        }

        if (balance > 0) {
            return "Помилка: не вистачає " + balance + " закриваючих дужок";
        }

        return "OK: дужки збалансовані";
    }
}
