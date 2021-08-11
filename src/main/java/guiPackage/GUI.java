package guiPackage;

import java.awt.BorderLayout;
import java.awt.Color;   
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
	//hardcode entry tabbed page
	private JTabbedPane pane;
	//blank home page
	private JPanel temp = new JPanel();
	//single file upload
	private JPanel singleFilePanel;
	private String companyName;
	 
	public String getCompanyName() {
		return this.companyName;
	}

	public GUI(String companyName){
	    
		this.companyName = companyName;

		setTitle("Menu - CompanyVault.exe");
		setBackground(Color.gray);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(650, 450));
		topPanel.setLayout(new BorderLayout());
		getContentPane().add(topPanel);

		// Create the panels
		createDirectory();
		createPanel2();
		
		showFrame();
		singleFilePanel();
		// Create a splitter pane
		splitPaneV = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		topPanel.add(splitPaneV, BorderLayout.CENTER);

		splitPaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPaneH.setLeftComponent(directory);
		splitPaneH.setRightComponent(temp);
		splitPaneH.setEnabled(false);
		splitPaneV.setEnabled(false);
		splitPaneV.setLeftComponent(splitPaneH);
		
	


    // Create a splitter pane
    splitPaneV = new JSplitPane( JSplitPane.VERTICAL_SPLIT );
    topPanel.add( splitPaneV, BorderLayout.CENTER );

    splitPaneH = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT );
    splitPaneH.setLeftComponent(directory);
    splitPaneH.setRightComponent(temp);

    splitPaneV.setLeftComponent(splitPaneH);

}

	String[] messages = {"Select", "Single File Upload", "Bulk File Upload", "Manual File Entry"};
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox messageList = new JComboBox(messages);
	JButton goButton = new JButton("GO");
	JPanel insertRecords;



