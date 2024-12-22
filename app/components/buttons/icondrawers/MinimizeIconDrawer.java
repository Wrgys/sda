package app.components.buttons.icondrawers;

import javax.swing.*;
import java.awt.*;

import static app.theme.Core.headerButtonIconMargin;

public class MinimizeIconDrawer extends JPanel {
    private final Dimension size;
    private Color color;

    public MinimizeIconDrawer(Dimension size) {
        this(size,Color.black);
    }

    public MinimizeIconDrawer(Dimension size, Color color) {
        this.size = size;
        this.color = color;
        this.setOpaque(false);
    }

    public void setIconColor(Color color){
        this.color = color;
    }

    public void paint(Graphics graphics) {
        int offset = (int)(headerButtonIconMargin * size.height);

        Graphics2D g = (Graphics2D) graphics;
        g.setStroke(new BasicStroke(2));
        g.setColor(this.color);
        g.drawLine(offset,size.height/2,size.width-offset,size.height/2);
    }
}
