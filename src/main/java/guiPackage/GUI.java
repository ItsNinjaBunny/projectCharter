package guiPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

 

@SuppressWarnings("serial")
class GUI extends JFrame {

private JSplitPane splitPaneV;
private JSplitPane splitPaneH;
private JPanel directory;
private JPanel panel2;
private JPanel panel3;
private JTabbedPane pane;
private JPanel temp = new JPanel();

 


public GUI(){
    
    setTitle( "Menu - CompanyVault.exe" );
    setBackground( Color.gray );
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    JPanel topPanel = new JPanel();
    topPanel.setPreferredSize( new Dimension( 650, 450 ) );
    topPanel.setLayout( new BorderLayout() );
    getContentPane().add( topPanel );

    // Create the panels
    createDirectory();
    createPanel2();
    //showFrame();
    

 

    // Create a splitter pane
    splitPaneV = new JSplitPane( JSplitPane.VERTICAL_SPLIT );
    topPanel.add( splitPaneV, BorderLayout.CENTER );

    splitPaneH = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT );
    splitPaneH.setLeftComponent(directory);
    splitPaneH.setRightComponent(temp);

    splitPaneV.setLeftComponent(splitPaneH);
    splitPaneV.setRightComponent(panel3);
}

 
//creation of the static directory on the left hand side 
public void createDirectory(){
    directory = new JPanel();
    directory.setLayout( new GridLayout(5,1) );
    JPanel insertRecords = new JPanel();
    JButton goButton = new JButton("GO");
    JButton insertButton = new JButton("INSERT RECORDS");
    
    
    insertButton.setPreferredSize(new Dimension(100,25));
    
    insertButton.addActionListener(new ActionListener() {
    	
    	@Override
    	@SuppressWarnings({"rawtypes", "unchecked"})
    	public void actionPerformed(ActionEvent e) {
    		setTitle("InsertRecords - CompanyVault");
    		JPanel selectorPanel = new JPanel();
    		
    		
    		String[] messages = {"Select", "Single File Upload", "Bulk File Upload", "Manual File Entry"};
    		JComboBox messageList = new JComboBox(messages);
    		JLabel selectionText = new JLabel();
    		selectorPanel.add(selectionText);
    		
    		class directoryAction implements ActionListener {
    			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				
    				if(e.getSource().equals(messageList)) {
    					
    					JComboBox cb = (JComboBox) e.getSource();
    					String msg = cb.getSelectedItem().toString();
    					switch(msg) {
    					
    					case "Select":
    						selectionText.setText("you selected: " + msg);
    						goButton.addActionListener(new ActionListener() {
    							
    							@Override
    							public void actionPerformed(ActionEvent e) {
    								JOptionPane.showMessageDialog(null, "Please select one of the other options");
    							}
    						});
    						break;
    					case "Single File Upload":
    						goButton.addActionListener(new ActionListener() {
    							
    							@Override
    							public void actionPerformed(ActionEvent e) {
    								showFrame();
    								splitPaneH.remove(insertRecords);
    								splitPaneH.setRightComponent(pane);
    							}
    						});
    					}
    				}
    				
    			}
    		}
    		messageList.setSelectedIndex(1);
    		messageList.addActionListener(new directoryAction());
    		
    		insertRecords.add(selectionText);
    		insertRecords.add(goButton);
    		insertRecords.add(messageList);
    		splitPaneH.setRightComponent(insertRecords);
    	}
    });
    
    
    directory.add(insertButton);
    directory.add(new JButton("UPDATE"));
    directory.add(new JButton("DELETE"));
    directory.add(new JButton("FIND"));
    directory.add(new JButton("BACK"));

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
    pane.setBackground(Color.WHITE);
    pane.setForeground(Color.BLACK);  
}

 

public static void main( String args[] ){
    try {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    } catch (Exception evt) {}
    // Create an instance of the test application
    GUI mainFrame = new GUI();
    mainFrame.pack();
    mainFrame.setVisible( true );
}
}