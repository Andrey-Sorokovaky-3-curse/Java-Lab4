package pro.sorokovsky.simple;

import pro.sorokovsky.common.container.Stack;
import pro.sorokovsky.console.commands.Command;
import pro.sorokovsky.console.commands.Context;
import pro.sorokovsky.console.inputs.IntegerInput;

import java.util.LinkedList;

public class SimpleSevenTask extends Command {
    private final IntegerInput input = new IntegerInput();

    @Override
    protected String getName() {
        return "Створює 2 стеки, поміщає в них по 4 елементи та порівнює стеки між собою";
    }

    @Override
    public void execute(Context context) {
        final var firstStack = new Stack<Integer>();
        final var secondStack = new Stack<Integer>();

        for (int i = 0; i < 4; i++) {
            final var number = input.enter("%d елемент першого стеку".formatted(i));
            firstStack.push(number);
        }

        for (int i = 0; i < 4; i++) {
            final var number = input.enter("%d елемент другого стеку".formatted(i));
            secondStack.push(number);
        }

        System.out.printf("Стеки %s ідентичні%n", firstStack.equals(secondStack) ? "" : "не");
    }
}
