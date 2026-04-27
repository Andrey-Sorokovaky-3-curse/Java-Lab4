package pro.sorokovsky.hard;

import pro.sorokovsky.console.commands.Command;
import pro.sorokovsky.console.commands.Context;

public class HardCommand extends Command {
    @Override
    protected String getName() {
        return "Складний рівень";
    }

    @Override
    public void execute(Context context) {
        new HardFrame(getName()).setVisible(true);
    }
}
