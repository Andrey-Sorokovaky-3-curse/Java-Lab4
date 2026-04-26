package pro.sorokovsky.console.commands;

import pro.sorokovsky.console.exceptions.ValidationException;

import java.util.*;

/**
 * Контекст команд, клас призначений для керування командами.
 *
 * @author Сороковський Андрій
 * @version 1.0.0
 */
public class Context extends Command {
    private final String name;
    private final Dictionary<String, Object> claims = new Hashtable<>();

    private boolean isRunning;
    private final List<Command> commands = new LinkedList<>();

    /**
     * Створює контекст команд.
     *
     * @param name назва контексту.
     */
    public Context(String name) {
        this.name = name;
    }

    /**
     * Додає команду до контексту команд.
     *
     * @param command команда
     * @return модифікований контекст команди.
     */
    public Context addCommand(Command command) {
        commands.add(command);
        return this;
    }

    /**
     * Запускає контекст команд.
     */
    public void start() {
        isRunning = true;
        loop();
    }

    /**
     * Зупиняє контекст команд.
     */
    public void stop() {
        isRunning = false;
    }

    /**
     * Запускає контекст.
     *
     * @param context контекст команд для виконання.
     */
    @Override
    public void execute(Context context) {
        start();
    }

    /**
     * Перевіряє наявність клейму.
     * @param name Назва клейму.
     * @return true якщо клейм є, false якщо його нема
     */
    public boolean hasClaim(String name) {
        return claims.get(name) != null;
    }

    /**
     * Встановлює клейм.
     * @param name ім'я клейму.
     * @param value значення клейму.
     * @param <T> тип значення клейму.
     */
    public <T> void setClaim(String name, T value) {
        claims.put(name, value);
    }

    /**
     * Повертає клейм
     * @param name ім'я клейму
     * @param requiredType клас значення у яке потрібно перетворити клейма.
     * @return значення клейму
     * @param <T> тип значення клейму.
     * @throws NullPointerException якщо клейм не знайдено.
     * @throws ValidationException якщо помилка валідації.
     */
    public <T> T getClaim(String name, Class<T> requiredType) throws ValidationException {
        Object value = claims.get(name);
        if (value == null) throw new ValidationException("Клейм не знайдено");
        else if (requiredType.isInstance(value)) {
            return requiredType.cast(value);
        } else {
            throw new ValidationException("Клейм не правильного типу.");
        }
    }

    /**
     * Видаляє клейм.
     *
     * @param name ім'я клейму
     */
    public void clearClaim(String name) {
        if (!hasClaim(name)) return;
        claims.remove(name);
    }

    /**
     * Повертає назву контексту.
     *
     * @return назва контексту.
     */
    @Override
    protected String getName() {
        return name;
    }

    /**
     * Запитує та запускає команди поки контекст не зупиниться.
     */
    private void loop() {
        while (isRunning) {
            try {
                prepare();
                showCommands();
                chooseCommand().execute(this);
            } catch (Exception exception) {
                System.out.println("Сталася незрозуміла помилка, спробуйте ще.");
            }
        }
    }

    /**
     * Виводить список команд на екран.
     */
    private void showCommands() {
        System.out.printf("%s:%n", name);
        commands.forEach(command -> System.out.println(command.getTitle()));
    }

    /**
     * Запитує у користувача команду.
     *
     * @return вибрану команду.
     */
    private Command chooseCommand() {
        boolean isOk;
        Command command = null;
        do {
            try {
                final var scanner = new Scanner(System.in);
                isOk = true;
                System.out.print(">> ");
                final var number = scanner.nextLong();
                command = commands.stream().filter(item -> item.getId().equals(number)).findFirst().orElse(null);
                if (command == null) {
                    isOk = false;
                    System.out.println("Команду не розпізнано, спробуйте ще.");
                }
            } catch (InputMismatchException exception) {
                System.out.println("Ви ввели не число, спробуйте ще.");
                isOk = false;
            }
        } while (!isOk);
        return command;
    }

    /**
     * Підготовлює команди для виконання.
     */
    private void prepare() {
        var index = 0L;
        for (Command command : commands) {
            command.setId(index);
            index++;
        }
    }
}
