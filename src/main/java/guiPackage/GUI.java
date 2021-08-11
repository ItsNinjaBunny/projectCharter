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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")
class GUI extends JFrame {

	private JSplitPane splitPaneV;
	private JSplitPane splitPaneH;
	private JPanel directory;
	private JPanel panel2;
	private JPanel panel3;
	// hardcode entry tabbed page
	private JTabbedPane pane;
	// blank home page
	private JPanel temp = new JPanel();
	// single file upload
	private JPanel singleFilePanel;
	private String companyName;

	public String getCompanyName() {
		return this.companyName;
	}

	JPanel topPanel;

	public GUI(String companyName) {

		this.companyName = companyName;

		setTitle("Menu - CompanyVault.exe");
		setBackground(Color.gray);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(650, 450));
		topPanel.setLayout(new BorderLayout());
		getContentPane().add(topPanel);

		// Create the panels
		createDirectory();
		createPanel2();
		createPanel3();
		showFrame();
		singleFilePanel();
		// Create a splitter pane
		splitPaneV = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

		splitPaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		topPanel.add(splitPaneH, BorderLayout.CENTER);
		splitPaneH.setLeftComponent(directory);
		splitPaneH.setRightComponent(temp);
		splitPaneH.setEnabled(false);
		splitPaneV.setEnabled(false);

	}

	String[] messages = { "Select", "Single File Upload", "Bulk File Upload", "Manual File Entry" };
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox messageList = new JComboBox(messages);
	JButton goButton = new JButton("GO");
	JPanel insertRecords;

//creation of the static directory on the left hand side 
	public static String fileupload() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// currently picks file and gets file path
		JFileChooser chooser = new JFileChooser("C:/Users");

		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open: " + chooser.getSelectedFile().getName() + "\n"
					+ chooser.getSelectedFile().getAbsolutePath());
		}
		return chooser.getSelectedFile().getAbsolutePath();
	}

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

				setTitle("Menu - CompanyVault.exe");
				splitPaneH.setEnabled(false);
				splitPaneV.setEnabled(false);
				splitPaneH.setLeftComponent(directory);
				splitPaneH.setRightComponent(temp);
//				creating third panel from current screen layout 
//				if needed and to undo off other screens just redo topPanel
//				topPanel.removeAll();
//				topPanel.revalidate();
//				topPanel.add(splitPaneV, BorderLayout.CENTER);
//				splitPaneV.setLeftComponent(splitPaneH);
//				splitPaneV.setRightComponent(panel3);

			}

		});

		insertButton.setPreferredSize(new Dimension(100, 25));

		insertButton.addActionListener(new ActionListener() {

			@Override
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public void actionPerformed(ActionEvent e) {
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

									}

								});
								break;
							case "Bulk File Upload":
								goButton.addActionListener(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent e) {

										splitPaneH.setRightComponent(pane);

									}

								});
								break;
							case "Manual File Entry":
								goButton.addActionListener(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent e) {

										splitPaneH.setRightComponent(pane);

									}

								});
								break;

							case "Select":
								goButton.addActionListener(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent e) {
										JOptionPane.showMessageDialog(null, "Select is not a valid option");

										splitPaneH.setLeftComponent(directory);
										splitPaneH.setRightComponent(insertRecords);

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
				text.setBounds(140, 5, 500, 100);
				goButton.setBounds(295, 70, 50, 40);
				messageList.setBounds(140, 70, 150, 40);
				messageList.setBorder(null);
				insertRecords.add(goButton);
				insertRecords.add(messageList);
				insertRecords.add(text);
				setTitle("InsertRecords - CompanyVault.exe");

				splitPaneH.setLeftComponent(directory);
				splitPaneH.setRightComponent(insertRecords);

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

	JRadioButton rb1, rb2;
	JButton b;
	String[] CSV = { "Employee", "Properties", "Products or Services", "Financial Holdings" };
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox csvList = new JComboBox(CSV);

	public void singleFilePanel() {
		singleFilePanel = new JPanel();
		singleFilePanel.setLayout(null);
		JLabel text = new JLabel();
		text.setText("Please select the type of file(CSV or JSON)");
		text.setBounds(140, 10, 500, 100);

		rb1 = new JRadioButton("CSV");
		rb1.setBounds(180, 70, 100, 30);
		rb2 = new JRadioButton("JSON");
		rb2.setBounds(180, 120, 100, 30);
		ButtonGroup bg = new ButtonGroup();
		bg.add(rb1);
		bg.add(rb2);

		b = new JButton("Select File");
		JLabel text1 = new JLabel();

		text1.setText("Please select the collection where the file will be stored");
		text1.setBounds(140, 170, 500, 10);
		csvList.setBounds(180, 205, 150, 40);
		csvList.setBorder(null);

		b.setBounds(195, 260, 120, 30);


		singleFilePanel.add(text);
		singleFilePanel.add(rb1);
		singleFilePanel.add(rb2);
		singleFilePanel.add(b);
		singleFilePanel.add(text1);
		singleFilePanel.add(csvList);

	}

	// select file button which will change upon csv or json selector to which file
	// will be chosen
	class collectionAction implements ActionListener {
		ArrayList<JButton> btn = new ArrayList<JButton>();

		@Override
		public void actionPerformed(ActionEvent e) {
			btn.add(b);
			for (JButton currentButton : btn) {
				for (ActionListener al : currentButton.getActionListeners()) {
					currentButton.removeActionListener(al);
				}
			}
			if (e.getSource().equals(csvList)) {

				JComboBox cb = (JComboBox) e.getSource();
				String msg = cb.getSelectedItem().toString();

				switch (msg) {
				case "Employee":
					if (rb1.isSelected()) {
						JLabel text2 = new JLabel();

						text2.setText("With CSV if headers are included");
						text2.setBounds(140, 280, 500, 10);
						
						b.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {

								splitPaneH.setRightComponent(pane);

							}

						});
					}
					
					
					break;
				case "Properties":
					b.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {

							splitPaneH.setRightComponent(pane);

						}

					});
					break;
				case "Products or Services":
					b.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {

							splitPaneH.setRightComponent(pane);

						}

					});
					break;
				case "Financial Holdings":
					b.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {

							splitPaneH.setRightComponent(pane);

						}

					});
					break;

				}
			}
		}
	}

	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception evt) {
		}
		// Create an instance of the test application
		GUI mainFrame = new GUI("");
		mainFrame.pack();

		mainFrame.setVisible(true);

	}
}