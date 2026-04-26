package pro.sorokovsky.normal.command;

import pro.sorokovsky.console.commands.Command;
import pro.sorokovsky.console.commands.Context;
import pro.sorokovsky.normal.frame.ThreeFrame;

public class NormalThreeTask extends Command {
    @Override
    protected String getName() {
        return "Реалізувати чергу, по натисканню на кнопку додавати елементи в чергу";
    }

    @Override
    public void execute(Context context) {
        new ThreeFrame(getName()).setVisible(true);
    }
}
