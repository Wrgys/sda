package app.components;

import app.overrides.oJButton;
import app.theme.Core;

import java.awt.*;
import java.util.ArrayList;

public class BasicButton extends oJButton {
    private static final ArrayList<BasicButton> instanceList = new ArrayList<BasicButton>();
    private static final Dimension defaultButtonSize = new Dimension(50,30);

    public BasicButton(){
        this(Color.RED,Color.GREEN,Color.BLUE);
    }

    public BasicButton(String text){
        this(text, Color.RED,Color.GREEN,Color.BLUE);
    }

    public BasicButton(Dimension size){
        this("", size, Color.RED,Color.GREEN,Color.BLUE);
    }

    public BasicButton(String text, Dimension size){
        this(text, size, Color.RED,Color.GREEN,Color.BLUE);
    }

    public BasicButton(String text, int size){
        this(text, new Dimension(size,size), Color.RED,Color.GREEN,Color.BLUE);
    }

    public BasicButton(Color backgroundColor, Color hoverColor, Color clickColor){
        this(null, defaultButtonSize, backgroundColor,hoverColor,clickColor);
    }

    public BasicButton(Dimension size, Color backgroundColor, Color hoverColor, Color clickColor){
        this(null, size, backgroundColor,hoverColor,clickColor);
    }

    public BasicButton(String text, Color backgroundColor, Color hoverColor, Color clickColor){
        super(text, backgroundColor,hoverColor,clickColor);
        instanceList.add(this);
    }

    public BasicButton(String text, Dimension size, Color backgroundColor, Color hoverColor, Color clickColor){
        super(text, backgroundColor,hoverColor,clickColor);
        this.setPreferredSize(size);
        instanceList.add(this);
    }

    /**
     * Refreshes the font color of all instantiated BasicButton objects so that they match the theme font color
     */
    public static void updateColors() {
        for (BasicButton instance : instanceList) {
            instance.setForeground(Core.ThemeColors.FOREGROUND.getColor());
        }
    }
}
