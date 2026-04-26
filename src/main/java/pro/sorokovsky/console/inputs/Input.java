package pro.sorokovsky.console.inputs;

import pro.sorokovsky.console.exceptions.ValidationException;

import java.util.List;
import java.util.Scanner;

/**
 * Клас призначений для введення даних.
 *
 * @author Сороковський Андрій.
 * @version 1.0.0
 */
public abstract class Input<T> {
    /**
     * Отримання заклику до діх.
     *
     * @return заклик до дії.
     */
    protected String getAction() {
        return "Введіть";
    }

    /**
     * Отримання опцій введення.
     *
     * @return опції введення.
     */
    protected List<String> getOptions() {
        return List.of();
    }

    /**
     * Перетворює сире значення у потрібний тип.
     *
     * @param value сире значення, щойно введене із клавіатури.
     * @return трансформоване значення.
     * @throws ValidationException помилка при некоректному значенню.
     */
    protected abstract T transform(String value) throws ValidationException;

    /**
     * Запит на введення від користувача.
     *
     * @param name назва об'єкта введення
     * @return запитання до введення.
     */
    protected String getQuestion(String name) {
        final var options = getOptions().isEmpty() ? "" : "(%s)".formatted(String.join(", ", getOptions()));
        return "%s %s%s: ".formatted(getAction(), name, options);
    }

    /**
     * Отримує дані від користувача.
     *
     * @param name назва сутності для введення
     * @return введенні дані.
     */

    public T enter(String name) {
        boolean isOk;
        T value = null;
        do {
            try {
                final var scanner = new Scanner(System.in);
                isOk = true;
                System.out.print(getQuestion(name));
                value = transform(scanner.nextLine());
            } catch (ValidationException exception) {
                isOk = false;
                System.out.println(exception.getMessage());
                System.out.println("Спробуйте ще.");
            }
        } while (!isOk);
        return value;
    }
}
