package enigma.ui;

import javax.swing.*;
import java.awt.*;

public class MRotorVertical extends JTextArea {

    private static final int ROTAR_SIZE=26;
    private static final char TO_REFLECTOR = '<';
    private static final char FROM_REFLECTOR = '>';

    public MRotorVertical(String rotorText){
        super(String.join("\n",rotorText.split("")));
        setEditable(false);
        setEnabled(false);
        setDisabledTextColor(Color.BLACK);
        setRows(ROTAR_SIZE);
    }

    public void setRotorText(String text){
        setText(String.join("\n",text.split("")));
    }

    public void setRotorPosition(int fromReflector,int toReflector){
        setText(String.join("\n",generateToFromString(fromReflector, toReflector).split("")));
    }

    public MRotorVertical(int fromReflector,int toReflector){
        this(generateToFromString(fromReflector, toReflector));
    }

    private static String generateToFromString(int fromReflector,int toReflector){
        fromReflector %= ROTAR_SIZE;
        toReflector %= ROTAR_SIZE;
        StringBuilder builder = new StringBuilder(" ".repeat(ROTAR_SIZE));
        builder.setCharAt(toReflector-1,TO_REFLECTOR);
        builder.setCharAt(fromReflector-1,FROM_REFLECTOR);
        return builder.toString();
    }

}