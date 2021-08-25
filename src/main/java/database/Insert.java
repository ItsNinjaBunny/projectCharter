package database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.opencsv.bean.CsvToBeanBuilder;

import Encryption.Encrypt;
import csvFiles.Employee;
import csvFiles.FinancialHoldings;
import csvFiles.ProductServices;
import csvFiles.Property;
import guiPackage.GUI;

public class Insert {
	// pass Jprogressbar as a parameter
		public static void insertEmployee(String CompanyName, String firstname, String lastname, String hireYear,
				String ssn, String occupation, JProgressBar progressBar1) {
			ssn = ssn.replace("-", "");
			Employee emp = new Employee(firstname, lastname, hireYear, ssn, occupation);

			progressBar1.setValue(300);
			progressBar1.setVisible(true);
			boolean success = false;

			try {

				// gathers information about the records being inserted and database information

				// gets access of the database using local host
				CompanyName = CompanyName.toLowerCase();
				MongoClientURI uri = new MongoClientURI(
						"" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/" + CompanyName
								+ "?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true");
				MongoClient mongoClient = new MongoClient(uri);
				MongoDatabase database = mongoClient.getDatabase(CompanyName);
				MongoCollection<Document> collection = database.getCollection("Employees");

				// creates the document to insert into the database
			if(Integer.parseInt(emp.getHireYear()) > 0 && Integer.parseInt(emp.getSSN()) > 0)
			{
						
				Document test = new Document();
				test.append("id", (collection.count() + 1));
				test.append("first name", emp.getFirstName(true));
				test.append("last name", emp.getLastName(true));
				test.append("hire year", emp.getHireYear(true));
				test.append("ssn", emp.getSSN(true));
				test.append("occupation", emp.getOccupation(true));
				System.out.println(emp.toString());
				// adds the document to the database
				collection.insertOne(test);
				mongoClient.close();
				success = true;
			}
		
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Please enter numbers into: Hire Year and SSN.");
				e.printStackTrace();
			}
			if(success == true)
			{
			JOptionPane.showMessageDialog(null, "Upload Complete");
			}
		}

