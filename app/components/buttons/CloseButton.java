package app.components.buttons;

import app.components.BasicButton;
import app.components.buttons.icondrawers.CloseIconDrawer;
import app.theme.Core;
import app.theme.CoreActions;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CloseButton extends BasicButton {
    private static final ArrayList<CloseButton> instanceList = new ArrayList<CloseButton>();
    public CloseIconDrawer icon;
    private boolean isChangeable = true;

    public CloseButton(JFrame parentFrame){
        this(parentFrame,null);
    }

    public CloseButton(JFrame parentFrame, CoreActions.FrameAction action){
        // Create a Button object
        super(null, Core.ThemeColors.BUTTON_HOVER.getColor(), Core.ThemeColors.BUTTON_CLICK.getColor());

        instanceList.add(this);

        // Set the button size
        int buttonSize = Core.headerHeight;
        this.setPreferredSize(new Dimension(buttonSize,buttonSize));

        // Add the close icon
        icon = new CloseIconDrawer(this.getPreferredSize());
        this.add(icon);

        // Add the close action
        if (action!=null)
            this.addActionListener(e-> action.performAction(parentFrame));
        else
            this.addActionListener(e-> CoreActions.closeButtonAction(parentFrame));
    }

    public static void updateColors(){
        for (CloseButton button : instanceList){
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
