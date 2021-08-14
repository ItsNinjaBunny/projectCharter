package database;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
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
	
	
	public static void updateEmployee(String companyName,String ssn, String firstName, String lastName, String hireYear, String occupation ) {

		try {
			
			

			MongoClientURI uri = new MongoClientURI("" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/" + companyName + "?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true" );
			MongoClient mongoClient = new MongoClient(uri);
			MongoDatabase database = mongoClient.getDatabase(companyName);			
			MongoCollection<Document> collection = database.getCollection("Employees");
			Document test = collection.find(new Document("ssn", ssn)).first();
			if(test.get("ssn")==ssn) {
				JOptionPane.showMessageDialog(null, "Now Updating"+test.toString());
				
				System.out.println(test.toString());
				collection.updateOne(Filters.eq("ssn", ssn), Updates.set("first name", firstName));
				collection.updateOne(Filters.eq("ssn", ssn), Updates.set("last name", lastName));
				collection.updateOne(Filters.eq("ssn", ssn), Updates.set("hire year", hireYear));
				collection.updateOne(Filters.eq("ssn", ssn), Updates.set("hire year", hireYear));
			}else {
				JOptionPane.showMessageDialog(null, "The Employee does not exist in our system. Make sure you are entering in the correct Employee ssn");
				
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
	private static JProgressBar progressBar1 = new JProgressBar();
	private static JProgressBar progressBar2 = new JProgressBar();
	private static JProgressBar progressBar3 = new JProgressBar();
	private static JProgressBar progressBar4 = new JProgressBar();
	private static JProgressBar progressBar5 = new JProgressBar();

	public static JPanel createEmployeeTab(String companyName) {
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
				if (lFirstName.getText().isEmpty() || lLastName.getText().isEmpty() || lHireYear.getText().isEmpty() ||lSocial.getText().isEmpty()||lOccupation.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Incorrect credentials one or more fields left blank");
					progressBar1.setValue(0);
				}else{
				progressBar1.setValue(40);
				progressBar1.setVisible(true);
				progressBar1.setValue(40);
				JOptionPane.showMessageDialog(null, "Uploading Employee...");
				
				p.revalidate();
				
				Encrypt p2 = new Encrypt();
				String ssn = lSocial.getText().replace("-","");
				//make all strings capital then encode them then put them in the 
				//insert methods do this for all uploads csv and manual entry
				updateEmployee(companyName, lFirstName.getText(), lLastName.getText(),lHireYear.getText() ,
						ssn, lOccupation.getText());
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

}
