package pro.sorokovsky.console.inputs;

/**
 * Клас для введення рядків.
 * @author Сороковський Андрій
 * @version 1.0.0
 */
public class StringInput extends Input<String> {
    /**
     * Перетворює сире значення у рядок.
     * @param value сире значення, щойно введене із клавіатури.
     * @return рядкове значення
     */
    @Override
    protected String transform(String value) {
        return value;
    }
}
