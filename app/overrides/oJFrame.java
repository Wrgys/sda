package app.overrides;
import app.theme.Core;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
/**
 * Expands upon <code>JFrame</code> to implement shadowing logic
 * <br> <br>
 * Methods added:
 * <br>
 * <code>setShadowed()</code>
 * <br>
 * <code>setShadowColor()</code>
 * <br>
 * <code>updateColors()</code>
 */
public class oJFrame extends JFrame {
    public static ArrayList<oJFrame> instanceList = new ArrayList<oJFrame>();
    private Color shadowColor = Core.ThemeColors.SHADOW.getColor();
    private boolean isShadowable = true;
    private boolean isChangeable = true;

    public oJFrame() {
        instanceList.add(this);
        JPanel overlay = new JPanel() {
            @Override
            public boolean isOpaque() {
                return false;
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(shadowColor);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        this.setGlassPane(overlay);
        this.setIconImage(Core.iconApp.getImage());
    }

    public void setShadowed(boolean value) {
        if (isShadowable)
            this.getGlassPane().setVisible(value);
    }

    public void setShadowColor(Color color) {
        this.shadowColor = color;
    }

    public static void updateColors() {
        for (oJFrame instance : instanceList) {
            if (instance.isChangeable)
                instance.getContentPane().setBackground(Core.ThemeColors.BACKGROUND.getColor());
        }
    }

    public void setShadowable(boolean value){
        this.isShadowable = value;
    }

    public void setIsChangeable(boolean value){
        this.isChangeable = value;
    }
}
