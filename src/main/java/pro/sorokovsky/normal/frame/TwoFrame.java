package pro.sorokovsky.normal.frame;

import pro.sorokovsky.normal.handler.AddItemToStackHandler;
import pro.sorokovsky.normal.handler.RemoveFromStackHandler;

import javax.swing.*;
import java.awt.*;

public class TwoFrame extends JFrame {
    public TwoFrame(String title) {
        setTitle(title);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 600);

        final var model = new DefaultListModel<Integer>();

        final var panel = new JPanel();
        final var label = new JLabel("Стек");
        final var list = new JList<>(model);
        final var addButton = new JButton("Додати елемент у стек");
        final var removeButton = new JButton("Видалити елемент зі стеку");
        final var text = new JTextField("Текстове поле");
        text.setEditable(false);
        final var addItem = new AddItemToStackHandler(model);
        final var removeItem = new RemoveFromStackHandler(model, text);
        removeButton.addActionListener(removeItem);
        addButton.addActionListener(addItem);
        panel.add(label);
        panel.add(list);
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(text);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        add(panel);
    }
}
