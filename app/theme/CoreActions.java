package app.theme;

import app.frames.MainFrame;
import app.frames.SettingsFrame;
import app.helpers.HelperMethods;
import app.overrides.oJFrame;

import javax.swing.*;
import java.awt.*;

public class CoreActions {

    public enum FrameAction {
        EXIT {
            @Override
            public void performAction(JFrame frame) {
                if (frame != null) {
                    System.exit(0);
                }
            }
        },
        ICONIFY {
            @Override
            public void performAction(JFrame frame) {
                if (frame != null) {
                    frame.setState(Frame.ICONIFIED);
                }
            }
        },
        HIDE {
            @Override
            public void performAction(JFrame frame) {
                if (frame != null) {
                    frame.setVisible(false);
                }
            }
        },
        SHOW {
            @Override
            public void performAction(JFrame frame) {
                if (frame != null) {
                    frame.setVisible(true);
                }
            }
        },
        DISPOSE {
            @Override
            public void performAction(JFrame frame) {
                if (frame != null) {
                    frame.dispose();
                }
            }
        };

        public abstract void performAction(JFrame frame);
    }

    private static void exitApp(JFrame frame) {
        if (frame.getTitle().equals(Core.mainFrameName)) {
            System.exit(0);
        }
    }

    private static void minimizeFrame(JFrame frame) {
        frame.setState(Frame.ICONIFIED);
    }

    private static void setFrameVisibility(JFrame frame, boolean isVisible) {
        frame.setVisible(isVisible);
    }

    private static void updateShadowState(FrameAction action, oJFrame... relatedFrames) {
        boolean shadowed = action != FrameAction.HIDE;

        for (oJFrame relatedFrame : relatedFrames) {
            if (relatedFrame != null) {
                relatedFrame.setShadowed(shadowed);
            }
        }
    }

    public static void closeButtonAction(JFrame frame) {
        switch (frame.getTitle()) {
            case Core.mainFrameName -> FrameAction.EXIT.performAction(frame);
            case Core.settingsFrameName -> FrameAction.HIDE.performAction(getMainFrame());
        }
    }

    public static void minimizeButtonAction(JFrame frame) {
        switch (frame.getTitle()) {
            case Core.mainFrameName -> FrameAction.ICONIFY.performAction(frame);
            case Core.settingsFrameName -> FrameAction.HIDE.performAction(getSettingsFrame());
        }
    }

    private static MainFrame getMainFrame() {
        return (MainFrame) HelperMethods.getFrameByTitle(Core.mainFrameName);
    }

    private static SettingsFrame getSettingsFrame() {
        return (SettingsFrame) HelperMethods.getFrameByTitle(Core.settingsFrameName);
    }
}
