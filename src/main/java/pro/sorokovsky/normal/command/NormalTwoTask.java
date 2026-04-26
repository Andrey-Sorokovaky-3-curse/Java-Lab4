package pro.sorokovsky.normal.command;

import pro.sorokovsky.console.commands.Command;
import pro.sorokovsky.console.commands.Context;
import pro.sorokovsky.normal.frame.TwoFrame;

public class NormalTwoTask extends Command {
    @Override
    protected String getName() {
        return "Реалізувати стек, по натисканню на кнопку видаляти елементи зі стеку, поміщати їх значення в текстове поле";
    }

    @Override
    public void execute(Context context) {
        new TwoFrame(getName()).setVisible(true);
    }
}
