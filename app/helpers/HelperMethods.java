package app.helpers;

import app.components.BasicButton;
import app.components.Header;
import app.components.buttons.CloseButton;
import app.components.buttons.MinimizeButton;
import app.overrides.oJFrame;
import app.overrides.oJLabel;

import java.awt.*;

public class HelperMethods {
    public static oJFrame getFrameByTitle(String title) {
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof oJFrame && window.isVisible()) {
                oJFrame frame = (oJFrame) window;
                if (frame.getTitle().equals(title)) {
                    return frame;
                }
            }
        }
        return null;
    }

    public static Window getWindowByTitle(String title) {
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof oJFrame && window.isVisible()) {
                oJFrame frame = (oJFrame) window;
                if (frame.getTitle().equals(title)) {
                    return window;
                }
            }
        }
        return null;
    }

    public static void setAllVisibleFramesShadowed(boolean value){
        for (oJFrame object : oJFrame.instanceList){
            if (object.isVisible())
                object.setShadowed(value);
        }
    }

    public static void updateTheme(){
        oJFrame.updateColors();
        BasicButton.updateColors();
        Header.updateColors();
        CloseButton.updateColors();
        MinimizeButton.updateColors();
        oJLabel.updateColors();
    }
}
