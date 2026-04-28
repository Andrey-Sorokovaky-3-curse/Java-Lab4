package pro.sorokovsky.hard;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class HardFrame extends JFrame {
    private final JPanel rootPanel = new JPanel();

    public HardFrame(String title) {
        final var scrollPane = new JScrollPane();
        scrollPane.setViewportView(rootPanel);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setTitle(title);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(900,600);
        setResizable(true);
        add(scrollPane);
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
        rootPanel.setAlignmentY(TOP_ALIGNMENT);
        rootPanel.setAlignmentX(LEFT_ALIGNMENT);
        setupFirstTask();
        setupSecondTask();
        setupThirdTask();
        setupFourthTask();
        setupFifthTask();
        setupSixthTask();
        setupSeventhTask();
        setupEightTask();
        setupNinetiethTask();
        setupTenthTask();
        setupEleventhTask();
        setupTwelfthTask();
        setupThirteenthTask();
        setupFourteenthTask();
        setupFifteenthTask();
        setupSixteenthTask();
        setupSeventeenthTask();
        setupEighteenthTask();
        setupNineteenthTask();
    }

    private void setupNineteenthTask() {
        rootPanel.add(configureConvertorTask(
                "Вивести значення логічного виразу, заданого у вигляді рядка S.\n" +
                        "Вираз визначається наступним чином (\"T\" - True, \"F\" - False):\n" +
                        "<вираз> :: = T | F | And (<операнди>) | Or (<операнди>) <операнди> :: = <вираз> , <вираз>.",
                new NineteenthValidator()
        ));
    }

    private void setupEighteenthTask() {
        rootPanel.add(configureConvertorTask(
                "Перевірити правильність виразу, заданого у вигляді рядка S\n" +
                        "(вираз визначається за тими ж правилами, що і в завданні 16).\n" +
                        "Якщо вираз складено правильно, то вивести 0, у противному\n" +
                        "випадку вивести номер першого помилкового (або зайвого) символу в рядку S.",
                new SixteenthWithPositionValidation()
        ));
    }

    private void setupSeventeenthTask() {
        rootPanel.add(configureConvertorTask(
                "Перевірити правильність виразу, заданого у вигляді рядка S\n" +
                        "(вираз визначається за тими ж правилами, що і в завданні 16).\n" +
                        "Якщо вираз складено правильно, то вивести True, інакше вивести False.",
                new SixteenthValidator()
        ));
    }

    private void setupSixteenthTask() {
        rootPanel.add(configureConvertorTask(
                "Вивести значення цілочисельного виразу, заданого у вигляді\n" +
                        "рядка S. Вираз визначається наступним чином: <вираз> :: =\n" +
                        "<цифра> | (<вираз> <знак> <вираз>) <знак> :: = + | - | *.",
                new BracketExpressionEvaluator()
        ));
    }

    private void setupFirstTask() {
        rootPanel.add(configureConvertorTask(
                "Перетворення математичного виразу в інфіксній (a+b*c) формі до префіксної (+a*bc) форми",
                new InfixToPrefixConverter()
        ));
    }

    private void setupSecondTask() {
        rootPanel.add(configureConvertorTask(
                "Перетворення математичного виразу в інфіксній (a+b*c) формі до постфіксної (abc*+) форми.",
                new InfixToPostfixConverter()
                ));
    }

    private void setupThirdTask() {
        rootPanel.add(configureConvertorTask(
                "Перетворення математичного виразу в префіксній (+a*bc) формі до інфіксної (a+b*c) форми.",
                new PrefixToInfixConverter()
        ));
    }

    private void setupFourthTask() {
        rootPanel.add(configureConvertorTask(
                "Перетворення математичного виразу в префіксній (+a*bc) формі до постфіксної (abc*+) форми.",
                new PrefixToPostfixConverter()
        ));
    }

    private void setupFifthTask() {
        rootPanel.add(configureConvertorTask(
                "Перетворення математичного виразу в постфіксній (abc*+) формі до префіксної (+a*bc) форми.",
                new PostfixToPrefixConverter()
        ));
    }

    private void setupSixthTask() {
        rootPanel.add(configureConvertorTask(
                "Перетворення математичного виразу в постфіксній (abc*+) формі до інфіксної (a+b*c) форми.",
                new PostfixToInfixConverter()
        ));
    }

    private void setupSeventhTask() {
        rootPanel.add(configureConvertorTask(
                "Заданий вираз, що містить круглі дужки. Визначити баланс дужок (Кожній відкриваючій відповідає закриваюча).",
                new BracketBalancer()
        ));
    }

    private void setupEightTask() {
        rootPanel.add(configureConvertorTask(
                "Заданий вираз, що містить круглі, квадратні та фігурні дужки. Визначити баланс дужок",
                new MultiBracketBalancer()
        ));
    }

    private void setupNinetiethTask() {
        rootPanel.add(configureConvertorTask(
                "Необхідно в новому рядку\n" +
                        "записати всі символи, розміщені поза дужками, а потім в зворотньому порядку символи, що заключні в дужки.",
                new BracketReverseExtractor()
        ));
    }

    private void setupTenthTask() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(
                "Перевірка прямокутності грядок (садова ділянка M×N)"
        ));

        JButton openGardenBtn = new JButton("Відкрити перевірку грядок");
        openGardenBtn.addActionListener(e -> {
            GardenFrame gardenFrame = new GardenFrame();
            gardenFrame.setVisible(true);
        });

        panel.add(openGardenBtn);
        rootPanel.add(panel);
        rootPanel.add(Box.createVerticalStrut(15));
    }

    private void setupEleventhTask() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(
                "Садова ділянка - Аналіз грядок (садова ділянка M×N)"
        ));

        JButton openGardenBtn = new JButton("Відкрити перевірку грядок");
        openGardenBtn.addActionListener(e -> {
            CountGardenFrame gardenFrame = new CountGardenFrame();
            gardenFrame.setVisible(true);
        });

        panel.add(openGardenBtn);
        rootPanel.add(panel);
        rootPanel.add(Box.createVerticalStrut(15));
    }

    private void setupTwelfthTask() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(LEFT_ALIGNMENT);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("12. Підрахунок квадратних грядок"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        JButton openGardenBtn = new JButton("⬛ Знайти квадратні грядки");
        openGardenBtn.setFont(new Font("Arial", Font.BOLD, 12));
        openGardenBtn.setAlignmentX(LEFT_ALIGNMENT);
        openGardenBtn.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                new SquareGardenFrame().setVisible(true);
            });
        });

        JLabel infoLabel = new JLabel("📌 Визначає кількість квадратних грядок (наприклад: 2×2, 3×3)");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        infoLabel.setForeground(Color.GRAY);
        infoLabel.setAlignmentX(LEFT_ALIGNMENT);

        panel.add(openGardenBtn);
        panel.add(Box.createVerticalStrut(5));
        panel.add(infoLabel);

        rootPanel.add(panel);
        rootPanel.add(Box.createVerticalStrut(15));
    }

    private void setupThirteenthTask() {
        rootPanel.add(configureConvertorTask(
                "Вивести значення цілочисельного виразу, заданого у вигляді\n" +
                        "рядка S. Вираз визначається наступним чином: <вираз> :: =\n" +
                        "<цифра> | <вираз> + <цифра> | <вираз> - <цифра>.",
                new IntegerExpressionEvaluator()
        ));
    }

    private void setupFourteenthTask() {
        rootPanel.add(configureConvertorTask(
                "Вивести значення цілочисельного виразу, заданого у вигляді\n" +
                        "рядка S. Вираз визначається наступним чином: <вираз> :: =\n" +
                        "<терм> | <вираз> + <терм> | <вираз> - <терм> <терм> :: = <цифра> | <терм> * <цифра>.",
                new IntegerExpression2Evaluator()
        ));
    }

    private void setupFifteenthTask() {
        rootPanel.add(configureConvertorTask(
                "Вивести значення цілочисельного виразу, заданого у вигляді\n" +
                        "рядка S. Вираз визначається наступним чином: <вираз> :: =\n" +
                        "<терм> | <вираз> + <терм> | <вираз> - <терм> <терм> :: = <елемент> | <терм> * <елемент> <елемент> :: = <цифра> | (<вираз>).",
                new FullExpressionEvaluator()
        ));
    }

    private @NotNull JPanel configureConvertorTask(String title, Converter converter) {
        final var panel = new JPanel();
        final var label = new JLabel(title);
        label.setSize(new Dimension(100, 100));
        final var input = new JTextField("Вхідне значення");
        final var output = new JTextField("Вихідне значення");
        final var button = new JButton("перетворити");
        button.addActionListener(e -> output.setText(converter.convert(input.getText())));
        output.setEditable(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setAlignmentX(LEFT_ALIGNMENT);
        rootPanel.add(label);
        panel.add(input);
        panel.add(output);
        panel.add(button);
        return panel;
    }
}
