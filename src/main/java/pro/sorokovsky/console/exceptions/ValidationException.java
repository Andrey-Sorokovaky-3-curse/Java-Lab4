package pro.sorokovsky.console.exceptions;

/**
 * Виняток валідації.
 *
 * @author Сороковський Андрій
 * @version 1.0.0
 */
public class ValidationException extends Exception {
    /**
     * Створює виняток.
     * @param message повідомлення.
     */
    public ValidationException(String message) {
        super(message);
    }

    /**
     * Створює виняток.
     * @param message повідомлення винятку.
     * @param cause причина винятку.
     */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
