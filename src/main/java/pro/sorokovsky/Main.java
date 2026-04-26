package pro.sorokovsky;

import org.jetbrains.annotations.NotNull;
import pro.sorokovsky.console.commands.Context;
import pro.sorokovsky.console.commands.ExitCommand;
import pro.sorokovsky.simple.*;

public class Main {
    public static void main(String[] args) {
        final var context = new Context("Головне меню");
        context.addCommand(new ExitCommand());
        context.addCommand(setupSimpleLevel());
        context.start();
    }

    private static @NotNull Context setupSimpleLevel() {
        final var context = new Context("Простий рівень");
        context.addCommand(new ExitCommand());
        context.addCommand(new SimpleOneTask());
        context.addCommand(new SimpleTwoTask());
        context.addCommand(new SimpleThreeTask());
        context.addCommand(new SimpleFourTask());
        context.addCommand(new SimpleFiveTask());
        return context;
    }
}
