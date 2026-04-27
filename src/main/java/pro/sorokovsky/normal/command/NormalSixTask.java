package pro.sorokovsky.normal.command;

import pro.sorokovsky.console.commands.Command;
import pro.sorokovsky.console.commands.Context;
import pro.sorokovsky.normal.frame.SixFrame;

public class NormalSixTask extends Command {
    @Override
    protected String getName() {
        return "Реалізувати однозв’язний список, по натисканню на кнопку додавати елементи перед виділеним у список. Якщо не виділено – в кінець";
    }

    @Override
    public void execute(Context context) {
        new SixFrame(getName()).setVisible(true);
    }
}
