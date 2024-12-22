package app.helpers;

import app.theme.Core;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// COMMENTS 95% debug mode chestia cu center

/**A customizable panel that utilizes BorderLayout to easily arrange components
 * within different regions of the frame (center, left, top, right, and bottom). This panel
 * allows for flexible management of layout and visual styling, including an optional
 * debugging mode for visualization purposes.
 * <ul>
 * <li>The parent frame must use a <code>BorderLayout</code></li>
 * </ul>
 * Key methods:
 * <br>
 * {@link #setDebugMode(boolean)}: Toggles debug mode for the object
 * <br>
 * {@link #setGlobalDebugMode(boolean)}: Toggles debug mode for all objects
 *  <br>
 * {@link #addCenterPanel()}: Creates a new center panel. Available for each panel type
 * <br>
 * {@link #getCenterPanel()}: Provides access to the center panel for higher flexibility. Available for each panel type
 * @example
 * <pre>
 * JFrame frame = new Jframe();
 * frame.setLayout(new BorderLayout());

 * BorderedPanel panel = new BorderedPanel();
 *
 * frame.add(panel);
 * </pre>
 */
public class BorderedPanel extends JPanel {
    /**
     * Valid panel types:
     * <br>
     * <code>CENTER</code>
     * <br>
     * <code>LEFT</code>
     * <br>
     * <code>TOP</code>
     * <br>
     * <code>RIGHT</code>
     * <br>
     * <code>BOTTOM</code>
     */
    public enum PanelType {
        CENTER,
        LEFT,
        TOP,
        RIGHT,
        BOTTOM;
    }


// --- Variables: Non-Customizable (Do not modify)

    // Instance list to keep track of all Header objects
    private static final ArrayList<BorderedPanel> instanceList = new ArrayList<BorderedPanel>();

    // Panels to be added to / modified
    private JPanel centerPanel;
    private JPanel leftPanel;
    private JPanel topPanel;
    private JPanel rightPanel;
    private JPanel bottomPanel;


// --- Variables: Customizable (Can be modified)

    // Color values for each panel's background color
    private final Color colorCenterPanel = new Color(197, 197, 197);
    private final Color colorLeftPanel = new Color(202, 85, 136);
    private final Color colorTopPanel = new Color(241, 152, 77);
    private final Color colorRightPanel = new Color(126, 186, 91);
    private final Color colorBottomPanel = new Color(44, 136, 197);

    // Defines the size of the debug visualization panels when in debug mode
    private final int emptyPanelSize = 10;


// CONSTRUCTOR METHODS - to support various ways of initializing the object

    /**
     * Default constructor with no parameters. All panels will be created by default
     * when calling this constructor.
     */
    public BorderedPanel() {
        this(true,true,true,true,true);
    }

    /**
     * Constructor to initialize the panel based on whether it should have all panels or none.
     * @param createNoPanels If true, a BorderedPanel object will be created with no panels. If false,
     *                   the object will have all 5 panels
     */
    public BorderedPanel(boolean createNoPanels) {
        this(!createNoPanels, !createNoPanels, !createNoPanels, !createNoPanels, !createNoPanels);
    }

    /**
     * Constructor which takes an explicit boolean for each panel
     * @param center If true, creates the center panel
     * @param left If true, creates the left panel
     * @param top If true, creates the top panel
     * @param right If true, creates the right panel
     * @param bottom If true, creates the bottom panel
     */
    public BorderedPanel(boolean center, boolean left, boolean top, boolean right, boolean bottom) {
        // Add the object to the instance list
        instanceList.add(this);

        // Configure the BorderedPanel object
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(false);

        // Add panels based on given arguments
        if (center) addCenterPanel();
        if (left) addLeftPanel();
        if (top) addTopPanel();
        if (right) addRightPanel();
        if (bottom) addBottomPanel();
    }

    /**
     * Constructor which takes an array of panels
     * @param panels An array of PanelType values indicating which panels to create.
     *               Only the panels specified in the array will be included.
     */
    public BorderedPanel(PanelType... panels) {
        // Add the object to the instance list
        instanceList.add(this);

        // Configure the BorderedPanel object
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(false);

        // Add all specified panels
        if (panels != null) {
            for (PanelType panel : panels) {
                createPanel(panel);
            }
        }
    }


// INTERFACE METHODS - to allow for customization of the object by the user

    /**
     * Toggles debug mode for the BorderedPanel object. When enabled, the object's panels
     * become visible with distinct colors for visualization purposes
     * <br>
     * When disabled, the object's panels become transparent (default state)
     * <br>
     * Unless specified, the center panel will be transparent even when debug mode is enabled
     * @param value If true, activate debug mode
     */
    public void setDebugMode(boolean value){
        setDebugMode(value,false);
    }

