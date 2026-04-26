package pro.sorokovsky.console.commands;

/**
 * Абстрактний клас для роботи із консольними командами у контексті команд.
 *
 * @author Сороковський Андрій
 * @version 1.0.0
 */
public abstract class Command {
    private Long id;

    /**
     * Отримує назву команди.
     *
     * @return назва команди.
     */
    protected abstract String getName();

    /**
     * Виконує команду.
     *
     * @param context контекст команд для виконання.
     */
    public abstract void execute(Context context);

    /**
     * Отримує заголовок команди для відображення у контексті команд.
     *
     * @return Заголовок команди.
     */
    public String getTitle() {
        return "%d-%s.".formatted(id, getName());
    }

    /**
     * Встановлення унікального номера команди.
     *
     * @param id для ідентифікації команди у контексті команд.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Отримання унікального номера команди.
     *
     * @return id унікальний номер команди у контексті.
     */
    public Long getId() {
        return id;
    }
}
