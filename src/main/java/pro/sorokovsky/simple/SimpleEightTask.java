package pro.sorokovsky.simple;

import pro.sorokovsky.common.container.Queue;
import pro.sorokovsky.console.commands.Command;
import pro.sorokovsky.console.commands.Context;
import pro.sorokovsky.console.inputs.IntegerInput;

import java.util.LinkedList;

public class SimpleEightTask extends Command {
    private final IntegerInput input = new IntegerInput();

    @Override
    protected String getName() {
        return "Створює 2 черги, поміщає в них по 4 елементи та порівнює черги між собою";
    }

    @Override
    public void execute(Context context) {
        final var firstQueue = new Queue<Integer>();
        final var secondQueue = new Queue<Integer>();

        for (int i = 0; i < 4; i++) {
            final var number = input.enter("%d елемент першої черги".formatted(i));
            firstQueue.offer(number);
        }

        for (int i = 0; i < 4; i++) {
            final var number = input.enter("%d елемент другої черги".formatted(i));
            secondQueue.offer(number);
        }

        System.out.printf("Черги %s ідентичні%n", firstQueue.equals(secondQueue) ? "" : "не");
    }
}
