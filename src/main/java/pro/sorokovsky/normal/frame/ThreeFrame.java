package pro.sorokovsky.normal.frame;

import pro.sorokovsky.normal.handler.AddItemToQueueHandler;

import javax.swing.*;

public class ThreeFrame extends JFrame {
    public ThreeFrame(String title) {
        setTitle(title);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 600);

        final var model = new DefaultListModel<Integer>();

        final var panel = new JPanel();
        final var label = new JLabel("Черга");
        final var list = new JList<>(model);
        final var addButton = new JButton("Додати елемент у чергу");
        final var addItem = new AddItemToQueueHandler(model);
        addButton.addActionListener(addItem);
        panel.add(label);
        panel.add(list);
        panel.add(addButton);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        add(panel);
    }
}
