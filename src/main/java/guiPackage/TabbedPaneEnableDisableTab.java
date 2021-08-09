package guiPackage;

import javax.swing.*;
import java.awt.*;

public class TabbedPaneEnableDisableTab extends JPanel {
    public TabbedPaneEnableDisableTab() {
        initializeUI();
    }

    private void initializeUI() {
        JTabbedPane pane = new JTabbedPane();
        pane.addTab("Employee",new JPanel() );
        pane.addTab("Properties", new JPanel());
        pane.addTab("Products or Services", new JPanel());
        pane.addTab("Financial Holdings", new JPanel());
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(500, 500));
        this.add(pane, BorderLayout.CENTER);
    }

    public static Container showFrame() {
        JPanel panel = new TabbedPaneEnableDisableTab();
        panel.setOpaque(true);

        JFrame frame = new JFrame("CompanyVault - I.B.A.G™");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
		return frame;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TabbedPaneEnableDisableTab.showFrame();
            }
        });
    }
}
