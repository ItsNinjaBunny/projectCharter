package database;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
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
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class Update {
	
	private static JPanel panel;
	private static Vector<String> found = new Vector<String>();	
	private static JScrollPane scroller = new JScrollPane();
	private static JPanel tester = new JPanel();
	private static JButton button = new JButton();
	
	
	public static void updateEmployee(String companyName, String collectionName) {

		try {

			int id = 0;
			System.out.print("What employee would you like to update: ");
		
			String updateFirstName = "", updateLastName = "";
			int hireYear = 0;

			System.out.print("What do you want to change the first name to: ");
			
			System.out.print("What do you want to change the last name to: ");
			
			System.out.print("What do you want to change the hire year to: ");
			

			MongoClientURI uri = new MongoClientURI("" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/" + companyName + "?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true" );
			MongoClient mongoClient = new MongoClient(uri);
			MongoDatabase database = mongoClient.getDatabase(companyName);			
			MongoCollection<Document> collection = database.getCollection(collectionName);
			Document test = collection.find(new Document("id", id)).first();
			System.out.println("\nNOW UPDATING...\n\n\nObjectID: " + test.get("_id") + "\nID:" + test.get("id")
					+ "\nFirst Name: " + test.get("first name") + "\nLast Name: " + test.get("last name")
					+ "\nHire Year: " + test.get("hire year"));
			collection.updateOne(Filters.eq("id", id), Updates.set("first name", updateFirstName));
			collection.updateOne(Filters.eq("id", id), Updates.set("last name", updateLastName));
			collection.updateOne(Filters.eq("id", id), Updates.set("hire year", hireYear));
			System.out.println("Updated to");
			Document test1 = collection.find(new Document("id", id)).first();
			System.out.println("\nNEW CREDENTIALS...\n\n\nObjectID: " + test1.get("_id") + "\nID:" + test1.get("id")
					+ "\nFirst Name: " + test1.get("first name") + "\nLast Name: " + test1.get("last name")
					+ "\nHire Year: " + test1.get("hire year"));
			mongoClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static JPanel createJPanel(/*JPanel footnotes, JScrollPane scroller*/) {
		panel = new JPanel();
		panel.setLayout(null);
		//panel .setBounds(100, 100, 500, 400);
		
		JLabel firstLabel = new JLabel("First name: ");
		JLabel lastLabel = new JLabel("Last name: ");
		JLabel hireLabel = new JLabel("Hire Year: ");
		
		ArrayList<JLabel> list = new ArrayList<>();
		list.add(firstLabel);
		list.add(lastLabel);
		list.add(hireLabel);
		
		int x = 10;
		int y = 20;
		for(JLabel label: list) {
			label.setBounds(x, y, 80, 20);
			y += 20;
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
			label.setBounds(w, h, 150, 20);
			h += 20;
			panel.add(label);
		}
		

		
		button.setForeground(Color.BLACK);
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
		
			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				int hireYear = -1;
				try {
					if(hireText.getText() != null) {
							
						hireYear = Integer.parseInt(hireText.getText());
					}
					
					
				}catch(NumberFormatException e1) {
					
				}
					
				String firstName = firstText.getText();
				String lastName = lastText.getText();
			
				Vector<Document> document = new Vector<>();
				
				Find.findRecords(firstName, lastName, hireYear, found);
				@SuppressWarnings({ "rawtypes", "unchecked" })
				JList vector = new JList(found);
				vector.setLayoutOrientation(JList.VERTICAL);
				vector.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
				scroller.add(vector);
			
				panel.add(scroller);
				button.setBounds(180, 300, 40, 30);
				
				button.setVisible(true);
				panel.add(button);
				
			}
		});
		
		

		return panel;
	}
		
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JPanel test = new JPanel();
		test = createJPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(500, 300));
		
		frame.getContentPane().add(button);
		frame.getRootPane().setDefaultButton(button);
		frame.add(test);
		frame.pack();
		frame.validate();
		frame.setVisible(true);
	}
}
