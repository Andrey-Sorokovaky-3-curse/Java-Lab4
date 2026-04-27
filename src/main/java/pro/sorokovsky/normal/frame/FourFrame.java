package pro.sorokovsky.normal.frame;

import pro.sorokovsky.normal.handler.AddItemToQueueHandler;
import pro.sorokovsky.normal.handler.RemoveItemFromQueueHandler;

import javax.swing.*;

public class FourFrame extends JFrame {
    public FourFrame(String title) {
        setTitle(title);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 600);

        final var model = new DefaultListModel<Integer>();

        final var panel = new JPanel();
        final var label = new JLabel("Черга");
        final var list = new JList<>(model);
        final var addButton = new JButton("Додати елемент у чергу");
        final var removeButton = new JButton("Видалити елемент із черги");
        final var text = new JTextField("Текстове поле");
        text.setEditable(false);
        addButton.addActionListener(new AddItemToQueueHandler(model));
        removeButton.addActionListener(new RemoveItemFromQueueHandler(model, text));
        panel.add(label);
        panel.add(list);
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(text);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        add(panel);
    }
}
