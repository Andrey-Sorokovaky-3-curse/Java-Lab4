package pro.sorokovsky.normal.handler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddElementBeforeWithError implements ActionListener {
    private final JTextField textField;
    private final JList<Integer> list;
    private Integer count = 1;

    public AddElementBeforeWithError(JTextField textField, JList<Integer> list) {
        this.textField = textField;
        this.list = list;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        final var selected = list.getSelectedIndex();
        textField.setText("Текстове поле");
        if (selected < 0) {
            textField.setText("Елемент не виділено");
        } else {
            final var model = (DefaultListModel<Integer>)list.getModel();
            model.add(selected, ++count);
        }
    }
}
