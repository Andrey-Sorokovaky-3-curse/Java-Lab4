package pro.sorokovsky.console.commands;

/**
 * Клас команди, яка відповідає за вихід із контексту команд.
 *
 * @author Сорковський Андрій
 * @version 1.0.0
 */
public class ExitCommand extends Command {

    /**
     * Отримує поточну назву команди.
     *
     * @return "Вихід"
     */
    @Override
    protected String getName() {
        return "Вихід";
    }

    /**
     * Зупиняє контекст команди.
     *
     * @param context контекст команд для виконання.
     */
    @Override
    public void execute(Context context) {
        context.stop();
    }
}
