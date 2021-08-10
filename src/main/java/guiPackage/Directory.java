package guiPackage;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

import guiPackage.ComboBoxExample.Action;
import guiPackage.ComboBoxExample.Action0;
import guiPackage.ComboBoxExample.Action1;
import guiPackage.ComboBoxExample.Action2;

public class Directory extends JFrame{
	
	CardLayout cardLayout;
	JPanel mainPanel;
	JPanel tab;
	JPanel directoryPanel = new JPanel();
	

	public Directory() {
		
		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);
		add(mainPanel);
		directoryPanel = menu();
		mainPanel.add(directoryPanel, "directory");
		
		setTitle("Menu - CompanyVault.exe");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
		
	}
	
	private JPanel menu() {
		String [] messageStrings = {"Single File Upload", "Select","Bulk File Upload","Manual File Entry"};
		JComboBox cmbMessageList =  new JComboBox (messageStrings);
		JLabel lblText = new JLabel ();
		JButton button = new JButton();
        JButton btn1 = new JButton(">>Insert");
        JButton btn2 = new JButton(">>Upload");
        JButton btn3 = new JButton(">>Delete");
        JButton btn4 = new JButton(">>Find");
        JButton btn5 = new JButton(">>Back");
        
         
          class Action implements ActionListener{
      		@SuppressWarnings("rawtypes")
			public void actionPerformed(ActionEvent e) {
      			
      		
      			if(e.getSource()==cmbMessageList) {
      				
      				JComboBox cb = (JComboBox)e.getSource();
      				String msg = (String) cb.getSelectedItem();
      				switch(msg) {
      				case"Single File Upload": lblText.setText("You Selected:" + msg);
      					button.addMouseListener( new MouseAdapter() {
                    	  
                    	  @Override
                    	  public void mouseClicked(MouseEvent e) {
                    		JPanel tabbed = new TabbedPaneEnableDisableTab();
                    		setTitle("Insert Records");
                    		mainPanel.add(tabbed, "tabbed");
               				cardLayout.show(mainPanel, "tabbed");
                    	  }});
      				break;
      				case"Bulk File Type Upload": lblText.setText("You Selected:" + msg);
      				break;
      				case"Manual Entry": lblText.setText("You Selected:" + msg);
      				break;
      				case "Select":
      					JOptionPane.showMessageDialog(null, "Please select one of the options listed");
      					break;
      				default:
      					lblText.setText("WHOOPS ERROR");
      					break;      				
      				}
      			}
      			
      		}
      	}
     	
          ArrayList<JButton> list = new ArrayList<JButton>();
          list.add(btn1);
          list.add(btn2);
          list.add(btn3);
          list.add(btn4);
          list.add(btn5);
          
          for(JButton x:list) {
        	  
        	  x.addMouseListener((MouseListener) new MouseAdapter() {
            	  
            	  @Override
            	  public void mouseClicked(MouseEvent e) {
            		  
            		directoryPanel.revalidate();
          			directoryPanel.setVisible(true);
          			
          			button.setBounds(100, 50, 100, 60);
          			button.setText("GO");
          			//button.setSize(10,10);
          			button.setFocusable(false);
          			directoryPanel.add(button, "East");
          			cmbMessageList.setSelectedIndex(1);
          			cmbMessageList.addActionListener(new Action());
          			cmbMessageList.setBounds(200, 200, 160, 50);
          			directoryPanel.add(cmbMessageList);
          			directoryPanel.add(lblText);
            	  }
            	  
            	@Override
      		    public void mouseEntered(MouseEvent e) {
            		  x.setBorderPainted(false);
            		  x.setForeground(Color.white);
            		 
            		  x.setBackground(Color.BLACK);
            		  
            		directoryPanel.revalidate();
      		    	directoryPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));      		    	
      		    }

      		    @Override
      		    public void mouseExited(MouseEvent e) {
      		    	
      		    	x.setForeground(Color.black);
      		    	x.setBackground(Color.WHITE);
            		directoryPanel.revalidate();
            		
      		        directoryPanel.setCursor(Cursor.getDefaultCursor());
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
          
          add(panel, "West");
         
          return directoryPanel;
	}
	
	private JPanel find() {
		JPanel find = new JPanel();
		
		
		return find;
	}
	public static void main(String [] args) {
		Directory directory = new Directory();
	}
}
