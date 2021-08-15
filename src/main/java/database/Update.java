package database;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import Encryption.Encrypt;

public class Update {
	

	public static void updateEmployee(String companyName, String firstName, String lastName, String hireYear,String ssn, String occupation ) {

		try {
			
			System.out.print(ssn);
			MongoClientURI uri = new MongoClientURI("" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/" + companyName + "?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true" );
			MongoClient mongoClient = new MongoClient(uri);
			MongoDatabase database = mongoClient.getDatabase(companyName);			
			MongoCollection<Document> collection = database.getCollection("Employees");
			
			if(collection.find(new Document("ssn", ssn)).first()==null) {
				JOptionPane.showMessageDialog(null, "The Employee does not exist in our system. Make sure you are entering in the correct Employee ssn");
			}else {
				Document test = collection.find(new Document("ssn", ssn)).first();
				if(test.get("ssn")==ssn) 
					{
					JOptionPane.showMessageDialog(null, "Now Updating"+test.toString());
					System.out.println(test.toString());
					collection.updateOne(Filters.eq("ssn", ssn), Updates.set("first name", firstName));
					collection.updateOne(Filters.eq("ssn", ssn), Updates.set("last name", lastName));
					collection.updateOne(Filters.eq("ssn", ssn), Updates.set("hire year", hireYear));
					collection.updateOne(Filters.eq("ssn", ssn), Updates.set("occupation", occupation));
					
				}else {
					JOptionPane.showMessageDialog(null, "The Employee does not exist in our system. Make sure you are entering in the correct Employee ssn");
				}
			}
//			Document test1 = collection.find(new Document("ssn", ssn)).first();
//			System.out.println("\nNEW CREDENTIALS...\n\n\nObjectID: " + test1.get("_id") + "\nID:" + test1.get("id")
//					+ "\nFirst Name: " + test1.get("first name") + "\nLast Name: " + test1.get("last name")
//					+ "\nHire Year: " + test1.get("hire year"));
			mongoClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static JPanel panelEmployee;
	static JFrame n;
	private static JProgressBar progressBar1 = new JProgressBar();
	private static JProgressBar progressBar2 = new JProgressBar();
	private static JProgressBar progressBar3 = new JProgressBar();
	private static JProgressBar progressBar4 = new JProgressBar();
	private static JProgressBar progressBar5 = new JProgressBar();
	//Panel that will be used for Update Input
	//
	public static JPanel createEmployeeTab(JFrame n) {
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
			
				progressBar1.setValue(40);
				progressBar1.setVisible(true);
				progressBar1.setValue(40);
				
				p.revalidate();
				
				Encrypt p2 = new Encrypt();
				String ssn = lSocial.getText().replace("-","");
				//make all strings capital then encode them then put them in the 
				//insert methods do this for all uploads csv and manual entry
				//updateEmployee(companyName, lFirstName.getText(), lLastName.getText(),lHireYear.getText() ,
						//ssn, lOccupation.getText());
				lFirstName.setText("");
				lLastName.setText("");
				lHireYear.setText("");
				lSocial.setText("");
				lOccupation.setText("");
				progressBar1.setValue(0);
				p.revalidate();
				n.dispose();
				
			}
		});
		p.add(Submit);
		p.add(progressBar1);
		panelEmployee.add(p, BorderLayout.PAGE_START);
		return panelEmployee;

	}

	private static JPanel panelFinancial;

