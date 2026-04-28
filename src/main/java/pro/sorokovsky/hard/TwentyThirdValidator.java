package pro.sorokovsky.hard;

import pro.sorokovsky.common.container.Stack;

public class TwentyThirdValidator implements Converter {

    @Override
    public String convert(String input) {
        if (input == null || input.isEmpty()) {
            return "0";
        }

        try {
            String expr = input;
            Stack<BracketInfo> stack = new Stack<>();

            for (int i = 0; i < expr.length(); i++) {
                char c = expr.charAt(i);

                if (c == '(' || c == '[' || c == '{') {
                    stack.push(new BracketInfo(c, i + 1));
                }
                else if (c == ')') {
                    if (stack.isEmpty()) {
                        return String.valueOf(i + 1);
                    }
                    BracketInfo last = stack.pop();
                    if (last.bracket != '(') {
                        return String.valueOf(i + 1);
                    }
                }
                else if (c == ']') {
                    if (stack.isEmpty()) {
                        return String.valueOf(i + 1);
                    }
                    BracketInfo last = stack.pop();
                    if (last.bracket != '[') {
                        return String.valueOf(i + 1);
                    }
                }
                else if (c == '}') {
                    if (stack.isEmpty()) {
                        return String.valueOf(i + 1);
                    }
                    BracketInfo last = stack.pop();
                    if (last.bracket != '{') {
                        return String.valueOf(i + 1);
                    }
                }
                else if (c != 'a' && c != 'b' && c != 'c' && c != 'd' && c != 'e') {
                    return String.valueOf(i + 1);
                }
            }

            if (!stack.isEmpty()) {
                return "-1";
            }

            return "0";

        } catch (Exception e) {
            return "-1";
        }
    }

    private static class BracketInfo {
        char bracket;
        int position;

        BracketInfo(char bracket, int position) {
            this.bracket = bracket;
            this.position = position;
        }
    }
}