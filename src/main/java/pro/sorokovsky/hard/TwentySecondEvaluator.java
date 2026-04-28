package pro.sorokovsky.hard;


import pro.sorokovsky.common.container.Stack;

public class TwentySecondEvaluator implements Converter {

    @Override
    public String convert(String input) {
        if (input == null || input.isEmpty()) {
            return "True";
        }

        try {
            String expr = input;
            Stack<Character> stack = new Stack<>();

            for (int i = 0; i < expr.length(); i++) {
                char c = expr.charAt(i);

                if (c == '(' || c == '[' || c == '{') {
                    stack.push(c);
                }
                else if (c == ')') {
                    if (stack.isEmpty() || stack.pop() != '(') {
                        return "False";
                    }
                }
                else if (c == ']') {
                    if (stack.isEmpty() || stack.pop() != '[') {
                        return "False";
                    }
                }
                else if (c == '}') {
                    if (stack.isEmpty() || stack.pop() != '{') {
                        return "False";
                    }
                }
                else if (c != 'a' && c != 'b' && c != 'c') {
                    return "False";
                }
            }

            return stack.isEmpty() ? "True" : "False";

        } catch (Exception e) {
            return "False";
        }
    }
}