    /**
     * Toggles debug mode for the BorderedPanel object. When enabled, the object's panels
     * become visible with distinct colors for visualization purposes.
     * <br>
     * When disabled, the object's panels become transparent (default state)
     * @param value If true, activate debug mode
     * @param showCenterPanel If true, when debug mode is activated, the center panel
     *                        will also be visible
     */
    public void setDebugMode(boolean value, boolean showCenterPanel) {
        // Add spacers to all available panels that have no components
        if (value) {
            addSpacer(centerPanel);
            addSpacer(leftPanel);
            addSpacer(topPanel);
            addSpacer(rightPanel);
            addSpacer(bottomPanel);

        // Remove the spacers from all available panels which have them
        } else {
            removeSpacer(centerPanel);
            removeSpacer(leftPanel);
            removeSpacer(topPanel);
            removeSpacer(rightPanel);
            removeSpacer(bottomPanel);
        }

        // Make the panels visible
        if (centerPanel != null) {
            centerPanel.setOpaque(value);
            centerPanel.setBackground(value ? colorCenterPanel : Core.transparentColor);
        }
        if (leftPanel != null) {
            leftPanel.setOpaque(value);
            leftPanel.setBackground(value ? colorLeftPanel : Core.transparentColor);
        }
        if (topPanel != null) {
            topPanel.setOpaque(value);
            topPanel.setBackground(value ? colorTopPanel : Core.transparentColor);
        }
        if (rightPanel != null) {
            rightPanel.setOpaque(value);
            rightPanel.setBackground(value ? colorRightPanel : Core.transparentColor);
        }
        if (bottomPanel != null) {
            bottomPanel.setOpaque(value);
            bottomPanel.setBackground(value ? colorBottomPanel : Core.transparentColor);
        }
    }

    /**
     * Toggles debug mode for all BorderedPanel objects. When debug mode
     * is enabled for an object, the object's panels become visible with
     * distinct colors for visualization purposes
     * <br>
     * When disabled, the object's panels become transparent (default state)
     * <br>
     * Unless specified, the center panel will be transparent even when debug mode is enabled
     * @param value If true, activate debug mode for all BorderedPanel objects
     */
    public static void setGlobalDebugMode(boolean value) {
        for (BorderedPanel object : instanceList) {
            object.setDebugMode(value);
            if (!value)
                object.removeSpacer(object);
        }
    }

    /**
     * Toggles debug mode for all BorderedPanel objects. When debug mode
     * is enabled for an object, the object's panels become visible with
     * distinct colors for visualization purposes
     * <br>
     * When disabled, the object's panels become transparent (default state)
     *
     * @param value If true, activate debug mode for all BorderedPanel objects
     * @param showCenterPanel If true, when debug mode is activated, the center panel
     *                        will also be visible
     */
    public static void setGlobalDebugMode(boolean value, boolean showCenterPanel) {
        for (BorderedPanel object : instanceList) {
            object.setDebugMode(value,showCenterPanel);
            if (!value)
                object.removeSpacer(object);
        }
    }

    /**
     * This method allows creating any of the object's panels depending on the PanelType
     * passed as an argument.
     * @param panel The type of panel to create (CENTER, LEFT, TOP, RIGHT, BOTTOM)
     */
    public void addPanel(PanelType panel) {
        switch (panel) {
            case CENTER -> addCenterPanel();
            case LEFT -> addLeftPanel();
            case TOP -> addTopPanel();
            case RIGHT -> addRightPanel();
            case BOTTOM -> addBottomPanel();
        };
    }

    /**
     * This method allows access to any of the object's panels depending on the PanelType
     * passed as an argument.
     * @param panel The type of panel to retrieve (CENTER, LEFT, TOP, RIGHT, BOTTOM)
     * @return The corresponding JPanel of the PanelType
     */
    public JPanel getPanel(PanelType panel) {
        return switch (panel) {
            case CENTER -> getCenterPanel();
            case LEFT -> getLeftPanel();
            case TOP -> getTopPanel();
            case RIGHT -> getRightPanel();
            case BOTTOM -> getBottomPanel();
        };
    }

    /**
     * Creates and adds a new panel of CENTER type to the BorderedPanel
     */
    public void addCenterPanel() {
        centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        this.add(centerPanel, BorderLayout.CENTER);
        this.setDebugMode(false);
    }

    /**
     * Allows individual access to the center panel for higher flexibility
     * @return The center panel component
     */
    public JPanel getCenterPanel() {
        return centerPanel;
    }