		public static void insertFinance(String CompanyName, String accountName, String Balance, String Bank, String accountNumber, JProgressBar progressBar5) {
			progressBar5.setValue(300);
			progressBar5.setVisible(true);
			FinancialHoldings fin = new FinancialHoldings(accountName, Balance, Bank,accountNumber);
			try {

				// gathers information about the records being inserted and database information

				// gets access of the database using local host
				CompanyName = CompanyName.toLowerCase();
				MongoClientURI uri = new MongoClientURI(
						"" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/" + CompanyName
								+ "?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true");
				MongoClient mongoClient = new MongoClient(uri);
				MongoDatabase database = mongoClient.getDatabase(CompanyName);
				MongoCollection<Document> collection = database.getCollection("Financial Holdings");

				// creates the document to insert into the database
				if(Integer.parseInt(fin.getAccountNumber()) > 0 && Integer.parseInt(fin.getBalance()) >= 0)
				{
					
				
				Document test = new Document();
				test.append("id", (collection.count() + 1));
				test.append("account name", fin.getAccountName(true));
				
				test.append("balance", fin.getBalance(true));
				test.append("bank", fin.getBankingInstitution(true));
				test.append("account number", fin.getAccountNumber(true));
				// adds the document to the database
				collection.insertOne(test);
				mongoClient.close();
				JOptionPane.showMessageDialog(null, "Upload Complete");
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Invalid entry detected. Please enter numbers into: Balance and Account Number.");

				e.printStackTrace();
			}
		}

		public static void insertProperty(String CompanyName, String propertyName, String cost, String location,JProgressBar progressBar2) {
			progressBar2.setValue(300);
			progressBar2.setVisible(true);
			Property prop = new Property(propertyName, cost, location);
			try {

				// gathers information about the records being inserted and database information

				// gets access of the database using local host
				CompanyName = CompanyName.toLowerCase();
				MongoClientURI uri = new MongoClientURI(
						"" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/" + CompanyName
								+ "?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true");
				MongoClient mongoClient = new MongoClient(uri);
				MongoDatabase database = mongoClient.getDatabase(CompanyName);
				MongoCollection<Document> collection = database.getCollection("Properties");

				// creates the document to insert into the database
				if(Integer.parseInt(prop.getCost()) >= 0)
			{
				Document test = new Document();
				test.append("id", (collection.count() + 1));
				test.append("property name", prop.getTitle(true));
				test.append("cost", prop.getCost(true));
				test.append("location", prop.getLocation(true));

				// adds the document to the database
				collection.insertOne(test);
				mongoClient.close();
				JOptionPane.showMessageDialog(null, "Upload Complete");
			}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Invalid Entry detected in Cost. Please use numbers only.");

				e.printStackTrace();
			}
		}

		public static boolean insertProduct(String CompanyName, String productName, String cost, String category,
				String supplier, JProgressBar progressBar4) {
			ProductServices prod = new ProductServices(productName, cost, category, supplier);
			progressBar4.setValue(300);
			progressBar4.setVisible(true);

			try {

				// gathers information about the records being inserted and database information

				// gets access of the database using local host
				CompanyName = CompanyName.toLowerCase();
				MongoClientURI uri = new MongoClientURI(
						"" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/" + CompanyName
								+ "?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true");
				MongoClient mongoClient = new MongoClient(uri);
				MongoDatabase database = mongoClient.getDatabase(CompanyName);
				MongoCollection<Document> collection = database.getCollection("Products");

				// creates the document to insert into the database
				if(Integer.parseInt(prod.getCost()) >= 0)
				{
				Document test = new Document();
				test.append("id", (collection.count() + 1));
				test.append("product name", prod.getTitle(true));
				test.append("cost", prod.getCost(true));
				test.append("category", prod.getCategory(true));
				test.append("supplier", prod.getSupplier(true));

				// adds the document to the database
				collection.insertOne(test);
				mongoClient.close();
				JOptionPane.showMessageDialog(null, "Upload Complete");
				}
				return true;

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Invalid entry detected in Cost. Please use numbers only.");

				e.printStackTrace();
				return false;
			}

		}

		public static void insertService(String CompanyName, String serviceName, String cost, String category ,JProgressBar progressBar3 ) {

			progressBar3.setValue(300);
			progressBar3.setVisible(true);
			ProductServices serv = new ProductServices(serviceName, cost, category);

			try {

				// gathers information about the records being inserted and database information

				// gets access of the database using local host
				CompanyName = CompanyName.toLowerCase();
				MongoClientURI uri = new MongoClientURI(
						"" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/" + CompanyName
								+ "?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true");
				MongoClient mongoClient = new MongoClient(uri);
				MongoDatabase database = mongoClient.getDatabase(CompanyName);
				MongoCollection<Document> collection = database.getCollection("Services");
				
				// creates the document to insert into the database
				if(Integer.parseInt(serv.getCost()) >= 0)
				{
				Document test = new Document();
				test.append("id", (collection.count() + 1));
				test.append("service name", serv.getTitle(true));
				test.append("cost", serv.getCost(true));
				test.append("category", serv.getCategory(true));

				// adds the document to the database
				collection.insertOne(test);
				mongoClient.close();
				JOptionPane.showMessageDialog(null, "Upload Complete");
				}

			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Invalid entry detected in Cost. Please use numbers only.");

			}
		}
		//json upload

		public static void uploadJSONEmployee(String CompanyName) {

			try {
				CompanyName = CompanyName.toLowerCase();
				MongoClientURI uri = new MongoClientURI(
						"" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/" + CompanyName
								+ "?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true");
				MongoClient mongoClient = new MongoClient(uri);
				MongoDatabase database = mongoClient.getDatabase(CompanyName);
				MongoCollection<Document> collection = database.getCollection("Employees");

				
			    ArrayList<JSONObject> json=new ArrayList<JSONObject>();
			    JSONObject obj;
			    // The name of the file to open.

			    // This will reference one line at a time
			    String line = null;

			    try {
			        // FileReader reads text files in the default encoding.
			        FileReader fileReader = new FileReader(guiPackage.GUI.fileUpload());

			        // Always wrap FileReader in BufferedReader.
			        BufferedReader bufferedReader = new BufferedReader(fileReader);

			        while((line = bufferedReader.readLine()) != null) {
			            obj = (JSONObject) new JSONParser().parse(line);
			            json.add(obj);
			           
						String first = (String) obj.get("first name");
						String last = (String) obj.get("last name");
						String ssn = (String) obj.get("ssn");
						String hire = (String) obj.get("hire year");
						String occupation = (String) obj.get("occupation");		
						
						first = Encrypt.encryptData(first.toUpperCase());
						last = Encrypt.encryptData(last.toUpperCase());
						ssn = Encrypt.encryptData(ssn.toUpperCase());
						hire = Encrypt.encryptData(hire.toUpperCase());
						occupation = Encrypt.encryptData(occupation.toUpperCase());
						
						
						int docID = 0;
						int id = 0;
						
						BasicDBObject getID = new BasicDBObject("id", docID);
						boolean isValid = true;
						Document nextID;
						
						nextID = collection.find(getID).first();
						do {
							
							if(nextID == null) {
								id = docID;
								isValid = false;
								break;
							}
							else {
								nextID.clear();
								getID.clear();
								docID++;
								getID.append("id", docID);
								nextID = collection.find(getID).first();
								
							}
						}while(isValid);
						Document doc = new Document();
						
						doc.append("id", id).append("first name", first).append("last name", last).append("ssn", ssn).append("hire year", hire).append("occupation", occupation);
						collection.insertOne(doc);
			        }
			        // Always close files.
			        bufferedReader.close();         
			    }catch(IOException e) {
			    	e.printStackTrace();
			    }
				mongoClient.close();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		public static void uploadJSONProperty(String CompanyName) {
				
			try {
				CompanyName = CompanyName.toLowerCase();
				MongoClientURI uri = new MongoClientURI(
						"" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/" + CompanyName
								+ "?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true");
				MongoClient mongoClient = new MongoClient(uri);
				MongoDatabase database = mongoClient.getDatabase(CompanyName);
				MongoCollection<Document> collection = database.getCollection("Properties");

				
			    ArrayList<JSONObject> json=new ArrayList<JSONObject>();
			    JSONObject obj;

			    // This will reference one line at a time
			    String line = null;

			    try {
			        // FileReader reads text files in the default encoding.
			        FileReader fileReader = new FileReader(guiPackage.GUI.fileUpload());

			        // Always wrap FileReader in BufferedReader.
			        BufferedReader bufferedReader = new BufferedReader(fileReader);

			        while((line = bufferedReader.readLine()) != null) {
			            obj = (JSONObject) new JSONParser().parse(line);
			            json.add(obj);
			            
						String property = (String) obj.get("property name");
						String cost = (String) obj.get("cost");
						String location = (String) obj.get("location");	
						
						property = Encrypt.encryptData(property.toUpperCase());
						cost = Encrypt.encryptData(cost.toUpperCase());
						location = Encrypt.encryptData(location.toUpperCase());
						
						
						int docID = 0;
						int id = 0;
						
						BasicDBObject getID = new BasicDBObject("id", docID);
						boolean isValid = true;
						Document nextID;
						
						nextID = collection.find(getID).first();
						do {
							
							if(nextID == null) {
								id = docID;
								isValid = false;
								break;
							}
							else {
								nextID.clear();
								getID.clear();
								docID++;
								getID.append("id", docID);
								nextID = collection.find(getID).first();
								
							}
						}while(isValid);
						Document doc = new Document();
						
						doc.append("id", id).append("property name", property).append("cost", cost).append("location", location);
						collection.insertOne(doc);
			        }
			        // Always close files.
			        bufferedReader.close();         
			    }catch(IOException e) {
			    	e.printStackTrace();
			    }
				mongoClient.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public static void uploadJSONProduct(String CompanyName) {

			try {
				CompanyName = CompanyName.toLowerCase();
				MongoClientURI uri = new MongoClientURI(
						"" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/" + CompanyName
								+ "?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true");
				MongoClient mongoClient = new MongoClient(uri);
				MongoDatabase database = mongoClient.getDatabase(CompanyName);
				MongoCollection<Document> collection = database.getCollection("Products");

				
				
			    ArrayList<JSONObject> json=new ArrayList<JSONObject>();
			    JSONObject obj;
			    // The name of the file to open

			    // This will reference one line at a time
			    String line = null;

			    try {
			        // FileReader reads text files in the default encoding.
			        FileReader fileReader = new FileReader(guiPackage.GUI.fileUpload());

			        // Always wrap FileReader in BufferedReader.
			        BufferedReader bufferedReader = new BufferedReader(fileReader);

			        while((line = bufferedReader.readLine()) != null) {
			            obj = (JSONObject) new JSONParser().parse(line);
			            json.add(obj);
			            
						String product = (String) obj.get("product name");
						String cost = (String) obj.get("cost");
						String category = (String) obj.get("category");
						String supplier = (String) obj.get("supplier");	
						
						product = Encrypt.encryptData(product.toUpperCase());
						cost = Encrypt.encryptData(cost.toUpperCase());
						category = Encrypt.encryptData(category.toUpperCase());
						supplier = Encrypt.encryptData(supplier.toUpperCase());
						
						
						int docID = 0;
						int id = 0;
						
						BasicDBObject getID = new BasicDBObject("id", docID);
						boolean isValid = true;
						Document nextID;
						
						nextID = collection.find(getID).first();
						do {
							
							if(nextID == null) {
								id = docID;
								isValid = false;
								break;
							}
							else {
								nextID.clear();
								getID.clear();
								docID++;
								getID.append("id", docID);
								nextID = collection.find(getID).first();
								
							}
						}while(isValid);
						Document doc = new Document();
						
						doc.append("id", id).append("product name", product).append("cost", cost).append("category", category).append("supplier", supplier);
						collection.insertOne(doc);
			            		        }
			        // Always close files.
			        bufferedReader.close();         
			    }catch(IOException e) {
			    	e.printStackTrace();
			    }
				mongoClient.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public static void uploadJSONService(String CompanyName) {

			try {
				CompanyName = CompanyName.toLowerCase();
				MongoClientURI uri = new MongoClientURI(
						"" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/" + CompanyName
								+ "?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true");
				MongoClient mongoClient = new MongoClient(uri);
				MongoDatabase database = mongoClient.getDatabase(CompanyName);
				MongoCollection<Document> collection = database.getCollection("Services");

				
			    ArrayList<JSONObject> json=new ArrayList<JSONObject>();
			    JSONObject obj;
			    // The name of the file to open.

			    // This will reference one line at a time
			    String line = null;

			    try {
			        // FileReader reads text files in the default encoding.
			        FileReader fileReader = new FileReader(guiPackage.GUI.fileUpload());

			        // Always wrap FileReader in BufferedReader.
			        BufferedReader bufferedReader = new BufferedReader(fileReader);

			        while((line = bufferedReader.readLine()) != null) {
			            obj = (JSONObject) new JSONParser().parse(line);
			            json.add(obj);
			            
						String service = (String) obj.get("service name");
						String cost = (String) obj.get("cost");
						String category = (String) obj.get("category");	
						
						service = Encrypt.encryptData(service.toUpperCase());
						cost = Encrypt.encryptData(cost.toUpperCase());
						category = Encrypt.encryptData(category.toUpperCase());
						
						int docID = 0;
						int id = 0;
						
						BasicDBObject getID = new BasicDBObject("id", docID);
						boolean isValid = true;
						Document nextID;
						
						nextID = collection.find(getID).first();
						do {
							
							if(nextID == null) {
								id = docID;
								isValid = false;
								break;
							}
							else {
								nextID.clear();
								getID.clear();
								docID++;
								getID.append("id", docID);
								nextID = collection.find(getID).first();
								
							}
						}while(isValid);
						Document doc = new Document();
						
						doc.append("id", id).append("service name", service).append("cost", cost).append("category", category);
						collection.insertOne(doc);
			        }
			        // Always close files.
			        bufferedReader.close();         
			    }catch(IOException e) {
			    	e.printStackTrace();
			    }
				mongoClient.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public static void uploadJSONFinancials(String CompanyName) {

			try {
				CompanyName = CompanyName.toLowerCase();
				MongoClientURI uri = new MongoClientURI(
						"" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/" + CompanyName
								+ "?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true");
				MongoClient mongoClient = new MongoClient(uri);
				MongoDatabase database = mongoClient.getDatabase(CompanyName);
				MongoCollection<Document> collection = database.getCollection("Financial Holdings");

				
			    ArrayList<JSONObject> json=new ArrayList<JSONObject>();
			    JSONObject obj;
			    // The name of the file to open.

			    // This will reference one line at a time
			    String line = null;

			    try {
			        // FileReader reads text files in the default encoding.
			        FileReader fileReader = new FileReader(guiPackage.GUI.fileUpload());

			        // Always wrap FileReader in BufferedReader.
			        BufferedReader bufferedReader = new BufferedReader(fileReader);

			        while((line = bufferedReader.readLine()) != null) {
			            obj = (JSONObject) new JSONParser().parse(line);
			            json.add(obj);
			            
						String accountName = (String) obj.get("account name");
						String accountNumber = (String) obj.get("account number");
						String balance = (String) obj.get("balance");
						String bank = (String) obj.get("bank");	
						
						accountName = Encrypt.encryptData(accountName.toUpperCase());
						accountNumber = Encrypt.encryptData(accountNumber.toUpperCase());
						balance = Encrypt.encryptData(balance.toUpperCase());
						bank = Encrypt.encryptData(bank.toUpperCase());			
						
						int docID = 0;
						int id = 0;
						
						BasicDBObject getID = new BasicDBObject("id", docID);
						boolean isValid = true;
						Document nextID;
						
						nextID = collection.find(getID).first();
						do {
							
							if(nextID == null) {
								id = docID;
								isValid = false;
								break;
							}
							else {
								nextID.clear();
								getID.clear();
								docID++;
								getID.append("id", docID);
								nextID = collection.find(getID).first();
								
							}
						}while(isValid);
						Document doc = new Document();
						
						doc.append("id", id).append("account name", accountName).append("account number", accountNumber).append("balance", balance).append("bank", bank);
						collection.insertOne(doc);
			        }
			        // Always close files.
			        bufferedReader.close();         
			    }catch(IOException e) {
			    	e.printStackTrace();
			    }
				mongoClient.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public static void uploadEmployeeCSV(String CompanyName, String CollectionName, JProgressBar progressBar) throws NumberFormatException {

			try {

				CompanyName = CompanyName.toLowerCase();
				MongoClientURI uri = new MongoClientURI(
						"" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/" + CompanyName
								+ "?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true");
				MongoClient mongoClient = new MongoClient(uri);
				MongoDatabase database = mongoClient.getDatabase(CompanyName);
				MongoCollection<Document> collection = database.getCollection(CollectionName);

				// convert CSV directly
				try {
					String file = GUI.fileUpload();
					List<Employee> beans = new CsvToBeanBuilder(new FileReader(file))
							// we ask if the file contians headers upon radial selection it will skip first
							// header line
							.withType(Employee.class).withSkipLines(1).build().parse();

					if (beans.get(0).getId() != 1) {
						beans = new CsvToBeanBuilder(new FileReader(file))
								// we ask if the file contians headers upon radial selection it will skip first
								// header line
								.withType(Employee.class).withSkipLines(0).build().parse();
						for (int x = 0; x < beans.size(); x++) {

							Document doc = new Document("id", beans.get(x).getId());
							doc.append("first name", Encrypt.encryptData(beans.get(x).getFirstName().toUpperCase()));
							doc.append("last name", Encrypt.encryptData(beans.get(x).getLastName().toUpperCase()));
							doc.append("hire year", Encrypt.encryptData(beans.get(x).getHireYear().toUpperCase()));
							doc.append("ssn", Encrypt.encryptData(beans.get(x).getSSN().replace("-", "").toUpperCase()));
							doc.append("occupation", Encrypt.encryptData(beans.get(x).getOccupation().toUpperCase()));
							collection.insertOne(doc);
						}
					} else {

						for (int x = 0; x < beans.size(); x++) {

							Document doc = new Document("id", beans.get(x).getId());
							doc.append("first name", Encrypt.encryptData(beans.get(x).getFirstName().toUpperCase()));
							doc.append("last name", Encrypt.encryptData(beans.get(x).getLastName().toUpperCase()));
							doc.append("hire year", Encrypt.encryptData(beans.get(x).getHireYear().toUpperCase()));
							doc.append("ssn", Encrypt.encryptData(beans.get(x).getSSN().replace("-", "").toUpperCase()));
							doc.append("occupation", Encrypt.encryptData(beans.get(x).getOccupation().toUpperCase()));
							collection.insertOne(doc);
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				progressBar.setValue(300);

				mongoClient.close();
				JOptionPane.showMessageDialog(null, "CSV Upload Complete");

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		public static void uploadPropertyCSV(String CompanyName, String CollectionName, JProgressBar progressBar) throws NumberFormatException {

			try {

				CompanyName = CompanyName.toLowerCase();
				MongoClientURI uri = new MongoClientURI(
						"" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/" + CompanyName
								+ "?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true");
				MongoClient mongoClient = new MongoClient(uri);
				MongoDatabase database = mongoClient.getDatabase(CompanyName);
				MongoCollection<Document> collection = database.getCollection(CollectionName);

				// convert CSV directly
				try {
					String file = GUI.fileUpload();
					List<Property> beans = new CsvToBeanBuilder(new FileReader(file))
							// we ask if the file contians headers upon radial selection it will skip first
							// header line
							.withType(Property.class).withSkipLines(1).build().parse();

					if (beans.get(0).getId() != 1) {
						beans = new CsvToBeanBuilder(new FileReader(file))
								// we ask if the file contians headers upon radial selection it will skip first
								// header line
								.withType(Property.class).withSkipLines(0).build().parse();
						for (int x = 0; x < beans.size(); x++) {

							Document doc = new Document("id", beans.get(x).getId());
							doc.append("title", Encrypt.encryptData(beans.get(x).getTitle().toUpperCase()));
							doc.append("cost", Encrypt.encryptData(beans.get(x).getCost().toUpperCase()));
							doc.append("location", Encrypt.encryptData(beans.get(x).getLocation().toUpperCase()));
							collection.insertOne(doc);
						}
					} else {

						for (int x = 0; x < beans.size(); x++) {

							Document doc = new Document("id", beans.get(x).getId());
							doc.append("property name", Encrypt.encryptData(beans.get(x).getTitle().toUpperCase()));
							doc.append("cost", Encrypt.encryptData(beans.get(x).getCost().toUpperCase()));
							doc.append("location", Encrypt.encryptData(beans.get(x).getLocation().toUpperCase()));
							collection.insertOne(doc);
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				progressBar.setValue(300);
				mongoClient.close();
				JOptionPane.showMessageDialog(null, "CSV Upload Complete");

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		public static void uploadProductsCSV(String CompanyName, String CollectionName, JProgressBar progressBar) throws NumberFormatException {

			try {

				CompanyName = CompanyName.toLowerCase();
				MongoClientURI uri = new MongoClientURI(
						"" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/" + CompanyName
								+ "?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true");
				MongoClient mongoClient = new MongoClient(uri);
				MongoDatabase database = mongoClient.getDatabase(CompanyName);
				MongoCollection<Document> collection = database.getCollection(CollectionName);

				// convert CSV directly
				try {
					String file = GUI.fileUpload();
					List<ProductServices> beans = new CsvToBeanBuilder(new FileReader(file))
							// we ask if the file contians headers upon radial selection it will skip first
							// header line
							.withType(ProductServices.class).withSkipLines(1).build().parse();

					if (beans.get(0).getId() != 1) {
						beans = new CsvToBeanBuilder(new FileReader(file))
								// we ask if the file contians headers upon radial selection it will skip first
								// header line
								.withType(ProductServices.class).withSkipLines(0).build().parse();
						for (int x = 0; x < beans.size(); x++) {

							Document doc = new Document("id", beans.get(x).getId());
							doc.append("product name", Encrypt.encryptData(beans.get(x).getTitle().toUpperCase()));
							doc.append("cost", Encrypt.encryptData(beans.get(x).getCost().toUpperCase()));
							doc.append("category", Encrypt.encryptData(beans.get(x).getCategory().toUpperCase()));
							doc.append("supplier", Encrypt.encryptData(beans.get(x).getSupplier().toUpperCase()));
							collection.insertOne(doc);
						}
					} else {

						for (int x = 0; x < beans.size(); x++) {

							Document doc = new Document("id", beans.get(x).getId());
							doc.append("product name", Encrypt.encryptData(beans.get(x).getTitle().toUpperCase()));
							doc.append("cost", Encrypt.encryptData(beans.get(x).getCost().toUpperCase()));
							doc.append("category", Encrypt.encryptData(beans.get(x).getCategory().toUpperCase()));
							doc.append("supplier", Encrypt.encryptData(beans.get(x).getSupplier().toUpperCase()));
							collection.insertOne(doc);
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				progressBar.setValue(300);
				mongoClient.close();
				JOptionPane.showMessageDialog(null, "CSV Upload Complete");

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		public static void uploadServiceCSV(String CompanyName, String CollectionName, JProgressBar progressBar) throws NumberFormatException {

			try {

				CompanyName = CompanyName.toLowerCase();
				MongoClientURI uri = new MongoClientURI(
						"" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/" + CompanyName
								+ "?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true");
				MongoClient mongoClient = new MongoClient(uri);
				MongoDatabase database = mongoClient.getDatabase(CompanyName);
				MongoCollection<Document> collection = database.getCollection(CollectionName);

				// convert CSV directly
				try {
					String file = GUI.fileUpload();
					List<ProductServices> beans = new CsvToBeanBuilder(new FileReader(file))
							// we ask if the file contians headers upon radial selection it will skip first
							// header line
							.withType(ProductServices.class).withSkipLines(1).build().parse();

					if (beans.get(0).getId() != 1) {
						beans = new CsvToBeanBuilder(new FileReader(file))
								// we ask if the file contians headers upon radial selection it will skip first
								// header line
								.withType(ProductServices.class).withSkipLines(0).build().parse();
						for (int x = 0; x < beans.size(); x++) {

							Document doc = new Document("id", beans.get(x).getId());
							doc.append("service name", Encrypt.encryptData(beans.get(x).getTitle().toUpperCase()));
							doc.append("cost", Encrypt.encryptData(beans.get(x).getCost().toUpperCase()));
							doc.append("category", Encrypt.encryptData(beans.get(x).getCategory().toUpperCase()));
							collection.insertOne(doc);
						}
					} else {

						for (int x = 0; x < beans.size(); x++) {

							Document doc = new Document("id", beans.get(x).getId());
							doc.append("service name", Encrypt.encryptData(beans.get(x).getTitle().toUpperCase()));
							doc.append("cost", Encrypt.encryptData(beans.get(x).getCost().toUpperCase()));
							doc.append("category", Encrypt.encryptData(beans.get(x).getCategory().toUpperCase()));
							collection.insertOne(doc);
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				progressBar.setValue(300);
				mongoClient.close();
				JOptionPane.showMessageDialog(null, "CSV Upload Complete");

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		public static void uploadFinancialHoldingsCSV(String CompanyName, String CollectionName , JProgressBar progressBar)
				throws NumberFormatException {

			try {

				CompanyName = CompanyName.toLowerCase();
				MongoClientURI uri = new MongoClientURI(
						"" + "mongodb://User_1:Passw0rd1@companyvault-shard-00-00.yjpzu.mongodb.net:27017/" + CompanyName
								+ "?ssl=true&replicaSet=atlas-6z6827-shard-0&authSource=admin&retryWrites=true");
				MongoClient mongoClient = new MongoClient(uri);
				MongoDatabase database = mongoClient.getDatabase(CompanyName);
				MongoCollection<Document> collection = database.getCollection(CollectionName);

				// convert CSV directly
				try {
					String file = GUI.fileUpload();
					List<FinancialHoldings> beans = new CsvToBeanBuilder(new FileReader(file))
							// we ask if the file contians headers upon radial selection it will skip first
							// header line
							.withType(FinancialHoldings.class).withSkipLines(1).build().parse();

					if (beans.get(0).getId() != 1) {
						beans = new CsvToBeanBuilder(new FileReader(file))
								// we ask if the file contians headers upon radial selection it will skip first
								// header line
								.withType(FinancialHoldings.class).withSkipLines(0).build().parse();
						for (int x = 0; x < beans.size(); x++) {

							Document doc = new Document("id", beans.get(x).getId());
							doc.append("account name", Encrypt.encryptData(beans.get(x).getAccountName().toUpperCase()));
							doc.append("balance", Encrypt.encryptData(beans.get(x).getBalance()));
							doc.append("bank", Encrypt.encryptData(beans.get(x).getBankingInstitution().toUpperCase()));
							doc.append("account number",
									Encrypt.encryptData(beans.get(x).getAccountNumber().toUpperCase()));
							collection.insertOne(doc);
						}
					} else {

						for (int x = 0; x < beans.size(); x++) {

							Document doc = new Document("id", beans.get(x).getId());
							doc.append("account name", Encrypt.encryptData(beans.get(x).getAccountName().toUpperCase()));
							doc.append("balance", Encrypt.encryptData(beans.get(x).getBalance().toUpperCase()));
							doc.append("bank", Encrypt.encryptData(beans.get(x).getBankingInstitution().toUpperCase()));
							doc.append("account number",
									Encrypt.encryptData(beans.get(x).getAccountNumber().toUpperCase()));
							collection.insertOne(doc);
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				progressBar.setValue(300);
				mongoClient.close();
				JOptionPane.showMessageDialog(null, "CSV Upload Complete");

			} catch (Exception e) {
				e.printStackTrace();
			}

		}


}