	public static JPanel createFinancialTab() {
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

		p.setLayout(new GridLayout(6, 1));
		p.add(jAccountName);
		p.add(lAccountName);
		p.add(jBalance);
		p.add(lBalance);
		p.add(jBank);
		p.add(lBank);

		JButton Submit = new JButton("Submit");
		Submit.setSize(new Dimension(1, 1));
		Submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (lAccountName.getText().isEmpty() || lBalance.getText().isEmpty() || lBank.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "Incorrect credentials one or more fields left blank");
					progressBar5.setValue(0);

				}else {
				progressBar5.setValue(40);
				progressBar5.setVisible(true);
				progressBar5.setValue(40);
				JOptionPane.showMessageDialog(null, "Uploading Finances...");

				p.revalidate();
				Double balance = Double.parseDouble(lBalance.getText());
				//insertFinance(companyName, lAccountName.getText(), balance, lBank.getText());
				lAccountName.setText("");
				lBalance.setText("");
				lBank.setText("");
				progressBar5.setValue(0);
				p.revalidate();
				}
			}
		});
		p.add(Submit);
		p.add(progressBar5);
		panelFinancial.add(p, BorderLayout.NORTH);
		return panelFinancial;

	}

	private static JPanel panelProperties;

	public static JPanel createPropertiesTab() {
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

				}else {
				progressBar2.setValue(40);
				progressBar2.setVisible(true);
				progressBar2.setValue(40);
				JOptionPane.showMessageDialog(null, "Uploading Finances...");

				p.revalidate();
				double cost = Double.parseDouble(lCost.getText());
				//insertProperty(companyName, lPropertyName.getText(), cost, lLocation.getText());
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
		return panelProperties;
	}

	private static JPanel panelService;

	public static JPanel createServiceTab() {
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

					//insertService(companyName, lServiceName.getText(), lCost.getText(), lCategory.getText());
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
		return panelService;
	}

	private static JPanel panelProduct;

	public static JPanel  createProductTab() {
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
					double cost = Double.parseDouble(lCost.getText());
					progressBar4.setValue(40);
					progressBar4.setVisible(true);
					progressBar4.setValue(40);
					JOptionPane.showMessageDialog(null, "Uploading Product...");
					//insertProduct(companyName, lProductName.getText(), cost, lCategory.getText(),
							//lSupplier.getText());
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
		return panelProduct;
	}

	private static JPanel panel;
	private static JPanel panel2;
	private static JPanel panel3;
	private static JPanel panel4;
	private static JPanel panel5;
	public static JPanel updateEmployee(JPanel footnotes,String companyName) {
		JButton search = new JButton("SEARCH");
		panel = new JPanel();
		panel.setLayout(null);
		
		
		JLabel firstLabel = new JLabel("First name: ");
		JLabel lastLabel = new JLabel("Last name: ");
		JLabel hireLabel = new JLabel("SSN: ");
		JLabel jHireYear = new JLabel("Hire Year:");
		JLabel jOccupation = new JLabel("Occupation:");
		
		ArrayList<JLabel> list = new ArrayList<>();
		list.add(firstLabel);
		list.add(lastLabel);
		list.add(hireLabel);
		list.add(jHireYear);
		list.add(jOccupation);
		jHireYear.setVisible(false);
		jOccupation.setVisible(false);
		int x = 10;
		int y = 20;
		for(JLabel label: list) {
			label.setBounds(x, y, 80, 25);
			y += 30;
			panel.add(label);
		}
		
		JTextField firstText = new JTextField();
		JTextField lastText = new JTextField();
		JTextField hireText = new JTextField();
		JTextField lHireYear = new JTextField(20);
		JTextField lOccupation = new JTextField(20);
		ArrayList<JTextField> list1 = new ArrayList<>();
		list1.add(firstText);
		list1.add(lastText);
		list1.add(hireText);
		list1.add(lOccupation);
		list1.add(lHireYear);
		lHireYear.setVisible(false);
		lOccupation.setVisible(false);
		
		int h = 20;
		int w = 100;
		for(JTextField label: list1) {
			label.setBounds(w, h, 150, 25);
			h += 30;
			panel.add(label);
		}
		

		
		search.setForeground(Color.BLACK);
		search.setOpaque(true);
		search.setBounds(320, 52, 100, 20);
		panel.add(search);
		
		JButton update = new JButton("UPDATE");
		update.setBounds(320, 74, 100, 20);
		update.setVisible(false);
		JButton realUpdate = new JButton("-UPDATE-");
		
		realUpdate.setBounds(320, 74, 100, 20);
		realUpdate.setVisible(false);
		panel.add(realUpdate);
		realUpdate.addActionListener(new ActionListener() {
			
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
				//do update function here
				JOptionPane.showMessageDialog(null, "Updating...");
				search.setVisible(true);
				realUpdate.setVisible(false);
				jHireYear.setVisible(false);
				jOccupation.setVisible(false);
				lHireYear.setVisible(false);
				lOccupation.setVisible(false);
				footnotes.removeAll();
				firstText.setText("");
				lastText.setText("");
				hireText.setText("");
				lHireYear.setText("");
				lOccupation.setText("");
				
			}});
		panel.add(update);
		
				
		search.addActionListener(new ActionListener() {
		
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				String hireYear = hireText.getText().replace("-", "");
				
				
				footnotes.removeAll();
				footnotes.revalidate();
			
				search.setVisible(true);
				update.setVisible(true);
				
				
				String firstName = firstText.getText();
				String lastName = lastText.getText();
				
				String db = "northwind";
				
				
				DefaultListModel document = new DefaultListModel();
				
				Find.findEmployee(db, firstName, lastName, hireYear, document);
				
				JList vector = new JList(document);
								
				JScrollPane scroll = new JScrollPane(vector);
				vector.setVisibleRowCount(5);
				vector.setLayoutOrientation(JList.VERTICAL);
				vector.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
				
				scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				scroll.setVisible(true);
				footnotes.add(scroll, BorderLayout.CENTER);
				
				update.addActionListener(new ActionListener() {
				
					@Override
					public void actionPerformed(ActionEvent e) {
						
						String test = String.valueOf(vector.getSelectedValue());
						//collection.deleteOne(query).first();
						System.out.println(test);
						String[] result = test.split(": ");
						for(int i = 0; i < result.length; i++) {
							System.out.println(result[i]);
						}
						footnotes.removeAll();
						//Update method here from results
						
						update.setVisible(false);
						realUpdate.setVisible(true);
						jHireYear.setVisible(true);
						jOccupation.setVisible(true);
						lHireYear.setVisible(true);
						lOccupation.setVisible(true);
						
						firstText.setText(result[2].replace("last name", ""));
						lastText.setText(result[3].replace("hire year", ""));
						hireText.setText(result[6].replace("occupation", ""));
						lHireYear.setText(result[4].replace("ssn",""));
						lOccupation.setText(result[5]);
						
						realUpdate.addActionListener(new ActionListener() {
							
							@SuppressWarnings({ "rawtypes", "unchecked" })
							@Override
							public void actionPerformed(ActionEvent e) {
								//do update function here
								JOptionPane.showMessageDialog(null, "Updating...");
								search.setVisible(true);
								realUpdate.setVisible(false);
								jHireYear.setVisible(false);
								jOccupation.setVisible(false);
								lHireYear.setVisible(false);
								lOccupation.setVisible(false);
								footnotes.removeAll();
								firstText.setText("");
								lastText.setText("");
								hireText.setText("");
								lHireYear.setText("");
								lOccupation.setText("");
								
							}});
						update.setVisible(false);
						JScrollPane scroll = new JScrollPane();
						scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
						scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
						scroll.setVisible(true);
						footnotes.add(scroll, BorderLayout.CENTER);
						footnotes.revalidate();
					}
				});
				
				footnotes.revalidate();
			
			}
		});
		return panel;
	}

	//Property update method panel
	public static JPanel updateProperty(JPanel footnotes,String companyName) {
		panel2 = new JPanel();
		panel2.setLayout(null);
		JButton search = new JButton("SEARCH");
				
		JLabel firstLabel = new JLabel("Property Name: ");
		JLabel costLabel = new JLabel("Cost: ");
		JLabel locationLabel = new JLabel("Location: ");
		ArrayList<JLabel> list = new ArrayList<>();
		list.add(firstLabel);
		list.add(costLabel);
		list.add(locationLabel);
		costLabel.setVisible(false);
		locationLabel.setVisible(false);
		
		
	
		int x = 10;
		int y = 20;
		for(JLabel label: list) {
			label.setBounds(x, y, 120, 25);
			y += 30;
			panel2.add(label);
		}
		
		JTextField firstText = new JTextField();
		JTextField costText = new JTextField();
		JTextField locationText = new JTextField();
		ArrayList<JTextField> list1 = new ArrayList<>();
		list1.add(firstText);
		list1.add(costText);
		list1.add(locationText);
		
		locationText.setVisible(false);
		costText.setVisible(false);
		int h = 20;
		int w = 100;
		for(JTextField label: list1) {
			label.setBounds(w, h, 150, 25);
			h += 30;
			panel2.add(label);
		}
		
		JButton update1 = new JButton("UPDATE");
		update1.setBounds(320, 74, 100, 20);
		update1.setVisible(false);
		panel2.add(update1);
		search.setForeground(Color.BLACK);
		search.setOpaque(true);
		search.setBounds(320, 52, 100, 20);
		panel2.add(search);
		JButton realUpdate = new JButton("-UPDATE-");
		realUpdate.setBounds(320, 74, 100, 20);
		realUpdate.setVisible(false);
		panel2.add(realUpdate);
		realUpdate.addActionListener(new ActionListener() {
			
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
				//do update function here
				JOptionPane.showMessageDialog(null, "Updating...");
				search.setVisible(true);
				realUpdate.setVisible(false);
				costLabel.setVisible(false);
				locationLabel.setVisible(false);
				costText.setVisible(false);
				locationText.setVisible(false);
				footnotes.removeAll();
				costText.setText("");
				locationText.setText("");
				firstText.setText("");
				
				
			}});
		search.addActionListener(new ActionListener() {
		
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
			
				footnotes.removeAll();
				footnotes.revalidate();
				update1.setVisible(true);
				DefaultListModel document = new DefaultListModel();
				//searches by property name
				Find.findProperty(companyName,firstText.getText(), document);
				
				@SuppressWarnings({ })
				JList vector = new JList(document);
				
				
				JScrollPane scroll = new JScrollPane(vector);
				vector.setVisibleRowCount(5);
				vector.setLayoutOrientation(JList.VERTICAL);
				vector.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
				
				scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				scroll.setVisible(true);
				footnotes.add(scroll, BorderLayout.CENTER);
				
				
				update1.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						costLabel.setVisible(true);
						locationLabel.setVisible(true);
						locationText.setVisible(true);
						costText.setVisible(true);
						update1.setVisible(true);
					
						String test = String.valueOf(vector.getSelectedValue());
						//collection.deleteOne(query).first();
						System.out.println(test);
						String[] result = test.split(": ");
						for(int i = 0; i < result.length; i++) {
							System.out.println(result[i]);
						}
						update1.setVisible(false);
						realUpdate.setVisible(true);
						JScrollPane scroll = new JScrollPane();
						scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
						scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
						scroll.setVisible(true);
						footnotes.add(scroll, BorderLayout.CENTER);
						
					}
				});
				footnotes.revalidate();
			
			}
		});
		return panel2;
	}
	
	
	//Products search
	public static JPanel updateProduct(JPanel footnotes,String companyName) {
		JButton search = new JButton("SEARCH");
		panel3 = new JPanel();
		panel3.setLayout(null);
		
		
		JLabel firstLabel = new JLabel("Product name: ");
		JLabel lastLabel = new JLabel("Category: ");
		JLabel hireLabel = new JLabel("Supplier: ");
		JLabel costLabel = new JLabel("Cost: ");
		costLabel.setVisible(false);
		ArrayList<JLabel> list = new ArrayList<>();
		list.add(firstLabel);
		list.add(lastLabel);
		list.add(hireLabel);
		list.add(costLabel);
		int x = 10;
		int y = 20;
		for(JLabel label: list) {
			label.setBounds(x, y, 80, 25);
			y += 30;
			panel3.add(label);
		}
		
		JTextField firstText = new JTextField();
		JTextField lastText = new JTextField();
		JTextField hireText = new JTextField();
		JTextField costText = new JTextField();
	costText.setVisible(false);
		
		ArrayList<JTextField> list1 = new ArrayList<>();
		list1.add(firstText);
		list1.add(lastText);
		list1.add(hireText);
		list1.add(costText);
		
		int h = 20;
		int w = 100;
		for(JTextField label: list1) {
			label.setBounds(w, h, 150, 25);
			h += 30;
			panel3.add(label);
		}
		

		JButton update2 = new JButton("UPDATE");
		update2.setBounds(320, 74, 100, 20);
		update2.setVisible(false);
		panel3.add(update2);
		
		search.setForeground(Color.BLACK);
		search.setOpaque(true);
		search.setBounds(320, 52, 100, 20);
		panel3.add(search);
		JButton realUpdate = new JButton("-UPDATE-");
		realUpdate.setBounds(320, 74, 100, 20);
		realUpdate.setVisible(false);
		panel3.add(realUpdate);
		realUpdate.addActionListener(new ActionListener() {
			
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
				//do update function here
				JOptionPane.showMessageDialog(null, "Updating...");
				search.setVisible(true);
				realUpdate.setVisible(false);
				costLabel.setVisible(false);
				
				costText.setVisible(false);
				
				footnotes.removeAll();
				costText.setText("");
				lastText.setText("");
				firstText.setText("");
				hireText.setText("");
				
				
			}});
		search.addActionListener(new ActionListener() {
		
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
				
			
				update2.setVisible(true);
				footnotes.removeAll();
				footnotes.revalidate();				
				
				DefaultListModel document = new DefaultListModel();
				//insert find records for this type
				//Find.findRecords(firstName, lastName, document);
				
				@SuppressWarnings({ })
				JList vector = new JList(document);
				
				
				JScrollPane scroll = new JScrollPane(vector);
				vector.setVisibleRowCount(5);
				vector.setLayoutOrientation(JList.VERTICAL);
				vector.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
				
				scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				scroll.setVisible(true);
				footnotes.add(scroll, BorderLayout.CENTER);
				
				update2.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						costText.setVisible(true);
						costLabel.setVisible(true);	
						String test = String.valueOf(vector.getSelectedValue());
						//collection.deleteOne(query).first();
						System.out.println(test);
						String[] result = test.split(": ");
						for(int i = 0; i < result.length; i++) {
							System.out.println(result[i]);
						}
						
						update2.setVisible(false);
						realUpdate.setVisible(true);
						JScrollPane scroll = new JScrollPane();
						scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
						scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
						scroll.setVisible(true);
						footnotes.add(scroll, BorderLayout.CENTER);
					}
				});
				footnotes.revalidate();
			
				
			}
		});
		return panel3;
	}
	
	//search service
	public static JPanel updateService(JPanel footnotes,String companyName) {
		JButton search = new JButton("SEARCH");
		panel4 = new JPanel();
		panel4.setLayout(null);
		
		
		JLabel firstLabel = new JLabel("Service name: ");
		JLabel lastLabel = new JLabel("Category: ");
		JLabel costLabel = new JLabel("Cost: ");
		costLabel.setVisible(false);
		
		ArrayList<JLabel> list = new ArrayList<>();
		list.add(firstLabel);
		list.add(lastLabel);
		list.add(costLabel);
		
		int x = 10;
		int y = 20;
		for(JLabel label: list) {
			label.setBounds(x, y, 80, 25);
			y += 30;
			panel4.add(label);
		}
		
		JTextField firstText = new JTextField();
		JTextField lastText = new JTextField();
		JTextField costText = new JTextField();
		costText.setVisible(false);
		
		ArrayList<JTextField> list1 = new ArrayList<>();
		list1.add(firstText);
		list1.add(lastText);
		list1.add(costText);
		
		int h = 20;
		int w = 100;
		for(JTextField label: list1) {
			label.setBounds(w, h, 150, 25);
			h += 30;
			panel4.add(label);
		}
		

		JButton update3 = new JButton("UPDATE");
		update3.setBounds(320, 74, 100, 20);
		update3.setVisible(false);
		panel4.add(update3);
		search.setForeground(Color.BLACK);
		search.setOpaque(true);
		search.setBounds(320, 52, 100, 20);
		panel4.add(search);
		JButton realUpdate = new JButton("-UPDATE-");
		realUpdate.setBounds(320, 74, 100, 20);
		realUpdate.setVisible(false);
		panel4.add(realUpdate);
		realUpdate.addActionListener(new ActionListener() {
			
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
				//do update function here
				JOptionPane.showMessageDialog(null, "Updating...");
				search.setVisible(true);
				realUpdate.setVisible(false);
				costLabel.setVisible(false);
				
				costText.setVisible(false);
				
				footnotes.removeAll();
				costText.setText("");
				lastText.setText("");
				firstText.setText("");
			
				
				
			}});
		search.addActionListener(new ActionListener() {
		
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				update3.setVisible(true);
				footnotes.removeAll();
				footnotes.revalidate();
				
				DefaultListModel document = new DefaultListModel();
				//insert find records for this type
				//Find.findService(, lastName, hireYear, document);
				
				@SuppressWarnings({ })
				JList vector = new JList(document);
				
				JScrollPane scroll = new JScrollPane(vector);
				vector.setVisibleRowCount(5);
				vector.setLayoutOrientation(JList.VERTICAL);
				vector.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
				
				scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				scroll.setVisible(true);
				footnotes.add(scroll, BorderLayout.CENTER);
				update3.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						costLabel.setVisible(true);
						costText.setVisible(true);
						String test = String.valueOf(vector.getSelectedValue());
						//collection.deleteOne(query).first();
						System.out.println(test);
						String[] result = test.split(": ");
						for(int i = 0; i < result.length; i++) {
							System.out.println(result[i]);
						}
						//delete method here from results
						update3.setVisible(false);
						realUpdate.setVisible(true);
						
						JScrollPane scroll = new JScrollPane();
						scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
						scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
						scroll.setVisible(true);
						footnotes.add(scroll, BorderLayout.CENTER);
					}
				});
				footnotes.revalidate();
				
			}
		});
		return panel4;
	}
	
	//search Financials
	public static JPanel updateFinancials(JPanel footnotes,String companyName) {
		JButton search = new JButton("SEARCH");
		panel5 = new JPanel();
		panel5.setLayout(null);
		
		
		JLabel firstLabel = new JLabel("Account name: ");
		JLabel accountLabel = new JLabel("Account ID: ");
		JLabel lastLabel = new JLabel("Bank: ");
		JLabel balanceLabel = new JLabel("Balance: ");
		balanceLabel.setVisible(false);
		ArrayList<JLabel> list = new ArrayList<>();
		list.add(firstLabel);
		list.add(accountLabel);
		list.add(lastLabel);
		list.add(balanceLabel);
		
		int x = 10;
		int y = 20;
		for(JLabel label: list) {
			label.setBounds(x, y, 100, 25);
			y += 30;
			panel5.add(label);
		}
		
		JTextField firstText = new JTextField();
		JTextField lastText = new JTextField();
		JTextField bankText = new JTextField();
		JTextField balanceText = new JTextField();
		balanceText.setVisible(false);
		
		ArrayList<JTextField> list1 = new ArrayList<>();
		list1.add(firstText);
		list1.add(bankText);
		list1.add(lastText);
		list1.add(balanceText);
		balanceLabel.setVisible(false);
		int h = 20;
		int w = 100;
		for(JTextField label: list1) {
			label.setBounds(w, h, 150, 25);
			h += 30;
			panel5.add(label);
		}
		JButton update2 = new JButton("UPDATE");
		update2.setBounds(320, 74, 100, 20);
		update2.setVisible(false);
		panel5.add(update2);
		
		search.setForeground(Color.BLACK);
		search.setOpaque(true);
		search.setBounds(320, 52, 100, 20);
		panel5.add(search);
		JButton realUpdate = new JButton("-UPDATE-");
		realUpdate.setBounds(320, 74, 100, 20);
		realUpdate.setVisible(false);
		panel5.add(realUpdate);
		realUpdate.addActionListener(new ActionListener() {
			
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
				//do update function here
				JOptionPane.showMessageDialog(null, "Updating...");
				search.setVisible(true);
				realUpdate.setVisible(false);
				balanceText.setVisible(false);
				balanceLabel.setVisible(false);
				
				
				footnotes.removeAll();
				balanceText.setText("");
				bankText.setText("");
				lastText.setText("");
				firstText.setText("");
			
				
				
			}});
		search.addActionListener(new ActionListener() {
		
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
				
				footnotes.removeAll();
				footnotes.revalidate();
				update2.setVisible(true);
				DefaultListModel document = new DefaultListModel();
				//insert find records for this type
				//Find.findRecords(firstName, lastName, hireYear, document);
				
				@SuppressWarnings({ })
				JList vector = new JList(document);
				
				JScrollPane scroll = new JScrollPane(vector);
				vector.setVisibleRowCount(5);
				vector.setLayoutOrientation(JList.VERTICAL);
				vector.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
				
				scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				scroll.setVisible(true);
				footnotes.add(scroll, BorderLayout.CENTER);
				update2.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						balanceText.setVisible(true);
						balanceLabel.setVisible(true);
						String test = String.valueOf(vector.getSelectedValue());
						//collection.deleteOne(query).first();
						System.out.println(test);
						String[] result = test.split(": ");
						for(int i = 0; i < result.length; i++) {
							System.out.println(result[i]);
						}
						//delete method here from results
						update2.setVisible(false);
						realUpdate.setVisible(true);
						JScrollPane scroll = new JScrollPane();
						scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
						scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
						scroll.setVisible(true);
						footnotes.add(scroll, BorderLayout.CENTER);
					}
				});
				footnotes.revalidate();
			
			
			}
		});
		return panel5;
	}

}
