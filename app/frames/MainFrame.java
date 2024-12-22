package app.frames;

import app.components.BasicButton;
import app.components.Header;
import app.helpers.BorderedPanel;
import app.overrides.*;
import app.theme.Core;

import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainFrame extends oJFrame {
    public MainFrame(){
        init();
        Header header = new Header(this, BorderedPanel.PanelType.RIGHT, BorderedPanel.PanelType.LEFT);

        header.panel.getLeftPanel().add(new BasicButton("Settings",
                new Dimension(Core.headerHeight*2,Core.headerHeight),
                null,
                Core.ThemeColors.BUTTON_HOVER.getColor(),
                Core.ThemeColors.BUTTON_CLICK.getColor()){{
                    addActionListener(e->new SettingsFrame());
        }});

        this.add(header,BorderLayout.NORTH);

        oJPanel panel = new oJPanel(10, 10, 10, 10);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        oJLabel textArea = new oJLabel();
        textArea.setForeground(Core.ThemeColors.FOREGROUND.getColor());
        textArea.setFont(new Font("Montserrat", Font.PLAIN, 16));
        textArea.setText("<html>come on don't leave me it cant be that easy babe if you believe me ill guess ill get on a plane fly to your city excited to see your face hold me console me</html>");
        textArea.setBorder(new EmptyBorder(10, 15, 10, 15));
        textArea.setPreferredSize(new Dimension((int) Core.mainFrameSize.getWidth() / 3, 100));
        textArea.setOpaque(true);
        textArea.setBackground(Core.ThemeColors.UNCHECKED.getColor());
        textArea.setFocusable(false);

        panel.add(textArea);
        this.add(panel);


    }

    private void init(){
        this.setTitle(Core.mainFrameName);
        this.setSize(Core.mainFrameSize);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(oJFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(0,0));
        this.getContentPane().setBackground(Core.ThemeColors.BACKGROUND.getColor());
        this.setUndecorated(true);
    }
}
