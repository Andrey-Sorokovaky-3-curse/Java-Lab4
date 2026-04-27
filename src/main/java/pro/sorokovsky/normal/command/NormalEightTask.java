package pro.sorokovsky.normal.command;

import pro.sorokovsky.console.commands.Command;
import pro.sorokovsky.console.commands.Context;
import pro.sorokovsky.normal.frame.EightFrame;

public class NormalEightTask extends Command {
    @Override
    protected String getName() {
        return "Реалізувати однозв’язний список, по натисканню на кнопку видаляти виділений елемент зі списку, помістити його значення в текстове поле. Якщо не виділено – видаляти перший елемент";
    }

    @Override
    public void execute(Context context) {
        new EightFrame(getName()).setVisible(true);
    }
}
