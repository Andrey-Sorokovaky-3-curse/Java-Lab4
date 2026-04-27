package pro.sorokovsky.hard;

import org.jetbrains.annotations.NotNull;

public class InfixToPrefixConverter implements Converter {

    private final Converter infixToPostfixConverter = new InfixToPostfixConverter();

    public @NotNull String convert(String expression) {
        String reversed = new StringBuilder(expression).reverse().toString();
        StringBuilder modified = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            if (c == '(') {
                modified.append(')');
            } else if (c == ')') {
                modified.append('(');
            } else {
                modified.append(c);
            }
        }
        String postfix = infixToPostfixConverter.convert(modified.toString());
        return new StringBuilder(postfix).reverse().toString();
    }
}