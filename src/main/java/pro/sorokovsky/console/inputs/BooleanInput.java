package pro.sorokovsky.console.inputs;

import pro.sorokovsky.console.exceptions.ValidationException;

import java.util.List;

/**
 * Клас для введення логічного true, false.
 * @author Сороковський Андрій
 * @version 1.0.0
 */
public class BooleanInput extends Input<Boolean> {
    private final String yes;
    private final String no;

    /**
     * Ініціації класу позамовчуваню.
     */
    public BooleanInput() {
        yes = "Так";
        no = "Ні";
    }

    /**
     * Ініціації класу із параметрами.
     * @param yes значення, яке відповідає за true.
     * @param no значення, яке відповідає за false.
     */
    public BooleanInput(String yes, String no) {
        this.yes = yes;
        this.no = no;
    }

    /**
     * Отримання опцій.
     * @return Опції для введення.
     */
    @Override
    public List<String> getOptions() {
        return List.of(yes, no);
    }

    /**
     * Перетворює сире значення у Boolean значення.
     * @param value сире значення, щойно введене із клавіатури.
     * @return Boolean значення.
     * @throws ValidationException помилка валідації.
     */
    @Override
    protected Boolean transform(String value) throws ValidationException {
        final var exception = new ValidationException("Значення має бути '%s' чи '%s'.".formatted(yes, no));
        if (value == null || value.isBlank()) throw exception;
        if (value.trim().equalsIgnoreCase(yes)) return true;
        if (value.trim().equalsIgnoreCase(no)) return false;
        throw exception;
    }
}
