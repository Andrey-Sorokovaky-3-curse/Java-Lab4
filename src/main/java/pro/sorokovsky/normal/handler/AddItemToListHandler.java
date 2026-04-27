package pro.sorokovsky.normal.handler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddItemToListHandler implements ActionListener {
    private final JList<Integer> list;
    private final boolean toLast;

    private Integer count = 0;

    public AddItemToListHandler(JList<Integer> list, boolean toLast) {
        this.list = list;
        this.toLast = toLast;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        final var selected = list.getSelectedIndex();
        int index;
        if (toLast) {
            index = selected < 0 ? list.getModel().getSize() : selected;
        } else {
            index = Math.max(selected, 0);
        }
        DefaultListModel<Integer> model = (DefaultListModel<Integer>) list.getModel();
        model.add(index, ++count);

    }
}
