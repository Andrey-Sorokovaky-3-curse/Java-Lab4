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
