package pro.sorokovsky.normal.handler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveFromStackHandler implements ActionListener {
    private final DefaultListModel<Integer> listModel;
    private final JTextField textField;

    public RemoveFromStackHandler(DefaultListModel<Integer> listModel, JTextField textField) {
        this.listModel = listModel;
        this.textField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (listModel.getSize() <= 0) return;
        final var item = listModel.get(0);
        textField.setText(item.toString());
        listModel.remove(0);
    }
}
