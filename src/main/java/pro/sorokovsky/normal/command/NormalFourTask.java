package pro.sorokovsky.normal.command;

import pro.sorokovsky.console.commands.Command;
import pro.sorokovsky.console.commands.Context;
import pro.sorokovsky.normal.frame.FourFrame;

public class NormalFourTask extends Command {
    @Override
    protected String getName() {
        return "Реалізувати чергу, по натисканню на кнопку видаляти елементи з черги, поміщати їх значення в текстове поле";
    }

    @Override
    public void execute(Context context) {
        new FourFrame(getName()).setVisible(true);
    }
}
