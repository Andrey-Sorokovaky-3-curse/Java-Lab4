package pro.sorokovsky.normal.command;

import pro.sorokovsky.console.commands.Command;
import pro.sorokovsky.console.commands.Context;
import pro.sorokovsky.normal.frame.TenFrame;

public class NormalTenTask extends Command {
    @Override
    protected String getName() {
        return "Реалізувати однозв’язний список, по натисканню на кнопку видаляти виділений елемент зі списку, помістити його значення в текстове поле. Якщо не виділено – видати повідомлення про помилку";
    }

    @Override
    public void execute(Context context) {
        new TenFrame(getName()).setVisible(true);
    }
}
