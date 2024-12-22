package app.frames;

import app.components.BasicButton;
import app.components.ColorPicker;
import app.helpers.BorderedPanel;
import app.helpers.HelperMethods;
import app.helpers.PopupFrame;
import app.overrides.oJLabel;
import app.overrides.oJPanel;
import app.theme.Core;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SettingsFrame extends PopupFrame {
    public SettingsFrame(){
        super(Core.settingsFrameSize, true);

        oJPanel panel = new oJPanel(30,30,0,48);
        panel.setLayout(new GridLayout(8, 1,0,2));
        this.add(panel);
        this.setShadowable(false);

        panel.add(createItemColorPanel("Override color:", Color.RED));
        panel.add(createTitlePanel("THEME SETTINGS"));
        panel.add(createThemeColorPanel("Header color:", Core.ThemeColors.HEADER,null));
        panel.add(createThemeColorPanel("Background color:", Core.ThemeColors.BACKGROUND,null));
        panel.add(createThemeColorPanel("Unchecked color:", Core.ThemeColors.UNCHECKED,null));
        panel.add(createThemeColorPanel("Checked color:", Core.ThemeColors.CHECKED,null));
        panel.add(createThemeColorPanel("System font color:", Core.ThemeColors.FOREGROUND,null));

        BorderedPanel.setGlobalDebugMode(false);
        this.revalidate();
    }

    private BorderedPanel createColorSwitch(BasicButton parent, Color color){
        return null;
    }
    private BorderedPanel createItemColorPanel(String title, Color colorToControl){
        return createThemeColorPanel(title,null,colorToControl);
    }
    private BorderedPanel createThemeColorPanel(String title, Core.ThemeColors themeColorToControl, Color colorToControl){
        BorderedPanel borderedPanel = new BorderedPanel(BorderedPanel.PanelType.CENTER, BorderedPanel.PanelType.RIGHT);
        borderedPanel.getRightPanel().setPreferredSize(new Dimension(125, 125));
        borderedPanel.getCenterPanel().setLayout(new BorderLayout(0,0));

        // TEXT
        oJLabel label = new oJLabel(title);
        label.setIsChangeable(false);
        label.setFont(new Font("Montserrat",Font.PLAIN,19));
        borderedPanel.getCenterPanel().add(label);

        // BUTTON
        BasicButton colorButton = new BasicButton(new Dimension(60,60),
                themeColorToControl == null ? colorToControl : themeColorToControl.getColor(),
                null,null);
        borderedPanel.getRightPanel().setLayout(new GridBagLayout());
        if (themeColorToControl != null)
            colorButton.addActionListener(e-> {
                Color newColor = ColorPicker.showDialog(HelperMethods.getFrameByTitle(Core.settingsFrameName),
                        "Color picker",themeColorToControl.getColor(),false, themeColorToControl,colorButton);
                themeColorToControl.setColor(newColor);
            });
        colorButton.setBorder(new LineBorder(new Color(0,0,0,18),1));

        // GRID CONSTRAINTS
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 1;

        colorButton.setHorizontalAlignment(BasicButton.CENTER);
        borderedPanel.getRightPanel().add(colorButton,gbc);

        return borderedPanel;
    }

    private JLabel createTitlePanel(String title){
        oJLabel label = new oJLabel(title);
        label.setIsChangeable(false);
        label.setFont(new Font("Montserrat",Font.PLAIN,40));
        label.setHorizontalAlignment(JLabel.CENTER);

        return label;
    }
}
