package pro.sorokovsky.simple;

import pro.sorokovsky.console.commands.Command;
import pro.sorokovsky.console.commands.Context;
import pro.sorokovsky.console.inputs.IntegerInput;

import java.util.LinkedList;

public class SimpleTwoTask extends Command {
    private final IntegerInput input = new IntegerInput();

    @Override
    protected String getName() {
        return "Створює чергу, поміщає в неї 5 елементів, видаляє їх та виводить на екран";
    }

    @Override
    public void execute(Context context) {
        final var queue = new LinkedList<Integer>();
        for (int i = 0; i < 5; i++) {
            final var number = input.enter("%d елемент черги".formatted(i));
            queue.addFirst(number);
        }
        System.out.println("Видалення із черги");
        while (!queue.isEmpty()) {
            final var number = queue.removeLast();
            System.out.println(number);
        }
    }
}
