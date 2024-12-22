package app.components;

import app.helpers.BorderedPanel;
import app.helpers.HelperMethods;
import app.helpers.PopupFrame;
import app.overrides.oJFrame;
import app.theme.Core;
import app.theme.CoreActions;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ColorPicker extends JColorChooser {
    ColorPicker(BasicButton colorSwitch, Core.ThemeColors themeColorToControl) {
        AbstractColorChooserPanel[] panels = this.getChooserPanels();
        for (AbstractColorChooserPanel panel : panels) {
            if (!panel.getDisplayName().equals("HSV")) {
                this.removeChooserPanel(panel);
            }
        }

        AbstractColorChooserPanel hsvPanel = this.getChooserPanels()[0];
        for (Component component : hsvPanel.getComponents()) {
            if (component instanceof JPanel) {
                component.setVisible(false);
            }
        }

        hsvPanel.setBackground(Color.black);

        this.setPreviewPanel(new JPanel());
    }

    public static Color showDialog(Component parent, String title, Color initialColor, boolean colorTransparencySelectionEnabled, Core.ThemeColors themeColorToControl, BasicButton colorSwitch) {
        oJFrame existingDialog = HelperMethods.getFrameByTitle(Core.colorPickerName);
        if (existingDialog != null)
            existingDialog.dispose();

        ColorPicker colorPicker = new ColorPicker(colorSwitch, themeColorToControl);
        colorPicker.setColor(initialColor);

        if (colorTransparencySelectionEnabled) {
            colorPicker.setPreviewPanel(new JPanel());
        }

        colorPicker.getSelectionModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Color currentColor = colorPicker.getColor();
                themeColorToControl.setColor(currentColor);
                colorSwitch.setBackground(themeColorToControl.getColor());
                HelperMethods.updateTheme();
            }
        });

        oJFrame frame = new oJFrame();

        Header header = (new Header(frame, false, BorderedPanel.PanelType.RIGHT));
        header.setBackground(new Color(158,158,158));
        header.setIsChangeable(false);
        //header.setPreferredSize(new Dimension(20,20));
        header.getCloseButton().setIsChangeable(false);
        header.replaceCloseButtonAction(frame, CoreActions.FrameAction.DISPOSE);
        frame.setTitle(Core.colorPickerName);
        frame.setShadowable(false);
        frame.setUndecorated(true);
        frame.add(header, BorderLayout.NORTH);
        frame.add(colorPicker);
        frame.setAlwaysOnTop(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setIconImage(Core.iconBlank.getImage());
        frame.setType(Window.Type.UTILITY);
        frame.pack();
        frame.setVisible(true);
        frame.revalidate();
        return colorPicker.getColor();
    }
}
