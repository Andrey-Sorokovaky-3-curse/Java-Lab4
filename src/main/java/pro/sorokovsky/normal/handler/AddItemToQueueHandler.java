package pro.sorokovsky.normal.handler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddItemToQueueHandler implements ActionListener {
    private Integer count = 0;
    private final DefaultListModel<Integer> listModel;

    public AddItemToQueueHandler(DefaultListModel<Integer> listModel) {
        this.listModel = listModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listModel.add(0, ++count);
    }
}
