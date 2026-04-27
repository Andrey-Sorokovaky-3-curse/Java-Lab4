package pro.sorokovsky.normal.handler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddItemToListHandler implements ActionListener {
    private final JList<Integer> list;

    private Integer count = 0;

    public AddItemToListHandler(JList<Integer> list) {
        this.list = list;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        final var selected = list.getSelectedIndex();
        final var index = Math.max(selected, 0);
        DefaultListModel<Integer> model = (DefaultListModel<Integer>) list.getModel();
        model.add(index, ++count);

    }
}
