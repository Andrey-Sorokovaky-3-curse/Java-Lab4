package pro.sorokovsky.normal;

import javax.swing.*;

public class OneFrame extends JFrame {
    private Integer count = 0;

    public OneFrame(String title) {
        setTitle(title);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 600);

        final var model = new DefaultListModel<Integer>();

        final var panel = new JPanel();
        final var label = new JLabel("Стек");
        final var list = new JList<>(model);
        final var button = new JButton("Додати елемент у стек");
        button.addActionListener(e -> model.add(0, ++count));
        panel.add(label);
        panel.add(list);
        panel.add(button);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        add(panel);
    }
}
