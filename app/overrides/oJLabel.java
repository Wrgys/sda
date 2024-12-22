package app.overrides;

import app.theme.Core;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Label that allows updating font color with theme settings
 */
public class oJLabel extends JLabel {
    private static final ArrayList<oJLabel> instanceList = new ArrayList<oJLabel>();
    private boolean isChangeable = true;

    public oJLabel(String text){
        super(text);
        instanceList.add(this);
    }

    public oJLabel(){
        super();
        instanceList.add(this);
    }

    public static void updateColors(){
        for (oJLabel label : instanceList){
            if (label.isChangeable)
                label.setForeground(Core.ThemeColors.FOREGROUND.getColor());
        }
    }

    public void setIsChangeable(boolean value){
        this.isChangeable = value;
    }
}
