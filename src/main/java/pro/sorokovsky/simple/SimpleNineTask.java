package pro.sorokovsky.simple;

import pro.sorokovsky.console.commands.Command;
import pro.sorokovsky.console.commands.Context;
import pro.sorokovsky.console.inputs.IntegerInput;

import java.util.LinkedList;

public class SimpleNineTask extends Command {
    private final IntegerInput input = new IntegerInput();

    @Override
    protected String getName() {
        return "Створює 2 списки, поміщає в них по 4 елементи та порівнює списки між собою.";
    }

    @Override
    public void execute(Context context) {
        final var firstList = new LinkedList<Integer>();
        final var secondList = new LinkedList<Integer>();

        for (int i = 0; i < 4; i++) {
            final var number = input.enter("%d елемент першого списку".formatted(i));
            firstList.addFirst(number);
        }

        for (int i = 0; i < 4; i++) {
            final var number = input.enter("%d елемент другого списку".formatted(i));
            secondList.addFirst(number);
        }

        System.out.printf("Списки %s ідентичні%n", firstList.equals(secondList) ? "" : "не");
    }
}
