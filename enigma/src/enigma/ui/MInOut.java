package enigma.ui;

import enigma.core.OnChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MInOut extends JTextField implements DocumentListener {

    //TODO : MInOut builder class
    //TODO : set max length
    //TODO : input validation

    private OnChangeListener onChangeListener;
    private int id;

    public MInOut(boolean in,OnChangeListener onChangeListener,int id,int columns){
        super();
        if(columns==0)
            setColumns(id==0 ? 8 : 1);
        else
            setColumns(columns);
        if(!in){
            setEnabled(false);
            setEditable(false);
        }
        this.onChangeListener = onChangeListener;
        this.id = id;
        this.getDocument().addDocumentListener(this);
    }

    public MInOut(boolean in,OnChangeListener onChangeListener,int id){
        this(in, onChangeListener,id,0);
    }

    public MInOut(boolean in,OnChangeListener onChangeListener){
        this(in,onChangeListener,0);
    }

    public MInOut(boolean in){
        this(in,null);
    }

    @Override
    public void insertUpdate(DocumentEvent documentEvent) {
        /*
        try{
            int x = Integer.parseInt(getText());
            if(x<1 || x>9){
                setText("");
                return;
            }
        }catch (Exception e){
            //setText("");
            return;
        }
         */
        if(onChangeListener!=null){
            if(id!=0){
                onChangeListener.onChange(getText(),id);
            }else{
                onChangeListener.onChange(getText());
            }
        }
    }

    @Override
    public void removeUpdate(DocumentEvent documentEvent) {
        /*
        try{
            int x = Integer.parseInt(getText());
            if(x<1 || x>9){
                setText("");
                return;
            }
        }catch (Exception e){
            //setText("");
            return;
        }
         */
        if(onChangeListener!=null){
            if(id!=0){
                onChangeListener.onChange(getText(),id);
            }else{
                onChangeListener.onChange(getText());
            }
        }
    }

    @Override
    public void changedUpdate(DocumentEvent documentEvent) {
        /*
        try{
            int x = Integer.parseInt(getText());
            if(x<1 || x>9){
                setText("");
                return;
            }
        }catch (Exception e){
            //setText("");
            return;
        }
         */
        if(onChangeListener!=null){
            if(id!=0){
                onChangeListener.onChange(getText(),id);
            }else{
                onChangeListener.onChange(getText());
            }
        }
    }
}
