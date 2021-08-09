<<<<<<< HEAD
package guiPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import guiPackage.ComboBoxExample.Action;
import guiPackage.ComboBoxExample.Action0;
import guiPackage.ComboBoxExample.Action1;
import guiPackage.ComboBoxExample.Action2;

public class panels {
	static JFrame frame = new JFrame("Menu - CompanyVault.exe");
	static void Menu() {
		String [] messageStrings = {"Single File Upload","Bulk File Type Upload","Manual Entry"};
		JComboBox cmbMessageList =  new JComboBox (messageStrings);
		JLabel lblText = new JLabel ();
		JButton button = new JButton();
          JButton btn1 = new JButton(">>Insert");
          JButton btn2 = new JButton(">>Upload");
          JButton btn3 = new JButton(">>Delete");
          JButton btn4 = new JButton(">>Find");
         
          class Action1 implements ActionListener{
      		public void actionPerformed(ActionEvent e) {
      			
      		
      			if(e.getSource()==cmbMessageList) {
      				
      				JComboBox cb = (JComboBox)e.getSource();
      				String msg = (String)cb.getSelectedItem();
      				switch(msg) {
      				case"Single File Upload": lblText.setText("You Selected:"+msg);button.addMouseListener((MouseListener) new MouseAdapter() {
                    	  
                    	  @Override
                    	  public void mouseClicked(MouseEvent e) {
                    		
               				frame.dispose();
               				frame.setLayout(new BorderLayout());
               		       frame.setSize(new Dimension(600, 600));
               		        
               		       frame.setContentPane(TabbedPaneEnableDisableTab.showFrame());
               		       frame.revalidate();
               				
                    	  }});
      				break;
      				case"Bulk File Type Upload": lblText.setText("You Selected:"+msg);
      				break;
      				case"Manual Entry": lblText.setText("You Selected:"+msg);
      				break;
      				default:lblText.setText("WHOOPS ERROR");
      				
      				}
      			}
      			
      		}
      	}
     	
     	
          ArrayList<JButton> list = new ArrayList<JButton>();
          list.add(btn1);
          list.add(btn2);
          list.add(btn3);
          list.add(btn4);
          int posy = 0;
          for(JButton x:list) {
        	  
        	  x.addMouseListener((MouseListener) new MouseAdapter() {
            	  
            	  @Override
            	  public void mouseClicked(MouseEvent e) {
            		  
            		 frame.revalidate();
          			frame.setVisible(true);
          			
          			
          		
          			
          			button.setBounds(100,200,100,100);
          			
          			button.setText("GO");
          			button.setSize(10,10);
          			button.setFocusable(false);
          			frame.add(button);
          			cmbMessageList.setSelectedIndex(1);
          			cmbMessageList.addActionListener(new Action1());
          			cmbMessageList.setBounds(200,200, 100,100);
          			frame.add(cmbMessageList);
          			frame.add(lblText);
          	        frame.revalidate();
            	  }
            	  
            	  @Override
      		    public void mouseEntered(MouseEvent e) {
            		  x.setBorderPainted(false);
            		  x.setForeground(Color.white);
            		 
            		  x.setBackground(Color.BLACK);
            		  
            		frame.revalidate();
      		    	frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      		    	
      		    }
      		    
      		 
      		    @Override
      		    public void mouseExited(MouseEvent e) {
      		    	
      		    	x.setForeground(Color.black);
      		    	x.setBackground(Color.WHITE);
            		frame.revalidate();
            		
      		        frame.setCursor(Cursor.getDefaultCursor());
      		      x.setBorderPainted(true);
      		    }
              });
        	 
        	  x.setBackground(Color.white);
        	  x.setFocusable(false);
          }
          
          // create grid layout with 3 rows , 2 columns with horizontal
          // and vertical gap set to 10
          JPanel panel = new JPanel(new GridLayout(4,1));
          // add buttons to the panel
          
          panel.add(btn1);
          panel.add(btn2);
          panel.add(btn3);
          panel.add(btn4);
          
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setSize(500,500);
          frame.setLocationRelativeTo(null);
          frame.getContentPane().add(panel,"West");
         
          frame.setVisible(true);
	}
	
	
	public static void main(String [] args) {
		Menu();
	}
}
=======
package guiPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import guiPackage.ComboBoxExample.Action;
import guiPackage.ComboBoxExample.Action0;
import guiPackage.ComboBoxExample.Action1;
import guiPackage.ComboBoxExample.Action2;

