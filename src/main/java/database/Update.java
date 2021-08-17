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
		
		
		JLabel firstLabel = new JLabel("First name: ");
		JLabel lastLabel = new JLabel("Last name: ");
		JLabel ssnLabel = new JLabel("SSN: ");
		JLabel hireYearLabel = new JLabel("Hire Year:");
		JLabel occupationLabel = new JLabel("Occupation:");
		
		ArrayList<JLabel> list = new ArrayList<>();
		list.add(firstLabel);
		list.add(lastLabel);
		list.add(hireYearLabel);
		list.add(hireYearLabel);
		list.add(occupationLabel);
		hireYearLabel.setVisible(false);
		occupationLabel.setVisible(false);
		int x = 10;
		int y = 20;
		for(JLabel label: list) {
			label.setBounds(x, y, 80, 25);
			y += 30;
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
		list1.add(occupationText);
		list1.add(hireText);
		hireText.setVisible(false);
		occupationText.setVisible(false);
		
		int h = 20;
		int w = 100;
		for(JTextField label: list1) {
			label.setBounds(w, h, 150, 25);
			h += 30;
			employeePanel.add(label);
		}
		

		
		search.setForeground(Color.BLACK);
		search.setOpaque(true);
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
				JOptionPane.showMessageDialog(null, "Updating...");
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
								
								MongoClient mongoClient = connectDatabase("northwind");
								MongoDatabase database = mongoClient.getDatabase("northwind");
								MongoCollection<Document> collection = database.getCollection("Employees");
								
								JOptionPane.showMessageDialog(null, "Updating...");
								search.setVisible(true);
								upload.setVisible(false);
								hireYearLabel.setVisible(false);
								occupationLabel.setVisible(false);
								hireText.setVisible(false);
								occupationText.setVisible(false);
								footnotes.removeAll();
								
								
								
								String first = firstText.getText();
								String last = lastText.getText();
								String ssnText = ssn.getText();
								String hire = hireText.getText();
								String occupation = occupationText.getText();
								
								if(!first.equals(""))
									collection.updateOne(Filters.eq("id", resultID), Updates.set("first name", first));
								if(!last.equals(""))
									collection.updateOne(Filters.eq("id", resultID), Updates.set("last name", last));
								if(!ssnText.equals(""))
									collection.updateOne(Filters.eq("id", resultID), Updates.set("ssn", ssnText));
								if(!hire.equals(""))
									collection.updateOne(Filters.eq("id", resultID), Updates.set("hire year", hire));
								if(!occupation.equals(""))
									collection.updateOne(Filters.eq("id", resultID), Updates.set("occupation", occupation));
				
								firstText.setText("");
								lastText.setText("");
								ssn.setText("");
								hireText.setText("");
								occupationText.setText("");
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
		return employeePanel;
	}

	//Property update method panel
	public static JPanel updateProperty(JPanel footnotes,String companyName) {
		propertyPanel = new JPanel();
		propertyPanel.setLayout(null);
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
			propertyPanel.add(label);
		}
		
		JTextField property = new JTextField();
		JTextField costText = new JTextField();
		JTextField locationText = new JTextField();
		ArrayList<JTextField> list1 = new ArrayList<>();
		list1.add(property);
		list1.add(costText);
		list1.add(locationText);
		
		locationText.setVisible(false);
		costText.setVisible(false);
		int h = 20;
		int w = 100;
		for(JTextField label: list1) {
			label.setBounds(w, h, 150, 25);
			h += 30;
			propertyPanel.add(label);
		}
		
		JButton update = new JButton("UPDATE");
		update.setBounds(320, 74, 100, 20);
		update.setVisible(false);
		propertyPanel.add(update);
		search.setForeground(Color.BLACK);
		search.setOpaque(true);
		search.setBounds(320, 52, 100, 20);
		propertyPanel.add(search);
		JButton upload = new JButton("UPLOAD");
		upload.setBounds(320, 74, 100, 20);
		upload.setVisible(false);
		propertyPanel.add(upload);
		upload.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				resultID = Integer.parseInt(tester[0]);
				
				MongoClient mongoClient = connectDatabase("northwind");
				MongoDatabase database = mongoClient.getDatabase("northwind");
				MongoCollection<Document> collection = database.getCollection("Properties");
				
				//do update function here
				JOptionPane.showMessageDialog(null, "Updating...");
				search.setVisible(true);
				upload.setVisible(false);
				costLabel.setVisible(false);
				locationLabel.setVisible(false);
				costText.setVisible(false);
				locationText.setVisible(false);
				footnotes.removeAll();
				costText.setText("");
				locationText.setText("");
				property.setText("");
				
				
								
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
				Find.findProperty(companyName,property.getText(), document);
				
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
					
						String test = String.valueOf(vector.getSelectedValue());
						//collection.deleteOne(query).first();
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
						
					}
				});
				footnotes.revalidate();
			
			}
		});
		return propertyPanel;
	}
	
	
	//Products search
	public static JPanel updateProduct(JPanel footnotes,String companyName) {
		JButton search = new JButton("SEARCH");
		productPanel = new JPanel();
		productPanel.setLayout(null);
		
		
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
			productPanel.add(label);
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
			productPanel.add(label);
		}
		

		JButton update2 = new JButton("UPDATE");
		update2.setBounds(320, 74, 100, 20);
		update2.setVisible(false);
		productPanel.add(update2);
		
		search.setForeground(Color.BLACK);
		search.setOpaque(true);
		search.setBounds(320, 52, 100, 20);
		productPanel.add(search);
		JButton realUpdate = new JButton("-UPDATE-");
		realUpdate.setBounds(320, 74, 100, 20);
		realUpdate.setVisible(false);
		productPanel.add(realUpdate);
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
		return productPanel;
	}
	
	//search service
	public static JPanel updateService(JPanel footnotes,String companyName) {
		JButton search = new JButton("SEARCH");
		servicePanel = new JPanel();
		servicePanel.setLayout(null);
		
		
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
			servicePanel.add(label);
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
			servicePanel.add(label);
		}
		

		JButton update3 = new JButton("UPDATE");
		update3.setBounds(320, 74, 100, 20);
		update3.setVisible(false);
		servicePanel.add(update3);
		search.setForeground(Color.BLACK);
		search.setOpaque(true);
		search.setBounds(320, 52, 100, 20);
		servicePanel.add(search);
		JButton realUpdate = new JButton("-UPDATE-");
		realUpdate.setBounds(320, 74, 100, 20);
		realUpdate.setVisible(false);
		servicePanel.add(realUpdate);
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
		return servicePanel;
	}
	
	//search Financials
	public static JPanel updateFinancials(JPanel footnotes,String companyName) {
		JButton search = new JButton("SEARCH");
		financialPanel = new JPanel();
		financialPanel.setLayout(null);
		
		
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
			financialPanel.add(label);
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
			financialPanel.add(label);
		}
		JButton update2 = new JButton("UPDATE");
		update2.setBounds(320, 74, 100, 20);
		update2.setVisible(false);
		financialPanel.add(update2);
		
		search.setForeground(Color.BLACK);
		search.setOpaque(true);
		search.setBounds(320, 52, 100, 20);
		financialPanel.add(search);
		JButton realUpdate = new JButton("-UPDATE-");
		realUpdate.setBounds(320, 74, 100, 20);
		realUpdate.setVisible(false);
		financialPanel.add(realUpdate);
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
		return financialPanel;
	}

}
