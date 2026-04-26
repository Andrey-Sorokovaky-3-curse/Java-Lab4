package pro.sorokovsky.normal.command;

import pro.sorokovsky.console.commands.Command;
import pro.sorokovsky.console.commands.Context;
import pro.sorokovsky.normal.frame.OneFrame;

public class NormalOneTask extends Command {
    @Override
    protected String getName() {
        return "Реалізувати стек, по натисканню на кнопку додавати елементи в стек";
    }

    @Override
    public void execute(Context context) {
        new OneFrame(getName()).setVisible(true);
    }
}
