package pro.sorokovsky.normal.frame;

import pro.sorokovsky.normal.handler.AddItemToStackHandler;

import javax.swing.*;

public class OneFrame extends JFrame {
    public OneFrame(String title) {
        setTitle(title);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 600);

        final var model = new DefaultListModel<Integer>();

        final var panel = new JPanel();
        final var label = new JLabel("Стек");
        final var list = new JList<>(model);
        final var button = new JButton("Додати елемент у стек");
        final var listener = new AddItemToStackHandler(model);
        button.addActionListener(listener);
        panel.add(label);
        panel.add(list);
        panel.add(button);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        add(panel);
    }
}
