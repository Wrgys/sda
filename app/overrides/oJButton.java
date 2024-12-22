package app.overrides;

import javax.swing.JButton;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * Expands upon <code>JButton</code> to implement the ability of manipulating the hover, click, and background color
 * <br> <br>
 */
public class oJButton extends JButton {
    private Color overlay = null;
    private int borderRadius = 0;

    private Color backgroundColor = null;
    private Color hoverColor = null;
    private Color clickColor = null;

    public oJButton() {
        this(null,Color.RED,Color.GREEN,Color.BLUE);
    }

    public oJButton(String text) {
        this(text,Color.RED,Color.GREEN,Color.BLUE);
    }

    public oJButton(String text, Color backgroundColor, Color hoverColor, Color clickColor) {
        super(text);

        this.backgroundColor = backgroundColor;
        this.hoverColor = hoverColor;
        this.clickColor = clickColor;

        this.setBackground(backgroundColor);
        this.setBorder(null);
        this.setFocusable(false);
        this.setContentAreaFilled(false);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                overlay = oJButton.this.hoverColor;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                overlay = oJButton.this.backgroundColor;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                overlay = oJButton.this.clickColor;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                overlay = oJButton.this.backgroundColor;
                repaint();
            }
        });
    }

    /**
     * Set the border radius for the rounded corners.
     *
     * @param radius the radius for the rounded corners. A value of 0 means no rounding.
     */
    public void setBorderRadius(int radius) {
        this.borderRadius = radius;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getBackground() != null) {
            g2d.setColor(getBackground());
            if (borderRadius > 0) {
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);
            } else {
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        }

        if (overlay != null) {
            g2d.setColor(overlay);
            if (borderRadius > 0) {
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);
            } else {
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        }

        super.paintComponent(g);
        g2d.dispose();
    }
    @Override
    public void setBackground(Color color){
        this.backgroundColor = color;
        overlay = backgroundColor;
        this.repaint();
        super.setBackground(color);
    }

    public void setHoverColor(Color color){
        this.hoverColor = color;
    }

    public void setClickColor(Color color){
        this.clickColor = color;
    }

}
