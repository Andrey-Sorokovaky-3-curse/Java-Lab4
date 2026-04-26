package pro.sorokovsky.normal;

import pro.sorokovsky.console.commands.Command;
import pro.sorokovsky.console.commands.Context;

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
