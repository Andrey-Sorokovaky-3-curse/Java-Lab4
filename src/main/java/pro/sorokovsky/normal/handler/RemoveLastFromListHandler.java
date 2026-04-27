package pro.sorokovsky.normal.handler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveLastFromListHandler implements ActionListener {
    private final JTextField textField;
    private final JList<Integer> list;

    public RemoveLastFromListHandler(JTextField textField, JList<Integer> list) {
        this.textField = textField;
        this.list = list;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        DefaultListModel<Integer> model = (DefaultListModel<Integer>) list.getModel();
        if (model.isEmpty()) return;
        final var selectedIndex = list.getSelectedIndex();
        int index;
        if(selectedIndex <= 0) index = model.size() - 1;
        else index = selectedIndex;
        final var item = model.get(index);
        textField.setText(item.toString());
        model.remove(index);
    }
}
