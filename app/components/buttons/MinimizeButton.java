package app.components.buttons;

import app.components.BasicButton;
import app.components.buttons.icondrawers.CloseIconDrawer;
import app.components.buttons.icondrawers.MinimizeIconDrawer;
import app.helpers.HelperMethods;
import app.theme.Core;
import app.theme.CoreActions;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MinimizeButton extends BasicButton {
    public static ArrayList<MinimizeButton> instanceList = new ArrayList<MinimizeButton>();
    public MinimizeIconDrawer icon;
    private boolean isChangeable = true;

    public MinimizeButton(JFrame parentFrame){
        this(parentFrame,null);
    }

    public MinimizeButton(JFrame parentFrame, CoreActions.FrameAction action){
        // Create a Button object
        super(null, Core.ThemeColors.BUTTON_HOVER.getColor(), Core.ThemeColors.BUTTON_CLICK.getColor());
        instanceList.add(this);

        // Set the button size
        int buttonSize = Core.headerHeight;
        this.setPreferredSize(new Dimension(buttonSize, buttonSize));

        // Add the minimize icon
        icon = new MinimizeIconDrawer(this.getPreferredSize());
        this.add(icon);

        // Add the minimize action
        if (action!=null)
            this.addActionListener(e-> action.performAction(parentFrame));
        else
            this.addActionListener(e-> CoreActions.minimizeButtonAction(parentFrame));
    }

    public static void updateColors(){
        for (MinimizeButton button : instanceList){
            if (button.isChangeable) {
                button.icon.setIconColor(Core.ThemeColors.FOREGROUND.getColor());
                button.icon.repaint();
            }
        }
    }

    public void setIsChangeable(boolean value){
        this.isChangeable = value;
    }
}