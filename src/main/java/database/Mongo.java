package database;

import com.mongodb.*;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.Updates;
import com.opencsv.bean.CsvToBeanBuilder;
import com.mongodb.BasicDBObject;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Mongo {

	public static void main(String[] args) throws NumberFormatException, IOException {
		//CRUD:Create, Read, Update, Delete
		/*Create*/
		//insertEmployee();
		/*Read*/
		//findRecords();
		/*Update*/
		//updateEmployee();
		/*Delete*/
		//deleteEmployee();
		/*Upload file types*/
		//uploadCSV(1) ;
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception evt) {
		}
		//uploadJSON("x","y");
		/*login to database*/
		// logInto();
		/*File picker that gets absolute path so it can be used to upload file from that path*/
		//fileUpload();
		

	}

	
//login page
	public static void logInto() {
		String User = "";
		String Password = "";
		String databasehost = "";
		String replicaSet = "";
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		

		
			try {
				System.out.print("Username:");
				User = buffer.readLine();
				System.out.print("Password:");
				Password = buffer.readLine();
				System.out.print("Database hostname:");
				databasehost = buffer.readLine();
				System.out.print("ReplicaSet name:");
				replicaSet = buffer.readLine();
				MongoClientURI uri = new MongoClientURI("" + "mongodb://" + User + ":" + Password + "@" + databasehost
						+ "?" + "ssl=true&replicaSet=" + replicaSet + "&authSource=admin&retryWrites=true" + "");
				MongoClient mongoClient = new MongoClient(uri);
				
				mongoClient.close();
			} catch (IOException e) {
				System.out.println("Bad credentials try again");
			}
		

	}
