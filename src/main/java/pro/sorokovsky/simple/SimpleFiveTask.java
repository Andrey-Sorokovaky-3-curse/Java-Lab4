package pro.sorokovsky.simple;

import pro.sorokovsky.common.container.Queue;
import pro.sorokovsky.console.commands.Command;
import pro.sorokovsky.console.commands.Context;
import pro.sorokovsky.console.inputs.IntegerInput;

public class SimpleFiveTask extends Command {
    private final IntegerInput input = new IntegerInput();

    @Override
    protected String getName() {
        return "Створює чергу, поміщає в неї 7 елементів, виводить вміст на екран";
    }

    @Override
    public void execute(Context context) {
        final var queue = new Queue<Integer>();
        for (int i = 0; i < 7; i++) {
            final var number = input.enter("%d елемент черги".formatted(i));
            queue.offer(number);
        }
        System.out.print("Черга: ");
        for (final var number : queue) {
            System.out.printf("%d ", number);
        }
        System.out.println();
    }
}
