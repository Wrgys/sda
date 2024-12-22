package app.theme;

import app.Main;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Core {
    /**
     * <code>Hardcoded</code> core settings
     */
    public static final double headerButtonIconMargin = 0.4;
    public static final Dimension mainFrameSize = (new Dimension(1100,700));
    public static final Dimension settingsFrameSize = (new Dimension(450,620));
    public static final int headerHeight = 40;
    public static final Color transparentColor = new Color(0,0,0,0);
    public static final String mainFrameName = "Checklist App";
    public static final String settingsFrameName = "Settings";
    public static final String colorPickerName = "Color picker";
    public static final ImageIcon iconApp = new ImageIcon(Main.class.getResource("/resources/images/icon.png"));
    public static final ImageIcon iconBlock = new ImageIcon(Main.class.getResource("/resources/images/block.png"));
    public static final ImageIcon iconBlank = new ImageIcon(Main.class.getResource("/resources/images/transparent.png"));

    public static final Color defaultHeaderColor = new Color(175, 175, 175);
    public static final Color defaultBackgroundColor = new Color(255, 255, 255);

    /**
     * <code>Changeable</code> at run-time core settings
     */
    public enum ThemeColors{
        HEADER(new Color(175, 175, 175)),
        BACKGROUND(new Color(255, 255, 255)),
        FOREGROUND(new Color(0,0,0)),
        UNCHECKED(new Color(175, 175, 175)),
        CHECKED(new Color(114, 227, 106)),
        BUTTON_HOVER(new Color(255,255,255,100)),
        BUTTON_CLICK(new Color(255,255,255,150)),
        SHADOW(new Color(0, 0, 0,25));

        private Color color;

        ThemeColors(Color color){
            this.color = color;
        }

        public Color getColor(){
            if (this.color == null)
                return null;
            else
                return color;

        }

        public void setColor(Color color){
            this.color = color;
        }
    }
}
