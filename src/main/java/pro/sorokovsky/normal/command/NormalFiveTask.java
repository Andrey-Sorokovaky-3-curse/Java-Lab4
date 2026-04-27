package pro.sorokovsky.normal.command;

import pro.sorokovsky.console.commands.Command;
import pro.sorokovsky.console.commands.Context;
import pro.sorokovsky.normal.frame.FiveFrame;

public class NormalFiveTask extends Command {
    @Override
    protected String getName() {
        return "Реалізувати однозв’язний список, по натисканню на кнопку додавати елементи перед виділеним у список. Якщо не виділено – в початок";
    }

    @Override
    public void execute(Context context) {
        new FiveFrame(getName()).setVisible(true);
    }
}
