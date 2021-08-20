package guiPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ColorUIResource;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.InsertOneModel;
import com.opencsv.bean.CsvToBeanBuilder;

import Encryption.Encrypt;
import Property.Property;
import database.Delete;
import database.Employee;
import database.Find;
import database.ProductServices;
import database.Update;
import database.FinancialHoldings;

@SuppressWarnings("serial")
class GUI extends JFrame {

	private JSplitPane splitPaneV;
	private JSplitPane splitPaneH;
	private JPanel directory;
	private JPanel panel2;
	private JPanel panel3;
	// hardcode entry tabbed page
	private JTabbedPane pane;
	// pane2 for search method
	private JTabbedPane pane2;
	// pane 3 for updating the document in the cloud
	private JTabbedPane pane3;
	// pane 3 for deleting the document in the cloud
	private JTabbedPane pane4;
	// blank home page
	private JPanel temp = new JPanel();
	// single file upload
	private static JPanel singleFilePanel;
	private String companyName;

	public String getCompanyName() {
		return this.companyName;
	}

	private static JPanel topPanel;

	@SuppressWarnings("static-access")
	public GUI(String companyName) {
		this.companyName = companyName;

		setTitle("Menu - CompanyVault.exe");
		setBackground(Color.gray);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(650, 450));
		topPanel.setLayout(new BorderLayout());
		getContentPane().add(topPanel);
		BufferedImage image = null;
		try {
			image = ImageIO.read(GUI.class.getResourceAsStream("img.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		temp.setLayout(null);
		temp.setBackground(Color.GRAY);

		JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(300, 300, image.SCALE_SMOOTH)));
		label.setBounds(105, 70, 300, 300);
		temp.add(label, BorderLayout.CENTER);
		// Create the panels
		createDirectory();
		createPanel2();
		createPanel3();
		createEmployeeTab();
		createFinancialTab();
		createPropertiesTab();
		createServiceTab();
		createProductTab();
		showFrame();
		showFrame2();
		showFrame3();
		showFrame4();
		singleFilePanel();

		// Create a splitter pane

		splitPaneV = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

		splitPaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		topPanel.add(splitPaneH, BorderLayout.CENTER);
		topPanel.setBackground(Color.black);
		directory.setBackground(Color.black);
		splitPaneH.setLeftComponent(directory);
		splitPaneH.setRightComponent(temp);
		splitPaneH.setEnabled(false);
		splitPaneV.setEnabled(false);

	}

	String[] messages = { "Select", "Single File Upload", "Manual File Entry" };
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox messageList = new JComboBox(messages);
	
	JButton goButton = new JButton("GO");
	JPanel insertRecords;

