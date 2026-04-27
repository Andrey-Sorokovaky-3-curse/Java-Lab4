package pro.sorokovsky.simple;

import pro.sorokovsky.common.container.Stack;
import pro.sorokovsky.console.commands.Command;
import pro.sorokovsky.console.commands.Context;
import pro.sorokovsky.console.inputs.IntegerInput;

public class SimpleFourTask extends Command {
    private final IntegerInput input = new IntegerInput();

    @Override
    protected String getName() {
        return "Створює стек, поміщає в нього 7 елементів, виводить вміст на екран";
    }

    @Override
    public void execute(Context context) {
        final var stack = new Stack<Integer>();
        for (int i = 0; i < 7; i++) {
            final var number = input.enter("%d елемент стеку".formatted(i));
            stack.push(number);
        }
        System.out.print("Стек: ");
        for (var number : stack) {
            System.out.printf("%d ", number);
        }
        System.out.println();
    }
}
