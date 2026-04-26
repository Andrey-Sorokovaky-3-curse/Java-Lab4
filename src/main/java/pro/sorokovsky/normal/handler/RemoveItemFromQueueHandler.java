package pro.sorokovsky.normal.handler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveItemFromQueueHandler implements ActionListener {
    private final DefaultListModel<Integer> listModel;
    private final JTextField textField;

    public RemoveItemFromQueueHandler(DefaultListModel<Integer> listModel, JTextField textField) {
        this.listModel = listModel;
        this.textField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (listModel.size() <= 0) return;
        final var number = listModel.lastElement();
        listModel.remove(listModel.size() - 1);
        textField.setText(number.toString());
    }
}