//creation of the static directory on the left hand side 

	public void createDirectory() {
		
		directory = new JPanel();
		directory.setLayout(new GridLayout(5, 1));
		insertRecords = new JPanel();
		insertRecords.setLayout(null);
		insertRecords.remove(messageList);

		JButton insertButton = new JButton("INSERT RECORDS");
		JButton homeButton = new JButton("HOME");
		directory.add(insertButton);
		directory.add(new JButton("UPDATE"));
		directory.add(new JButton("DELETE"));
		directory.add(new JButton("FIND"));
		directory.add(homeButton);
		homeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				splitPaneH.removeAll();
				splitPaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
				setTitle("Menu - CompanyVault.exe");
				splitPaneH.setEnabled(false);
				splitPaneV.setEnabled(false);
				splitPaneH.setLeftComponent(directory);
				splitPaneH.setRightComponent(temp);
				splitPaneV.setLeftComponent(splitPaneH);

			}

		});

		insertButton.setPreferredSize(new Dimension(100, 25));

		insertButton.addActionListener(new ActionListener() {

			@Override
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public void actionPerformed(ActionEvent e) {
				
				
				JPanel selectorPanel = new JPanel();

				JLabel selectionText = new JLabel();
				selectorPanel.add(selectionText);

				class directoryAction implements ActionListener {
					ArrayList<JButton> btn = new ArrayList<JButton>();

					@Override
					public void actionPerformed(ActionEvent e) {
						btn.add(goButton);
						for (JButton currentButton : btn) {
							for (ActionListener al : currentButton.getActionListeners()) {
								currentButton.removeActionListener(al);
							}
						}
						if (e.getSource().equals(messageList)) {

							JComboBox cb = (JComboBox) e.getSource();
							String msg = cb.getSelectedItem().toString();

							switch (msg) {

							case "Single File Upload":
								goButton.addActionListener(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent e) {
										
										splitPaneH.setRightComponent(singleFilePanel);
										pack();
										splitPaneH.setEnabled(false);
										splitPaneV.setEnabled(false);
									}

								});
								break;
							case "Bulk File Upload":
								goButton.addActionListener(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent e) {

										splitPaneH.setRightComponent(pane);
										splitPaneH.setEnabled(false);
										splitPaneV.setEnabled(false);
									}

								});
								break;
							case "Manual File Entry":
								goButton.addActionListener(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent e) {

										splitPaneH.setRightComponent(pane);
										splitPaneH.setEnabled(false);
										splitPaneV.setEnabled(false);
									}

								});
								break;

							case "Select":
								goButton.addActionListener(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent e) {
										JOptionPane.showMessageDialog(null, "Select is not a valid option");
										splitPaneH.removeAll();
										splitPaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
										splitPaneH.setLeftComponent(directory);
										splitPaneH.setRightComponent(insertRecords);
										splitPaneV.setLeftComponent(splitPaneH);
										splitPaneH.setEnabled(false);
										splitPaneV.setEnabled(false);

									}

								});
								break;
							default:

								break;
							}

						}

					}
				}
				messageList.setSelectedIndex(0);
				messageList.addActionListener(new directoryAction());
				JLabel text = new JLabel();
				text.setText("Please select the type of insert:");
				text.setBounds(140,5,500,100);
				goButton.setBounds(295,70,50,40);
				messageList.setBounds(140,70,150,40);
				messageList.setBorder(null);
				insertRecords.add(goButton);
				insertRecords.add(messageList);
				insertRecords.add(text);
				setTitle("InsertRecords - CompanyVault.exe");
				splitPaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
				splitPaneH.setEnabled(false);
				splitPaneV.setEnabled(false);
				splitPaneH.setLeftComponent(directory);
				splitPaneH.setRightComponent(insertRecords);
				splitPaneV.setLeftComponent(splitPaneH);
			}
		});

	}

	public void createPanel2() {
		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());

		panel2.add(new JButton("Button 1"));
		panel2.add(new JButton("Button 2"));
		panel2.add(new JButton("Button 3"));
	}

	public void createPanel3() {
		panel3 = new JPanel();
		panel3.setLayout(new BorderLayout());
		panel3.setPreferredSize(new Dimension(500, 100));
		panel3.setMinimumSize(new Dimension(100, 50));

		panel3.add(new JLabel("Notes:"), BorderLayout.NORTH);
		panel3.add(new JTextArea(), BorderLayout.CENTER);
	}

	public void showFrame() {
		pane = new JTabbedPane();
		pane.addTab("Employee", new JPanel());
		pane.addTab("Properties", new JPanel());
		pane.addTab("Products or Services", new JPanel());
		pane.addTab("Financial Holdings", new JPanel());
		pane.setBackground(Color.WHITE);
		pane.setForeground(Color.BLACK);
	}
	JRadioButton rb1,rb2;    
	JButton b;   
	public void singleFilePanel() {
		singleFilePanel = new JPanel();
		singleFilePanel.setLayout(null);
		JLabel text = new JLabel();
		text.setText("Please select the type of file(CSV or JSON)");
		 text.setBounds(140,10,500,100);
		    
		rb1=new JRadioButton("CSV");    
		rb1.setBounds(140,70,100,30);      
		rb2=new JRadioButton("JSON");    
		rb2.setBounds(140,120,100,30);    
		ButtonGroup bg=new ButtonGroup();    
		bg.add(rb1);
		bg.add(rb2);   
		
		b=new JButton("Select File");    
		b.setBounds(180,165,120,30);    
		b.addActionListener(new Action1()); 
		singleFilePanel.add(text);
		singleFilePanel.add(rb1);
		singleFilePanel.add(rb2);
		singleFilePanel.add(b);  
		
		 
	}
	 class Action1 implements ActionListener{
   		public void actionPerformed(ActionEvent e) {
   			 
		if(rb1.isSelected()){    
		JOptionPane.showMessageDialog(null,"You are Male.");    
		}    
		if(rb2.isSelected()){    

		}    
		}  
	 }
	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception evt) {
		}
		// Create an instance of the test application
		GUI mainFrame = new GUI("");
		mainFrame.pack();
		mainFrame.setVisible(true);

	}
}