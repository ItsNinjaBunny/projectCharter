package database;

import java.util.Iterator;
import java.util.Vector;

import javax.swing.DefaultListModel;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Find {
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void findRecords(String firstName, String lastName, int hireYear, DefaultListModel document) {
		
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
						
						document.addElement("id: " + String.valueOf(search.get(i).get("id") +
								"\nfirst name: " + search.get(i).get("first name") +
								"\nlast name: " + search.get(i).get("last name") +
								"\nhire year: " + search.get(i).get("hire year") + "\n"));
						System.out.println(document.getElementAt(i));
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
						
						document.addElement("id: " + String.valueOf(search.get(i).get("id") +
								"\nfirst name: " + search.get(i).get("first name") +
								"\nlast name: " + search.get(i).get("last name") +
								"\nhire year: " + search.get(i).get("hire year") + "\n"));
						System.out.println(document.getElementAt(i));
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
						
						document.addElement("id: " + String.valueOf(search.get(i).get("id") +
								"\nfirst name: " + search.get(i).get("first name") +
								"\nlast name: " + search.get(i).get("last name") +
								"\nhire year: " + search.get(i).get("hire year") + "\n"));
						System.out.println(document.getElementAt(i));
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
							
							document.addElement("id: " + String.valueOf(search.get(i).get("id") +
									"\nfirst name: " + search.get(i).get("first name") +
									"\nlast name: " + search.get(i).get("last name") +
									"\nhire year: " + search.get(i).get("hire year") + "\n"));
							System.out.println(document.getElementAt(i));
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
						
						document.addElement("id: " + String.valueOf(search.get(i).get("id") +
								"\nfirst name: " + search.get(i).get("first name") +
								"\nlast name: " + search.get(i).get("last name") +
								"\nhire year: " + search.get(i).get("hire year") + "\n"));
						System.out.println(document.getElementAt(i));
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
						
						document.addElement("id: " + String.valueOf(search.get(i).get("id") +
								"\nfirst name: " + search.get(i).get("first name") +
								"\nlast name: " + search.get(i).get("last name") +
								"\nhire year: " + search.get(i).get("hire year") + "\n"));
						System.out.println(document.getElementAt(i));
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
}
