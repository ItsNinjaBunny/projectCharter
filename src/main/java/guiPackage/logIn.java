package guiPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.text.StyledEditorKit.FontSizeAction;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class logIn {
	
	
	
	//When testing use user name: "user" and password: "password" when entering in the user name and password fields
	
    //https://beginnersbook.com/2015/07/java-swing-tutorial/
	//hide menus options make them pick home
	//creates global variables that are utilized
    private static JPanel panel;
    private static JFrame frame;
    private static JButton button;
    private static JLabel userLabel, passwordLabel, companyLabel;
    private static JTextField userText;
    private static JPasswordField passwordText;
    private static JTextField companyText;

    public static void main(String[] args) {
    	try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			UIManager.getLookAndFeelDefaults().put("Button.background", Color.gray);
            UIManager.getLookAndFeelDefaults().put("Button.textForeground", new Color(255,255,255));
		} catch (Exception evt) {
		}
    	run();
    }
    
    //initializes all the graphical user interface objects
    public static void run() {
    	panel = new JPanel();
        
        frame = new JFrame();
        frame.setTitle("CompanyVault - I.B.A");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);
        
        companyLabel = new JLabel("Company name: ");
        companyLabel.setBounds(50, 125, 140, 25);
        panel.add(companyLabel);
        
        companyText = new JTextField();
        companyText.setBounds(150, 125, 150, 25);
        companyText.setBackground(Color.LIGHT_GRAY);
        panel.add(companyText);

        userLabel = new JLabel("Username: ");
        userLabel.setBounds(50, 160, 140, 25);
        panel.add(userLabel);

        passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(50, 195, 140, 25);
        panel.add(passwordLabel);

        userText = new JTextField();
        userText.setBounds(150, 160, 150, 25);
        userText.setBackground(Color.LIGHT_GRAY);
        panel.add(userText);
        passwordText = new JPasswordField();
        passwordText.setBounds(150, 195, 150, 25);
        passwordText.setBackground(Color.LIGHT_GRAY);
        panel.add(passwordText);

        button = new JButton("Login");
        button.setForeground(Color.BLACK);
        button.setOpaque(true);
        
        
        button.addActionListener(new ActionListener() {
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
                    GUI gui = new GUI(company);
                    gui.pack();
                    gui.setLocationRelativeTo(null);
                    gui.setVisible(true);
                }
                
                else {
                	 JOptionPane.showMessageDialog(null, "Incorrect credentials");
                }
                companyText.setText("");
                userText.setText("");
                passwordText.setText("");
            }
        });
        BufferedImage image = null;
		try {
			image = ImageIO.read(
					new File("src/main/java/guiPackage/img1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		panel.setBackground(Color.gray);
		
		JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(200, 200, image.SCALE_SMOOTH)));
		label.setBounds(290, 28, 300, 300);
		JLabel label1 = new JLabel("Welcome to CompanyVault");
		label1.setBounds(50, 75, 500, 35);
	
	
		panel.add(label);
		panel.add(label1);
        frame.setContentPane(panel);
        frame.getContentPane().add(button);
        frame.getRootPane().setDefaultButton(button);
        button.setBounds(200, 230, 100, 25);
        frame.setLocationRelativeTo(companyLabel);
        frame.setVisible(true);
    }
    
    //goes into the second cluster to make sure that their user name and password are valid
	private static boolean logInto(String company, String user, String password) {
		boolean isValid = false;
		final String logInCluster = "mongodb://User_1:Passw0rd1@cluster0-shard-00-00.iani6.mongodb.net:27017/users?ssl=true&replicaSet=atlas-118lud-shard-0&authSource=admin&retryWrites=true";
		final String dbName = "users";
		
		MongoClientURI uri = new MongoClientURI(logInCluster);
		MongoClient mongoClient = new MongoClient(uri);
		MongoDatabase database = mongoClient.getDatabase(dbName);
		String name = (database.getCollection(company).getNamespace()).toString().replace("users.", "");
		
		//tests to see if the collection in our user name log on cluster exists
		if(company.equalsIgnoreCase(name)) {
			MongoCollection<Document> collection = database.getCollection(company);
			//query to find the user name and password
			BasicDBObject query = new BasicDBObject("username", user).append("password", password);
			Document northwind = collection.find(query).first();
			//for database collection names
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

}