package pro.sorokovsky.normal.frame;

import pro.sorokovsky.normal.handler.RemoveFromListWithErrorHandler;

import javax.swing.*;

public class TenFrame extends JFrame {
    public TenFrame(String title) {
        setTitle(title);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 600);

        final var panel = new JPanel();
        final var model = new DefaultListModel<Integer>();
        model.addElement(1);
        model.addElement(2);
        model.addElement(3);
        model.addElement(4);

        final var label = new JLabel("Однозв'язний список");
        final var list = new JList<>(model);
        final var addButton = new JButton("Видалити");
        final var text = new JTextField("Текстове поле");
        addButton.addActionListener(new RemoveFromListWithErrorHandler(text, list));
        panel.add(label);
        panel.add(list);
        panel.add(addButton);
        panel.add(text);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        add(panel);
    }
}
