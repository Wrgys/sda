package app.overrides;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
/**
 * Expands upon <code>JPanel</code> to allow for more control over the margins
 * <br> <br>
 * Parameters:
 * <br>
 * <code>Size</code>: 1 Integer / 2 Integers / 4 Integers
 * <br>
 * <code>Debug color</code>
 * <br> <br>
 * Methods added:
 * <br>
 * <code>setBorderColor()</code>
 * <br>
 * <code>setBorderSizes()</code>
 */
public class oJPanel extends JPanel {
    // --- Variables: Non-Customizable (Do not modify) ---
    private final int topBorderSize;
    private final int leftBorderSize;
    private final int bottomBorderSize;
    private final int right;

    private Color debugColor;

    // Border size when one isn't specified
    private static final int defaultSize = 10;


    // CONSTRUCTOR METHODS - to support various ways of initializing the object

    public oJPanel(){
        this(defaultSize,defaultSize,defaultSize,defaultSize,null);
    }

    public oJPanel(boolean basicJPanel_ANSWER_DOESNT_MATTER){
        this(0,0,0,0,null);
    }

    public oJPanel(int size){
        this(size,size,size,size,null);
    }

    public oJPanel(int size, Color debugColor){
        this(size,size,size,size,debugColor);
    }

    public oJPanel(Color debugColor){
        this(defaultSize,defaultSize,defaultSize,defaultSize,debugColor);
    }

    public oJPanel(int horizontal, int vertical){
        this(vertical,horizontal,vertical,horizontal,null);
    }

    public oJPanel(int horizontal, int vertical, Color debugColor){
        this(vertical,horizontal,vertical,horizontal,debugColor);
    }

    public oJPanel(int top, int left, int bottom, int right) {
        this(top,left,bottom,right,null);
    }

    public oJPanel(int top, int left, int bottom, int right, Color debugColor){
        super();
        this.topBorderSize = top;
        this.leftBorderSize = left;
        this.bottomBorderSize = bottom;
        this.right = right;
        this.debugColor = debugColor;

        this.setBorderColor(debugColor);
        this.setBackground(null);
        this.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
    }

    public void setBorderColor(Color debugColor){
        this.debugColor = debugColor;
        this.setBorder(new MatteBorder(
                this.topBorderSize,
                this.leftBorderSize,
                this.bottomBorderSize,
                this.right,
                this.debugColor
        ));
    }

    public void setBorderSizes(int size){
        this.setBorder(new MatteBorder(size, size, size, size, this.debugColor));
    }

    public void setBorderSizes(int horizontal, int vertical){
        this.setBorder(new MatteBorder(vertical, horizontal, vertical, horizontal, this.debugColor));
    }

    public void setBorderSizes(int top, int left, int bottom, int right){
        this.setBorder(new MatteBorder(
                this.topBorderSize,
                this.leftBorderSize,
                this.bottomBorderSize,
                this.right,
                this.debugColor
        ));
    }
}
