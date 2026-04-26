package pro.sorokovsky.simple;

import pro.sorokovsky.console.commands.Command;
import pro.sorokovsky.console.commands.Context;
import pro.sorokovsky.console.inputs.IntegerInput;

import java.util.LinkedList;

public class SimpleThreeTask extends Command {
    private final IntegerInput input = new IntegerInput();

    @Override
    protected String getName() {
        return "Створює однозв’язний список, додає в нього 3 елементи, а потім додає елемент в позицію №2";
    }

    @Override
    public void execute(Context context) {
        final var linkedList = new LinkedList<Integer>();
        for (int i = 0; i < 3; i++) {
            final var number = input.enter("%d елемент однозв'язного списку".formatted(i));
            linkedList.add(number);
        }
        final var number = input.enter("2 елемент однозв'язного списку");
        linkedList.add(2, number);
        System.out.println("Однозв'язний список: ");
        for (var item : linkedList) {
            System.out.printf("%d ", item);
        }
        System.out.println();
    }
}
