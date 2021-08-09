package guiPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;


public class logIn implements ActionListener{
	
	
	
	//When testing use user name: "user" and password: "password" when entering in the user name and password fields
	
    //https://beginnersbook.com/2015/07/java-swing-tutorial/
	
	//creates global variables that are utilized
    private static JPanel panel;
    private static JFrame frame;
    private static JButton button;
    private static JLabel userLabel, passwordLabel, companyLabel;
    private static JTextField userText;
    private static JPasswordField passwordText;
    private static JTextField companyText;

    public static void main(String[] args) {
    	run();
    }
    
    //initializes all the graphical user interface objects
    public static void run() {
    	panel = new JPanel();
    
        frame = new JFrame();
        frame.setTitle("CompanyVault - I.B.A.G™");
        frame.setBounds(100, 100, 500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);
        
        companyLabel = new JLabel("Company name: ");
        companyLabel.setBounds(10, 20, 140, 25);
        panel.add(companyLabel);
        
        companyText = new JTextField();
        companyText.setBounds(150, 20, 180, 25);
        panel.add(companyText);

        userLabel = new JLabel("Username: ");
        userLabel.setBounds(10, 50, 140, 25);
        panel.add(userLabel);

        passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(10, 80, 140, 25);
        panel.add(passwordLabel);

        userText = new JTextField();
        userText.setBounds(150, 50, 180, 25);
        panel.add(userText);

        passwordText = new JPasswordField();
        passwordText.setBounds(150, 80, 180, 25);
        panel.add(passwordText);

        button = new JButton("Login");
        button.setBounds(10, 110, 80, 25);
        button.addActionListener(new logIn());
        panel.add(button);

        frame.setVisible(true);
    }
    
    //goes into the second cluster to make sure that their user name and password are valid
	private boolean logInto(String company, String user, String password) {
		boolean isValid = false;
		final String logInCluster = "mongodb://User_1:Passw0rd1@cluster0-shard-00-00.iani6.mongodb.net:27017/users?ssl=true&replicaSet=atlas-118lud-shard-0&authSource=admin&retryWrites=true";
		final String dbName = "users";
		
		MongoClientURI uri = new MongoClientURI(logInCluster);
		MongoClient mongoClient = new MongoClient(uri);
		MongoDatabase database = mongoClient.getDatabase(dbName);
		String name = (database.getCollection(company).getNamespace()).toString().replace("users.", "");
		System.out.println(name);
		
		//tests to see if the collection in our user name log on cluster exists
		if(company.equalsIgnoreCase(name)) {
			System.out.println("hello");
			MongoCollection<Document> collection = database.getCollection(company);
			
			//query to find the user name and password
			BasicDBObject query = new BasicDBObject("username", user).append("password", password);
			Document northwind = collection.find(query).first();
			//for database collection names
			System.out.println(northwind.get("username" + " " + northwind.get("password")));
			try {
				if(northwind.get("username").equals(user) && northwind.get("password").equals(password)) {
				
					mongoClient.close();
					return true;
				}
				else {
					mongoClient.close();
					return isValid;
				}
			}catch(Exception e) {
				e.printStackTrace();
			} 
			mongoClient.close();
			return false;	
		}
		else {
			JOptionPane.showMessageDialog(null, "The company name does not exist in our system. Make sure you are entering in the correct company name");
			mongoClient.close();
			return isValid;		
		}
	}

	//On button click it it calls on the logInto method and a dialog message is appears on what happened
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String user = String.valueOf(userText.getText().trim());
        String company = String.valueOf(companyText.getText().trim());
        @SuppressWarnings("deprecation")
		String password = String.valueOf(passwordText.getText().trim());
        
        if(user.equals("") || password.equals("")) {
        	JOptionPane.showMessageDialog(null, "Don't leave username or password blank");
        }
        
        else if(logInto(company, user, password)) {
            JOptionPane.showMessageDialog(null, "Login Successful");
            frame.dispose();
            Program program = new Program();
            program.setVisible(true);
        }
        
        else {
        	 JOptionPane.showMessageDialog(null, "Incorrect credentials. Please make sure both your username and password are correct.");
        }
        companyText.setText("");
        userText.setText("");
        passwordText.setText("");
    }

}