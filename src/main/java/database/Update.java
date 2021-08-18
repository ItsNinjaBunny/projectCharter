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
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import Encryption.Encrypt;

public class Update {

	private static JPanel employeePanel;
	private static JPanel propertyPanel;
	private static JPanel productPanel;
	private static JPanel servicePanel;
	private static JPanel financialPanel;
	private static int resultID;
	private static String[] tester;
	
	private static MongoClient connectDatabase(String databaseName) {
		
		MongoClientURI uri = new MongoClientURI("" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/" + databaseName + "?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true");
		MongoClient mongoClient = new MongoClient(uri);
				
		return mongoClient;
	}
	
	public static JPanel updateEmployee(JPanel footnotes,String companyName) {
		JButton search = new JButton("SEARCH");
		employeePanel = new JPanel();
		employeePanel.setLayout(null);
		employeePanel.setBackground(Color.gray);
		
		JLabel firstLabel = new JLabel("First name: ");
		JLabel lastLabel = new JLabel("Last name: ");
		JLabel ssnLabel = new JLabel("SSN: ");
		JLabel hireYearLabel = new JLabel("Hire Year:");
		JLabel occupationLabel = new JLabel("Occupation:");
		JLabel instLabel = new JLabel("1.Search  2.Select One  3.Update");
		instLabel.setBounds(280,20,300,20);
		instLabel.setForeground(Color.white);
		employeePanel.add(instLabel);
		ArrayList<JLabel> list = new ArrayList<>();
		list.add(firstLabel);
		list.add(lastLabel);
		list.add(ssnLabel);
		list.add(hireYearLabel);
		list.add(occupationLabel);
		hireYearLabel.setVisible(false);
		occupationLabel.setVisible(false);
		int x = 10;
		int y = 20;
		for(JLabel label: list) {
			label.setBounds(x, y, 80, 25);
			y += 30;
			label.setForeground(Color.white);
			employeePanel.add(label);
		}
		
		JTextField firstText = new JTextField();
		JTextField lastText = new JTextField();
		JTextField ssn = new JTextField();
		JTextField hireText = new JTextField(20);
		JTextField occupationText = new JTextField(20);
		ArrayList<JTextField> list1 = new ArrayList<>();
		list1.add(firstText);
		list1.add(lastText);
		list1.add(ssn);
		list1.add(hireText);
		list1.add(occupationText);
		hireText.setVisible(false);
		occupationText.setVisible(false);
		
		int h = 20;
		int w = 100;
		for(JTextField label: list1) {
			label.setBounds(w, h, 150, 25);
			h += 30;
			label.setBackground(Color.LIGHT_GRAY);
		
			employeePanel.add(label);
		}
		

		search.setBackground(Color.black);
		search.setForeground(Color.white);
	
		search.setBounds(320, 52, 100, 20);
		employeePanel.add(search);
		
		JButton update = new JButton("UPDATE");
		update.setBounds(320, 74, 100, 20);
		update.setVisible(false);
		JButton upload = new JButton("UPLOAD");
		
		upload.setBounds(320, 74, 100, 20);
		upload.setVisible(false);
		employeePanel.add(upload);
		upload.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				//do update function here
				//JOptionPane.showMessageDialog(null, "Updating...");
				search.setVisible(true);
				upload.setVisible(false);
				hireYearLabel.setVisible(false);
				occupationLabel.setVisible(false);
				hireText.setVisible(false);
				occupationText.setVisible(false);
				footnotes.removeAll();
				firstText.setText("");
				lastText.setText("");
				ssn.setText("");
				hireText.setText("");
				occupationText.setText("");
				JScrollPane scroll = new JScrollPane();
				scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				scroll.setVisible(true);
				footnotes.add(scroll, BorderLayout.CENTER);
			}});
		employeePanel.add(update);
		
				
		search.addActionListener(new ActionListener() {
		
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				String hireYear = ssn.getText().replace("-", "");
				
				
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
						String[] result = test.split(": ");
						String[] id = result[1].split(", ");
						tester = id;
						
						//System.out.println(resultID);
						footnotes.removeAll();
						//Update method here from results
						instLabel.setVisible(false);
						instLabel.setText("1.Update Fields  2.Upload ");
						instLabel.setVisible(true);
						update.setVisible(false);
						upload.setVisible(true);
						hireYearLabel.setVisible(true);
						occupationLabel.setVisible(true);
						hireText.setVisible(true);
						occupationText.setVisible(true);
						firstText.setText("");
						lastText.setText("");
						ssn.setText("");
//						firstText.setText(result[2].replace("last name", ""));
//						lastText.setText(result[3].replace("hire year", ""));
//						hireText.setText(result[6].replace("occupation", ""));
//						lHireYear.setText(result[4].replace("ssn",""));
//						lOccupation.setText(result[5]);
						
						
						
						upload.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								//do update function here
								resultID = Integer.parseInt(tester[0]);
								
								MongoClient mongoClient = connectDatabase(companyName);
								MongoDatabase database = mongoClient.getDatabase(companyName);
								MongoCollection<Document> collection = database.getCollection("Employees");
								
								JOptionPane.showMessageDialog(null, "Updating...");
								search.setVisible(true);
								upload.setVisible(false);
								hireYearLabel.setVisible(false);
								occupationLabel.setVisible(false);
								hireText.setVisible(false);
								occupationText.setVisible(false);
								footnotes.removeAll();
								
								
								
								String first = firstText.getText().toUpperCase();
								String last = lastText.getText().toUpperCase();
								String ssnText = ssn.getText().toUpperCase();
								String hire = hireText.getText().toUpperCase();
								String occupation = occupationText.getText().toUpperCase();
								
								if(!first.equals(""))
									collection.updateOne(Filters.eq("id", resultID), Updates.set("first name", Encrypt.encryptData(first)));
								if(!last.equals(""))
									collection.updateOne(Filters.eq("id", resultID), Updates.set("last name", Encrypt.encryptData(last)));
								if(!ssnText.equals(""))
									collection.updateOne(Filters.eq("id", resultID), Updates.set("ssn", Encrypt.encryptData(ssnText)));
								if(!hire.equals(""))
									collection.updateOne(Filters.eq("id", resultID), Updates.set("hire year", Encrypt.encryptData(hire)));
								if(!occupation.equals(""))
									collection.updateOne(Filters.eq("id", resultID), Updates.set("occupation", Encrypt.encryptData(occupation)));
				
								mongoClient.close();
								firstText.setText("");
								lastText.setText("");
								ssn.setText("");
								hireText.setText("");
								occupationText.setText("");
								instLabel.setVisible(false);
								instLabel.setText("1.Search  2.Select One  3.Update");
								instLabel.setVisible(true);
								footnotes.removeAll();
								JScrollPane scroll = new JScrollPane();
								scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
								scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
								scroll.setVisible(true);
								footnotes.add(scroll, BorderLayout.CENTER);
								footnotes.revalidate();
							}});
					}
				});
				
				footnotes.revalidate();
			
			}
		});
		return employeePanel;
	}

	//Property update method panel
	public static JPanel updateProperty(JPanel footnotes,String companyName) {
		propertyPanel = new JPanel();
		propertyPanel.setLayout(null);
		propertyPanel.setBackground(Color.gray);
		JLabel instLabel = new JLabel("1.Search  2.Select One  3.Update");
		instLabel.setBounds(280,20,300,20);
		instLabel.setForeground(Color.white);
		propertyPanel.add(instLabel);
		JButton search = new JButton("SEARCH");
				
		JLabel propertyLabel = new JLabel("Property Name: ");
		JLabel costLabel = new JLabel("Cost: ");
		JLabel locationLabel = new JLabel("Location: ");
		ArrayList<JLabel> list = new ArrayList<>();
		list.add(propertyLabel);
		list.add(costLabel);
		list.add(locationLabel);
		costLabel.setVisible(false);
		locationLabel.setVisible(false);
		
		
	
		int x = 10;
		int y = 20;
		for(JLabel label: list) {
			label.setBounds(x, y, 120, 25);
			y += 30;
			label.setForeground(Color.white);
			propertyPanel.add(label);
		}
		
		JTextField propertyText = new JTextField();
		JTextField costText = new JTextField();
		JTextField locationText = new JTextField();
		ArrayList<JTextField> list1 = new ArrayList<>();
		list1.add(propertyText);
		list1.add(costText);
		list1.add(locationText);
		
		locationText.setVisible(false);
		costText.setVisible(false);
		int h = 20;
		int w = 100;
		for(JTextField label: list1) {
			label.setBounds(w, h, 150, 25);
			h += 30;
			label.setBackground(Color.LIGHT_GRAY);
			propertyPanel.add(label);
		}
		
		JButton update = new JButton("UPDATE");
		update.setBounds(320, 74, 100, 20);
		update.setVisible(false);
		propertyPanel.add(update);
		search.setBackground(Color.black);
		search.setForeground(Color.white);
		search.setBounds(320, 52, 100, 20);
		propertyPanel.add(search);
		
		JButton upload = new JButton("UPLOAD");
		upload.setBounds(320, 74, 100, 20);
		upload.setVisible(false);
		propertyPanel.add(upload);
		upload.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				//do update function here
				//JOptionPane.showMessageDialog(null, "Updating...");
				search.setVisible(true);
				upload.setVisible(false);
				costLabel.setVisible(false);
				locationLabel.setVisible(false);
				costText.setVisible(false);
				locationText.setVisible(false);
				footnotes.removeAll();
				costText.setText("");
				locationText.setText("");
				propertyText.setText("");
				footnotes.removeAll();
				footnotes.setBackground(Color.gray);
				
								
			}});
		search.addActionListener(new ActionListener() {
		
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
			
				footnotes.removeAll();
				footnotes.revalidate();
				update.setVisible(true);
				DefaultListModel document = new DefaultListModel();
				//searches by property name
				Find.findProperty(companyName,propertyText.getText(), document);
				
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
				
				
				update.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						costLabel.setVisible(true);
						locationLabel.setVisible(true);
						locationText.setVisible(true);
						costText.setVisible(true);
						update.setVisible(true);
						instLabel.setVisible(false);
						instLabel.setText("1.Update Fields  2.Upload ");
						instLabel.setVisible(true);
						
						String test = String.valueOf(vector.getSelectedValue());
						
						System.out.println(test);
						String[] result = test.split(": ");
						String[] id = result[1].split(", ");
						tester = id;
						update.setVisible(false);
						upload.setVisible(true);
						JScrollPane scroll = new JScrollPane();
						scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
						scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
						scroll.setVisible(true);
						footnotes.add(scroll, BorderLayout.CENTER);
						
						upload.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								
								MongoClient mongoClient = connectDatabase(companyName);
								MongoDatabase database = mongoClient.getDatabase(companyName);
								MongoCollection<Document> collection = database.getCollection("Properties");
								
								resultID = Integer.parseInt(tester[0]);
								
								JOptionPane.showMessageDialog(null, "uploading...");
								
								search.setVisible(true);
								upload.setVisible(false);
								costLabel.setVisible(false);
								locationLabel.setVisible(false);
								footnotes.removeAll();
								
								String property = propertyText.getText().toUpperCase();
								String cost = costText.getText().toUpperCase();
								String location = locationText.getText().toUpperCase();
								
								if(!property.equals(""))
									collection.updateOne(Filters.eq("id", resultID), Updates.set("property name", Encrypt.encryptData(property)));
								if(!cost.equals(""))
									collection.updateOne(Filters.eq("id", resultID), Updates.set("cost", cost));
								if(!location.equals(""))
									collection.updateOne(Filters.eq("id", resultID), Updates.set("location", location));
								
								mongoClient.close();
								propertyText.setText("");
								costText.setText("");
								locationText.setText("");
								instLabel.setVisible(false);
								instLabel.setText("1.Search  2.Select One  3.Update");
								instLabel.setVisible(true);
								footnotes.removeAll();
								JScrollPane scroll = new JScrollPane();
								scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
								scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
								scroll.setVisible(true);
								footnotes.add(scroll, BorderLayout.CENTER);
								footnotes.revalidate();
							}
							
							
						});
						
					}
				});
				footnotes.revalidate();
			
			}
		});
		return propertyPanel;
	}
	
	
	//Update Products 
	public static JPanel updateProduct(JPanel footnotes,String companyName) {
		JButton search = new JButton("SEARCH");
		productPanel = new JPanel();
		productPanel.setLayout(null);
		productPanel.setBackground(Color.gray);
		JLabel instLabel = new JLabel("1.Search  2.Select One  3.Update");
		instLabel.setBounds(280,20,300,20);
		instLabel.setForeground(Color.white);
		productPanel.add(instLabel);
		JLabel productLabel = new JLabel("Product name: ");
		JLabel categoryLabel = new JLabel("Category: ");
		JLabel supplierLabel = new JLabel("Supplier: ");
		JLabel costLabel = new JLabel("Cost: ");
		costLabel.setVisible(false);
		
		ArrayList<JLabel> list = new ArrayList<>();
		list.add(productLabel);
		list.add(categoryLabel);
		list.add(supplierLabel);
		list.add(costLabel);
		int x = 10;
		int y = 20;
		for(JLabel label: list) {
			label.setBounds(x, y, 80, 25);
			y += 30;
			label.setForeground(Color.white);
			productPanel.add(label);
		}
		
		JTextField productText = new JTextField();
		JTextField categoryText = new JTextField();
		JTextField supplierText = new JTextField();
		JTextField costText = new JTextField();
		costText.setVisible(false);
		
		ArrayList<JTextField> list1 = new ArrayList<>();
		list1.add(productText);
		list1.add(categoryText);
		list1.add(supplierText);
		list1.add(costText);
		
		int h = 20;
		int w = 100;
		for(JTextField label: list1) {
			label.setBounds(w, h, 150, 25);
			h += 30;
			label.setBackground(Color.LIGHT_GRAY);
			productPanel.add(label);
		}
		

		JButton update = new JButton("UPDATE");
		update.setBounds(320, 74, 100, 20);
		update.setVisible(false);
		productPanel.add(update);
		
		search.setBackground(Color.black);
		search.setForeground(Color.white);
		search.setBounds(320, 52, 100, 20);
		productPanel.add(search);
		JButton upload = new JButton("UPLOAD");
		upload.setBounds(320, 74, 100, 20);
		upload.setVisible(false);
		productPanel.add(upload);
		upload.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//do update function here
				//JOptionPane.showMessageDialog(null, "Updating...");
				search.setVisible(true);
				upload.setVisible(false);
				costLabel.setVisible(false);
				
				costText.setVisible(false);
				
				footnotes.removeAll();
				costText.setText("");
				categoryText.setText("");
				productText.setText("");
				supplierText.setText("");
				footnotes.removeAll();
				footnotes.setBackground(Color.gray);
				
			}});
		search.addActionListener(new ActionListener() {
		
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
				
			
				update.setVisible(true);
				footnotes.removeAll();
				footnotes.revalidate();				
				
				DefaultListModel document = new DefaultListModel();
				//insert find records for this type
				Find.findProducts(companyName, productText.getText() ,categoryText.getText(), supplierText.getText(), document);
				
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
				
				update.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						costText.setVisible(true);
						costLabel.setVisible(true);
						instLabel.setVisible(false);
						instLabel.setText("1.Update Fields  2.Upload ");
						instLabel.setVisible(true);
						
						
						String test = String.valueOf(vector.getSelectedValue());
						
						System.out.println(test);
						String[] result = test.split(": ");
						String[] id = result[1].split(", ");
						tester = id;
						
						update.setVisible(false);
						upload.setVisible(true);
						JScrollPane scroll = new JScrollPane();
						scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
						scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
						scroll.setVisible(true);
						footnotes.add(scroll, BorderLayout.CENTER);
						
						upload.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								
								MongoClient mongoClient = connectDatabase(companyName);
								MongoDatabase database = mongoClient.getDatabase(companyName);
								MongoCollection<Document> collection = database.getCollection("Products");
								
								resultID = Integer.parseInt(tester[0]);
								
								String product = productText.getText().toUpperCase();
								String category = categoryText.getText().toUpperCase();
								String supplier = supplierText.getText().toUpperCase();
								String cost = costText.getText();
								
								if(!product.equals(""))
									collection.updateOne(Filters.eq("id", resultID), Updates.set("product name", Encrypt.encryptData(product)));
								if(!category.equals(""))
									collection.updateOne(Filters.eq("id", resultID), Updates.set("category", Encrypt.encryptData(category)));							
								if(!supplier.equals(""))
									collection.updateOne(Filters.eq("id", resultID), Updates.set("supplier", Encrypt.encryptData(supplier)));
								if(!cost.equals(""))
									collection.updateOne(Filters.eq("id", resultID), Updates.set("cost",  Encrypt.encryptData(cost)));
								
								mongoClient.close();
								productText.setText("");
								categoryText.setText("");
								supplierText.setText("");
								costText.setText("");
								
								instLabel.setVisible(false);
								instLabel.setText("1.Search  2.Select One  3.Update");
								instLabel.setVisible(true);
								footnotes.removeAll();
								JScrollPane scroll = new JScrollPane();
								scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
								scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
								scroll.setVisible(true);
								footnotes.add(scroll, BorderLayout.CENTER);
								footnotes.revalidate();
							}
						});
					}
					
				});
				footnotes.revalidate();
			
				
			}
		});
		return productPanel;
	}
	
	//Update service
	public static JPanel updateService(JPanel footnotes,String companyName) {
		JButton search = new JButton("SEARCH");
		servicePanel = new JPanel();
		servicePanel.setLayout(null);
		servicePanel.setBackground(Color.gray);
		JLabel instLabel = new JLabel("1.Search  2.Select One  3.Update");
		instLabel.setBounds(280,20,300,20);
		instLabel.setForeground(Color.white);
		servicePanel.add(instLabel);
		JLabel serviceLabel = new JLabel("Service name: ");
		JLabel categoryLabel = new JLabel("Category: ");
		JLabel costLabel = new JLabel("Cost: ");
		costLabel.setVisible(false);
		
		ArrayList<JLabel> list = new ArrayList<>();
		list.add(serviceLabel);
		list.add(categoryLabel);
		list.add(costLabel);
		
		int x = 10;
		int y = 20;
		for(JLabel label: list) {
			label.setBounds(x, y, 80, 25);
			y += 30;
			label.setForeground(Color.white);
			servicePanel.add(label);
		}
		
		JTextField serviceText = new JTextField();
		JTextField categoryText = new JTextField();
		JTextField costText = new JTextField();
		costText.setVisible(false);
		
		ArrayList<JTextField> list1 = new ArrayList<>();
		list1.add(serviceText);
		list1.add(categoryText);
		list1.add(costText);
		
		int h = 20;
		int w = 100;
		for(JTextField label: list1) {
			label.setBounds(w, h, 150, 25);
			label.setBackground(Color.LIGHT_GRAY);
			h += 30;
			servicePanel.add(label);
		}
		

		JButton update = new JButton("UPDATE");
		update.setBounds(320, 74, 100, 20);
		update.setVisible(false);
		servicePanel.add(update);
		search.setBackground(Color.black);
		search.setForeground(Color.white);
		search.setBounds(320, 52, 100, 20);
		servicePanel.add(search);
		JButton upload = new JButton("UPLOAD");
		upload.setBounds(320, 74, 100, 20);
		upload.setVisible(false);
		servicePanel.add(upload);
		upload.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//do update function here
				JOptionPane.showMessageDialog(null, "Updating...");
				search.setVisible(true);
				upload.setVisible(false);
				costLabel.setVisible(false);
				
				costText.setVisible(false);
				
				footnotes.removeAll();
				costText.setText("");
				categoryText.setText("");
				serviceText.setText("");
				footnotes.removeAll();
				footnotes.setBackground(Color.gray);
				
				
			}});
		search.addActionListener(new ActionListener() {
		
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				update.setVisible(true);
				footnotes.removeAll();
				footnotes.revalidate();
				
				DefaultListModel document = new DefaultListModel();
				//insert find records for this type
				Find.findService(companyName, serviceText.getText(), categoryText.getText(), document);
				
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
				update.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						costLabel.setVisible(true);
						costText.setVisible(true);
						instLabel.setVisible(false);
						instLabel.setText("1.Update Fields  2.Upload ");
						instLabel.setVisible(true);
						
						String test = String.valueOf(vector.getSelectedValue());
						
						System.out.println(test);
						String[] result = test.split(": ");
						String[] id = result[1].split(", ");
						tester = id;
						//delete method here from results
						update.setVisible(false);
						upload.setVisible(true);
						
						JScrollPane scroll = new JScrollPane();
						scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
						scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
						scroll.setVisible(true);
						footnotes.add(scroll, BorderLayout.CENTER);
						
						upload.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
							
								MongoClient mongoClient = connectDatabase(companyName);
								MongoDatabase database = mongoClient.getDatabase(companyName);
								MongoCollection<Document> collection = database.getCollection("Services");
								
								resultID = Integer.parseInt(tester[0]);
								
								String service = serviceText.getText().toUpperCase();
								String category = categoryText.getText().toUpperCase();
								String cost = costText.getText();
								
								if(!service.equals("")) 
									collection.updateOne(Filters.eq("id", resultID), Updates.set("service name",  Encrypt.encryptData(service)));
								if(!category.equals(""))
									collection.updateOne(Filters.eq("id", resultID), Updates.set("category", Encrypt.encryptData(category)));
								if(!cost.equals(""))
									collection.updateOne(Filters.eq("id", resultID), Updates.set("cost", Encrypt.encryptData(cost)));
								
								mongoClient.close();
								serviceText.setText("");
								categoryText.setText("");
								costText.setText("");
							
								instLabel.setVisible(false);
								instLabel.setText("1.Search  2.Select One  3.Update");
								instLabel.setVisible(true);
								footnotes.removeAll();
								JScrollPane scroll = new JScrollPane();
								scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
								scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
								scroll.setVisible(true);
								footnotes.add(scroll, BorderLayout.CENTER);
								footnotes.revalidate();
							}
						});
					}
				});
				footnotes.revalidate();
				
			}
		});
		return servicePanel;
	}
	
	//Update Financials
	public static JPanel updateFinancials(JPanel footnotes,String companyName) {
		JButton search = new JButton("SEARCH");
		financialPanel = new JPanel();
		financialPanel.setLayout(null);
		financialPanel.setBackground(Color.gray);
		JLabel instLabel = new JLabel("1.Search  2.Select One  3.Update");
		instLabel.setBounds(280,20,300,20);
		instLabel.setForeground(Color.white);
		financialPanel.add(instLabel);
		footnotes.setBackground(Color.gray);
		JLabel accNameLabel = new JLabel("Account name: ");
		JLabel accIDLabel = new JLabel("Account ID: ");
		JLabel bankLabel = new JLabel("Bank: ");
		JLabel balanceLabel = new JLabel("Balance: ");
		balanceLabel.setVisible(false);
		ArrayList<JLabel> list = new ArrayList<>();
		list.add(accNameLabel);
		list.add(accIDLabel);
		list.add(bankLabel);
		list.add(balanceLabel);
		
		int x = 10;
		int y = 20;
		for(JLabel label: list) {
			label.setBounds(x, y, 120, 25);
			y += 30;
			label.setForeground(Color.white);
			financialPanel.add(label);
		}
		
		JTextField accountName = new JTextField();
		JTextField accountID = new JTextField();
		JTextField bankText = new JTextField();
		JTextField balanceText = new JTextField();
		balanceText.setVisible(false);
		
		ArrayList<JTextField> list1 = new ArrayList<>();
		list1.add(accountName);
		list1.add(accountID);
		list1.add(bankText);
		list1.add(balanceText);
		balanceLabel.setVisible(false);
		int h = 20;
		int w = 100;
		for(JTextField label: list1) {
			label.setBounds(w, h, 150, 25);
			h += 30;
			label.setBackground(Color.LIGHT_GRAY);
			financialPanel.add(label);
		}
		JButton update = new JButton("UPDATE");
		update.setBounds(320, 74, 100, 20);
		update.setVisible(false);
		financialPanel.add(update);
		search.setBackground(Color.black);
		search.setForeground(Color.white);
		search.setBounds(320, 52, 100, 20);
		financialPanel.add(search);
		JButton upload = new JButton("UPLOAD");
		upload.setBounds(320, 74, 100, 20);
		upload.setVisible(false);
		financialPanel.add(upload);
		upload.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//do update function here
				JOptionPane.showMessageDialog(null, "Updating...");
				search.setVisible(true);
				upload.setVisible(false);
				balanceText.setVisible(false);
				balanceLabel.setVisible(false);
				
				
				footnotes.removeAll();
				balanceText.setText("");
				bankText.setText("");
				accountID.setText("");
				accountName.setText("");
			
				
				
			}});
		search.addActionListener(new ActionListener() {
		
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
				
				footnotes.removeAll();
				footnotes.revalidate();
				update.setVisible(true);
				DefaultListModel document = new DefaultListModel();
				//insert find records for this type
				Find.findFinancials(companyName, accountName.getText(), accountID.getText(), bankText.getText(), document);
				
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
				update.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						balanceText.setVisible(true);
						balanceLabel.setVisible(true);
						instLabel.setVisible(false);
						instLabel.setText("1.Update Fields  2.Upload ");
						instLabel.setVisible(true);
					
						String test = String.valueOf(vector.getSelectedValue());
						
						System.out.println(test);
						String[] result = test.split(": ");
						String[] id = result[1].split(", ");
						tester = id;
						//delete method here from results
						update.setVisible(false);
						upload.setVisible(true);
						JScrollPane scroll = new JScrollPane();
						scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
						scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
						scroll.setVisible(true);
						footnotes.add(scroll, BorderLayout.CENTER);
						
						upload.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								
								MongoClient mongoClient = connectDatabase(companyName);
								MongoDatabase database = mongoClient.getDatabase(companyName);
								MongoCollection<Document> collection = database.getCollection("Financial Holdings");
								
								resultID = Integer.parseInt(tester[0]);
								
								String account = accountName.getText().toUpperCase();
								String accID = accountID.getText();
								String bank = bankText.getText().toUpperCase();
								String balance = balanceText.getText();
								
								if(!account.equals(""))
									collection.updateOne(Filters.eq("id", resultID), Updates.set("account name", Encrypt.encryptData(account)));
								if(!accID.equals(""))
									collection.updateOne(Filters.eq("id", resultID), Updates.set("account number", Encrypt.encryptData(accID)));
								if(!bank.equals(""))
									collection.updateOne(Filters.eq("id", resultID), Updates.set("bank", Encrypt.encryptData(bank)));
								if(!balance.equals(""))
									collection.updateOne(Filters.eq("id", resultID), Updates.set("balance", Encrypt.encryptData(balance)));
								
								mongoClient.close();
								accountName.setText("");
								accountID.setText("");
								bankText.setText("");
								balanceText.setText("");
								instLabel.setVisible(false);
								instLabel.setText("1.Search  2.Select One  3.Update");
								instLabel.setVisible(true);
								footnotes.removeAll();
								JScrollPane scroll = new JScrollPane();
								scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
								scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
								scroll.setVisible(true);
								footnotes.add(scroll, BorderLayout.CENTER);
								footnotes.revalidate();
							}
						});
					}
				});
				footnotes.revalidate();
			
			
			}
		});
		return financialPanel;
	}

}
