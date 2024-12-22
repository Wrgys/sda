package app.helpers;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

// COMMENTS 100%

/**
 * A transparent object designed to be used as a spacer between elements
 */
public class Spacer extends JLabel {
    /**
     * Valid spacer types:
     * <br>
     * <code>HORIZONTAL</code>
     * <br>
     * <code>VERTICAL</code>
     * <br>
     * <code>SQUARE</code>
     */
    public enum Type{
        HORIZONTAL,
        VERTICAL,
        SQUARE;
    }


// --- Variables: Non-Customizable (Do not modify)

    // Instance list to keep track of all Spacer objects
    private static final ArrayList<Spacer> instanceList = new ArrayList<Spacer>();

    // Random number generator for giving each object a different color when debug mode is enabled
    private static final Random rng = new Random();


// CONSTRUCTOR METHODS - to support various ways of initializing the object

    /**
     * Creates a Spacer object with the specified type and size
     * @param type The type of spacer to be used (HORIZONTAL, VERTICAL, SQUARE)
     * @param size The size of the spacer in pixels
     */
    public Spacer(Type type, int size){
        this(type,size,null);
    }

    /**
     * Creates a Spacer object with the specified type and size, as well as a color
     * for debugging purposes
     * @param type The type of spacer to be used (HORIZONTAL, VERTICAL, SQUARE)
     * @param size The size of the spacer in pixels
     * @param debugColor Color for visualizing the spacer's boundaries
     */
    public Spacer(Type type, int size, Color debugColor){
        // Add the object to the instance list
        instanceList.add(this);

        // Update the background color
        this.setBackground(debugColor);

        // Render the background if the input color is valid
        if (debugColor!=null)
            this.setOpaque(true);

        // Handle the spacer based on its type (horizontal, vertical, square)
        if (type == Type.HORIZONTAL)
            this.setPreferredSize(new Dimension(size,1));
        else if (type == Type.VERTICAL)
            this.setPreferredSize(new Dimension(1,size));
        else if (type == Type.SQUARE)
            this.setPreferredSize(new Dimension(size,size));
    }

    /**
     * Toggles debug mode for all spacers. When enabled, the spacers
     * become visible with randomly assigned colors for visualization purposes
     * <br>
     * <br>
     * Can also take a Spacer object for individual toggling of the debug mode
     * @param value If true, enable debug mode
     */
    public static void setDebugMode(boolean value){
        for (Spacer object : instanceList){
            object.setOpaque(value);
            object.setBackground(new Color(
                    rng.nextInt(256),
                    rng.nextInt(256),
                    rng.nextInt(256)
            ));
        }
    }

    /**
     * Toggles debug mode for the specified spacer. When enabled, the spacer
     * becomes visible with a randomly assigned color for visualization purposes
     * <br>
     * @param spacer The spacer object which will be modified
     * @param value If true, enable debug mode
     */
    public static void setDebugMode(Spacer spacer, boolean value){
        spacer.setOpaque(value);
        spacer.setBackground(new Color(
                rng.nextInt(256),
                rng.nextInt(256),
                rng.nextInt(256)
        ));
    }

    // Allows for non-ordered input of setDebugMode when a Spacer object is specified
    /**
     * Toggles debug mode for the specified spacer. When enabled, the spacer
     * becomes visible with a randomly assigned color for visualization purposes
     * <br>
     * @param spacer The spacer object which will be modified
     * @param value If true, enable debug mode
     */
    public static void setDebugMode(boolean value, Spacer spacer){
        setDebugMode(spacer,value);
    }
}
