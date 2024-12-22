package app.components;

import app.components.buttons.CloseButton;
import app.components.buttons.MinimizeButton;
import app.helpers.BorderedPanel;
import app.overrides.oJPanel;
import app.theme.Core;
import app.theme.CoreActions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

// COMMENTS 95% redo part with addaction

/**
 * A highly customizable header bar that holds components and enables window dragging functionality for an undecorated frame
 * <ul>
 * <li>The parent frame must use a <code>BorderLayout</code></li>
 * <li>The header should be added to the <code>BorderLayout.NORTH</code> position of the parent frame</li>
 * </ul>
 * Key methods:
 * <br>
 * {@link #updateColors()}: Updates the background color of all instantiated Header objects to match the current theme color
 * <br>
 * {@link #enableDragging(boolean)}: Toggles the dragging functionality for the header. By default, dragging is enabled
 * <br>
 * <br>
 * @example
 * <pre>
 * JFrame frame = new JFrame();
 * frame.setLayout(new BorderLayout())
 *
 * Header header = new Header(frame);
 *
 * frame.add(header,BorderLayout.NORTH);
 * </pre>
 */
public class Header extends oJPanel {
// --- Variables: Non-Customizable (Do not modify)

    // Instance list to keep track of all Header objects
    private static final ArrayList<Header> instanceList = new ArrayList<Header>();

    // Required for class functionality
    private int xOffset;
    private int yOffset;
    private boolean isChangeable = true;

    // Minimize and close buttons
    private MinimizeButton minimizeButton;
    private CloseButton closeButton;

    // Allows access to all panels of the Header object
    public BorderedPanel panel;


// --- Variables: Customizable (Can be modified)

    // Indicates whether dragging functionality is enabled or disabled by default
    private boolean isDraggingEnabled = true;


// CONSTRUCTOR METHODS - to support various ways of initializing the object

    /**
     * Creates a Header object with minimize and close buttons, along with a BorderedPanel object containing 3 panels (LEFT, CENTER, RIGHT)
     * @param parentFrame The JFrame to which the header is attached
     */
    public Header(JFrame parentFrame){
        this(parentFrame,true,true,
                BorderedPanel.PanelType.RIGHT, BorderedPanel.PanelType.LEFT, BorderedPanel.PanelType.CENTER);
    }

    /**
     * Creates a Header object with minimize and close buttons, while providing full control over the panels included in the BorderedPanel object
     * @param parentFrame The JFrame to which the header is attached
     * @param panels The panel types to include in the header (CENTER, LEFT, TOP, RIGHT, BOTTOM)
     *               <br>
     *               The buttons are added to the first panel given
     */
    public Header(JFrame parentFrame, BorderedPanel.PanelType... panels){
        this(parentFrame,true,true, panels);
    }

    /**
     * Creates a Header object with a close button and a BorderedPanel object containing three panels (LEFT, CENTER, RIGHT),
     * while providing full control over the visibility of the minimize button
     * @param parentFrame The JFrame to which the header is attached
     * @param showMinimizeButton If true, displays the minimize button
     */
    public Header(JFrame parentFrame, boolean showMinimizeButton){
        this(parentFrame,showMinimizeButton,true,
                BorderedPanel.PanelType.LEFT, BorderedPanel.PanelType.CENTER, BorderedPanel.PanelType.RIGHT);
    }

    /**
     * Creates a Header object with a close button, while providing full control over the visibility
     * of the minimize button and the panels included in the BorderedPanel object
     * @param parentFrame The JFrame to which the header is attached
     * @param showMinimizeButton If true, show the minimize button
     * @param panels The panel types to include in the header (CENTER, LEFT, TOP, RIGHT, BOTTOM).
     *               <br>
     *               The buttons are added to the first panel given
     */
    public Header(JFrame parentFrame, boolean showMinimizeButton, BorderedPanel.PanelType... panels){
        this(parentFrame,showMinimizeButton,true, panels);
    }

