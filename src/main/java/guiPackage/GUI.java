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

import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.opencsv.bean.CsvToBeanBuilder;

import Encryption.Encrypt;
import csvFiles.Employee;
import csvFiles.FinancialHoldings;
import csvFiles.ProductServices;
import csvFiles.Property;
import database.Delete;
import database.Find;
import database.Update;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	// splits pane vertically
	private JSplitPane splitPaneV;
	// splits pane horizontally
	private JSplitPane splitPaneH;
	// Menu dashboard
	private JPanel directory;
	// bottom panel that is used for results
	private JPanel resultsPanel;
	// hardcode entry tabbed page
	private JTabbedPane manualInsertPanes;
	// pane2 for search method
	private JTabbedPane findRecordsPanes;
	// pane 3 for updating the document in the cloud
	private JTabbedPane updatingRecordsPanes;
	// pane 3 for deleting the document in the cloud
	private JTabbedPane deletingRecordsPanes;
	// blank home page
	private JPanel temp = new JPanel();
	// single file upload
	private static JPanel singleFilePanel;
	// elements within single file upload
	JRadioButton csvRadial, jsonRadial;
	JButton fileUploadButton;
	private static String[] CSV = { "--Select--", "Employees", "Properties", "Products", "Services",
			"Financial Holdings" };
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static JComboBox csvList = new JComboBox(CSV);
	//instruction labels
	private static JLabel text2;
	private static JLabel text3;
	//radial group holder for json or csv upload page
	private static ButtonGroup bg;
	private static JProgressBar progressBar;
	// companyName passed through from login
	private String companyName;
	// type pof upload drop down box here
	String[] messages = { "Select", "Single File Upload", "Manual File Entry" };
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox messageList = new JComboBox(messages);
	// go button on insert record panel
	JButton goButton = new JButton("GO");
	// panel where they decide what type of insert
	JPanel insertRecords;
	// Employee Panel in manualInsertPanes
	private static JPanel panelEmployee;
	// Financial Panel in manualInsertPanes
	private static JPanel panelFinancial;
	// Properties Panel in manualInsertPanes
	private static JPanel panelProperties;
	// Service Panel in manualInsertPanes
	private static JPanel panelService;
	// Product Panel in manualInsertPanes
	private static JPanel panelProduct;
	// panel which holds everything from splitPaneH and is split vertically
	private static JPanel topPanel;
	// JProgress Bars for each Panel
	private static JProgressBar progressBar1 = new JProgressBar();
	private static JProgressBar progressBar2 = new JProgressBar();
	private static JProgressBar progressBar3 = new JProgressBar();
	private static JProgressBar progressBar4 = new JProgressBar();
	private static JProgressBar progressBar5 = new JProgressBar();

	public String getCompanyName() {
		return this.companyName;
	}

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
		// image added
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
		// manual inserts
		createResultsPanel();
		createEmployeeTab();
		createFinancialTab();
		createPropertiesTab();
		createServiceTab();
		createProductTab();
		// manual insert panels
		showManualInsert();
		// find records panels
		showFind();
		// update records panels
		showUpdate();
		// delete record panels
		showDelete();
		// single file upload panel
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

	// creation of the static directory on the left hand side
	public void createDirectory() {

		directory = new JPanel();
		directory.setLayout(new GridLayout(5, 1));

		insertRecords = new JPanel();
		insertRecords.setLayout(null);
		insertRecords.remove(messageList);
		messageList.setForeground(Color.white);
		JButton insertButton = new JButton("INSERT RECORDS");
		JButton backButton = new JButton("BACK");
		JButton updateButton = new JButton("UPDATE");
		JButton deleteButton = new JButton("DELETE");
		JButton findButton = new JButton("FIND");
		JButton logoutButton = new JButton("LOGOUT");

		directory.remove(backButton);

		findButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				directory.add(backButton);
				directory.remove(updateButton);
				directory.remove(deleteButton);
				directory.remove(insertButton);
				directory.remove(logoutButton);
				topPanel.removeAll();
				topPanel.revalidate();
				resultsPanel.removeAll();
				setTitle("Find Records - CompanyVault.exe");
				splitPaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
				splitPaneH.setEnabled(false);
				splitPaneV.setEnabled(false);

				splitPaneH.setLeftComponent(directory);
				splitPaneH.setRightComponent(findRecordsPanes);
				splitPaneH.setDividerLocation(130);
				Border blackline = BorderFactory.createLineBorder(Color.black);
				splitPaneV.setDividerLocation(300);
				resultsPanel.setBorder(blackline);
				splitPaneV.setLeftComponent(splitPaneH);
				splitPaneV.setRightComponent(resultsPanel);
				topPanel.add(splitPaneV, BorderLayout.CENTER);
				topPanel.revalidate();
				revalidate();
			}
		});

		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				directory.add(backButton);

				directory.remove(logoutButton);
				directory.remove(findButton);

				directory.remove(deleteButton);
				directory.remove(insertButton);

				resultsPanel.removeAll();
				setTitle("Update Records - CompanyVault.exe");
				splitPaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
				splitPaneH.setEnabled(false);
				splitPaneV.setEnabled(false);
				splitPaneH.setLeftComponent(directory);
				splitPaneH.setRightComponent(updatingRecordsPanes);
				Border blackline = BorderFactory.createLineBorder(Color.black);
				splitPaneH.setDividerLocation(130);
				splitPaneV.setDividerLocation(300);
				resultsPanel.setBorder(blackline);
				topPanel.removeAll();
				topPanel.revalidate();

				splitPaneV.setLeftComponent(splitPaneH);
				splitPaneV.setRightComponent(resultsPanel);
				topPanel.add(splitPaneV);
				topPanel.revalidate();
			}
		});
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				directory.add(backButton);

				directory.remove(findButton);

				directory.remove(updateButton);

				directory.remove(insertButton);

				directory.remove(logoutButton);

				resultsPanel.removeAll();
				topPanel.removeAll();
				setTitle("Delete Records - CompanyVault.exe");
				splitPaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
				splitPaneH.setEnabled(false);
				splitPaneV.setEnabled(false);
				splitPaneH.setLeftComponent(directory);
				splitPaneH.setRightComponent(deletingRecordsPanes);
				splitPaneH.setDividerLocation(130);
				Border blackline = BorderFactory.createLineBorder(Color.black);
				splitPaneV.setDividerLocation(300);
				resultsPanel.setBorder(blackline);
				topPanel.removeAll();
				topPanel.revalidate();

				splitPaneV.setLeftComponent(splitPaneH);
				splitPaneV.setRightComponent(resultsPanel);
				topPanel.add(splitPaneV, BorderLayout.CENTER);
				topPanel.revalidate();
			}
		});
		directory.remove(backButton);
		directory.add(insertButton);
		directory.add(updateButton);
		directory.add(deleteButton);
		directory.add(findButton);
		directory.add(logoutButton);
		// directory.add(homeButton);
		ArrayList<JTabbedPane> list = new ArrayList<JTabbedPane>();
		list.add(manualInsertPanes);
		list.add(findRecordsPanes);
		list.add(updatingRecordsPanes);
		list.add(deletingRecordsPanes);
		ArrayList<JButton> directory1 = new ArrayList<JButton>();
		directory1.add(insertButton);
		directory1.add(updateButton);
		directory1.add(deleteButton);
		directory1.add(findButton);
		directory1.add(logoutButton);
		directory1.remove(backButton);
		for (JButton l : directory1) {

			l.setBackground(Color.BLACK);

		}

		directory.add(logoutButton);
		logoutButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					UIManager.getLookAndFeelDefaults().put("Button.background", Color.black);
					UIManager.getLookAndFeelDefaults().put("Button.textForeground", new Color(255, 255, 255));

				} catch (Exception evt) {
				}
				logIn.run();

			}
		});

		backButton.addActionListener(new ActionListener() {

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
										splitPaneH.setRightComponent(manualInsertPanes);

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

				directory.add(backButton);
				directory.remove(logoutButton);

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

	public void createResultsPanel() {
		resultsPanel = new JPanel();
		resultsPanel.setLayout(new BorderLayout());
		resultsPanel.setPreferredSize(new Dimension(400, 100));
		resultsPanel.setMinimumSize(new Dimension(50, 50));
		resultsPanel.add(new JLabel("Results:"), BorderLayout.NORTH);
		resultsPanel.add(new JScrollPane(), BorderLayout.CENTER);
	}

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
					database.Insert.insertEmployee(companyName, lFirstName.getText(), lLastName.getText(),
							lHireYear.getText(), ssn, lOccupation.getText(), progressBar1);
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
		JTextField lAccountNumber = new JTextField(20);

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
					database.Insert.insertFinance(companyName, lAccountName.getText(), lBalance.getText(),
							lBank.getText(), lAccountNumber.getText(), progressBar5);
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

					database.Insert.insertProperty(companyName, lPropertyName.getText(), lCost.getText(),
							lLocation.getText(), progressBar2);
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

					database.Insert.insertService(companyName, lServiceName.getText(), lCost.getText(),
							lCategory.getText(), progressBar3);
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
					database.Insert.insertProduct(companyName, lProductName.getText(), lCost.getText(),
							lCategory.getText(), lSupplier.getText(), progressBar4);
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

	public void showManualInsert() {
		manualInsertPanes = new JTabbedPane();
		manualInsertPanes.addTab("Employees", panelEmployee);
		manualInsertPanes.addTab("Properties", panelProperties);
		manualInsertPanes.addTab("Products", panelProduct);
		manualInsertPanes.addTab("Services", panelService);
		manualInsertPanes.addTab("Financial Holdings", panelFinancial);
		manualInsertPanes.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				resultsPanel.removeAll();
			}
		});

	}

	public void showFind() {
		findRecordsPanes = new JTabbedPane();

		findRecordsPanes.addTab("Employees", Find.searchEmployee(resultsPanel, companyName));
		findRecordsPanes.addTab("Properties", Find.searchProperty(resultsPanel, companyName));
		findRecordsPanes.addTab("Products", Find.searchProduct(resultsPanel, companyName));
		findRecordsPanes.addTab("Services", Find.searchService(resultsPanel, companyName));
		findRecordsPanes.addTab("Financial Holdings", Find.searchFinancials(resultsPanel, companyName));
		findRecordsPanes.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				resultsPanel.removeAll();
				resultsPanel.revalidate();
			}
		});

	}

	public void showUpdate() {
		updatingRecordsPanes = new JTabbedPane();
		updatingRecordsPanes.addTab("Employees", Update.updateEmployee(resultsPanel, companyName));
		updatingRecordsPanes.addTab("Properties", Update.updateProperty(resultsPanel, companyName));
		updatingRecordsPanes.addTab("Products", Update.updateProduct(resultsPanel, companyName));
		updatingRecordsPanes.addTab("Services", Update.updateService(resultsPanel, companyName));
		updatingRecordsPanes.addTab("Financial Holdings", Update.updateFinancials(resultsPanel, companyName));
		updatingRecordsPanes.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				resultsPanel.removeAll();
				resultsPanel.revalidate();
			}
		});

	}

	public void showDelete() {
		deletingRecordsPanes = new JTabbedPane();
		deletingRecordsPanes.addTab("Employees", Delete.deleteEmployee(resultsPanel, companyName));
		deletingRecordsPanes.addTab("Properties", Delete.deleteProperty(resultsPanel, companyName));
		deletingRecordsPanes.addTab("Products", Delete.deleteProduct(resultsPanel, companyName));
		deletingRecordsPanes.addTab("Services", Delete.deleteService(resultsPanel, companyName));
		deletingRecordsPanes.addTab("Financial Holdings", Delete.deleteFinancials(resultsPanel, companyName));
		deletingRecordsPanes.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				resultsPanel.removeAll();
				resultsPanel.revalidate();
			}
		});
	}

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

		csvRadial = new JRadioButton("CSV");
		csvRadial.setBounds(180, 70, 100, 30);
		jsonRadial = new JRadioButton("JSON");
		jsonRadial.setBounds(180, 120, 100, 30);
		bg = new ButtonGroup();
		bg.add(csvRadial);
		bg.add(jsonRadial);

		fileUploadButton = new JButton("Select File");
		JLabel text1 = new JLabel();

		text1.setText("2. Please select the collection where the file will be stored");
		text1.setBounds(140, 170, 500, 10);
		csvList.setBounds(180, 205, 150, 40);
		csvList.setBorder(null);

		fileUploadButton.setBounds(195, 260, 120, 30);
		text2 = new JLabel();
		text3 = new JLabel();

		text2.setBounds(140, 300, 500, 12);
		text3.setText("id, firstname, lastname, hireyear");
		text3.setBounds(140, 330, 500, 12);
		csvList.addActionListener(new collectionAction());
		jsonRadial.addActionListener(new ActionJson());
		csvRadial.addActionListener(new ActionCsv());
		csvList.setSelectedIndex(0);
		singleFilePanel.add(text);
		singleFilePanel.add(csvRadial);
		singleFilePanel.add(jsonRadial);

		singleFilePanel.add(fileUploadButton);
		singleFilePanel.add(text1);
		singleFilePanel.add(csvList);

		singleFilePanel.add(text2);
		singleFilePanel.add(text3);
		text2.setVisible(false);
		text3.setVisible(false);

	}
	//action listeners for upload type for json
	class ActionJson implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			csvList.setSelectedIndex(0);
			text2.setVisible(false);
			text3.setVisible(false);

		}
	}
	//for csv
	class ActionCsv implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			csvList.setSelectedIndex(0);

		}
	}

	// select file button which will change upon csv or json selector to which file
	// will be chosen
	//here which ever dropdown selection is made is to where the the file is sent
	class collectionAction implements ActionListener {
		ArrayList<JButton> btn = new ArrayList<JButton>();

		@Override
		public void actionPerformed(ActionEvent e) {
			btn.add(fileUploadButton);
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
					if (csvRadial.isSelected()) {
						text2.setText("With CSV Ensure columns for " + msg + " are in: ");
						text3.setText("id, firstname, lastname, hireyear, ssn, occupation");
						text2.setVisible(true);
						text3.setVisible(true);
						fileUploadButton.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								text2.setVisible(false);
								text3.setVisible(false);
								database.Insert.uploadEmployeeCSV(companyName, msg, progressBar);
								progressBar.setVisible(false);
								progressBar.setValue(0);

							}

						});

					}
					if (jsonRadial.isSelected()) {
						

						text2.setVisible(false);
						text3.setVisible(false);
						fileUploadButton.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								text2.setVisible(false);
								text3.setVisible(false);
								database.Insert.uploadJSONEmployee(companyName);
								splitPaneH.setLeftComponent(directory);
								splitPaneH.setRightComponent(temp);
								progressBar.setVisible(false);
								progressBar.setValue(0);

							}

						});

					}

					break;
				case "Properties":
					if (csvRadial.isSelected()) {
						text2.setText("With CSV Ensure columns for " + msg + " are in: ");
						text3.setText("id, propertyname, cost, location");
						text2.setVisible(true);
						text3.setVisible(true);
						fileUploadButton.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								text2.setVisible(false);
								text3.setVisible(false);
								database.Insert.uploadPropertyCSV(companyName, msg, progressBar);
								progressBar.setVisible(false);
								progressBar.setValue(0);

							}

						});

					}
					if (jsonRadial.isSelected()) {

						text2.setVisible(false);
						text3.setVisible(false);
						fileUploadButton.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								text2.setVisible(false);
								text3.setVisible(false);
								database.Insert.uploadJSONProperty(companyName);
								progressBar.setVisible(false);
								progressBar.setValue(0);

							}

						});

					}

					break;
				case "Products":
					if (csvRadial.isSelected()) {
						text2.setText("With CSV Ensure columns for " + msg + " are in: ");
						text3.setText("id, productname, cost, category, supplier");
						text2.setVisible(true);
						text3.setVisible(true);
						fileUploadButton.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								text2.setVisible(false);
								text3.setVisible(false);
								database.Insert.uploadProductsCSV(companyName, msg, progressBar);
								progressBar.setVisible(false);
								progressBar.setValue(0);
							}

						});

					}
					if (jsonRadial.isSelected()) {

						text2.setVisible(false);
						text3.setVisible(false);
						fileUploadButton.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								text2.setVisible(false);
								text3.setVisible(false);
								database.Insert.uploadJSONProduct(companyName);

								progressBar.setVisible(false);
								progressBar.setValue(0);
							}

						});

					}
					break;
				case "Services":
					if (csvRadial.isSelected()) {
						text2.setText("With CSV Ensure columns for " + msg + " are in: ");
						text3.setText("id, servicename, cost, category");
						text2.setVisible(true);
						text3.setVisible(true);
						fileUploadButton.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								text2.setVisible(false);
								text3.setVisible(false);
								database.Insert.uploadServiceCSV(companyName, msg, progressBar);
								progressBar.setVisible(false);
								progressBar.setValue(0);
							}

						});

					}
					if (jsonRadial.isSelected()) {

						text2.setVisible(false);
						text3.setVisible(false);
						fileUploadButton.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								text2.setVisible(false);
								text3.setVisible(false);
								database.Insert.uploadJSONService(companyName);
								progressBar.setVisible(false);
								progressBar.setValue(0);

							}

						});

					}
					break;

				case "Financial Holdings":
					if (csvRadial.isSelected()) {
						text2.setText("With CSV Ensure columns for " + msg + " are in: ");
						text3.setText("accountid, accountname, balance, bank");
						text2.setVisible(true);
						text3.setVisible(true);
						fileUploadButton.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								text2.setVisible(false);
								text3.setVisible(false);
								database.Insert.uploadFinancialHoldingsCSV(companyName, msg, progressBar);
								progressBar.setVisible(false);
								progressBar.setValue(0);
							}

						});

					}
					if (jsonRadial.isSelected()) {

						text2.setVisible(false);
						text3.setVisible(false);
						fileUploadButton.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								text2.setVisible(false);
								text3.setVisible(false);
								database.Insert.uploadJSONFinancials(companyName);
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
	//file upload method gets absolute path of file choosen
	public static String fileUpload() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
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

}
