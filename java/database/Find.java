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

import Encryption.Decrypt;

public class Find {
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void findRecords(String firstName, String lastName, String hireYear, DefaultListModel document) {
		
		try {
			Decrypt p = new Decrypt();
			
			
			firstName = firstName.toUpperCase();
			lastName = lastName.toUpperCase();
			Vector<Document> search = new Vector<>();

			MongoClientURI uri = new MongoClientURI("" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/northwind?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true" );
			MongoClient mongoClient = new MongoClient(uri);
			MongoDatabase database = mongoClient.getDatabase("northwind");			
			MongoCollection<Document> collection = database.getCollection("Employees");

			BasicDBObject query = new BasicDBObject();
			FindIterable<Document> doc;
			Iterator it;
			int i = 0;
			
			if(firstName.equals("")) {
				if(lastName.equals("")) {
					query.append("ssn", hireYear);
					
					doc = collection.find(query);
					it = doc.iterator();
					while(it.hasNext()) {
						search.add((Document) it.next());
						
						
						document.addElement("id: " + String.valueOf(search.get(i).get("id") +
								"\n first name: " + search.get(i).get("first name") +
								"\n last name: " + search.get(i).get("last name") +
								"\n hire year: " + search.get(i).get("hire year") + 
								"\n ssn: " + p.decryptShiftChars(search.get(i).get("ssn").toString())+
								"\n occupation: " + search.get(i).get("occupation") + 
								"\n"));
						System.out.println(document.getElementAt(i));
						i++;
					}
					
					mongoClient.close();
				}
				else if(!hireYear.isEmpty()){
					query.append("last name", lastName).append("ssn", hireYear);
					
					doc = collection.find(query);
					it = doc.iterator();
					while(it.hasNext()) {
						search.add((Document) it.next());
						
						document.addElement("id: " + String.valueOf(search.get(i).get("id") +
								"\n first name: " + search.get(i).get("first name") +
								"\n last name: " + search.get(i).get("last name") +
								"\n hire year: " + search.get(i).get("hire year") + 
								"\n ssn: " + p.decryptShiftChars(search.get(i).get("ssn").toString())+
								"\n occupation: " + search.get(i).get("occupation") + 
								"\n"));
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
								"\n first name: " + search.get(i).get("first name") +
								"\n last name: " + search.get(i).get("last name") +
								"\n hire year: " + search.get(i).get("hire year") + 
								"\n ssn: " + p.decryptShiftChars(search.get(i).get("ssn").toString())+
								"\n occupation: " + search.get(i).get("occupation") + 
								"\n"));
						System.out.println(document.getElementAt(i));
						i++;
					}
					
					mongoClient.close();
				}
			}
			else {
				if(lastName.equals("")) {
					if(hireYear.isEmpty()) {
						query.append("first name", firstName);
						
						doc = collection.find(query);
						it = doc.iterator();
						while(it.hasNext()) {
							search.add((Document) it.next());
							
							document.addElement("id: " + String.valueOf(search.get(i).get("id") +
									"\n first name: " + search.get(i).get("first name") +
									"\n last name: " + search.get(i).get("last name") +
									"\n hire year: " + search.get(i).get("hire year") + 
									"\n ssn: " + p.decryptShiftChars(search.get(i).get("ssn").toString())+
									"\n occupation: " + search.get(i).get("occupation") + 
									"\n"));
							System.out.println(document.getElementAt(i));
							i++;
						}
						
						mongoClient.close();
					}
				}
				else if(hireYear.isEmpty()) {
					query.append("first name", firstName).append("last name", lastName);
					
					doc = collection.find(query);
					it = doc.iterator();
					while(it.hasNext()) {
						search.add((Document) it.next());
						
						document.addElement("id: " + String.valueOf(search.get(i).get("id") +
								"\n first name: " + search.get(i).get("first name") +
								"\n last name: " + search.get(i).get("last name") +
								"\n hire year: " + search.get(i).get("hire year") + 
								"\n ssn: " + search.get(i).get("ssn")+
								"\n occupation: " + search.get(i).get("occupation") + 
								"\n"));
						System.out.println(document.getElementAt(i));
						i++;
					}
					
					mongoClient.close();
				}
				else {
					query.append("first name", firstName).append("last name", lastName).append("ssn", hireYear);
					
					doc = collection.find(query);
					it = doc.iterator();
					while(it.hasNext()) {
						search.add((Document) it.next());
						
						document.addElement("id: " + String.valueOf(search.get(i).get("id") +
								"\n first name: " + search.get(i).get("first name") +
								"\n last name: " + search.get(i).get("last name") +
								"\n hire year: " + search.get(i).get("hire year") + 
								"\n ssn: " + p.decryptShiftChars(search.get(i).get("ssn").toString())+
								"\n occupation: " + search.get(i).get("occupation") + 
								"\n"));
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
