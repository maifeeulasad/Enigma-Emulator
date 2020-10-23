package enigma.ui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MButton extends JButton {

    public MButton(String text, ActionListener actionListener){
        super(text);
        addActionListener(actionListener);
    }

}