	// creation of the static directory on the left hand side
	public void createDirectory() {
		
		directory = new JPanel();
		directory.setLayout(new GridLayout(5, 1));

		insertRecords = new JPanel();
		insertRecords.setLayout(null);
		insertRecords.remove(messageList);
		messageList.setForeground(Color.white);
		JButton insertButton = new JButton("INSERT RECORDS");
		JButton homeButton = new JButton("HOME");
		JButton updateButton = new JButton("UPDATE");
		JButton deleteButton = new JButton("DELETE");
		JButton findButton = new JButton("FIND");

		findButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				directory.remove(updateButton);
				directory.remove(deleteButton);
				directory.remove(insertButton);
				topPanel.removeAll();
				topPanel.revalidate();
				panel3.removeAll();
				setTitle("Find Records - CompanyVault.exe");
				splitPaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
				splitPaneH.setEnabled(false);
				splitPaneV.setEnabled(false);

				splitPaneH.setLeftComponent(directory);
				splitPaneH.setRightComponent(pane2);
				splitPaneH.setDividerLocation(130);
				Border blackline = BorderFactory.createLineBorder(Color.black);
				splitPaneV.setDividerLocation(300);
				panel3.setBorder(blackline);
				splitPaneV.setLeftComponent(splitPaneH);
				splitPaneV.setRightComponent(panel3);
				topPanel.add(splitPaneV, BorderLayout.CENTER);
				topPanel.revalidate();
				revalidate();
			}
		});

		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				directory.remove(findButton);

				directory.remove(deleteButton);
				directory.remove(insertButton);
				panel3.removeAll();
				setTitle("Update Records - CompanyVault.exe");
				splitPaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
				splitPaneH.setEnabled(false);
				splitPaneV.setEnabled(false);
				splitPaneH.setLeftComponent(directory);
				splitPaneH.setRightComponent(pane3);
				Border blackline = BorderFactory.createLineBorder(Color.black);
				splitPaneH.setDividerLocation(130);
				splitPaneV.setDividerLocation(300);
				panel3.setBorder(blackline);
				topPanel.removeAll();
				topPanel.revalidate();

				splitPaneV.setLeftComponent(splitPaneH);
				splitPaneV.setRightComponent(panel3);
				topPanel.add(splitPaneV);
				topPanel.revalidate();
			}
		});
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				directory.remove(findButton);

				directory.remove(updateButton);

				directory.remove(insertButton);
				panel3.removeAll();
				topPanel.removeAll();
				setTitle("Delete Records - CompanyVault.exe");
				splitPaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
				splitPaneH.setEnabled(false);
				splitPaneV.setEnabled(false);
				splitPaneH.setLeftComponent(directory);
				splitPaneH.setRightComponent(pane4);
				splitPaneH.setDividerLocation(130);
				Border blackline = BorderFactory.createLineBorder(Color.black);
				splitPaneV.setDividerLocation(300);
				panel3.setBorder(blackline);
				topPanel.removeAll();
				topPanel.revalidate();

				splitPaneV.setLeftComponent(splitPaneH);
				splitPaneV.setRightComponent(panel3);
				topPanel.add(splitPaneV, BorderLayout.CENTER);
				topPanel.revalidate();
			}
		});
		directory.add(insertButton);
		directory.add(updateButton);
		directory.add(deleteButton);
		directory.add(findButton);

		directory.add(homeButton);
		ArrayList<JTabbedPane> list = new ArrayList<JTabbedPane>();
		list.add(pane);
		list.add(pane2);
		list.add(pane3);
		list.add(pane4);
		ArrayList<JButton> directory1 = new ArrayList<JButton>();
		directory1.add(insertButton);
		directory1.add(updateButton);
		directory1.add(deleteButton);
		directory1.add(findButton);
		directory1.add(homeButton);
		for (JButton l : directory1) {

			l.setBackground(Color.BLACK);

		}

		directory.add(homeButton);
		homeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					UIManager.getLookAndFeelDefaults().put("Button.background", Color.black);
					UIManager.getLookAndFeelDefaults().put("Button.textForeground", new Color(255, 255, 255));
					UIManager.getLookAndFeelDefaults().put("Label.textForeground", new Color(255, 255, 255));
					UIManager.getLookAndFeelDefaults().put("TextField.background", Color.lightGray);
					UIManager.getLookAndFeelDefaults().put("Panel.background", Color.gray);

				} catch (Exception evt) {
				}
				GUI gui = new GUI(companyName);
				gui.pack();
				gui.setLocationRelativeTo(null);
				gui.setVisible(true);

			}

		});

		insertButton.setPreferredSize(new Dimension(100, 25));

		insertButton.addActionListener(new ActionListener() {

			@Override
			@SuppressWarnings({ "rawtypes" })
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
										splitPaneH.setLeftComponent(directory);
										splitPaneH.setRightComponent(singleFilePanel);

									}

								});
								break;

							case "Manual File Entry":
								goButton.addActionListener(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent e) {
										splitPaneH.setLeftComponent(directory);
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
				directory.remove(findButton);

				directory.remove(updateButton);

				directory.remove(deleteButton);
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
				insertRecords.setBackground(Color.GRAY);
				insertRecords.add(text);
				setTitle("InsertRecords - CompanyVault.exe");

				splitPaneH.setEnabled(false);
				splitPaneV.setEnabled(false);
				splitPaneH.setRightComponent(insertRecords);
				splitPaneH.setLeftComponent(directory);
				topPanel.removeAll();
				topPanel.revalidate();
				topPanel.add(splitPaneH, BorderLayout.CENTER);

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
		panel3.setPreferredSize(new Dimension(400, 100));
		panel3.setMinimumSize(new Dimension(50, 50));
		panel3.add(new JLabel("Results:"), BorderLayout.NORTH);
		panel3.add(new JScrollPane(), BorderLayout.CENTER);
	}

	private static JPanel panelEmployee;
	private static JProgressBar progressBar1 = new JProgressBar();
	private static JProgressBar progressBar2 = new JProgressBar();
	private static JProgressBar progressBar3 = new JProgressBar();
	private static JProgressBar progressBar4 = new JProgressBar();
	private static JProgressBar progressBar5 = new JProgressBar();

	public void createEmployeeTab() {
		panelEmployee = new JPanel();
		panelEmployee.setLayout(new BorderLayout());
		progressBar1.setValue(0);
		progressBar1.setBounds(130, 360, 300, 30);
		progressBar1.setVisible(true);

		JPanel p = new JPanel();
		JLabel jFirstName = new JLabel("First Name:");
		JTextField lFirstName = new JTextField(20);
		JLabel jLastName = new JLabel("Last Name:");
		JTextField lLastName = new JTextField(20);
		JLabel jHireYear = new JLabel("Hire Year:");
		JTextField lHireYear = new JTextField(20);
		JLabel jSocial = new JLabel("SSN:");
		JTextField lSocial = new JTextField(20);
		JLabel jOccupation = new JLabel("Occupation:");
		JTextField lOccupation = new JTextField(20);
		p.setLayout(new GridLayout(6, 1));
		p.add(jFirstName);
		p.add(lFirstName);
		p.add(jLastName);
		p.add(lLastName);
		p.add(jHireYear);
		p.add(lHireYear);
		p.add(jSocial);
		p.add(lSocial);
		p.add(jOccupation);
		p.add(lOccupation);

		JButton Submit = new JButton("Submit");
		Submit.setSize(new Dimension(1, 1));

		Submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (lFirstName.getText().isEmpty() || lLastName.getText().isEmpty() || lHireYear.getText().isEmpty()
						|| lSocial.getText().isEmpty() || lOccupation.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Incorrect credentials one or more fields left blank");
					progressBar1.setValue(0);
				} else {
					progressBar1.setValue(40);
					progressBar1.setVisible(true);
					progressBar1.setValue(40);
					JOptionPane.showMessageDialog(null, "Uploading Employee...");

					p.revalidate();

					String ssn = lSocial.getText().replace("-", "");
					// make all strings capital then encode them then put them in the
					// insert methods do this for all uploads csv and manual entry
					insertEmployee(companyName, lFirstName.getText(), lLastName.getText(), lHireYear.getText(), ssn,
							lOccupation.getText());
					lFirstName.setText("");
					lLastName.setText("");
					lHireYear.setText("");
					lSocial.setText("");
					lOccupation.setText("");
					progressBar1.setValue(0);
					p.revalidate();
				}
			}
		});
		p.add(Submit);
		p.add(progressBar1);
		panelEmployee.add(p, BorderLayout.PAGE_START);

	}

	private static JPanel panelFinancial;

	public void createFinancialTab() {
		panelFinancial = new JPanel();
		panelFinancial.setLayout(new BorderLayout());

		progressBar5.setValue(0);
		progressBar5.setBounds(130, 360, 300, 30);
		progressBar5.setVisible(true);
		JPanel p = new JPanel();
		JLabel jAccountName = new JLabel("Account Name:");
		JTextField lAccountName = new JTextField(20);
		JLabel jBalance = new JLabel("Balance:");
		JTextField lBalance = new JTextField(20);
		JLabel jBank = new JLabel("Banking Institution:");
		JTextField lBank = new JTextField(20);
		JLabel jAccountNumber = new JLabel("Account Number:");
		JTextField lAccountNumber= new JTextField(20);

		p.setLayout(new GridLayout(6, 1));
		p.add(jAccountName);
		p.add(lAccountName);
		p.add(jBalance);
		p.add(lBalance);
		p.add(jBank);
		p.add(lBank);
		p.add(jAccountNumber);
		p.add(lAccountNumber);

		JButton Submit = new JButton("Submit");
		Submit.setSize(new Dimension(1, 1));
		Submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (lAccountName.getText().isEmpty() || lBalance.getText().isEmpty() || lBank.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "Incorrect credentials one or more fields left blank");
					progressBar5.setValue(0);

				} else {
					progressBar5.setValue(40);
					progressBar5.setVisible(true);
					progressBar5.setValue(40);
					JOptionPane.showMessageDialog(null, "Uploading Finances...");
					p.revalidate();
					insertFinance(companyName, lAccountName.getText(), lBalance.getText(), lBank.getText(), lAccountNumber.getText());
					lAccountName.setText("");
					lBalance.setText("");
					lBank.setText("");
					lAccountNumber.setText("");
					progressBar5.setValue(0);
					p.revalidate();
				}
			}
		});
		p.add(Submit);
		p.add(progressBar5);
		panelFinancial.add(p, BorderLayout.NORTH);

	}

	private static JPanel panelProperties;

	public void createPropertiesTab() {
		panelProperties = new JPanel();
		panelProperties.setLayout(new BorderLayout());
		progressBar2.setValue(0);
		progressBar2.setBounds(130, 360, 300, 30);
		progressBar2.setVisible(true);
		JPanel p = new JPanel();
		JLabel jPropertyName = new JLabel("Property Name:");
		JTextField lPropertyName = new JTextField(20);
		JLabel jCost = new JLabel("Cost:");
		JTextField lCost = new JTextField(20);
		JLabel jLocation = new JLabel("Location:");
		JTextField lLocation = new JTextField(20);
		p.setLayout(new GridLayout(6, 1));
		p.add(jPropertyName);
		p.add(lPropertyName);
		p.add(jCost);
		p.add(lCost);
		p.add(jLocation);
		p.add(lLocation);
		JButton Submit = new JButton("Submit");
		Submit.setSize(new Dimension(1, 1));
		Submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (lPropertyName.getText().isEmpty() || lCost.getText().isEmpty() || lLocation.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "Incorrect credentials one or more fields left blank");
					progressBar2.setValue(0);

				} else {
					progressBar2.setValue(40);
					progressBar2.setVisible(true);
					progressBar2.setValue(40);
					JOptionPane.showMessageDialog(null, "Uploading Properties...");

					p.revalidate();

					insertProperty(companyName, lPropertyName.getText(), lCost.getText(), lLocation.getText());
					lPropertyName.setText("");
					lCost.setText("");
					lLocation.setText("");
					progressBar2.setValue(0);
					p.revalidate();
				}

			}
		});

		p.add(Submit);
		p.add(progressBar2);
		panelProperties.add(p, BorderLayout.NORTH);
	}

	private static JPanel panelService;

	public void createServiceTab() {
		panelService = new JPanel();
		panelService.setLayout(new BorderLayout());
		progressBar3.setValue(0);
		progressBar3.setBounds(130, 360, 300, 30);
		progressBar3.setVisible(true);
		JPanel p = new JPanel();
		JLabel jServiceName = new JLabel("Service Name:");
		JTextField lServiceName = new JTextField(20);
		JLabel jCost = new JLabel("Cost:");
		JTextField lCost = new JTextField(20);
		JLabel jCategory = new JLabel("Category:");
		JTextField lCategory = new JTextField(20);
		p.setLayout(new GridLayout(4, 1));
		p.add(jServiceName);
		p.add(lServiceName);
		p.add(jCost);
		p.add(lCost);
		p.add(jCategory);
		p.add(lCategory);
		JButton Submit = new JButton("Submit");
		Submit.setSize(new Dimension(1, 1));
		Submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (lServiceName.getText().isEmpty() || lCost.getText().isEmpty() || lCategory.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "Incorrect credentials one or more fields left blank");
					progressBar3.setValue(0);

				} else {
					progressBar3.setValue(40);
					progressBar3.setVisible(true);
					progressBar3.setValue(40);
					JOptionPane.showMessageDialog(null, "Uploading Service...");

					p.revalidate();

					insertService(companyName, lServiceName.getText(), lCost.getText(), lCategory.getText());
					lServiceName.setText("");
					lCost.setText("");
					lCategory.setText("");
					progressBar3.setValue(0);
					p.revalidate();
				}

			}
		});
		p.add(Submit);
		p.add(progressBar3);
		panelService.add(p, BorderLayout.NORTH);
	}

	private static JPanel panelProduct;

	private void createProductTab() {
		panelProduct = new JPanel();
		panelProduct.setLayout(new BorderLayout());
		progressBar4.setValue(0);
		progressBar4.setBounds(130, 360, 300, 30);
		progressBar4.setVisible(true);
		JPanel p = new JPanel();
		JLabel jProductName = new JLabel("Product Name:");
		JTextField lProductName = new JTextField(20);
		JLabel jCost = new JLabel("Cost:");
		JTextField lCost = new JTextField(20);
		JLabel jCategory = new JLabel("Category:");
		JTextField lCategory = new JTextField(20);
		JLabel jSupplier = new JLabel("Supplier:");
		JTextField lSupplier = new JTextField(20);

		p.setLayout(new GridLayout(5, 1));
		p.add(jProductName);
		p.add(lProductName);
		p.add(jCost);
		p.add(lCost);
		p.add(jCategory);
		p.add(lCategory);
		p.add(jSupplier);
		p.add(lSupplier);
		JButton Submit = new JButton("Submit");
		Submit.setSize(new Dimension(1, 1));
		Submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (lProductName.getText().isEmpty() || lCost.getText().isEmpty() || lCategory.getText().isEmpty()
						|| lSupplier.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "Incorrect credentials one or more fields left blank");
					progressBar4.setValue(0);

				} else {

					progressBar4.setValue(40);
					progressBar4.setVisible(true);
					progressBar4.setValue(40);
					JOptionPane.showMessageDialog(null, "Uploading Product...");
					insertProduct(companyName, lProductName.getText(), lCost.getText(), lCategory.getText(),
							lSupplier.getText());
					lProductName.setText("");
					lCost.setText("");
					lCategory.setText("");
					lSupplier.setText("");
					progressBar4.setValue(0);
					p.revalidate();
				}

				p.revalidate();

			}
		});
		p.add(Submit);
		p.add(progressBar4);
		panelProduct.add(p, BorderLayout.NORTH);
	}

	public void showFrame() {
		pane = new JTabbedPane();
		pane.addTab("Employees", panelEmployee);
		pane.addTab("Properties", panelProperties);
		pane.addTab("Products", panelProduct);
		pane.addTab("Services", panelService);
		pane.addTab("Financial Holdings", panelFinancial);
		pane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				panel3.removeAll();
			}
		});

	}

	public void showFrame2() {
		pane2 = new JTabbedPane();

		pane2.addTab("Employees", Find.searchEmployee(panel3, companyName));
		pane2.addTab("Properties", Find.searchProperty(panel3, companyName));
		pane2.addTab("Products", Find.searchProduct(panel3, companyName));
		pane2.addTab("Services", Find.searchService(panel3, companyName));
		pane2.addTab("Financial Holdings", Find.searchFinancials(panel3, companyName));
		pane2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				panel3.removeAll();
				panel3.revalidate();
			}
		});

	}

	public void showFrame3() {
		pane3 = new JTabbedPane();
		pane3.addTab("Employees", Update.updateEmployee(panel3, companyName));
		pane3.addTab("Properties", Update.updateProperty(panel3, companyName));
		pane3.addTab("Products", Update.updateProduct(panel3, companyName));
		pane3.addTab("Services", Update.updateService(panel3, companyName));
		pane3.addTab("Financial Holdings", Update.updateFinancials(panel3, companyName));
		pane3.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				panel3.removeAll();
				panel3.revalidate();
			}
		});

	}

	public void showFrame4() {
		pane4 = new JTabbedPane();
		pane4.addTab("Employees", Delete.deleteEmployee(panel3, companyName));
		pane4.addTab("Properties", Delete.deleteProperty(panel3, companyName));
		pane4.addTab("Products", Delete.deleteProduct(panel3, companyName));
		pane4.addTab("Services", Delete.deleteService(panel3, companyName));
		pane4.addTab("Financial Holdings", Delete.deleteFinancials(panel3, companyName));
		pane4.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				panel3.removeAll();
				panel3.revalidate();
			}
		});
	}

	JRadioButton rb1, rb2;
	JButton b;
	private static String[] CSV = { "--Select--", "Employees", "Properties", "Products", "Services",
			"Financial Holdings" };
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static JComboBox csvList = new JComboBox(CSV);
	private static JLabel text2;
	private static JLabel text3;
	private static ButtonGroup bg;
	private static JProgressBar progressBar;

	public void singleFilePanel() {
		singleFilePanel = new JPanel();
		singleFilePanel.setLayout(null);
		singleFilePanel.setBackground(Color.gray);
		JLabel text = new JLabel();
		progressBar = new JProgressBar();
		progressBar.setValue(0);
		progressBar.setBounds(130, 360, 300, 30);
		progressBar.setBackground(Color.green);
		progressBar.setVisible(false);

		singleFilePanel.add(progressBar);
		text.setText("1. Please select the type of file");
		text.setBounds(140, 10, 500, 100);

		rb1 = new JRadioButton("CSV");
		rb1.setBounds(180, 70, 100, 30);
		rb2 = new JRadioButton("JSON");
		rb2.setBounds(180, 120, 100, 30);
		bg = new ButtonGroup();
		bg.add(rb1);
		bg.add(rb2);

		b = new JButton("Select File");
		JLabel text1 = new JLabel();

		text1.setText("2. Please select the collection where the file will be stored");
		text1.setBounds(140, 170, 500, 10);
		csvList.setBounds(180, 205, 150, 40);
		csvList.setBorder(null);

		b.setBounds(195, 260, 120, 30);
		text2 = new JLabel();
		text3 = new JLabel();

		text2.setBounds(140, 300, 500, 12);
		text3.setText("id, firstname, lastname, hireyear");
		text3.setBounds(140, 330, 500, 12);
		csvList.addActionListener(new collectionAction());
		rb2.addActionListener(new ActionJson());
		rb1.addActionListener(new ActionCsv());
		csvList.setSelectedIndex(0);
		singleFilePanel.add(text);
		singleFilePanel.add(rb1);

		singleFilePanel.add(b);
		singleFilePanel.add(text1);
		singleFilePanel.add(csvList);

		singleFilePanel.add(text2);
		singleFilePanel.add(text3);
		text2.setVisible(false);
		text3.setVisible(false);

	}

	class ActionJson implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			csvList.setSelectedIndex(0);
			text2.setVisible(false);
			text3.setVisible(false);

		}
	}

	class ActionCsv implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			csvList.setSelectedIndex(0);

		}
	}

	// select file button which will change upon csv or json selector to which file
	// will be chosen
	class collectionAction implements ActionListener {
		ArrayList<JButton> btn = new ArrayList<JButton>();

		@Override
		public void actionPerformed(ActionEvent e) {
			btn.add(b);
			text2.setVisible(false);
			text3.setVisible(false);
			for (JButton currentButton : btn) {
				for (ActionListener al : currentButton.getActionListeners()) {
					currentButton.removeActionListener(al);
				}
			}
			if (e.getSource().equals(csvList)) {

				@SuppressWarnings("rawtypes")
				JComboBox cb = (JComboBox) e.getSource();
				String msg = cb.getSelectedItem().toString();

				switch (msg) {
				case "Employees":
					if (rb1.isSelected()) {
						text2.setText("With CSV Ensure columns for " + msg + " are in: ");
						text3.setText("id, firstname, lastname, hireyear, ssn, occupation");
						text2.setVisible(true);
						text3.setVisible(true);
						b.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								text2.setVisible(false);
								text3.setVisible(false);
								uploadEmployeeCSV(companyName, msg);
								progressBar.setVisible(false);
								progressBar.setValue(0);

							}

						});

					}
					if (rb2.isSelected()) {

						text2.setVisible(false);
						text3.setVisible(false);
						b.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								text2.setVisible(false);
								text3.setVisible(false);
								uploadJSON(companyName, msg);
								splitPaneH.setLeftComponent(directory);
								splitPaneH.setRightComponent(temp);
								progressBar.setVisible(false);
								progressBar.setValue(0);

							}

						});

					}

					break;
				case "Properties":
					if (rb1.isSelected()) {
						text2.setText("With CSV Ensure columns for " + msg + " are in: ");
						text3.setText("id, propertyname, cost, location");
						text2.setVisible(true);
						text3.setVisible(true);
						b.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								text2.setVisible(false);
								text3.setVisible(false);
								uploadPropertyCSV(companyName, msg);
								progressBar.setVisible(false);
								progressBar.setValue(0);
								dispose();
								try {
									UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
								} catch (Exception evt) {
								}
								GUI gui = new GUI(companyName);
								gui.pack();
								gui.setLocationRelativeTo(null);
								gui.setVisible(true);
							}

						});

					}
					if (rb2.isSelected()) {

						text2.setVisible(false);
						text3.setVisible(false);
						b.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								text2.setVisible(false);
								text3.setVisible(false);
								uploadJSON(companyName, msg);
								progressBar.setVisible(false);
								progressBar.setValue(0);

							}

						});

					}

					break;
				case "Products":
					if (rb1.isSelected()) {
						text2.setText("With CSV Ensure columns for " + msg + " are in: ");
						text3.setText("id, productname, cost, category, supplier");
						text2.setVisible(true);
						text3.setVisible(true);
						b.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								text2.setVisible(false);
								text3.setVisible(false);
								uploadProductsCSV(companyName, msg);
								progressBar.setVisible(false);
								progressBar.setValue(0);
								dispose();
								try {
									UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
								} catch (Exception evt) {
								}
								GUI gui = new GUI(companyName);
								gui.pack();
								gui.setLocationRelativeTo(null);
								gui.setVisible(true);
							}

						});

					}
					if (rb2.isSelected()) {

						text2.setVisible(false);
						text3.setVisible(false);
						b.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								text2.setVisible(false);
								text3.setVisible(false);
								uploadJSON(companyName, msg);

								progressBar.setVisible(false);
								progressBar.setValue(0);
							}

						});

					}
					break;
				case "Services":
					if (rb1.isSelected()) {
						text2.setText("With CSV Ensure columns for " + msg + " are in: ");
						text3.setText("id, servicename, cost, category");
						text2.setVisible(true);
						text3.setVisible(true);
						b.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								text2.setVisible(false);
								text3.setVisible(false);
								uploadServiceCSV(companyName, msg);
								progressBar.setVisible(false);
								progressBar.setValue(0);
								dispose();
								try {
									UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
								} catch (Exception evt) {
								}
								GUI gui = new GUI(companyName);
								gui.pack();
								gui.setLocationRelativeTo(null);
								gui.setVisible(true);

							}

						});

					}
					if (rb2.isSelected()) {

						text2.setVisible(false);
						text3.setVisible(false);
						b.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								text2.setVisible(false);
								text3.setVisible(false);
								uploadJSON(companyName, msg);
								progressBar.setVisible(false);
								progressBar.setValue(0);

							}

						});

					}
					break;

				case "Financial Holdings":
					if (rb1.isSelected()) {
						text2.setText("With CSV Ensure columns for " + msg + " are in: ");
						text3.setText("accountid, accountname, balance, bank");
						text2.setVisible(true);
						text3.setVisible(true);
						b.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								text2.setVisible(false);
								text3.setVisible(false);
								uploadFinancialHoldingsCSV(companyName, msg);
								progressBar.setVisible(false);
								progressBar.setValue(0);
								dispose();
								try {
									UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
								} catch (Exception evt) {
								}
								GUI gui = new GUI(companyName);
								gui.pack();
								gui.setLocationRelativeTo(null);
								gui.setVisible(true);

							}

						});

					}
					if (rb2.isSelected()) {

						text2.setVisible(false);
						text3.setVisible(false);
						b.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								text2.setVisible(false);
								text3.setVisible(false);
								uploadJSON(companyName, msg);
								progressBar.setVisible(false);
								progressBar.setValue(0);

							}

						});

					}

					break;

				}
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void uploadEmployeeCSV(String CompanyName, String CollectionName) throws NumberFormatException {

		try {

			CompanyName = CompanyName.toLowerCase();
			MongoClientURI uri = new MongoClientURI(
					"" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/" + CompanyName
							+ "?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true");
			MongoClient mongoClient = new MongoClient(uri);
			MongoDatabase database = mongoClient.getDatabase(CompanyName);
			MongoCollection<Document> collection = database.getCollection(CollectionName);

			// convert CSV directly
			try {
				String file = fileupload();
				List<Employee> beans = new CsvToBeanBuilder(new FileReader(file))
						// we ask if the file contians headers upon radial selection it will skip first
						// header line
						.withType(Employee.class).withSkipLines(1).build().parse();

				if (beans.get(0).getId() != 1) {
					beans = new CsvToBeanBuilder(new FileReader(file))
							// we ask if the file contians headers upon radial selection it will skip first
							// header line
							.withType(Employee.class).withSkipLines(0).build().parse();
					for (int x = 0; x < beans.size(); x++) {

						Document doc = new Document("id", beans.get(x).getId());
						doc.append("first name", Encrypt.encryptData(beans.get(x).getFirstName().toUpperCase()));
						doc.append("last name", Encrypt.encryptData(beans.get(x).getLastName().toUpperCase()));
						doc.append("hire year", Encrypt.encryptData(beans.get(x).getHireYear().toUpperCase()));
						doc.append("ssn", Encrypt.encryptData(beans.get(x).getSSN().replace("-", "").toUpperCase()));
						doc.append("occupation", Encrypt.encryptData(beans.get(x).getOccupation().toUpperCase()));
						collection.insertOne(doc);
					}
				} else {

					for (int x = 0; x < beans.size(); x++) {

						Document doc = new Document("id", beans.get(x).getId());
						doc.append("first name", Encrypt.encryptData(beans.get(x).getFirstName().toUpperCase()));
						doc.append("last name", Encrypt.encryptData(beans.get(x).getLastName().toUpperCase()));
						doc.append("hire year", Encrypt.encryptData(beans.get(x).getHireYear().toUpperCase()));
						doc.append("ssn", Encrypt.encryptData(beans.get(x).getSSN().replace("-", "").toUpperCase()));
						doc.append("occupation", Encrypt.encryptData(beans.get(x).getOccupation().toUpperCase()));
						collection.insertOne(doc);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			progressBar.setValue(300);

			mongoClient.close();
			JOptionPane.showMessageDialog(null, "CSV Upload Complete");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void uploadPropertyCSV(String CompanyName, String CollectionName) throws NumberFormatException {

		try {

			CompanyName = CompanyName.toLowerCase();
			MongoClientURI uri = new MongoClientURI(
					"" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/" + CompanyName
							+ "?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true");
			MongoClient mongoClient = new MongoClient(uri);
			MongoDatabase database = mongoClient.getDatabase(CompanyName);
			MongoCollection<Document> collection = database.getCollection(CollectionName);

			// convert CSV directly
			try {
				String file = fileupload();
				List<Property> beans = new CsvToBeanBuilder(new FileReader(file))
						// we ask if the file contians headers upon radial selection it will skip first
						// header line
						.withType(Property.class).withSkipLines(1).build().parse();

				if (beans.get(0).getId() != 1) {
					beans = new CsvToBeanBuilder(new FileReader(file))
							// we ask if the file contians headers upon radial selection it will skip first
							// header line
							.withType(Property.class).withSkipLines(0).build().parse();
					for (int x = 0; x < beans.size(); x++) {

						Document doc = new Document("id", beans.get(x).getId());
						doc.append("title", Encrypt.encryptData(beans.get(x).getTitle().toUpperCase()));
						doc.append("cost", Encrypt.encryptData(beans.get(x).getCost().toUpperCase()));
						doc.append("location", Encrypt.encryptData(beans.get(x).getLocation().toUpperCase()));
						collection.insertOne(doc);
					}
				} else {

					for (int x = 0; x < beans.size(); x++) {

						Document doc = new Document("id", beans.get(x).getId());
						doc.append("property name", Encrypt.encryptData(beans.get(x).getTitle().toUpperCase()));
						doc.append("cost", Encrypt.encryptData(beans.get(x).getCost().toUpperCase()));
						doc.append("location", Encrypt.encryptData(beans.get(x).getLocation().toUpperCase()));
						collection.insertOne(doc);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			progressBar.setValue(300);
			mongoClient.close();
			JOptionPane.showMessageDialog(null, "CSV Upload Complete");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void uploadProductsCSV(String CompanyName, String CollectionName) throws NumberFormatException {

		try {

			CompanyName = CompanyName.toLowerCase();
			MongoClientURI uri = new MongoClientURI(
					"" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/" + CompanyName
							+ "?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true");
			MongoClient mongoClient = new MongoClient(uri);
			MongoDatabase database = mongoClient.getDatabase(CompanyName);
			MongoCollection<Document> collection = database.getCollection(CollectionName);

			// convert CSV directly
			try {
				String file = fileupload();
				List<ProductServices> beans = new CsvToBeanBuilder(new FileReader(file))
						// we ask if the file contians headers upon radial selection it will skip first
						// header line
						.withType(ProductServices.class).withSkipLines(1).build().parse();

				if (beans.get(0).getId() != 1) {
					beans = new CsvToBeanBuilder(new FileReader(file))
							// we ask if the file contians headers upon radial selection it will skip first
							// header line
							.withType(ProductServices.class).withSkipLines(0).build().parse();
					for (int x = 0; x < beans.size(); x++) {

						Document doc = new Document("id", beans.get(x).getId());
						doc.append("product name", Encrypt.encryptData(beans.get(x).getTitle().toUpperCase()));
						doc.append("cost", Encrypt.encryptData(beans.get(x).getCost().toUpperCase()));
						doc.append("category", Encrypt.encryptData(beans.get(x).getCategory().toUpperCase()));
						doc.append("supplier", Encrypt.encryptData(beans.get(x).getSupplier().toUpperCase()));
						collection.insertOne(doc);
					}
				} else {

					for (int x = 0; x < beans.size(); x++) {

						Document doc = new Document("id", beans.get(x).getId());
						doc.append("product name", Encrypt.encryptData(beans.get(x).getTitle().toUpperCase()));
						doc.append("cost", Encrypt.encryptData(beans.get(x).getCost().toUpperCase()));
						doc.append("category", Encrypt.encryptData(beans.get(x).getCategory().toUpperCase()));
						doc.append("supplier", Encrypt.encryptData(beans.get(x).getSupplier().toUpperCase()));
						collection.insertOne(doc);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			progressBar.setValue(300);
			mongoClient.close();
			JOptionPane.showMessageDialog(null, "CSV Upload Complete");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void uploadServiceCSV(String CompanyName, String CollectionName) throws NumberFormatException {

		try {

			CompanyName = CompanyName.toLowerCase();
			MongoClientURI uri = new MongoClientURI(
					"" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/" + CompanyName
							+ "?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true");
			MongoClient mongoClient = new MongoClient(uri);
			MongoDatabase database = mongoClient.getDatabase(CompanyName);
			MongoCollection<Document> collection = database.getCollection(CollectionName);

			// convert CSV directly
			try {
				String file = fileupload();
				List<ProductServices> beans = new CsvToBeanBuilder(new FileReader(file))
						// we ask if the file contians headers upon radial selection it will skip first
						// header line
						.withType(ProductServices.class).withSkipLines(1).build().parse();

				if (beans.get(0).getId() != 1) {
					beans = new CsvToBeanBuilder(new FileReader(file))
							// we ask if the file contians headers upon radial selection it will skip first
							// header line
							.withType(ProductServices.class).withSkipLines(0).build().parse();
					for (int x = 0; x < beans.size(); x++) {

						Document doc = new Document("id", beans.get(x).getId());
						doc.append("service name", Encrypt.encryptData(beans.get(x).getTitle().toUpperCase()));
						doc.append("cost", Encrypt.encryptData(beans.get(x).getCost().toUpperCase()));
						doc.append("category", Encrypt.encryptData(beans.get(x).getCategory().toUpperCase()));
						collection.insertOne(doc);
					}
				} else {

					for (int x = 0; x < beans.size(); x++) {

						Document doc = new Document("id", beans.get(x).getId());
						doc.append("service name", Encrypt.encryptData(beans.get(x).getTitle().toUpperCase()));
						doc.append("cost", Encrypt.encryptData(beans.get(x).getCost().toUpperCase()));
						doc.append("category", Encrypt.encryptData(beans.get(x).getCategory().toUpperCase()));
						collection.insertOne(doc);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			progressBar.setValue(300);
			mongoClient.close();
			JOptionPane.showMessageDialog(null, "CSV Upload Complete");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void uploadFinancialHoldingsCSV(String CompanyName, String CollectionName)
			throws NumberFormatException {

		try {

			CompanyName = CompanyName.toLowerCase();
			MongoClientURI uri = new MongoClientURI(
					"" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/" + CompanyName
							+ "?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true");
			MongoClient mongoClient = new MongoClient(uri);
			MongoDatabase database = mongoClient.getDatabase(CompanyName);
			MongoCollection<Document> collection = database.getCollection(CollectionName);

			// convert CSV directly
			try {
				String file = fileupload();
				List<FinancialHoldings> beans = new CsvToBeanBuilder(new FileReader(file))
						// we ask if the file contians headers upon radial selection it will skip first
						// header line
						.withType(FinancialHoldings.class).withSkipLines(1).build().parse();

				if (beans.get(0).getId() != 1) {
					beans = new CsvToBeanBuilder(new FileReader(file))
							// we ask if the file contians headers upon radial selection it will skip first
							// header line
							.withType(FinancialHoldings.class).withSkipLines(0).build().parse();
					for (int x = 0; x < beans.size(); x++) {

						Document doc = new Document("id", beans.get(x).getId());
						doc.append("account name", Encrypt.encryptData(beans.get(x).getAccountName().toUpperCase()));
						doc.append("balance", Encrypt.encryptData(beans.get(x).getBalance()));
						doc.append("bank", Encrypt.encryptData(beans.get(x).getBankingInstitution().toUpperCase()));
						doc.append("account number",
								Encrypt.encryptData(beans.get(x).getAccountNumber().toUpperCase()));
						collection.insertOne(doc);
					}
				} else {

					for (int x = 0; x < beans.size(); x++) {

						Document doc = new Document("id", beans.get(x).getId());
						doc.append("account name", Encrypt.encryptData(beans.get(x).getAccountName().toUpperCase()));
						doc.append("balance", Encrypt.encryptData(beans.get(x).getBalance().toUpperCase()));
						doc.append("bank", Encrypt.encryptData(beans.get(x).getBankingInstitution().toUpperCase()));
						doc.append("account number",
								Encrypt.encryptData(beans.get(x).getAccountNumber().toUpperCase()));
						collection.insertOne(doc);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			progressBar.setValue(300);
			mongoClient.close();
			JOptionPane.showMessageDialog(null, "CSV Upload Complete");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void uploadJSON(String CompanyName, String CollectionName) {

		try {
			CompanyName = CompanyName.toLowerCase();
			MongoClientURI uri = new MongoClientURI(
					"" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/" + CompanyName
							+ "?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true");
			MongoClient mongoClient = new MongoClient(uri);
			MongoDatabase database = mongoClient.getDatabase(CompanyName);
			MongoCollection<Document> collection = database.getCollection(CollectionName);

			// convert JSON to DBObject directly

			int count = 0;
			int batch = 100;

			List<InsertOneModel<Document>> docs = new ArrayList<>();

			try (BufferedReader br = new BufferedReader(new FileReader(fileupload()))) {
				String line;
				while ((line = br.readLine()) != null) {
					docs.add(new InsertOneModel<>(Document.parse(line.toLowerCase())));

					count++;

					if (count == batch) {
						collection.bulkWrite(docs, new BulkWriteOptions().ordered(false));
						docs.clear();
						count = 0;
					}

				}

				// do some stuffs here
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				progressBar.setValue(300);
				JOptionPane.showMessageDialog(null, "JSON Upload Complete");

			} catch (Exception e) {
				e.printStackTrace();
			}
			mongoClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

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
			bg.clearSelection();
			csvList.setSelectedIndex(0);
			progressBar.setValue(40);
			progressBar.setVisible(true);
			singleFilePanel.revalidate();
			JOptionPane.showMessageDialog(null, "Now Uploading: " + chooser.getSelectedFile().getName());

		}

		return chooser.getSelectedFile().getAbsolutePath();
	}

	// pass Jprogressbar as a parameter
	public static void insertEmployee(String CompanyName, String firstname, String lastname, String hireYear,
			String ssn, String occupation) {
		ssn = ssn.replace("-", "");
		Employee emp = new Employee(firstname, lastname, hireYear, ssn, occupation);

		progressBar1.setValue(300);
		progressBar1.setVisible(true);

		try {

			// gathers information about the records being inserted and database information

			// gets access of the database using local host
			CompanyName = CompanyName.toLowerCase();
			MongoClientURI uri = new MongoClientURI(
					"" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/" + CompanyName
							+ "?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true");
			MongoClient mongoClient = new MongoClient(uri);
			MongoDatabase database = mongoClient.getDatabase(CompanyName);
			MongoCollection<Document> collection = database.getCollection("Employees");

			// creates the document to insert into the database
			Document test = new Document();
			test.append("id", (collection.count() + 1));
			test.append("first name", emp.getFirstName(true));
			test.append("last name", emp.getLastName(true));
			test.append("hire year", emp.getHireYear(true));
			test.append("ssn", emp.getSSN(true));
			test.append("occupation", emp.getOccupation(true));
			System.out.println(emp.toString());
			// adds the document to the database
			collection.insertOne(test);
			mongoClient.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Upload Complete");
	}

	public static void insertFinance(String CompanyName, String accountName, String Balance, String Bank, String accountNumber) {
		progressBar5.setValue(300);
		progressBar5.setVisible(true);
		FinancialHoldings fin = new FinancialHoldings(accountName, Balance, Bank,accountNumber);
		try {

			// gathers information about the records being inserted and database information

			// gets access of the database using local host
			CompanyName = CompanyName.toLowerCase();
			MongoClientURI uri = new MongoClientURI(
					"" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/" + CompanyName
							+ "?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true");
			MongoClient mongoClient = new MongoClient(uri);
			MongoDatabase database = mongoClient.getDatabase(CompanyName);
			MongoCollection<Document> collection = database.getCollection("Financial Holdings");

			// creates the document to insert into the database
			Document test = new Document();
			test.append("id", (collection.count() + 1));
			test.append("account name", fin.getAccountName(true));
			
			test.append("balance", fin.getBalance(true));
			test.append("bank", fin.getBankingInstitution(true));
			test.append("account number", fin.getAccountNumber(true));
			// adds the document to the database
			collection.insertOne(test);
			mongoClient.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Upload Complete");
	}

	public static void insertProperty(String CompanyName, String propertyName, String cost, String location) {
		progressBar2.setValue(300);
		progressBar2.setVisible(true);
		Property prop = new Property(propertyName, cost, location);
		try {

			// gathers information about the records being inserted and database information

			// gets access of the database using local host
			CompanyName = CompanyName.toLowerCase();
			MongoClientURI uri = new MongoClientURI(
					"" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/" + CompanyName
							+ "?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true");
			MongoClient mongoClient = new MongoClient(uri);
			MongoDatabase database = mongoClient.getDatabase(CompanyName);
			MongoCollection<Document> collection = database.getCollection("Properties");

			// creates the document to insert into the database
			Document test = new Document();
			test.append("id", (collection.count() + 1));
			test.append("property name", prop.getTitle(true));
			test.append("cost", prop.getCost(true));
			test.append("location", prop.getLocation(true));

			// adds the document to the database
			collection.insertOne(test);
			mongoClient.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Upload Complete");
	}

	public static boolean insertProduct(String CompanyName, String productName, String cost, String category,
			String supplier) {
		ProductServices prod = new ProductServices(productName, cost, category, supplier);
		progressBar4.setValue(300);
		progressBar4.setVisible(true);

		try {

			// gathers information about the records being inserted and database information

			// gets access of the database using local host
			CompanyName = CompanyName.toLowerCase();
			MongoClientURI uri = new MongoClientURI(
					"" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/" + CompanyName
							+ "?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true");
			MongoClient mongoClient = new MongoClient(uri);
			MongoDatabase database = mongoClient.getDatabase(CompanyName);
			MongoCollection<Document> collection = database.getCollection("Products");

			// creates the document to insert into the database
			Document test = new Document();
			test.append("id", (collection.count() + 1));
			test.append("product name", prod.getTitle(true));
			test.append("cost", prod.getCost(true));
			test.append("category", prod.getCategory(true));
			test.append("supplier", prod.getSupplier(true));

			// adds the document to the database
			collection.insertOne(test);
			mongoClient.close();
			JOptionPane.showMessageDialog(null, "Upload Complete");
			return true;

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

	}

	public static void insertService(String CompanyName, String serviceName, String cost, String category) {

		progressBar3.setValue(300);
		progressBar3.setVisible(true);
		ProductServices serv = new ProductServices(serviceName, cost, category);

		try {

			// gathers information about the records being inserted and database information

			// gets access of the database using local host
			CompanyName = CompanyName.toLowerCase();
			MongoClientURI uri = new MongoClientURI(
					"" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/" + CompanyName
							+ "?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true");
			MongoClient mongoClient = new MongoClient(uri);
			MongoDatabase database = mongoClient.getDatabase(CompanyName);
			MongoCollection<Document> collection = database.getCollection("Services");

			// creates the document to insert into the database
			Document test = new Document();
			test.append("id", (collection.count() + 1));
			test.append("service name", serv.getTitle(true));
			test.append("cost", serv.getCost(true));
			test.append("category", serv.getCategory(true));

			// adds the document to the database
			collection.insertOne(test);
			mongoClient.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Upload Complete");
	}

	public static void main(String args[]) {
		try {

			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			UIManager.getLookAndFeelDefaults().put("Button.background", Color.black);
			UIManager.getLookAndFeelDefaults().put("Button.textForeground", new Color(255, 255, 255));
			UIManager.getLookAndFeelDefaults().put("Label.textForeground", new Color(255, 255, 255));
			UIManager.getLookAndFeelDefaults().put("TextField.background", Color.lightGray);
			UIManager.getLookAndFeelDefaults().put("Panel.background", Color.gray);
			UIManager.getLookAndFeelDefaults().put("ComboBox.background", Color.black);
			UIManager.getLookAndFeelDefaults().put("ComboBox.textForeground", Color.white);
		} catch (Exception evt) {
		}
		// Create an instance of the test application
		GUI mainFrame = new GUI("");
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);

	}
}
