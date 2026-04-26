package pro.sorokovsky.normal.handler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddItemToStackHandler implements ActionListener {
    private final DefaultListModel<Integer> model;

    private Integer count = 0;

    public AddItemToStackHandler(DefaultListModel<Integer> model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.add(0, ++count);
    }
}
