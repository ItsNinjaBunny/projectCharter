package guiPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

class SplitPane extends JFrame {

private     JSplitPane  splitPaneV;
private     JSplitPane  splitPaneH;
private     JPanel      panel1;
private     JPanel      panel2;
private     JPanel      panel3;
private JTabbedPane pane;


public SplitPane(){
	
    setTitle( "Split Pane Application" );
    setBackground( Color.gray );

    JPanel topPanel = new JPanel();
    topPanel.setPreferredSize( new Dimension( 500, 500 ) );
    topPanel.setLayout( new BorderLayout() );
    getContentPane().add( topPanel );

    // Create the panels
    createPanel1();
    createPanel2();
    showFrame();
  
    

    // Create a splitter pane
    splitPaneV = new JSplitPane( JSplitPane.VERTICAL_SPLIT );
    topPanel.add( splitPaneV, BorderLayout.CENTER );

    splitPaneH = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT );
    splitPaneH.setLeftComponent( panel1 );
    splitPaneH.setRightComponent( pane );

    splitPaneV.setLeftComponent( splitPaneH );
    splitPaneV.setRightComponent( panel3 );
}

public void createPanel1(){
    panel1 = new JPanel();
    panel1.setLayout( new GridLayout(5,1) );

    // Add some buttons
    panel1.add( new JButton( "Center" ));
    panel1.add( new JButton( "Center1" ));
    panel1.add( new JButton( "Center2" ) );
    panel1.add( new JButton( "Center3" ) );
    panel1.add( new JButton( "Center4" ) );

}

public void createPanel2(){
    panel2 = new JPanel();
    panel2.setLayout( new FlowLayout() );

    panel2.add( new JButton( "Button 1" ) );
    panel2.add( new JButton( "Button 2" ) );
    panel2.add( new JButton( "Button 3" ) );
}

public void createPanel3(){
    panel3 = new JPanel();
    panel3.setLayout( new BorderLayout() );
    panel3.setPreferredSize( new Dimension( 500, 100 ) );
    panel3.setMinimumSize( new Dimension( 100, 50 ) );

    panel3.add( new JLabel( "Notes:" ), BorderLayout.NORTH );
    panel3.add( new JTextArea(), BorderLayout.CENTER );
}


public void showFrame() {
	pane = new JTabbedPane();
    pane.addTab("Employee",new JPanel() );
    pane.addTab("Properties", new JPanel());
    pane.addTab("Products or Services", new JPanel());
    pane.addTab("Financial Holdings", new JPanel());
	

    
}

public static void main( String args[] ){
    try {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    } catch (Exception evt) {}
    // Create an instance of the test application
    SplitPane mainFrame = new SplitPane();
    mainFrame.pack();
    mainFrame.setVisible( true );
}
}