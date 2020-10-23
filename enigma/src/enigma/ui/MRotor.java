package enigma.ui;

import javax.swing.*;
import java.awt.*;

public class MRotor extends JTextField {

    MRotor(String text){
        super(text,30);
        setDisabledTextColor(Color.BLACK);
        setEditable(false);
        setEnabled(false);
        setDragEnabled(false);
    }

    MRotor(String text,int index){
        this("#"+index+":"+text);
    }

}