public class panels {
	static JFrame frame = new JFrame("Menu - CompanyVault.exe");
	static void Menu() {
		String [] messageStrings = {"Single File Upload","Bulk File Type Upload","Manual Entry"};
		JComboBox cmbMessageList =  new JComboBox (messageStrings);
		JLabel lblText = new JLabel ();
		JButton button = new JButton();
          JButton btn1 = new JButton(">>Insert");
          JButton btn2 = new JButton(">>Upload");
          JButton btn3 = new JButton(">>Delete");
          JButton btn4 = new JButton(">>Find");
         
          class Action1 implements ActionListener{
      		public void actionPerformed(ActionEvent e) {
      			
      		
      			if(e.getSource()==cmbMessageList) {
      				
      				JComboBox cb = (JComboBox)e.getSource();
      				String msg = (String)cb.getSelectedItem();
      				switch(msg) {
      				case"Single File Upload": lblText.setText("You Selected:"+msg);button.addMouseListener((MouseListener) new MouseAdapter() {
                    	  
                    	  @Override
                    	  public void mouseClicked(MouseEvent e) {
                    		
               				frame.dispose();
               				frame.setLayout(new BorderLayout());
               		       frame.setSize(new Dimension(600, 600));
               		        
               		       frame.setContentPane(TabbedPaneEnableDisableTab.showFrame());
               		       frame.revalidate();
               				
                    	  }});
      				break;
      				case"Bulk File Type Upload": lblText.setText("You Selected:"+msg);
      				break;
      				case"Manual Entry": lblText.setText("You Selected:"+msg);
      				break;
      				default:lblText.setText("WHOOPS ERROR");
      				
      				}
      			}
      			
      		}
      	}
     	
     	
          ArrayList<JButton> list = new ArrayList<JButton>();
          list.add(btn1);
          list.add(btn2);
          list.add(btn3);
          list.add(btn4);
          int posy = 0;
          for(JButton x:list) {
        	  
        	  x.addMouseListener((MouseListener) new MouseAdapter() {
            	  
            	  @Override
            	  public void mouseClicked(MouseEvent e) {
            		  
            		 frame.revalidate();
          			frame.setVisible(true);
          			
          			
          		
          			
          			button.setBounds(100,200,100,100);
          			
          			button.setText("GO");
          			button.setSize(10,10);
          			button.setFocusable(false);
          			frame.add(button);
          			cmbMessageList.setSelectedIndex(1);
          			cmbMessageList.addActionListener(new Action1());
          			cmbMessageList.setBounds(200,200, 100,100);
          			frame.add(cmbMessageList);
          			frame.add(lblText);
          	        frame.revalidate();
            	  }
            	  
            	  @Override
      		    public void mouseEntered(MouseEvent e) {
            		  x.setBorderPainted(false);
            		  x.setForeground(Color.white);
            		 
            		  x.setBackground(Color.BLACK);
            		  
            		frame.revalidate();
      		    	frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      		    	
      		    }
      		    
      		 
      		    @Override
      		    public void mouseExited(MouseEvent e) {
      		    	
      		    	x.setForeground(Color.black);
      		    	x.setBackground(Color.WHITE);
            		frame.revalidate();
            		
      		        frame.setCursor(Cursor.getDefaultCursor());
      		      x.setBorderPainted(true);
      		    }
              });
        	 
        	  x.setBackground(Color.white);
        	  x.setFocusable(false);
          }
          
          // create grid layout with 3 rows , 2 columns with horizontal
          // and vertical gap set to 10
          JPanel panel = new JPanel(new GridLayout(4,1));
          // add buttons to the panel
          
          panel.add(btn1);
          panel.add(btn2);
          panel.add(btn3);
          panel.add(btn4);
          
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setSize(500,500);
          frame.setLocationRelativeTo(null);
          frame.getContentPane().add(panel,"West");
         
          frame.setVisible(true);
	}
	
	
	public static void main(String [] args) {
		Menu();
	}
}
>>>>>>> branch 'master' of https://github.com/braydenwonglee/projectCharter.git