//uploading file to cloud database
	public static  String fileupload() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//currently picks file and gets file path
		JFileChooser chooser = new JFileChooser("C:/Users");
        
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open: " +
                    chooser.getSelectedFile().getName()+"\n"+
                    chooser.getSelectedFile().getAbsolutePath());
        }
        return chooser.getSelectedFile().getAbsolutePath();
	}
	//json upload
	 public static void uploadJSON(String CompanyName,String CollectionName) {

	        try {
	        	CompanyName= CompanyName.toLowerCase();
	        	MongoClientURI uri = new MongoClientURI("" + "mongodb://User_1:Passw0rd1@"+CompanyName+"-shard-00-00.yjpzu.mongodb.net:27017/test?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true" );
				MongoClient mongoClient = new MongoClient(uri);
				MongoDatabase database = mongoClient.getDatabase(CompanyName);			
				MongoCollection<Document> collection = database.getCollection( CollectionName);

	            // convert JSON to DBObject directly

				int count = 0;
				int batch = 100;

				List<InsertOneModel<Document>> docs = new ArrayList<>();
				
				try (BufferedReader br = new BufferedReader(new FileReader(fileupload()))) {
				      String line;
				      while ((line = br.readLine()) != null) {
				    	  System.out.println(line);
				         docs.add(new InsertOneModel<>(Document.parse(line)));
				         count++;
				         if (count == batch) {
				           collection.bulkWrite(docs, new BulkWriteOptions().ordered(false));
				           docs.clear();
				           count = 0;
				        }
				    }

	            System.out.println("Done");

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
				mongoClient.close();
	        }catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	    }
	 public static void uploadCSV() throws NumberFormatException {

	        try {

	        	MongoClientURI uri = new MongoClientURI("" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/test?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true" );
				MongoClient mongoClient = new MongoClient(uri);
				MongoDatabase database = mongoClient.getDatabase("test");			
				MongoCollection<Document> collection = database.getCollection("test");

	            // convert CSV  directly
				try{
					@SuppressWarnings({ "unchecked", "rawtypes" })
					List<Employee> beans = new CsvToBeanBuilder(new FileReader(fileupload()))
							//we ask if the file contians headers upon radial selection it will skip first header line
			                .withType(Employee.class).withSkipLines(0)
			                .build()
			                .parse();
					

					//
					for(int x = 0; x<beans.size(); x++) {
					
						Document doc = new Document("id",beans.get(x).getId());  
		                doc.append("first name",beans.get(x).getFirstName());
		                doc.append("last name",beans.get(x).getLastName());
		                doc.append("hire year",beans.get(x).getHireYear());
		                collection.insertOne(doc);  
					}
					
				  }catch (Exception e) {
			            e.printStackTrace();
			      }
		      
	            System.out.println("Done");

	            mongoClient.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	    }

	public static void updateEmployee() {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

		try {

			

			int id = 0;
			System.out.print("What employee would you like to update: ");
			id = Integer.parseInt(buffer.readLine());

			String updateFirstName = "", updateLastName = "";
			int hireYear;

			System.out.print("What do you want to change the first name to: ");
			updateFirstName = buffer.readLine();
			System.out.print("What do you want to change the last name to: ");
			updateLastName = buffer.readLine();
			System.out.print("What do you want to change the hire year to: ");
			hireYear = Integer.parseInt(buffer.readLine());

			MongoClientURI uri = new MongoClientURI("" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/test?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true" );
			MongoClient mongoClient = new MongoClient(uri);
			MongoDatabase database = mongoClient.getDatabase("test");			
			MongoCollection<Document> collection = database.getCollection("test");
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

	public static void deleteEmployee() {

		/*
		 * what not to do MongoClient mongo = new MongoClient(new
		 * MongoClientURI("mongodb://localhost:27017")); mongo.getDatabase("test");
		 * MongoCollection<Document> collection = ((MongoDatabase)
		 * mongo).getCollection("tester");
		 */
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			
			int id = 0;

			System.out.print("What ID would you like to delete: ");
			id = Integer.parseInt(buffer.readLine());
			MongoClientURI uri = new MongoClientURI("" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/test?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true" );
			MongoClient mongoClient = new MongoClient(uri);
			MongoDatabase database = mongoClient.getDatabase("test");			
			MongoCollection<Document> collection = database.getCollection("test");
			Document test = collection.find(new Document("id", id)).first();
			System.out.println("\nNOW DELETING...\n\n\nObjectID: " + test.get("_id") + "\nID:" + test.get("id")
					+ "\nFirst Name: " + test.get("first name") + "\nLast Name: " + test.get("last name")
					+ "\nHire Year: " + test.get("hire year"));
			collection.deleteOne(new Document("id", id));

			mongoClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertEmployee() {

		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		
		try {

			// gathers information about the records being inserted and database information
			
			String firstName = "", lastName = "";
			int hireYear = 0;

			System.out.print("What is the first name you want to enter: ");
			firstName = buffer.readLine();
			System.out.print("What is the last name you want to enter: ");
			lastName = buffer.readLine();
			System.out.print("What was the hire year: ");
			hireYear = Integer.parseInt(buffer.readLine());

			// gets access of the database using local host
			MongoClientURI uri = new MongoClientURI("" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/test?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true" );
			MongoClient mongoClient = new MongoClient(uri);
			MongoDatabase database = mongoClient.getDatabase("test");			
			MongoCollection<Document> collection = database.getCollection("test");

			// creates the document to insert into the database
			Document test = new Document();
			test.append("id", (collection.count() + 1));
			test.append("first name", firstName);
			test.append("last name", lastName);
			test.append("hire year", hireYear);

			// adds the document to the database
			collection.insertOne(test);

			mongoClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void findRecords() {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			
			int id = 0;

			System.out.print("What ID would you like to find: ");
			id = Integer.parseInt(buffer.readLine());

			MongoClientURI uri = new MongoClientURI("" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/test?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true" );
			MongoClient mongoClient = new MongoClient(uri);
			MongoDatabase database = mongoClient.getDatabase("test");			
			MongoCollection<Document> collection = database.getCollection("test");

			BasicDBObject object = new BasicDBObject();
			object.put("id", id);
			

			// prints the document to the console
			Document test = collection.find(new Document("id", id)).first();
			System.out.println("\nObjectID: " + test.get("_id") + "\nID:" + test.get("id")
					+ "\nFirst Name: " + test.get("first name") + "\nLast Name: " + test.get("last name")
					+ "\nHire Year: " + test.get("hire year"));

			mongoClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
