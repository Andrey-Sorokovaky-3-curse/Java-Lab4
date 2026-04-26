package pro.sorokovsky.console.inputs;

import pro.sorokovsky.console.exceptions.ValidationException;

/**
 * Клас для введення чисел, що відповідають за розмір.
 *
 * @author Сороковський Андрій
 * @version 1.0.0
 */
public class SizeInput extends Input<Integer> {
    /**
     * Перетворює сире значення у число.
     * @param value сире значення, щойно введене із клавіатури.
     * @return Число введене із клавіатури.
     * @throws ValidationException помилка перетворення.
     */

    @Override
    protected Integer transform(String value) throws ValidationException {
        try {
            final var size = Integer.parseInt(value);
            if (size < 0) throw new ValidationException("Введене значення має бути позитивним числом.");
            return size;
        } catch (NumberFormatException exception) {
            throw new ValidationException("Введене значення не число.");
        }
    }
}
