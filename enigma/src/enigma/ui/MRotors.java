package enigma.ui;

import enigma.core.Enigma;

import javax.swing.*;

public class MRotors {

    private final static String INPUT = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    MRotorVertical reflector;
    MRotorVertical[] direction;
    MRotorVertical[] rotor;
    MRotorVertical[] arrow;
    MRotorVertical alphabets;

    Enigma enigma;

    public MRotors(Enigma enigma){
        this.enigma = enigma;
        direction = new MRotorVertical[4];
        rotor = new MRotorVertical[6];
        arrow = new MRotorVertical[3];
        reflector = new MRotorVertical();
        direction[3] = new MRotorVertical();
        direction[2] = new MRotorVertical();
        direction[1] = new MRotorVertical();
        direction[0] = new MRotorVertical();
        rotor[5] = new MRotorVertical();
        rotor[4] = new MRotorVertical();
        rotor[3] = new MRotorVertical();
        rotor[2] = new MRotorVertical();
        rotor[1] = new MRotorVertical();
        rotor[0] = new MRotorVertical();
        arrow[2] = new MRotorVertical();
        arrow[1] = new MRotorVertical();
        arrow[0] = new MRotorVertical();
        alphabets = new MRotorVertical(INPUT);
    }

    public void setDirection(int toReflector){
        MRotorVertical tem = direction[0];
        tem.setRotorPosition(1, toReflector);
        tem.revalidate();
        tem.repaint();
    }

    private void setDirection(int index,int fromReflector,int toReflector){
        MRotorVertical tem = direction[index];
        tem.setRotorPosition(fromReflector, toReflector);
        tem.revalidate();
        tem.repaint();
    }

    public JPanel addAll(JPanel parent){
        parent.add(reflector);
        parent.add(direction[3]);
        parent.add(rotor[5]);
        parent.add(arrow[2]);
        parent.add(rotor[4]);
        parent.add(direction[2]);
        parent.add(rotor[3]);
        parent.add(arrow[1]);
        parent.add(rotor[2]);
        parent.add(direction[1]);
        parent.add(rotor[1]);
        parent.add(arrow[0]);
        parent.add(rotor[0]);
        parent.add(direction[0]);
        parent.add(alphabets);
        return parent;
    }

}