    /**
     * Creates a Header object with a BorderedPanel object containing three panels (LEFT, CENTER, RIGHT),
     * while providing full control over the visibility of the minimize and close button
     * @param parentFrame The JFrame to which the header is attached
     * @param showMinimizeButton If true, displays the minimize button
     * @param showCloseButton If true, displays the close button
     */
    public Header(JFrame parentFrame, boolean showMinimizeButton, boolean showCloseButton){
        this(parentFrame,showMinimizeButton,showCloseButton,
                BorderedPanel.PanelType.LEFT, BorderedPanel.PanelType.CENTER, BorderedPanel.PanelType.RIGHT);
    }

    /**
     * Creates a Header object, while providing full control over the visibility of the minimize and close button,
     * as well as the panels included in the BorderedPanel object
     * @param parentFrame The JFrame to which the header is attached
     * @param showMinimizeButton If true, show the minimize button
     * @param showCloseButton If true, show the close button
     * @param panels The panel types to include in the header (CENTER, LEFT, TOP, RIGHT, BOTTOM)
     *               <br>
     *               The buttons are added to the first panel given
     *
     */
    public Header(JFrame parentFrame, boolean showMinimizeButton, boolean showCloseButton, BorderedPanel.PanelType... panels){
        super(true);
        // Add the object to the instance list
        instanceList.add(this);

        // Configure and initialize the header
        int headerSize = Core.headerHeight;
        this.setPreferredSize(new Dimension(headerSize,headerSize));

        this.setBackground(Core.ThemeColors.HEADER.getColor());

        this.setLayout(new BorderLayout(0,0));

        // Create the panels and, if applicable, add the buttons to the first given panel
        boolean buttonsAdded = false;

        panel = new BorderedPanel(true);

        for (BorderedPanel.PanelType panelType : panels) {
            panel.addPanel(panelType);
            if (!buttonsAdded){
                if (showMinimizeButton)
                {
                    minimizeButton = new MinimizeButton(parentFrame);
                    panel.getPanel(panelType).add(minimizeButton);
                }

                if (showCloseButton)
                {
                    closeButton = new CloseButton(parentFrame);
                    panel.getPanel(panelType).add(closeButton);
                }

                buttonsAdded = true;
            }
        }

        // Add the panels to the Header object
        this.add(panel);

        // Implement dragging functionality
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (isDraggingEnabled) {
                    xOffset = e.getX();
                    yOffset = e.getY();
                }
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (isDraggingEnabled) {
                    int x = e.getXOnScreen() - xOffset;
                    int y = e.getYOnScreen() - yOffset;
                    parentFrame.setLocation(x, y);
                }
            }
        });
    }

    /**
     * Updates the background color of all instantiated Header objects to match the current theme color.
     */
    public static void updateColors() {
        for (Header instance : instanceList) {
            if (instance.isChangeable)
                instance.setBackground(Core.ThemeColors.HEADER.getColor());
        }
    }

    /**
     * Toggles the dragging functionality for the panel. By default, dragging is enabled.
     * @param value If true, enable dragging functionality
     */
    public void enableDragging(boolean value){
        isDraggingEnabled = value;
    }

    public void replaceCloseButtonAction(JFrame parentFrame, CoreActions.FrameAction closeButtonAction) {
        for (ActionListener listener : closeButton.getActionListeners())
            closeButton.removeActionListener(listener);
        this.closeButton.addActionListener(e->closeButtonAction.performAction(parentFrame));
    }

    public void replaceMinimizeButtonAction(JFrame  parentFrame, CoreActions.FrameAction minimizeButtonAction) {
        for (ActionListener listener : minimizeButton.getActionListeners())
            minimizeButton.removeActionListener(listener);

        this.minimizeButton.addActionListener(e->minimizeButtonAction.performAction(parentFrame));
    }

    public void setIsChangeable(boolean value){
        this.isChangeable = value;
    }

    public CloseButton getCloseButton(){
        return this.closeButton;
    }

    public MinimizeButton getMinimizeButton(){
        return this.minimizeButton;
    }
}
