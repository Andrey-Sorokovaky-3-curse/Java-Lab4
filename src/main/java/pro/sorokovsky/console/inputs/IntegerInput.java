package pro.sorokovsky.console.inputs;

import pro.sorokovsky.console.exceptions.ValidationException;

/**
 * Клас для введення цілого числа.
 *
 * @author Сороковський Андрій
 * @version 1.0.0
 */
public class IntegerInput extends Input<Integer> {
    /**
     * Перетворює сире значення на число.
     *
     * @param value сире значення, щойно введене із клавіатури.
     * @return ціле число
     * @throws ValidationException якщо не число.
     */
    @Override
    protected Integer transform(String value) throws ValidationException {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            throw new ValidationException("Ви ввели не ціле число.");
        }
    }
}
