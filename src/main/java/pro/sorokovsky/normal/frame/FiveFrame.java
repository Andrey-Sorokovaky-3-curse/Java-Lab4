package pro.sorokovsky.normal.frame;

import pro.sorokovsky.normal.handler.AddItemToListHandler;

import javax.swing.*;

public class FiveFrame extends JFrame {
    public FiveFrame(String title) {
        setTitle(title);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 600);
        final var panel = new JPanel();
        final var model = new DefaultListModel<Integer>();

        final var label = new JLabel("Однозв'язний список");
        final var list = new JList<>(model);
        final var addButton = new JButton("Додати");
        addButton.addActionListener(new AddItemToListHandler(list));
        panel.add(label);
        panel.add(list);
        panel.add(addButton);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        add(panel);
    }
}
