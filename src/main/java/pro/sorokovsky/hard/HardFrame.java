package pro.sorokovsky.hard;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class HardFrame extends JFrame {
    private final JPanel rootPanel = new JPanel();

    public HardFrame(String title) {
        setTitle(title);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800,600);
        setResizable(false);
        add(rootPanel);
        rootPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        rootPanel.setAlignmentY(TOP_ALIGNMENT);
        setupFirstTask();
        setupSecondTask();
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

    private @NotNull JPanel configureConvertorTask(String title, Converter converter) {
        final var panel = new JPanel();
        final var label = new JLabel(title);
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
