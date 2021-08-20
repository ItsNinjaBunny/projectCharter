package database;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Delete {
	private static JPanel employeePanel;
	private static JPanel propertyPanel;
	private static JPanel productPanel;
	private static JPanel servicePanel;
	private static JPanel financialPanel;
	
	
	private static MongoClient connectDatabase(String databaseName) {
		
		MongoClientURI uri = new MongoClientURI("" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/" + databaseName + "?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true");
		MongoClient mongoClient = new MongoClient(uri);
				
		return mongoClient;
	}
	
	public static JPanel deleteEmployee(JPanel footnotes,String companyName) {
		JButton search = new JButton("SEARCH");
		employeePanel = new JPanel();
		employeePanel.setLayout(null);
		
		
		JLabel firstLabel = new JLabel("First name: ");
		JLabel lastLabel = new JLabel("Last name: ");
		JLabel hireLabel = new JLabel("SSN: ");
		
		ArrayList<JLabel> list = new ArrayList<>();
		list.add(firstLabel);
		list.add(lastLabel);
		list.add(hireLabel);
		
		int x = 10;
		int y = 20;
		for(JLabel label: list) {
			label.setBounds(x, y, 80, 25);
			y += 30;
			employeePanel.add(label);
		}
		
		JTextField firstText = new JTextField();
		JTextField lastText = new JTextField();
		JTextField ssnText = new JTextField();
		
		ArrayList<JTextField> list1 = new ArrayList<>();
		list1.add(firstText);
		list1.add(lastText);
		list1.add(ssnText);
		
		int h = 20;
		int w = 100;
		for(JTextField label: list1) {
			label.setBounds(w, h, 150, 25);
			h += 30;
			employeePanel.add(label);
		}
		

		
		search.setForeground(Color.BLACK);
		search.setBounds(320, 52, 100, 20);
		employeePanel.add(search);
		
		JButton delete = new JButton("Delete");
		delete.setBounds(320, 74, 100, 20);
		delete.setVisible(false);

		employeePanel.add(delete);
		
		search.addActionListener(new ActionListener() {
		
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				String ssn = ssnText.getText().replace("-", "");
				
				
				footnotes.removeAll();
				footnotes.revalidate();
			
				search.setVisible(true);
				delete.setVisible(true);
				
				
				String firstName = firstText.getText();
				String lastName = lastText.getText();
				
				
				
				
				DefaultListModel document = new DefaultListModel();
				
				Find.findEmployee(companyName, firstName, lastName, ssn, document);
				
				JList vector = new JList(document);
								
				JScrollPane scroll = new JScrollPane(vector);
				vector.setVisibleRowCount(5);
				vector.setLayoutOrientation(JList.VERTICAL);
				vector.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
				
				scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				scroll.setVisible(true);
				footnotes.add(scroll, BorderLayout.CENTER);
				
				delete.addActionListener(new ActionListener() {
				
					public void actionPerformed(ActionEvent e) {
						
						MongoClient mongoClient = connectDatabase(companyName);
						MongoDatabase database = mongoClient.getDatabase(companyName);
						MongoCollection<Document> collection = database.getCollection("Employees");
						
						String test = String.valueOf(vector.getSelectedValue());
						String[] result = test.split(": ");
						String[] id = result[1].split(", ");
						int resultID = Integer.parseInt(id[0]);
						//collection.deleteOne(query).first();
						BasicDBObject query = new BasicDBObject(resultID);
						collection.deleteOne(query);
						mongoClient.close();
						
						//delete method here from results
						JOptionPane.showMessageDialog(null, "Now Deleting "+test.toString());
						delete.setVisible(false);
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
		return employeePanel;
	}
	//Property search method panel
	public static JPanel deleteProperty(JPanel footnotes,String companyName) {
		propertyPanel = new JPanel();
		propertyPanel.setLayout(null);
		propertyPanel.setBackground(Color.gray);
		JLabel instLabel = new JLabel("1.Search  2.Select One  3.Delete");
		instLabel.setBounds(280,20,300,20);
		instLabel.setForeground(Color.white);
		propertyPanel.add(instLabel);
		JButton search = new JButton("SEARCH");
				
		JLabel firstLabel = new JLabel("Property Name: ");
		
		
		ArrayList<JLabel> list = new ArrayList<>();
		list.add(firstLabel);
		
		
		int x = 10;
		int y = 20;
		for(JLabel label: list) {
			label.setBounds(x, y, 120, 25);
			y += 30;
			propertyPanel.add(label);
		}
		
		JTextField propertyText = new JTextField();
		
		
		ArrayList<JTextField> list1 = new ArrayList<>();
		list1.add(propertyText);
		
		
		int h = 20;
		int w = 100;
		for(JTextField label: list1) {
			label.setBounds(w, h, 150, 25);
			h += 30;
			propertyPanel.add(label);
		}
		
		JButton delete = new JButton("Delete");
		delete.setBounds(320, 74, 100, 20);
		delete.setVisible(false);
		propertyPanel.add(delete);
		
		
		search.setBounds(320, 52, 100, 20);
		propertyPanel.add(search);
		search.addActionListener(new ActionListener() {
		
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
			
				footnotes.removeAll();
				footnotes.revalidate();
				delete.setVisible(true);
				
				DefaultListModel document = new DefaultListModel();
				//searches by property name
				Find.findProperty(companyName, propertyText.getText(), document);
				
				System.out.println(document.get(0));
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
				for (JTextField label : list1) {
					label.setText("");
				}
				
				delete.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						MongoClient mongoClient = connectDatabase(companyName);
						MongoDatabase database = mongoClient.getDatabase(companyName);
						MongoCollection<Document> collection = database.getCollection("Properties");
						
						String test = String.valueOf(vector.getSelectedValue());
						String[] result = test.split(": ");
						String[] id = result[1].split(", ");
						int resultID = Integer.parseInt(id[0]);
						//collection.deleteOne(query).first();
						BasicDBObject query = new BasicDBObject("id", resultID);
						collection.deleteOne(query);
						mongoClient.close();
						
						//delete method here from results
						JOptionPane.showMessageDialog(null, "Now Deleting "+test.toString());
						delete.setVisible(false);
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
		return propertyPanel;
	}
	
	//Products search
	public static JPanel deleteProduct(JPanel footnotes,String companyName) {
		JButton search = new JButton("SEARCH");
		productPanel = new JPanel();
		productPanel.setLayout(null);
		productPanel.setBackground(Color.gray);
		JLabel instLabel = new JLabel("1.Search  2.Select One  3.Delete");
		instLabel.setBounds(280,20,300,20);
		instLabel.setForeground(Color.white);
		productPanel.add(instLabel);
		
		JLabel productLabel = new JLabel("Product name: ");
		JLabel categoryLabel = new JLabel("Category: ");
		JLabel supplierLabel = new JLabel("Supplier: ");
		
		
		ArrayList<JLabel> list = new ArrayList<>();
		list.add(productLabel);
		list.add(categoryLabel);
		list.add(supplierLabel);
		
		int x = 10;
		int y = 20;
		for(JLabel label: list) {
			label.setBounds(x, y, 80, 25);
			y += 30;
			productPanel.add(label);
		}
		
		JTextField productText = new JTextField();
		JTextField categoryText = new JTextField();
		JTextField supplierText = new JTextField();
		
		ArrayList<JTextField> list1 = new ArrayList<>();
		list1.add(productText);
		list1.add(categoryText);
		list1.add(supplierText);
		
		int h = 20;
		int w = 100;
		for(JTextField label: list1) {
			label.setBounds(w, h, 150, 25);
			h += 30;
			productPanel.add(label);
		}
		

		JButton delete = new JButton("Delete");
		delete.setBounds(320, 74, 100, 20);
		delete.setVisible(false);
		productPanel.add(delete);
		
		
		
		search.setBounds(320, 52, 100, 20);
		productPanel.add(search);
		search.addActionListener(new ActionListener() {
		
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
				
			
				delete.setVisible(true);
				footnotes.removeAll();
				footnotes.revalidate();		
				
				String product, category, supplier;
				product = productText.getText();
				category = categoryText.getText();
				supplier = supplierText.getText();
				
				DefaultListModel document = new DefaultListModel();
				//insert find records for this type
				Find.findProducts(companyName, product, category, supplier, document);
				
				@SuppressWarnings({ })
				JList vector = new JList(document);
				
				
				JScrollPane scroll = new JScrollPane(vector);
				vector.setVisibleRowCount(5);
				vector.setLayoutOrientation(JList.VERTICAL);
				vector.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
				
				scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				scroll.setVisible(true);
				for (JTextField label : list1) {
					label.setText("");
				}
				footnotes.add(scroll, BorderLayout.CENTER);
				
				delete.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						MongoClient mongoClient = connectDatabase(companyName);
						MongoDatabase database = mongoClient.getDatabase(companyName);
						MongoCollection<Document> collection = database.getCollection("Products");
						
						String test = String.valueOf(vector.getSelectedValue());
						String[] result = test.split(": ");
						String[] id = result[1].split(", ");
						int resultID = Integer.parseInt(id[0]);
						//collection.deleteOne(query).first();
						BasicDBObject query = new BasicDBObject("id", resultID);
						collection.deleteOne(query);
						mongoClient.close();
						
						
						//delete method here from results
						JOptionPane.showMessageDialog(null, "Now Deleting "+test.toString());
						delete.setVisible(false);
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
		return productPanel;
	}
	
	//search service
	public static JPanel deleteService(JPanel footnotes,String companyName) {
		JButton search = new JButton("SEARCH");
		servicePanel = new JPanel();
		servicePanel.setLayout(null);
		servicePanel.setBackground(Color.gray);
		JLabel instLabel = new JLabel("1.Search  2.Select One  3.Delete");
		instLabel.setBounds(280,20,300,20);
		instLabel.setForeground(Color.white);
		servicePanel.add(instLabel);
		
		JLabel serviceLabel = new JLabel("Service name: ");
		JLabel categoryLabel = new JLabel("Category: ");
		
		
		ArrayList<JLabel> list = new ArrayList<>();
		list.add(serviceLabel);
		list.add(categoryLabel);
	
		
		int x = 10;
		int y = 20;
		for(JLabel label: list) {
			label.setBounds(x, y, 80, 25);
			y += 30;
			servicePanel.add(label);
		}
		
		JTextField serviceText = new JTextField();
		JTextField categoryText = new JTextField();
		
		
		ArrayList<JTextField> list1 = new ArrayList<>();
		list1.add(serviceText);
		list1.add(categoryText);
	
		
		int h = 20;
		int w = 100;
		for(JTextField label: list1) {
			label.setBounds(w, h, 150, 25);
			h += 30;
			servicePanel.add(label);
		}
		

		JButton delete = new JButton("Delete");
		delete.setBounds(320, 74, 100, 20);
		delete.setVisible(false);
		servicePanel.add(delete);
		
		
		search.setBounds(320, 52, 100, 20);
		servicePanel.add(search);
		search.addActionListener(new ActionListener() {
		
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				delete.setVisible(true);
				footnotes.removeAll();
				footnotes.revalidate();
				
				String service, category;
				service = serviceText.getText();
				category = categoryText.getText();
				
				DefaultListModel document = new DefaultListModel();
				
				
				//insert find records for this type
				Find.findService(companyName, service, category, document);
				
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
				for (JTextField label : list1) {
					label.setText("");
				}
				delete.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						MongoClient mongoClient = connectDatabase(companyName);
						MongoDatabase database = mongoClient.getDatabase(companyName);
						MongoCollection<Document> collection = database.getCollection("Services");
						
						String value = String.valueOf(vector.getSelectedValue());
						String[] result = value.split(": ");
						String[] id = result[1].split(", ");
						int resultID = Integer.parseInt(id[0]);
						//collection.deleteOne(query).first();
						BasicDBObject query = new BasicDBObject("id", resultID);
						collection.deleteOne(query);
						mongoClient.close();
						
						//delete method here from results
						JOptionPane.showMessageDialog(null, "Now Deleting "+value.toString());
						delete.setVisible(false);
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
		return servicePanel;
	}
	
	//search Financials
	public static JPanel deleteFinancials(JPanel footnotes,String companyName) {
		JButton search = new JButton("SEARCH");
		financialPanel = new JPanel();
		financialPanel.setLayout(null);
		JLabel instLabel = new JLabel("1.Search  2.Select One  3.Delete");
		instLabel.setBounds(280,20,300,20);
		instLabel.setForeground(Color.white);
		financialPanel.add(instLabel);
		financialPanel.setBackground(Color.gray);
		JLabel accountLabel = new JLabel("Account name: ");
		JLabel accIDLabel = new JLabel("Account ID: ");
		JLabel bankLabel = new JLabel("Bank: ");
		
		
		ArrayList<JLabel> list = new ArrayList<>();
		list.add(accountLabel);
		list.add(accIDLabel);
		list.add(bankLabel);
		
		
		int x = 10;
		int y = 20;
		for(JLabel label: list) {
			label.setBounds(x, y, 100, 25);
			y += 30;
			financialPanel.add(label);
		}
		
		JTextField accountText = new JTextField();
		JTextField bankText = new JTextField();
		JTextField accountID = new JTextField();
		
		ArrayList<JTextField> list1 = new ArrayList<>();
		list1.add(accountText);
		list1.add(accountID);
		list1.add(bankText);
	
		
		int h = 20;
		int w = 110;
		for(JTextField label: list1) {
			label.setBounds(w, h, 150, 25);
			h += 30;
			financialPanel.add(label);
		}
		JButton delete = new JButton("Delete");
		delete.setBounds(320, 74, 100, 20);
		delete.setVisible(false);
		financialPanel.add(delete);
		
		search.setForeground(Color.white);
	
		search.setBounds(320, 52, 100, 20);
		financialPanel.add(search);
		search.addActionListener(new ActionListener() {
		
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
				
				footnotes.removeAll();
				footnotes.revalidate();
				delete.setVisible(true);
				DefaultListModel document = new DefaultListModel();
				
				String account, accID, bank;
				
				account = accountText.getText();
				accID = accountID.getText();
				bank = bankText.getText();
				
				
				//insert find records for this type
				Find.findFinancials(companyName, account, accID, bank, document);
				
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
				for (JTextField label : list1) {
					label.setText("");
				}
				delete.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						MongoClient mongoClient = connectDatabase(companyName);
						MongoDatabase database = mongoClient.getDatabase(companyName);
						MongoCollection<Document> collection = database.getCollection("Financial Holdings");
						
						String test = String.valueOf(vector.getSelectedValue());
						String[] result = test.split(": ");
						String[] id = result[1].split(", ");
						int resultID = Integer.parseInt(id[0]);
						//collection.deleteOne(query).first();
						BasicDBObject query = new BasicDBObject("id", resultID);
						collection.deleteOne(query);
						mongoClient.close();
						
						//delete method here from results
						JOptionPane.showMessageDialog(null, "Now Deleting "+test.toString());
						delete.setVisible(false);
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
		return financialPanel;
	}

}
