package app.components.buttons.icondrawers;

import javax.swing.*;
import java.awt.*;

import static app.theme.Core.headerButtonIconMargin;

public class CloseIconDrawer extends JPanel {
    private final Dimension size;
    private Color color;

    public CloseIconDrawer(Dimension size) {
        this(size,Color.black);
    }

    public CloseIconDrawer(Dimension size, Color color) {
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
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(new BasicStroke(2));
        g.setColor(this.color);
        g.drawLine(offset, offset, size.width - offset, size.height - offset);
        g.drawLine(offset, size.height - offset, size.width - offset, offset);
    }
}