    /**
     * Creates and adds a new panel of LEFT type to the BorderedPanel
     */
    public void addLeftPanel() {
        leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setBackground(colorLeftPanel);
        leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        leftPanel.add(new Spacer(Spacer.Type.SQUARE,emptyPanelSize));
        this.add(leftPanel, BorderLayout.WEST);
        this.setDebugMode(false);
    }

    /**
     * Allows individual access to the left panel for higher flexibility
     * @return The left panel component
     */
    public JPanel getLeftPanel() {
        return leftPanel;
    }

    /**
     * Creates and adds a new panel of TOP type to the BorderedPanel
     */
    public void addTopPanel() {
        topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setBackground(colorTopPanel);
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        topPanel.add(new Spacer(Spacer.Type.SQUARE,emptyPanelSize));
        this.add(topPanel, BorderLayout.NORTH);
        this.setDebugMode(false);
    }

    /**
     * Allows individual access to the top panel for higher flexibility
     * @return The top panel component
     */
    public JPanel getTopPanel() {
        return topPanel;
    }

    /**
     * Creates and adds a new panel of RIGHT type to the BorderedPanel
     */
    public void addRightPanel() {
        rightPanel = new JPanel();
        rightPanel.setOpaque(false);
        rightPanel.setBackground(colorRightPanel);
        rightPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        rightPanel.add(new Spacer(Spacer.Type.SQUARE,emptyPanelSize));
        this.add(rightPanel, BorderLayout.EAST);
        this.setDebugMode(false);
    }

    /**
     * Allows individual access to the right panel for higher flexibility
     * @return The right panel component
     */
    public JPanel getRightPanel() {
        return rightPanel;
    }

    /**
     * Creates and adds a new panel of BOTTOM type to the BorderedPanel
     */
    public void addBottomPanel() {
        bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setBackground(colorBottomPanel);
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        bottomPanel.add(new Spacer(Spacer.Type.SQUARE,emptyPanelSize));
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.setDebugMode(false);
    }

    /**
     * Allows individual access to the bottom panel for higher flexibility
     * @return The bottom panel component
     */
    public JPanel getBottomPanel() {
        return bottomPanel;
    }

    /**
     * <ul>
     * <li> Only works when add() is directly called by the BorderedPanel object
     * </ul>
     * Changes the default add method to instead add the component to the
     * first available panel (CENTER, LEFT, TOP, RIGHT, BOTTOM) of the object
     * when no PanelType is specified
     * @param component the component to be added
     * @return The new modified BorderedPanel object
     * <br>
     * Null if no panel is available
     * @example
     * <pre>
     * BorderedPanel panel = new BorderedPanel();
     *
     * // Calling the overridden add method
     * panel.add(object);
     *
     * // Calling add for a specific panel
     * panel.getTopPanel().add(object);
     * </pre>
     */
    @Override
    public Component add(Component component) {
        if (centerPanel != null) {
            removeSpacer(centerPanel);
            return centerPanel.add(component);

        } else if (leftPanel != null) {
            removeSpacer(leftPanel);
            return leftPanel.add(component);

        } else if (topPanel != null) {
            removeSpacer(topPanel);
            return topPanel.add(component);

        } else if (rightPanel != null) {
            removeSpacer(rightPanel);
            return rightPanel.add(component);

        } else if (bottomPanel != null) {
            removeSpacer(bottomPanel);
            return bottomPanel.add(component);
        }
        else
            return null;
    }


// HELPER METHODS - in-class use only

    /**
     * Adds a spacer to the panel if it doesn't already have one.
     */
    private void addSpacer(JPanel panel) {
        if (panel != null && panel.getComponentCount() == 0) {
            panel.add(new Spacer(Spacer.Type.SQUARE, emptyPanelSize));
        }
    }

    /**
     * Iterates through the panel's elements and removes the first Spacer element encountered
     */
    private void removeSpacer(JPanel panel){
        if (panel!=null) {
            for (Component child : panel.getComponents()) {
                if (child instanceof Spacer) {
                    panel.remove(child);
                    break;
                }
            }
        }
    }

    /**
     * Creates and adds a panel to BorderedPanel based on the provided PanelType
     * @param panelType The type of panel to create (CENTER, LEFT, TOP, RIGHT, BOTTOM)
     */
    private void createPanel(PanelType panelType) {
        switch (panelType) {
            case CENTER -> addCenterPanel();
            case LEFT -> addLeftPanel();
            case TOP -> addTopPanel();
            case RIGHT -> addRightPanel();
            case BOTTOM -> addBottomPanel();
        }
    }
}
