package pro.sorokovsky.simple;

import pro.sorokovsky.console.commands.Command;
import pro.sorokovsky.console.commands.Context;
import pro.sorokovsky.console.inputs.IntegerInput;

import java.util.LinkedList;

public class SimpleSixTask extends Command {
    private final IntegerInput input = new IntegerInput();

    @Override
    protected String getName() {
        return "Створює однозв’язний список, додає в нього 3 елементи (в кінець списку), виводить вміст на екран";
    }

    @Override
    public void execute(Context context) {
        final var linkedList = new LinkedList<Integer>();
        for (int i = 0; i < 3; i++) {
            final var number = input.enter("%d елемент однозв'язного списку".formatted(i));
            linkedList.addLast(number);
        }
        System.out.print("Однозв'язний список: ");
        for (var number : linkedList) {
            System.out.printf("%d ", number);
        }
        System.out.println();
    }
}
