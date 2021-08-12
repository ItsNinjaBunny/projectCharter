package database;

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
	
	public static void createJPanel() {
		JFrame frame = new JFrame();
		panel = new JPanel();
		panel.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(300, 200));
		panel .setBounds(100, 100, 500, 400);
		
		JLabel firstLabel = new JLabel("First name: ");
		JLabel lastLabel = new JLabel("Last name: ");
		JLabel hireLabel = new JLabel("Hire Year: ");
		
		ArrayList<JLabel> list = new ArrayList<>();
		list.add(firstLabel);
		list.add(lastLabel);
		list.add(hireLabel);
		
		int x = 10;
		int y = 30;
		for(JLabel label: list) {
			label.setBounds(x, y, 80, 20);
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
		
		int h = 30;
		int w = 100;
		for(JTextField label: list1) {
			label.setBounds(w, h, 150, 20);
			h += 30;
			panel.add(label);
		}
		

		
		JButton button = new JButton("search");
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
				
				findRecords(firstName, lastName, hireYear, found);
				@SuppressWarnings({ "rawtypes", "unchecked" })
				JList vector = new JList(found);
				vector.setLayoutOrientation(JList.VERTICAL);
				vector.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				vector.setBounds(301, 101, 301, 399);
				
				
				JScrollPane scroller = new JScrollPane(vector);
				
				scroller.setBounds(300, 100, 300, 400);
				frame.getContentPane().add(scroller);
				
				button.setVisible(false);
				frame.validate();
			}
		});
		
		
		
		
		frame.getContentPane().add(button);
        frame.getRootPane().setDefaultButton(button);
        button.setBounds(10, 120, 45, 20);
		
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	@SuppressWarnings("rawtypes")
	public static void findRecords(String firstName, String lastName, int hireYear, Vector<String> strings) {
		
		try {
			
			firstName = firstName.toUpperCase();
			lastName = lastName.toUpperCase();
			Vector<Document> search = new Vector<>();

			MongoClientURI uri = new MongoClientURI("" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/test?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true" );
			MongoClient mongoClient = new MongoClient(uri);
			MongoDatabase database = mongoClient.getDatabase("test");			
			MongoCollection<Document> collection = database.getCollection("test");

			BasicDBObject query = new BasicDBObject();
			FindIterable<Document> doc;
			Iterator it;
			int i = 0;
			
			if(firstName.equals("")) {
				if(lastName.equals("")) {
					query.append("hire year", hireYear);
					
					doc = collection.find(query);
					it = doc.iterator();
					while(it.hasNext()) {
						search.add((Document) it.next());
						
						strings.add("id: " + String.valueOf(search.get(i).get("id") +
								"\nfirst name: " + search.get(i).get("first name") +
								"\nlast name: " + search.get(i).get("last name") +
								"\nhire year: " + search.get(i).get("hire year") + "\n"));
						System.out.println(strings.get(i));
						i++;
					}
					
					mongoClient.close();
				}
				else if(hireYear != -1){
					query.append("last name", lastName).append("hire year", hireYear);
					
					doc = collection.find(query);
					it = doc.iterator();
					while(it.hasNext()) {
						search.add((Document) it.next());
						
						strings.add("id: " + String.valueOf(search.get(i).get("id") +
								"\nfirst name: " + search.get(i).get("first name") +
								"\nlast name: " + search.get(i).get("last name") +
								"\nhire year: " + search.get(i).get("hire year") + "\n"));
						System.out.println(strings.get(i));
						i++;
					}
					
					mongoClient.close();
				}
				else {
					query.append("last name", lastName);
					
					doc = collection.find(query);
					it = doc.iterator();
					while(it.hasNext()) {
						search.add((Document) it.next());
						
						strings.add("id: " + String.valueOf(search.get(i).get("id") +
								"\nfirst name: " + search.get(i).get("first name") +
								"\nlast name: " + search.get(i).get("last name") +
								"\nhire year: " + search.get(i).get("hire year") + "\n"));
						System.out.println(strings.get(i));
						i++;
					}
					
					mongoClient.close();
				}
			}
			else {
				if(lastName.equals("")) {
					if(hireYear == -1) {
						query.append("first name", firstName);
						
						doc = collection.find(query);
						it = doc.iterator();
						while(it.hasNext()) {
							search.add((Document) it.next());
							
							strings.add("id: " + String.valueOf(search.get(i).get("id") +
									"\nfirst name: " + search.get(i).get("first name") +
									"\nlast name: " + search.get(i).get("last name") +
									"\nhire year: " + search.get(i).get("hire year") + "\n"));
							System.out.println(strings.get(i));
							i++;
						}
						
						mongoClient.close();
					}
				}
				else if(hireYear == -1) {
					query.append("first name", firstName).append("last name", lastName);
					
					doc = collection.find(query);
					it = doc.iterator();
					while(it.hasNext()) {
						search.add((Document) it.next());
						
						strings.add("id: " + String.valueOf(search.get(i).get("id") +
								"\nfirst name: " + search.get(i).get("first name") +
								"\nlast name: " + search.get(i).get("last name") +
								"\nhire year: " + search.get(i).get("hire year") + "\n"));
						System.out.println(strings.get(i));
						i++;
					}
					
					mongoClient.close();
				}
				else {
					query.append("first name", firstName).append("last name", lastName).append("hire year", hireYear);
					
					doc = collection.find(query);
					it = doc.iterator();
					while(it.hasNext()) {
						search.add((Document) it.next());
						
						strings.add("id: " + String.valueOf(search.get(i).get("id") +
								"\nfirst name: " + search.get(i).get("first name") +
								"\nlast name: " + search.get(i).get("last name") +
								"\nhire year: " + search.get(i).get("hire year") + "\n"));
						System.out.println(strings.get(i));
						i++;
					}
					
					mongoClient.close();
				}
				
				mongoClient.close();
				
			}
			
			

			
			// prints the document to the console
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		createJPanel();
	}
}
