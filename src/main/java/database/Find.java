package database;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import Encryption.Decrypt;
import Encryption.Encrypt;

public class Find {
	private static JPanel panel;
	private static JPanel panel2;
	private static JPanel panel3;
	private static JPanel panel4;
	private static JPanel panel5;
	
	private static MongoClient connectDatabase(String databaseName) {
		
		MongoClientURI uri = new MongoClientURI(""
				+ "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/" + databaseName + "?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true");
		MongoClient mongoClient = new MongoClient(uri);
				
		return mongoClient;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void findEmployee(String databaseName, String firstName, String lastName, String SSN, DefaultListModel document) {

		try {
			
			firstName = Encrypt.encryptData(firstName.toUpperCase());
			lastName = Encrypt.encryptData(lastName.toUpperCase());
			
			Vector<Document> search = new Vector<>();
			
			//connects the app to the mongodb database
			MongoClient mongoClient = connectDatabase(databaseName);
			MongoDatabase database = mongoClient.getDatabase(databaseName);
			MongoCollection<Document> collection = database.getCollection("Employees");
			
			//creates the query for the search method
			BasicDBObject query = new BasicDBObject();
			FindIterable<Document> doc;
			Iterator it;
			int i = 0;
			
			//filter what to search for
			if (firstName.equals("")) {
				if (lastName.equals("")) {
					query.append("ssn", SSN);
					doc = collection.find(query);
					it = doc.iterator();
					while (it.hasNext()) {
						search.add((Document) it.next());

						document.addElement("id: " + String.valueOf(search.get(i).get("id") + ", first name: "
								+ Decrypt.decryptData(search.get(i).get("first name").toString()) + ", last name: "
								+ Decrypt.decryptData(search.get(i).get("last name").toString()) + ", hire year: " + Decrypt.decryptData(search.get(i).get("hire year").toString())
								+ ", ssn: " + Decrypt.decryptData(search.get(i).get("ssn").toString())
								+ ", occupation: " + Decrypt.decryptData(search.get(i).get("occupation").toString())));
						i++;
					}

					mongoClient.close();
				} else if (!SSN.isEmpty()) {
					query.append("last name", lastName).append("ssn", SSN);

					doc = collection.find(query);
					it = doc.iterator();
					while (it.hasNext()) {
						search.add((Document) it.next());

						document.addElement("id: " + String.valueOf(search.get(i).get("id") + ", first name: "
								+ Decrypt.decryptData(search.get(i).get("first name").toString()) + ", last name: "
								+ Decrypt.decryptData(search.get(i).get("last name").toString()) + ", hire year: " + Decrypt.decryptData(search.get(i).get("hire year").toString())
								+ ", ssn: " + Decrypt.decryptData(search.get(i).get("ssn").toString())
								+ ", occupation: " + Decrypt.decryptData(search.get(i).get("occupation").toString())));
					i++;
					}
					mongoClient.close();
				} else {
					query.append("last name", lastName);

					doc = collection.find(query);
					it = doc.iterator();
					while (it.hasNext()) {
						search.add((Document) it.next());
						
						document.addElement("id: " + String.valueOf(search.get(i).get("id") + ", first name: "
								+ Decrypt.decryptData(search.get(i).get("first name").toString()) + ", last name: "
								+ Decrypt.decryptData(search.get(i).get("last name").toString()) + ", hire year: " + Decrypt.decryptData(search.get(i).get("hire year").toString())
								+ ", ssn: " + Decrypt.decryptData(search.get(i).get("ssn").toString())
								+ ", occupation: " + Decrypt.decryptData(search.get(i).get("occupation").toString())));
					i++;
					}

					mongoClient.close();
				}
			} else {
				if (lastName.equals("")) {
					if (SSN.isEmpty()) {
						query.append("first name", firstName);

						doc = collection.find(query);
						it = doc.iterator();
						while (it.hasNext()) {
							search.add((Document) it.next());

							document.addElement("id: " + String.valueOf(search.get(i).get("id") + ", first name: "
									+ Decrypt.decryptData(search.get(i).get("first name").toString()) + ", last name: "
									+ Decrypt.decryptData(search.get(i).get("last name").toString()) + ", hire year: " + Decrypt.decryptData(search.get(i).get("hire year").toString())
									+ ", ssn: " + Decrypt.decryptData(search.get(i).get("ssn").toString())
									+ ", occupation: " + Decrypt.decryptData(search.get(i).get("occupation").toString())));
						i++;
						}

						mongoClient.close();
					}
				} else if (SSN.isEmpty()) {
					query.append("first name", firstName).append("last name", lastName);

					doc = collection.find(query);
					it = doc.iterator();
					while (it.hasNext()) {
						search.add((Document) it.next());

						document.addElement("id: " + String.valueOf(search.get(i).get("id") + ", first name: "
								+ Decrypt.decryptData(search.get(i).get("first name").toString()) + ", last name: "
								+ Decrypt.decryptData(search.get(i).get("last name").toString()) + ", hire year: " + Decrypt.decryptData(search.get(i).get("hire year").toString())
								+ ", ssn: " + Decrypt.decryptData(search.get(i).get("ssn").toString())
								+ ", occupation: " + Decrypt.decryptData(search.get(i).get("occupation").toString())));
					i++;
					}

					mongoClient.close();
				} else {
					query.append("first name", firstName).append("last name", lastName).append("ssn", SSN);

					doc = collection.find(query);
					it = doc.iterator();
					while (it.hasNext()) {
						search.add((Document) it.next());

						document.addElement("id: " + String.valueOf(search.get(i).get("id") + ", first name: "
								+ Decrypt.decryptData(search.get(i).get("first name").toString()) + ", last name: "
								+ Decrypt.decryptData(search.get(i).get("last name").toString()) + ", hire year: " + Decrypt.decryptData(search.get(i).get("hire year").toString())
								+ ", ssn: " + Decrypt.decryptData(search.get(i).get("ssn").toString())
								+ ", occupation: " + Decrypt.decryptData(search.get(i).get("occupation").toString())));
					i++;
					}

					mongoClient.close();
				}

				mongoClient.close();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void findProperty(String databaseName, String property, DefaultListModel document) {

		try {
			
			property = Encrypt.encryptData(property.toUpperCase());
			//propertyName = propertyName.toLowerCase();
			Vector<Document> search = new Vector<>();
			
			//connects the app to the mongodb database
			MongoClient mongoClient = connectDatabase(databaseName);
			MongoDatabase database = mongoClient.getDatabase(databaseName);
			MongoCollection<Document> collection = database.getCollection("Properties");
			
			//creates the query for the search method
			BasicDBObject query = new BasicDBObject();
			FindIterable<Document> doc;
			Iterator it;
			int i = 0;
			
			//filter what to search for
			query.append("property name",property);
			doc = collection.find(query);
			it = doc.iterator();
			while (it.hasNext()) {
					search.add((Document) it.next());
					document.addElement("id: " + String.valueOf(search.get(i).get("id") + ", property name: "
					+ Decrypt.decryptData(search.get(i).get("property name").toString()) + ", cost: $" +  Decrypt.decryptData(search.get(i).get("cost").toString())
					+ ", location: " +Decrypt.decryptData(search.get(i).get("location").toString())));
					i++;
			}
			mongoClient.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void findProducts(String databaseName, String product, String category, String supplier, DefaultListModel document) {

		try {
			

			product = Encrypt.encryptData(product.toUpperCase());
			category = Encrypt.encryptData(category.toUpperCase());
			supplier = Encrypt.encryptData(supplier.toUpperCase());
			
			Vector<Document> search = new Vector<>();
			
			//connects the app to the mongodb database
			MongoClient mongoClient = connectDatabase(databaseName);
			MongoDatabase database = mongoClient.getDatabase(databaseName);
			MongoCollection<Document> collection = database.getCollection("Products");
			
			//creates the query for the search method
			BasicDBObject query = new BasicDBObject();
			FindIterable<Document> doc;
			Iterator it;
			int i = 0;
			
			//filter what to search for
			if (product.equals("")) {
				if (category.equals("")) {
					query.append("supplier", supplier);
					doc = collection.find(query);
					it = doc.iterator();
					while (it.hasNext()) {
						search.add((Document) it.next());

						
						document.addElement("id: " + String.valueOf(search.get(i).get("id") + ", product name: "
								+ Decrypt.decryptData(search.get(i).get("product name").toString()) + ", category: " + Decrypt.decryptData(search.get(i).get("category").toString()) 
								+ ", supplier: " + Decrypt.decryptData(search.get(i).get("supplier").toString())  + ",cost: "
								+ Decrypt.decryptData(search.get(i).get("cost").toString())));
						i++;
					}

					mongoClient.close();
				} else if (!supplier.isEmpty()) {
					query.append("category", category).append("supplier", supplier);

					doc = collection.find(query);
					it = doc.iterator();
					while (it.hasNext()) {
						search.add((Document) it.next());

						document.addElement("id: " + String.valueOf(search.get(i).get("id") + ", product name: "
								+ Decrypt.decryptData(search.get(i).get("product name").toString()) + ", category: " + Decrypt.decryptData(search.get(i).get("category").toString()) 
								+ ", supplier: " + Decrypt.decryptData(search.get(i).get("supplier").toString())  + ",cost: "
								+ Decrypt.decryptData(search.get(i).get("cost").toString())));
						i++;
					}

					mongoClient.close();
				} else {
					query.append("category", category);

					doc = collection.find(query);
					it = doc.iterator();
					while (it.hasNext()) {
						search.add((Document) it.next());
						
						document.addElement("id: " + String.valueOf(search.get(i).get("id") + ", product name: "
								+ Decrypt.decryptData(search.get(i).get("product name").toString()) + ", category: " + Decrypt.decryptData(search.get(i).get("category").toString()) 
								+ ", supplier: " + Decrypt.decryptData(search.get(i).get("supplier").toString())  + ",cost: "
								+ Decrypt.decryptData(search.get(i).get("cost").toString())));
						i++;
					}

					mongoClient.close();
				}
			} else {
				if (category.equals("")) {
					if (supplier.isEmpty()) {
						query.append("product name", product);

						doc = collection.find(query);
						it = doc.iterator();
						while (it.hasNext()) {
							search.add((Document) it.next());

							document.addElement("id: " + String.valueOf(search.get(i).get("id") + ", product name: "
									+ Decrypt.decryptData(search.get(i).get("product name").toString()) + ", category: " + Decrypt.decryptData(search.get(i).get("category").toString()) 
									+ ", supplier: " + Decrypt.decryptData(search.get(i).get("supplier").toString())  + ",cost: "
									+ Decrypt.decryptData(search.get(i).get("cost").toString())));
							i++;
						}

						mongoClient.close();
					}
				} else if (supplier.isEmpty()) {
					query.append("product name", product).append("category", category);

					doc = collection.find(query);
					it = doc.iterator();
					while (it.hasNext()) {
						search.add((Document) it.next());

						document.addElement("id: " + String.valueOf(search.get(i).get("id") + ", product name: "
								+ Decrypt.decryptData(search.get(i).get("product name").toString()) + ", category: " + Decrypt.decryptData(search.get(i).get("category").toString()) 
								+ ", supplier: " + Decrypt.decryptData(search.get(i).get("supplier").toString())  + ",cost: "
								+ Decrypt.decryptData(search.get(i).get("cost").toString())));
						i++;
					}

					mongoClient.close();
				} else {
					query.append("product name", product).append("category", category).append("supplier", supplier);

					doc = collection.find(query);
					it = doc.iterator();
					while (it.hasNext()) {
						search.add((Document) it.next());

						document.addElement("id: " + String.valueOf(search.get(i).get("id") + ", product name: "
								+ Decrypt.decryptData(search.get(i).get("product name").toString()) + ", category: " + Decrypt.decryptData(search.get(i).get("category").toString()) 
								+ ", supplier: " + Decrypt.decryptData(search.get(i).get("supplier").toString())  + ",cost: "
								+ Decrypt.decryptData(search.get(i).get("cost").toString())));
						i++;
					}

					mongoClient.close();
				}

				mongoClient.close();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}//search method for Employee 
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void findService(String databaseName, String service, String category, DefaultListModel document) {

		try {
			//Decrypt p = new Decrypt();

			service = Encrypt.encryptData(service.toUpperCase());
			category = Encrypt.encryptData(category.toUpperCase());
			
			Vector<Document> search = new Vector<>();
			
			//connects the app to the mongodb database
			MongoClient mongoClient = connectDatabase(databaseName);
			MongoDatabase database = mongoClient.getDatabase(databaseName);
			MongoCollection<Document> collection = database.getCollection("Services");
			
			//creates the query for the search method
			BasicDBObject query = new BasicDBObject();
			FindIterable<Document> doc;
			Iterator it;
			int i = 0;
			
			//filter what to search for
			if(category.equals("")) {
				query.append("service name", service);
				doc = collection.find(query);
				it = doc.iterator();
				while (it.hasNext()) {
					search.add((Document) it.next());
	
					document.addElement("id: " + String.valueOf(search.get(i).get("id") + ", service name: "
							+ Decrypt.decryptData(search.get(i).get("service name").toString()) + ", cost: " + Decrypt.decryptData(search.get(i).get("cost").toString())
							+ ", category: " + Decrypt.decryptData(search.get(i).get("category").toString())));
					i++;
				}
			}
			else {
				query.append("category", category);
				doc = collection.find(query);
				it = doc.iterator();
				while (it.hasNext()) {
					search.add((Document) it.next());
	
					document.addElement("id: " + String.valueOf(search.get(i).get("id") + ", service name: "
							+ Decrypt.decryptData(search.get(i).get("service name").toString()) + ", cost: " + Decrypt.decryptData(search.get(i).get("cost").toString())
							+ ", category: " + Decrypt.decryptData(search.get(i).get("category").toString())));
					i++;
				}
			}
			mongoClient.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void findFinancials(String databaseName, String accountName, String accountID, String bank, DefaultListModel document) {
//work on find encryupt search decrypt output
		try {
		

			accountName = Encrypt.encryptData(accountName.toUpperCase());
			accountID = Encrypt.encryptData(accountID);
			bank =  Encrypt.encryptData(bank.toUpperCase());
			
			Vector<Document> search = new Vector<>();
			
			//connects the app to the mongodb database
			MongoClient mongoClient = connectDatabase(databaseName);
			MongoDatabase database = mongoClient.getDatabase(databaseName);
			MongoCollection<Document> collection = database.getCollection("Financial Holdings");
			
			//creates the query for the search method
			BasicDBObject query = new BasicDBObject();
			FindIterable<Document> doc;
			Iterator it;
			int i = 0;
			
			//filter what to search for
			if (accountName.equals("")) {
				if (accountID.equals("")) {
					query.append("bank", bank);
					doc = collection.find(query);
					it = doc.iterator();
					while (it.hasNext()) {
						search.add((Document) it.next());

						document.addElement("id: " + String.valueOf(search.get(i).get("id") + ", account name: "
								+  Decrypt.decryptData(search.get(i).get("account name").toString()) + ", account number: " + Decrypt.decryptData(search.get(i).get("account number").toString())
								+ ", bank: " +  Decrypt.decryptData(search.get(i).get("bank").toString()) + ", balance: "
								+  Decrypt.decryptData(search.get(i).get("balance").toString())));
						i++;
					}

					mongoClient.close();
				} else if (!bank.isEmpty()) {
					query.append("account number", accountID).append("bank", bank);

					doc = collection.find(query);
					it = doc.iterator();
					while (it.hasNext()) {
						search.add((Document) it.next());

						document.addElement("id: " + String.valueOf(search.get(i).get("id") + ", account name: "
								+  Decrypt.decryptData(search.get(i).get("account name").toString()) + ", account number: " + Decrypt.decryptData(search.get(i).get("account number").toString())
								+ ", bank: " +  Decrypt.decryptData(search.get(i).get("bank").toString()) + ", balance: "
								+  Decrypt.decryptData(search.get(i).get("balance").toString())));
						i++;
					}

					mongoClient.close();
				} else {
					query.append("account number", accountID);

					doc = collection.find(query);
					it = doc.iterator();
					while (it.hasNext()) {
						search.add((Document) it.next());
						
						document.addElement("id: " + String.valueOf(search.get(i).get("id") + ", account name: "
								+  Decrypt.decryptData(search.get(i).get("account name").toString()) + ", account number: " + Decrypt.decryptData(search.get(i).get("account number").toString())
								+ ", bank: " +  Decrypt.decryptData(search.get(i).get("bank").toString()) + ", balance: "
								+  Decrypt.decryptData(search.get(i).get("balance").toString())));
						i++;
					}

					mongoClient.close();
				}
			} else {
				if (accountID.equals("")) {
					if (bank.isEmpty()) {
						query.append("account name", accountName);

						doc = collection.find(query);
						it = doc.iterator();
						while (it.hasNext()) {
							search.add((Document) it.next());

							document.addElement("id: " + String.valueOf(search.get(i).get("id") + ", account name: "
									+  Decrypt.decryptData(search.get(i).get("account name").toString()) + ", account number: " + Decrypt.decryptData(search.get(i).get("account number").toString())
									+ ", bank: " +  Decrypt.decryptData(search.get(i).get("bank").toString()) + ", balance: "
									+  Decrypt.decryptData(search.get(i).get("balance").toString())));
							i++;
						}

						mongoClient.close();
					}
				} else if (bank.isEmpty()) {
					query.append("account name", accountName).append("account number", accountID);

					doc = collection.find(query);
					it = doc.iterator();
					while (it.hasNext()) {
						search.add((Document) it.next());

						document.addElement("id: " + String.valueOf(search.get(i).get("id") + ", account name: "
								+  Decrypt.decryptData(search.get(i).get("account name").toString()) + ", account ID: " + Decrypt.decryptData(search.get(i).get("account ID").toString())
								+ ", bank: " +  Decrypt.decryptData(search.get(i).get("bank").toString()) + ", balance: "
								+  Decrypt.decryptData(search.get(i).get("balance").toString())));
						i++;
					}

					mongoClient.close();
				} else {
					query.append("account name", accountName).append("account number", accountID).append("bank", bank);

					doc = collection.find(query);
					it = doc.iterator();
					while (it.hasNext()) {
						search.add((Document) it.next());

						document.addElement("id: " + String.valueOf(search.get(i).get("id") + ", account name: "
								+  Decrypt.decryptData(search.get(i).get("account name").toString()) + ", account ID: " + Decrypt.decryptData(search.get(i).get("account ID").toString())
								+ ", bank: " +  Decrypt.decryptData(search.get(i).get("bank").toString()) + ", balance: "
								+  Decrypt.decryptData(search.get(i).get("balance").toString())));
						i++;
					}

					mongoClient.close();
				}

				mongoClient.close();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//COME IN GETTER :)
	public static JPanel searchEmployee(JPanel footnotes,String companyName) {
		JButton button = new JButton("SEARCH");
		panel = new JPanel();
		panel.setLayout(null);
		
		
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
			panel.add(label);
		}
		
		JTextField firstText = new JTextField();
		JTextField lastText = new JTextField();
		JTextField hireText = new JTextField();
		
		ArrayList<JTextField> list1 = new ArrayList<>();
		list1.add(firstText);
		list1.add(lastText);
		list1.add(hireText);
		
		int h = 20;
		int w = 100;
		for(JTextField label: list1) {
			label.setBounds(w, h, 150, 25);
			h += 30;
			panel.add(label);
		}
		

		
		button.setForeground(Color.BLACK);
		button.setOpaque(true);
		button.setBounds(320, 52, 100, 20);
		panel.add(button);
		
		button.addActionListener(new ActionListener() {
		
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				String hireYear = hireText.getText().replace("-", "");
				
				
				footnotes.removeAll();
				footnotes.revalidate();
			
				button.setVisible(true);
				
				
				String firstName = firstText.getText();
				String lastName = lastText.getText();
				
				String db = "northwind";
				
				
				DefaultListModel document = new DefaultListModel();
				
				findEmployee(db, firstName, lastName, hireYear, document);
				
				JList vector = new JList(document);
								
				JScrollPane scroll = new JScrollPane(vector);
				vector.setVisibleRowCount(5);
				vector.setLayoutOrientation(JList.VERTICAL);
				vector.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
				
				scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				scroll.setVisible(true);
				footnotes.add(scroll, BorderLayout.CENTER);
				
			
				
				footnotes.revalidate();
			
			}
		});
		return panel;
	}

	//Property search method panel
	public static JPanel searchProperty(JPanel footnotes,String companyName) {
		panel2 = new JPanel();
		panel2.setLayout(null);
		JButton button2 = new JButton("SEARCH");
				
		JLabel firstLabel = new JLabel("Property Name: ");
		
		
		ArrayList<JLabel> list = new ArrayList<>();
		list.add(firstLabel);
		
		
		int x = 10;
		int y = 20;
		for(JLabel label: list) {
			label.setBounds(x, y, 120, 25);
			y += 30;
			panel2.add(label);
		}
		
		JTextField firstText = new JTextField();
		
		
		ArrayList<JTextField> list1 = new ArrayList<>();
		list1.add(firstText);
		
		
		int h = 20;
		int w = 100;
		for(JTextField label: list1) {
			label.setBounds(w, h, 150, 25);
			h += 30;
			panel2.add(label);
		}
		

		
		button2.setForeground(Color.BLACK);
		button2.setOpaque(true);
		button2.setBounds(320, 52, 100, 20);
		panel2.add(button2);
		button2.addActionListener(new ActionListener() {
		
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
			
				footnotes.removeAll();
				footnotes.revalidate();
				
				DefaultListModel document = new DefaultListModel();
				//searches by property name
				
				findProperty( companyName,firstText.getText(), document);
				
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
				footnotes.revalidate();
				
			
			}
		});
		return panel2;
	}
	
	//Products search
	public static JPanel searchProduct(JPanel footnotes,String companyName) {
		JButton button3 = new JButton("SEARCH");
		panel3 = new JPanel();
		panel3.setLayout(null);
		
		
		JLabel firstLabel = new JLabel("Product name: ");
		JLabel lastLabel = new JLabel("Category: ");
		JLabel hireLabel = new JLabel("Supplier: ");
		
		
		ArrayList<JLabel> list = new ArrayList<>();
		list.add(firstLabel);
		list.add(lastLabel);
		list.add(hireLabel);
		
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
		
		ArrayList<JTextField> list1 = new ArrayList<>();
		list1.add(firstText);
		list1.add(lastText);
		list1.add(hireText);
		
		int h = 20;
		int w = 100;
		for(JTextField label: list1) {
			label.setBounds(w, h, 150, 25);
			h += 30;
			panel3.add(label);
		}
		

		
		button3.setForeground(Color.BLACK);
		button3.setOpaque(true);
		button3.setBounds(320, 52, 100, 20);
		panel3.add(button3);
		button3.addActionListener(new ActionListener() {
		
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
				
			
				
				footnotes.removeAll();
				footnotes.revalidate();				
				
				DefaultListModel document = new DefaultListModel();
				//insert find records for this type
				//Find.findRecords(firstName, lastName, document);
				findProducts(companyName, firstText.getText(), lastText.getText(), hireText.getText(), document) ;
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
				footnotes.revalidate();
			
				
			}
		});
		return panel3;
	}
	
	//search service
	public static JPanel searchService(JPanel footnotes,String companyName) {
		JButton button4 = new JButton("SEARCH");
		panel4 = new JPanel();
		panel4.setLayout(null);
		
		
		JLabel firstLabel = new JLabel("Service name: ");
		JLabel lastLabel = new JLabel("Category: ");
		
		
		ArrayList<JLabel> list = new ArrayList<>();
		list.add(firstLabel);
		list.add(lastLabel);
	
		
		int x = 10;
		int y = 20;
		for(JLabel label: list) {
			label.setBounds(x, y, 80, 25);
			y += 30;
			panel4.add(label);
		}
		
		JTextField firstText = new JTextField();
		JTextField lastText = new JTextField();
		
		
		ArrayList<JTextField> list1 = new ArrayList<>();
		list1.add(firstText);
		list1.add(lastText);
	
		
		int h = 20;
		int w = 100;
		for(JTextField label: list1) {
			label.setBounds(w, h, 150, 25);
			h += 30;
			panel4.add(label);
		}
		

		
		button4.setForeground(Color.BLACK);
		button4.setOpaque(true);
		button4.setBounds(320, 52, 100, 20);
		panel4.add(button4);
		button4.addActionListener(new ActionListener() {
		
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
				footnotes.removeAll();
				footnotes.revalidate();
				
				DefaultListModel document = new DefaultListModel();
				//insert find records for this type
				//Find.findRecords(firstName, lastName, hireYear, document);
				findService(companyName, firstText.getText(), lastText.getText(), document) ;
				
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
				footnotes.revalidate();
				
			}
		});
		return panel4;
	}
	
	//search Financials
	public static JPanel searchFinancials(JPanel footnotes,String companyName) {
		JButton button5 = new JButton("SEARCH");
		panel5 = new JPanel();
		panel5.setLayout(null);
		
		
		JLabel firstLabel = new JLabel("Account name: ");
		JLabel accountLabel = new JLabel("Account Number: ");
		JLabel lastLabel = new JLabel("Bank: ");
		
		
		ArrayList<JLabel> list = new ArrayList<>();
		list.add(firstLabel);
		list.add(accountLabel);
		list.add(lastLabel);
		
		
		int x = 10;
		int y = 20;
		for(JLabel label: list) {
			label.setBounds(x, y, 100, 25);
			y += 30;
			panel5.add(label);
		}
		
		JTextField firstText = new JTextField();
		JTextField lastText = new JTextField();
		JTextField banlText = new JTextField();
		
		ArrayList<JTextField> list1 = new ArrayList<>();
		list1.add(firstText);
		list1.add(banlText);
		list1.add(lastText);
	
		
		int h = 20;
		int w = 100;
		for(JTextField label: list1) {
			label.setBounds(w, h, 150, 25);
			h += 30;
			panel5.add(label);
		}
		
		button5.setForeground(Color.BLACK);
		button5.setOpaque(true);
		button5.setBounds(320, 52, 100, 20);
		panel5.add(button5);
		button5.addActionListener(new ActionListener() {
		
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
				
				footnotes.removeAll();
				footnotes.revalidate();
				
				DefaultListModel document = new DefaultListModel();
				//insert find records for this type
				//Find.findRecords(firstName, lastName, hireYear, document);
				findFinancials(companyName, firstText.getText(), lastText.getText(), banlText.getText(), document) ;
				
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
				footnotes.revalidate();
			
			
			}
		});
		return panel5;
	}
}
