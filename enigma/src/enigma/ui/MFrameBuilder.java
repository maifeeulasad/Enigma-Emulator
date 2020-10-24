package enigma.ui;

import enigma.core.Enigma;
import enigma.core.OnChangeListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MFrameBuilder implements OnChangeListener, ActionListener {

    Enigma enigma;

    MFrame mFrame;
    List<Integer> rotorIds = new ArrayList<>();
    List<String> rotors = new ArrayList<>();
    List<String> reflectors = new ArrayList<>();
    int[][] arrowCombination;
    String initialPermutation;

    JPanel parentPanel;
    JPanel availableParent;
    JPanel availableRotorsPanel;
    JPanel availableReflectorsPanel;
    JPanel rotorsAndReflectorsPanel;
    JPanel inputPanel;
    JPanel emulationPanel;

    MRotors mRotors;

    public MFrameBuilder(){
        enigma = Enigma.getEnigma();

        mFrame=new MFrame();
        mFrame.setSize(800,600);

        parentPanel = new JPanel();
        parentPanel.setLayout(new BoxLayout(parentPanel,BoxLayout.Y_AXIS));

        emulationPanel = new JPanel();
        emulationPanel.setLayout(new BoxLayout(emulationPanel,BoxLayout.X_AXIS));

        availableRotorsPanel = new JPanel();
        String availableRotorsPanelTitle = "All available rotors : ";
        Border availableRotorsPanelBorder = BorderFactory.createTitledBorder(availableRotorsPanelTitle);
        availableRotorsPanel.setBorder(availableRotorsPanelBorder);

        availableReflectorsPanel = new JPanel();
        String availableReflectorsPanelTitle = "All available reflector : ";
        Border availableReflectorsPanelBorder = BorderFactory.createTitledBorder(availableReflectorsPanelTitle);
        availableReflectorsPanel.setBorder(availableReflectorsPanelBorder);

        availableParent = new JPanel();
        availableParent.setLayout(new BoxLayout(availableParent,BoxLayout.X_AXIS));
        availableParent.add(availableRotorsPanel);
        availableParent.add(availableReflectorsPanel);

        mRotors = new MRotors(enigma);

        rotorsAndReflectorsPanel = new JPanel();
        String rotorsPanelTitle = "Set your rotors below : ";
        Border rotorsPanelBorder = BorderFactory.createTitledBorder(rotorsPanelTitle);
        rotorsAndReflectorsPanel.setBorder(rotorsPanelBorder);

        inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        String inputPanelTitle = "Input panel : ";
        Border inputPanelBorder = BorderFactory.createTitledBorder(inputPanelTitle);
        inputPanel.setBorder(inputPanelBorder);
        inputPanel.add(new JLabel("Input : "));
        inputPanel.add(new MInOut(true,this));
        inputPanel.add(new JLabel("Output : "));
        inputPanel.add(new MInOut(false));
        for(int i=0;i<6;i++){
            inputPanel.add(new JLabel("R"+(i+1)+" : "));
            inputPanel.add(new MInOut(true,this,i+1));
        }
        inputPanel.add(new JLabel("Reflector : "));
        inputPanel.add(new MInOut(true,this,7));
        inputPanel.add(new JLabel("Permutation : "));
        inputPanel.add(new MInOut(true,this,8,5));
        inputPanel.add(new MButton("Load",this));
    }

    public MFrameBuilder setHeight(int height){
        mFrame.setHeight(height);
        return this;
    }

    public MFrameBuilder setWidth(int width){
        mFrame.setWidth(width);
        return this;
    }

    public MFrameBuilder setTitle(String title){
        mFrame.setTitle(title);
        return this;
    }

    public MFrameBuilder setRotors(List<String> rotorsTexts){
        for(String rotorsText:rotorsTexts){
            this.addRotor(rotorsText);
        }
        return this;
    }

    public MFrameBuilder setReflectors(List<String> reflectorsTexts){
        for(String reflectorText:reflectorsTexts){
            this.addReflector(reflectorText);
        }
        return this;
    }

    private MFrameBuilder addRotor(String rotorText){
        if(rotorText.length()!=26)
            return null;
        rotors.add(rotorText);
        availableRotorsPanel.add(new MRotor(rotorText,rotors.size()));
        return this;
    }

    private MFrameBuilder addReflector(String reflectorText){
        if(reflectorText.length()!=26)
            return null;
        reflectors.add(reflectorText);
        availableReflectorsPanel.add(new MRotor(reflectorText,reflectors.size()));
        return this;
    }

    public MFrameBuilder setArrowCombination(int[][] arrowCombination){
        this.arrowCombination = arrowCombination;
        return this;
    }

    public MFrameBuilder setFullScreen(){
        mFrame.setFullScreen();
        return this;
    }

    public void build(){
        //enigma = Enigma.setEnigma();

        mFrame.add(parentPanel);

        rotorsAndReflectorsPanel = mRotors.addAll(rotorsAndReflectorsPanel);

        emulationPanel.add(rotorsAndReflectorsPanel);
        emulationPanel.add(inputPanel);

        parentPanel.add(emulationPanel);
        parentPanel.add(availableParent);

        mFrame.setVisible(true);
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void onChange(String text) {
        if(text==null || text.equals("") || text.equals("\n"))
            return;
        char last=text.charAt(text.length()-1);
        mRotors.setDirection((last-'a')%26 + 1);
    }

    @Override
    public void onChange(String text, int id) {
        System.out.println(text+" - "+id);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("button clicked");
    }
}
