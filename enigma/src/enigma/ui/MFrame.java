package enigma.ui;

import javax.swing.*;
import java.awt.*;

public class MFrame extends JFrame {

    public MFrame(){

    }

    void setFullScreen(){
        setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
    }

    void setHeight(int height){
        setSize(getWidth(),height);
    }

    void setWidth(int width){
        setSize(width,getHeight());
    }


}
