package pro.sorokovsky;

import org.jetbrains.annotations.NotNull;
import pro.sorokovsky.console.commands.Context;
import pro.sorokovsky.console.commands.ExitCommand;
import pro.sorokovsky.normal.command.NormalOneTask;
import pro.sorokovsky.normal.command.NormalThreeTask;
import pro.sorokovsky.normal.command.NormalTwoTask;
import pro.sorokovsky.simple.*;

public class Main {
    public static void main(String[] args) {
        final var context = new Context("Головне меню");
        context.addCommand(new ExitCommand());
        context.addCommand(setupSimpleLevel());
        context.addCommand(setupNormalLevel());
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
        context.addCommand(new SimpleSixTask());
        context.addCommand(new SimpleSevenTask());
        context.addCommand(new SimpleEightTask());
        context.addCommand(new SimpleNineTask());
        return context;
    }

    private static @NotNull Context setupNormalLevel() {
        final var context = new Context("Середній рівень");
        context.addCommand(new ExitCommand());
        context.addCommand(new NormalOneTask());
        context.addCommand(new NormalTwoTask());
        context.addCommand(new NormalThreeTask());
        return context;
    }
}
