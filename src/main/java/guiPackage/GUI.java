package guiPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.InsertOneModel;
import com.opencsv.bean.CsvToBeanBuilder;

import Property.Property;
import database.Employee;
import database.ProductServices;
import database.financialHoldings;

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
	private static JPanel singleFilePanel;
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

	String[] messages = { "Select", "Single File Upload", "Manual File Entry" };
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox messageList = new JComboBox(messages);
	JButton goButton = new JButton("GO");
	JPanel insertRecords;
	

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

										splitPaneH.setRightComponent(singleFilePanel);
										pack();

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
		pane.addTab("Products", new JPanel());
		pane.addTab("Services", new JPanel());
		pane.addTab("Financial Holdings", new JPanel());
		pane.setBackground(Color.WHITE);
		pane.setForeground(Color.BLACK);
	}

	JRadioButton rb1, rb2;
	JButton b;
	static String[] CSV = { "--Select--","Employees", "Properties", "Products","Services", "Financial Holdings" };
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static JComboBox csvList = new JComboBox(CSV);
	static JLabel text2;
	static JLabel text3;
	static JLabel loader;
	static ButtonGroup bg;
	static JProgressBar progressBar ;
	public void singleFilePanel() {
		singleFilePanel = new JPanel();
		singleFilePanel.setLayout(null);
		JLabel text = new JLabel();
		progressBar = new JProgressBar();
		 progressBar.setValue(0);
		 progressBar.setBounds(130, 360, 300, 30);
		 progressBar.setBackground(Color.green);
		 progressBar.setVisible(false);
		
		 singleFilePanel.add(progressBar);
		text.setText("Please select the type of file(CSV or JSON)");
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

		text1.setText("Please select the collection where the file will be stored");
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
		singleFilePanel.add(rb2);
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
						text2.setText("With CSV Ensure columns for "+msg+" are in: ");
						text3.setText("id, firstname, lastname, hireyear");
						text2.setVisible(true);
						text3.setVisible(true);
						b.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								text2.setVisible(false);
								text3.setVisible(false);
								uploadEmployeeCSV(companyName,msg);
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
								uploadJSON(companyName,msg);
								progressBar.setVisible(false);
								 progressBar.setValue(0);

							}

						});
						
					}
					
					
					break;
				case "Properties":
					if (rb1.isSelected()) {
						text2.setText("With CSV Ensure columns for "+msg+" are in: ");
						text3.setText("id, title, cost, location");
						text2.setVisible(true);
						text3.setVisible(true);
						b.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								text2.setVisible(false);
								text3.setVisible(false);
								uploadPropertyCSV(companyName,msg);
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
								uploadJSON(companyName,msg);
								progressBar.setVisible(false);
								 progressBar.setValue(0);

							}

						});
						
					}
					
					break;
				case "Products":
					if (rb1.isSelected()) {
						text2.setText("With CSV Ensure columns for "+msg+" are in: ");
						text3.setText("id, title, cost, category, supplier");
						text2.setVisible(true);
						text3.setVisible(true);
						b.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								text2.setVisible(false);
								text3.setVisible(false);
								uploadProductsCSV(companyName,msg);
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
								uploadJSON(companyName,msg);
								
								progressBar.setVisible(false);
								 progressBar.setValue(0);
							}

						});
						
					}
					break;
				case "Services":
					if (rb1.isSelected()) {
						text2.setText("With CSV Ensure columns for "+msg+" are in: ");
						text3.setText("id, title, cost, category, supplier");
						text2.setVisible(true);
						text3.setVisible(true);
						b.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								text2.setVisible(false);
								text3.setVisible(false);
								uploadServiceCSV(companyName,msg);
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
								uploadJSON(companyName,msg);
								progressBar.setVisible(false);
								 progressBar.setValue(0);

							}

						});
						
					}
					break;

				case "Financial Holdings":
					if (rb1.isSelected()) {
						text2.setText("With CSV Ensure columns for "+msg+" are in: ");
						text2.setVisible(true);
						text3.setVisible(true);
						b.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								text2.setVisible(false);
								text3.setVisible(false);
								uploadFinancialHoldingsCSV(companyName,msg);
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
								uploadJSON(companyName,msg);
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
	 public static void uploadEmployeeCSV(String CompanyName, String CollectionName) throws NumberFormatException {

	        try {

	        	CompanyName= CompanyName.toLowerCase();
	        	MongoClientURI uri = new MongoClientURI("" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/"+CompanyName+"?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true" );
				MongoClient mongoClient = new MongoClient(uri);
				MongoDatabase database = mongoClient.getDatabase(CompanyName);			
				MongoCollection<Document> collection = database.getCollection( CollectionName);

	            // convert CSV  directly
				try{
					String file = fileupload();
					List<Employee> beans = new CsvToBeanBuilder(new FileReader(file))
							//we ask if the file contians headers upon radial selection it will skip first header line
			                .withType(Employee.class).withSkipLines(1)
			                .build()
			                .parse();
					
					if(beans.get(0).getId()!=1) {
						beans = new CsvToBeanBuilder(new FileReader(file))
								//we ask if the file contians headers upon radial selection it will skip first header line
				                .withType(Employee.class).withSkipLines(0)
				                .build()
				                .parse();
						for(int x = 0; x<beans.size(); x++) {
							
							Document doc = new Document("id",beans.get(x).getId());  
			                doc.append("first name",beans.get(x).getFirstName());
			                doc.append("last name",beans.get(x).getLastName());
			                doc.append("hire year",beans.get(x).getHireYear());
			                collection.insertOne(doc);  
						}
					}else {
						
						for(int x = 0; x<beans.size(); x++) {
							
							Document doc = new Document("id",beans.get(x).getId());  
			                doc.append("first name",beans.get(x).getFirstName());
			                doc.append("last name",beans.get(x).getLastName());
			                doc.append("hire year",beans.get(x).getHireYear());
			                collection.insertOne(doc);  
						}
					}
					

				  }catch (Exception e) {
			            e.printStackTrace();
			      }
			      try
                  {
                      Thread.sleep(100);
                  }
                  catch (InterruptedException e)
                  {
                      e.printStackTrace();
                  }
                  progressBar.setValue(300);
				mongoClient.close();
				JOptionPane.showMessageDialog(null, "CSV Upload Complete");
			
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        
	    }
	 public static void uploadPropertyCSV(String CompanyName, String CollectionName) throws NumberFormatException {

	        try {

	        	CompanyName= CompanyName.toLowerCase();
	        	MongoClientURI uri = new MongoClientURI("" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/"+CompanyName+"?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true" );
				MongoClient mongoClient = new MongoClient(uri);
				MongoDatabase database = mongoClient.getDatabase(CompanyName);			
				MongoCollection<Document> collection = database.getCollection( CollectionName);

	            // convert CSV  directly
				try{
					String file = fileupload();
					List<Property> beans = new CsvToBeanBuilder(new FileReader(file))
							//we ask if the file contians headers upon radial selection it will skip first header line
			                .withType(Property.class).withSkipLines(1)
			                .build()
			                .parse();
					
					if(beans.get(0).getId()!=1) {
						beans = new CsvToBeanBuilder(new FileReader(file))
								//we ask if the file contians headers upon radial selection it will skip first header line
				                .withType(Property.class).withSkipLines(0)
				                .build()
				                .parse();
						for(int x = 0; x<beans.size(); x++) {
							
							Document doc = new Document("id",beans.get(x).getId());  
			                doc.append("title",beans.get(x).getTitle());
			                doc.append("cost",beans.get(x).getCost());
			                doc.append("location",beans.get(x).getLocation());
			                collection.insertOne(doc);  
						}
					}else {
						
						for(int x = 0; x<beans.size(); x++) {
							
							Document doc = new Document("id",beans.get(x).getId());  
			                doc.append("title",beans.get(x).getTitle());
			                doc.append("cost",beans.get(x).getCost());
			                doc.append("location",beans.get(x).getLocation());
			                collection.insertOne(doc);  
						}
					}
					

				  }catch (Exception e) {
			            e.printStackTrace();
			      }
			      try
               {
                   Thread.sleep(100);
               }
               catch (InterruptedException e)
               {
                   e.printStackTrace();
               }
               progressBar.setValue(300);
				mongoClient.close();
				JOptionPane.showMessageDialog(null, "CSV Upload Complete");
			
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        
	    }
	 public static void uploadProductsCSV(String CompanyName, String CollectionName) throws NumberFormatException {

	        try {

	        	CompanyName= CompanyName.toLowerCase();
	        	MongoClientURI uri = new MongoClientURI("" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/"+CompanyName+"?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true" );
				MongoClient mongoClient = new MongoClient(uri);
				MongoDatabase database = mongoClient.getDatabase(CompanyName);			
				MongoCollection<Document> collection = database.getCollection( CollectionName);

	            // convert CSV  directly
				try{
					String file = fileupload();
					List<ProductServices> beans = new CsvToBeanBuilder(new FileReader(file))
							//we ask if the file contians headers upon radial selection it will skip first header line
			                .withType(ProductServices.class).withSkipLines(1)
			                .build()
			                .parse();
					
					if(beans.get(0).getId()!=1) {
						beans = new CsvToBeanBuilder(new FileReader(file))
								//we ask if the file contians headers upon radial selection it will skip first header line
				                .withType(ProductServices.class).withSkipLines(0)
				                .build()
				                .parse();
						for(int x = 0; x<beans.size(); x++) {
							
							Document doc = new Document("id",beans.get(x).getId());  
			                doc.append("product name",beans.get(x).getTitle());
			                doc.append("cost",beans.get(x).getCost());
			                doc.append("category",beans.get(x).getCategory());
			                doc.append("supplier",beans.get(x).getSupplier());
			                collection.insertOne(doc);  
						}
					}else {
						
						for(int x = 0; x<beans.size(); x++) {
							
							Document doc = new Document("id",beans.get(x).getId());  
			                doc.append("product name",beans.get(x).getTitle());
			                doc.append("cost",beans.get(x).getCost());
			                doc.append("category",beans.get(x).getCategory());
			                doc.append("supplier",beans.get(x).getSupplier());
						}
					}
					

				  }catch (Exception e) {
			            e.printStackTrace();
			      }
			      try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            progressBar.setValue(300);
				mongoClient.close();
				JOptionPane.showMessageDialog(null, "CSV Upload Complete");
			
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        
	    }
	 public static void uploadServiceCSV(String CompanyName, String CollectionName) throws NumberFormatException {

	        try {

	        	CompanyName= CompanyName.toLowerCase();
	        	MongoClientURI uri = new MongoClientURI("" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/"+CompanyName+"?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true" );
				MongoClient mongoClient = new MongoClient(uri);
				MongoDatabase database = mongoClient.getDatabase(CompanyName);			
				MongoCollection<Document> collection = database.getCollection( CollectionName);

	            // convert CSV  directly
				try{
					String file = fileupload();
					List<ProductServices> beans = new CsvToBeanBuilder(new FileReader(file))
							//we ask if the file contians headers upon radial selection it will skip first header line
			                .withType(ProductServices.class).withSkipLines(1)
			                .build()
			                .parse();
					
					if(beans.get(0).getId()!=1) {
						beans = new CsvToBeanBuilder(new FileReader(file))
								//we ask if the file contians headers upon radial selection it will skip first header line
				                .withType(ProductServices.class).withSkipLines(0)
				                .build()
				                .parse();
						for(int x = 0; x<beans.size(); x++) {
							
							Document doc = new Document("id",beans.get(x).getId());  
			                doc.append("service name",beans.get(x).getTitle());
			                doc.append("cost",beans.get(x).getCost());
			                doc.append("category",beans.get(x).getCategory());
			                collection.insertOne(doc);  
						}
					}else {
						
						for(int x = 0; x<beans.size(); x++) {
							
							Document doc = new Document("id",beans.get(x).getId());  
			                doc.append("service name",beans.get(x).getTitle());
			                doc.append("cost",beans.get(x).getCost());
			                doc.append("category",beans.get(x).getCategory());
						}
					}
					

				  }catch (Exception e) {
			            e.printStackTrace();
			      }
			      try
         {
             Thread.sleep(100);
         }
         catch (InterruptedException e)
         {
             e.printStackTrace();
         }
         progressBar.setValue(300);
				mongoClient.close();
				JOptionPane.showMessageDialog(null, "CSV Upload Complete");
			
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        
	    }
	 public static void uploadFinancialHoldingsCSV(String CompanyName, String CollectionName) throws NumberFormatException {

	        try {

	        	CompanyName= CompanyName.toLowerCase();
	        	MongoClientURI uri = new MongoClientURI("" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/"+CompanyName+"?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true" );
				MongoClient mongoClient = new MongoClient(uri);
				MongoDatabase database = mongoClient.getDatabase(CompanyName);			
				MongoCollection<Document> collection = database.getCollection( CollectionName);

	            // convert CSV  directly
				try{
					String file = fileupload();
					List<financialHoldings> beans = new CsvToBeanBuilder(new FileReader(file))
							//we ask if the file contians headers upon radial selection it will skip first header line
			                .withType(financialHoldings.class).withSkipLines(1)
			                .build()
			                .parse();
					
					if(beans.get(0).getId()!=1) {
						beans = new CsvToBeanBuilder(new FileReader(file))
								//we ask if the file contians headers upon radial selection it will skip first header line
				                .withType(financialHoldings.class).withSkipLines(0)
				                .build()
				                .parse();
						for(int x = 0; x<beans.size(); x++) {
							
							Document doc = new Document("id",beans.get(x).getId());  
			                doc.append("account name",beans.get(x).getAccountName());
			                doc.append("Balance",beans.get(x).getBalance());
			                doc.append("Bank",beans.get(x).getBankingInstitution());
			                collection.insertOne(doc);  
						}
					}else {
						
						for(int x = 0; x<beans.size(); x++) {
							
							Document doc = new Document("id",beans.get(x).getId());  
			                doc.append("account name",beans.get(x).getAccountName());
			                doc.append("Balance",beans.get(x).getBalance());
			                doc.append("Bank",beans.get(x).getBankingInstitution());
			                collection.insertOne(doc);  
						}
					}
					

				  }catch (Exception e) {
			            e.printStackTrace();
			      }
			      try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            progressBar.setValue(300);
				mongoClient.close();
				JOptionPane.showMessageDialog(null, "CSV Upload Complete");
			
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        
	    }

	 public static void uploadJSON(String CompanyName,String CollectionName) {
		
	        try {
	        	CompanyName= CompanyName.toLowerCase();
	        	MongoClientURI uri = new MongoClientURI("" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/"+CompanyName+"?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true" );
				MongoClient mongoClient = new MongoClient(uri);
				MongoDatabase database = mongoClient.getDatabase(CompanyName);			
				MongoCollection<Document> collection = database.getCollection( CollectionName);

	            // convert JSON to DBObject directly

				int count = 0;
				int batch = 100;

				List<InsertOneModel<Document>> docs = new ArrayList<>();
				
				try (BufferedReader br = new BufferedReader(new FileReader(fileupload()))) {
				      String line;
				      while ((line = br.readLine()) != null) {
				         docs.add(new InsertOneModel<>(Document.parse(line)));
				         count++;
				         
				         if (count == batch) {
				           collection.bulkWrite(docs, new BulkWriteOptions().ordered(false));
				           docs.clear();
				           count = 0;
				        }
				       
				      
				    }
				      
                      
                      // do some stuffs here
                      try
                      {
                          Thread.sleep(100);
                      }
                      catch (InterruptedException e)
                      {
                          e.printStackTrace();
                      }
                      progressBar.setValue(300);
                      JOptionPane.showMessageDialog(null, "JSON Upload Complete");
				   
				      

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
				mongoClient.close();
	        }catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	    }
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
			bg.clearSelection();
			 csvList.setSelectedIndex(0);
			 progressBar.setValue(40);
			 progressBar.setVisible(true);
			 singleFilePanel.revalidate();
			 JOptionPane.showMessageDialog(null, "Now Uploading: " + chooser.getSelectedFile().getName());
			 
			
			 
		}
		
		return chooser.getSelectedFile().getAbsolutePath();
	}
	public static void main(String args[]) {
		try {
			
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			
		} catch (Exception evt) {
		}
		// Create an instance of the test application
		GUI mainFrame = new GUI("");
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);

	}
}