package enigma.ui;

import enigma.core.Enigma;

import javax.swing.*;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MRotors {

    private final static String INPUT = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final static int ROTOR_COUNT = 6;
    private final static int ARROW_COUNT = 3;
    private final static int DIRECTION_COUNT = 4;

    private final static int ROTOR_SIZE = 26;

    MRotorVertical reflector;
    MRotorVertical[] direction;
    MRotorVertical[] rotor;
    MRotorVertical[] arrow;
    MRotorVertical alphabets;

    Enigma enigma;

    public MRotors(Enigma enigma) {
        this.enigma = enigma;
        direction = new MRotorVertical[DIRECTION_COUNT];
        rotor = new MRotorVertical[ROTOR_COUNT];
        arrow = new MRotorVertical[ARROW_COUNT];
        reflector = new MRotorVertical();
        for (int i = 0; i < DIRECTION_COUNT; i++)
            direction[i] = new MRotorVertical();
        for (int i = 0; i < ARROW_COUNT; i++)
            arrow[i] = new MRotorVertical();
        for (int i = 0; i < ROTOR_COUNT; i++)
            rotor[i] = new MRotorVertical();
        alphabets = new MRotorVertical(INPUT);
    }

    public char setInputChar(char inputChar) {
        calculateArrow();
        return calculateEncodedChar(inputChar);
    }

    public JPanel addAll(JPanel parent) {
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

    public void load() {
        for (int i = 0; i < ARROW_COUNT; i++) {
            char x = enigma.initialPermutation.charAt(i);
            int toRotate = enigma.rotors[2 * i].toLowerCase().indexOf(x);
            enigma.arrowPositions[i] -= toRotate;
            enigma.arrowPositions[i] %= ROTOR_SIZE;
            enigma.rotors[2 * i]
                    = enigma.rotors[2 * i].substring(toRotate) + enigma.rotors[2 * i].substring(0, toRotate);
            enigma.rotors[2 * i + 1]
                    = enigma.rotors[2 * i + 1].substring(toRotate) + enigma.rotors[2 * i + 1].substring(0, toRotate);
        }

        for (int i = 0; i < ROTOR_COUNT; i++) {
            rotor[i].setRotorText(enigma.rotors[i]);
        }
        reflector.setRotorText(enigma.reflector);
        for (int i = 0; i < ARROW_COUNT; i++) {
            arrow[i].setArrowPosition(enigma.arrowPositions[i]);
        }
    }

    void calculateArrow() {
        enigma.arrowPositions[0]--;
        enigma.rotors[0] = stringRotate(enigma.rotors[0]);
        enigma.rotors[1] = stringRotate(enigma.rotors[1]);
        if (enigma.arrowPositions[0] == 0) {
            enigma.arrowPositions[0] = ROTOR_SIZE;
            enigma.arrowPositions[1]--;
            enigma.rotors[2] = stringRotate(enigma.rotors[2]);
            enigma.rotors[3] = stringRotate(enigma.rotors[3]);
        }
        if (enigma.arrowPositions[1] == 0) {
            enigma.arrowPositions[1] = ROTOR_SIZE;
            enigma.arrowPositions[2]--;
            enigma.rotors[4] = stringRotate(enigma.rotors[4]);
            enigma.rotors[5] = stringRotate(enigma.rotors[5]);
        }
        if (enigma.arrowPositions[2] == 0) {
            enigma.arrowPositions[2] = ROTOR_SIZE;
        }
        for (int i = 0; i < ARROW_COUNT; i++) {
            enigma.arrowPositions[i] %= 26;
            enigma.arrowPositions[i]++;
            arrow[i].setArrowPosition(enigma.arrowPositions[i]);
            arrow[i].revalidate();
            arrow[i].repaint();
        }
        for (int i = 0; i < ROTOR_COUNT; i++) {
            rotor[i].setRotorText(enigma.rotors[i]);
            rotor[i].revalidate();
            rotor[i].repaint();
        }
    }

    char calculateEncodedChar(char x) {
        enigma.inputIndex = (x - 'a') + 1;
        char rotor1char = enigma.rotors[0].charAt(enigma.inputIndex - 1);
        enigma.rotor2index = enigma.rotors[1].indexOf(rotor1char) + 1;
        char rotor3char = enigma.rotors[2].charAt(enigma.rotor2index - 1);
        enigma.rotor4index = enigma.rotors[3].indexOf(rotor3char) + 1;
        char rotor5char = enigma.rotors[4].charAt(enigma.rotor4index - 1);
        enigma.rotor6index = enigma.rotors[5].indexOf(rotor5char) + 1;
        enigma.reflectorInIndex = enigma.rotor6index;
        char reflectorChar = enigma.reflector.charAt(enigma.reflectorInIndex);
        List<Integer> indices = Pattern.compile(Pattern.quote(String.valueOf(reflectorChar)))
                .matcher(enigma.reflector)
                .results()
                .map(MatchResult::start)
                .collect(Collectors.toList());
        indices.remove((Integer) enigma.reflectorInIndex);
        enigma.reflectorOutIndex = indices.get(0) + 2;
        char rotor6char = enigma.rotors[5].charAt(enigma.reflectorOutIndex - 1);
        enigma.rotor5index = enigma.rotors[4].indexOf(rotor6char) + 1;
        char rotor4char = enigma.rotors[3].charAt(enigma.rotor5index - 1);
        enigma.rotor3index = enigma.rotors[2].indexOf(rotor4char) + 1;
        char rotor2char = enigma.rotors[1].charAt(enigma.rotor3index - 1);
        enigma.rotor1index = enigma.rotors[0].indexOf(rotor2char) + 1;
        enigma.outputIndex = enigma.rotor1index;

        enigma.outputIndex = enigma.outputIndex % 26;
        enigma.inputIndex = enigma.inputIndex % 26;
        enigma.rotor2index = enigma.rotor2index % 26;
        enigma.rotor4index = enigma.rotor4index % 26;
        enigma.rotor6index = enigma.rotor6index % 26;
        enigma.rotor3index = enigma.rotor3index % 26;
        enigma.rotor5index = enigma.rotor5index % 26;
        enigma.reflectorOutIndex = enigma.reflectorOutIndex % 26;

        direction[0].setRotorPosition(enigma.outputIndex, enigma.inputIndex);
        direction[1].setRotorPosition(enigma.rotor3index, enigma.rotor2index);
        direction[2].setRotorPosition(enigma.rotor5index, enigma.rotor4index);
        direction[3].setRotorPosition(enigma.reflectorOutIndex, enigma.rotor6index);

        for (int i = 0; i < DIRECTION_COUNT; i++) {
            direction[i].revalidate();
            direction[i].repaint();
        }

        return enigma.rotors[0].charAt(enigma.outputIndex);
    }

    String stringRotate(String x) {
        return x.substring(1) + x.charAt(0);
    }

}
