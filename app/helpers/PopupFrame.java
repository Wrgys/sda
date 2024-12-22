package app.helpers;

import app.components.Header;
import app.overrides.oJFrame;
import app.theme.Core;
import app.theme.CoreActions;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PopupFrame extends oJFrame {
    private Header header;

    public PopupFrame(Dimension dimension, boolean hasHeader){
        PopupFrame existingFrame = (PopupFrame) HelperMethods.getFrameByTitle(Core.settingsFrameName);
        if (existingFrame != null) {
            existingFrame.dispose();
            return;
        }
        init(dimension,hasHeader);

        if (hasHeader) {
            header = new Header(this, false, BorderedPanel.PanelType.RIGHT);
            header.replaceCloseButtonAction(this,CoreActions.FrameAction.DISPOSE);
            if (header.getCloseButton() != null) {
                header.getCloseButton().setIsChangeable(false);
            }
            if (header.getMinimizeButton() != null) {
                header.getMinimizeButton().setIsChangeable(false);
            }
            header.setIsChangeable(false);
            this.add(header, BorderLayout.NORTH);
        }

        header.setBackground(Core.defaultHeaderColor);

        HelperMethods.setAllVisibleFramesShadowed(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                HelperMethods.setAllVisibleFramesShadowed(false);
            }
        });

        this.setVisible(true);
    }

    private void init(Dimension dimension, boolean hasHeader){
        this.setTitle(Core.settingsFrameName);
        this.setSize(dimension);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setType(Type.UTILITY);
        this.setDefaultCloseOperation(oJFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout(0,0));
        this.getContentPane().setBackground(Color.WHITE);
        this.revalidate();
        this.setUndecorated(hasHeader);
        this.setIsChangeable(false);
    }

    public Header getHeader(){
        if (header!=null)
            return this.header;
        else
            return null;
    }
}
