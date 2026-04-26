package pro.sorokovsky.simple;

import pro.sorokovsky.console.commands.Command;
import pro.sorokovsky.console.commands.Context;
import pro.sorokovsky.console.inputs.IntegerInput;

import java.util.LinkedList;

public class SimpleFiveTask extends Command {
    private final IntegerInput input = new IntegerInput();

    @Override
    protected String getName() {
        return "Створює чергу, поміщає в неї 7 елементів, виводить вміст на екран";
    }

    @Override
    public void execute(Context context) {
        final var queue = new LinkedList<Integer>();
        for (int i = 0; i < 7; i++) {
            final var number = input.enter("%d елемент черги".formatted(i));
            queue.addFirst(number);
        }
        System.out.print("Черга: ");
        for (int i = queue.size() - 1; i >= 0; i--) {
            System.out.printf("%d ", queue.get(i));
        }
        System.out.println();
    }
}
