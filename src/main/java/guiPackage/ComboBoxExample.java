package guiPackage;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

 class ComboBoxExample extends JFrame implements ActionListener{
	String [] messageStrings = {"Single File Upload","Bulk File Type Upload","Manual Entry"};
	JComboBox cmbMessageList =  new JComboBox (messageStrings);
	JLabel lblText = new JLabel ();
	JButton button;
	JButton buttonx = new JButton() ;
	JPanel mainPanel;
	CardLayout cardLayout;
	JPanel insertRecords;
	public void begin() {
		setLayout(new FlowLayout());
		setSize(400,300);
		setSize(new Dimension(600, 600));
		
		buttonx.setBounds(500,500,500,500);
		buttonx.setText("GO");
		buttonx.setSize(500,500);
		buttonx.setFocusable(false);
		add(buttonx);
		buttonx.addActionListener(new Action0());
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	public ComboBoxExample() {
		
	
	}
	
	 class Action0 implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
				revalidate();
				setVisible(true);
				
				
			
				button = new JButton();
				button.setBounds(100,100,500,500);
				
				button.setText("GO");
				button.setSize(500,500);
				button.setFocusable(false);
				add(button);
				cmbMessageList.setSelectedIndex(1);
				cmbMessageList.addActionListener(new Action1());
				add(cmbMessageList);
				add(lblText);
		        revalidate();
				
			}
		}
	 public void ShowUpload() {
			
			
			
			
			
			revalidate();
			setVisible(true);
			
			
		
			button = new JButton();
			button.setBounds(100,100,500,500);
			
			button.setText("GO");
			button.setSize(500,500);
			button.setFocusable(false);
			add(button);
			cmbMessageList.setSelectedIndex(1);
			cmbMessageList.addActionListener(new Action1());
			add(cmbMessageList);
			add(lblText);
	        revalidate();
			
		}
	 class Action implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			
			remove(button);
			remove(cmbMessageList);
			setVisible(false);
			setVisible(true);
			
			revalidate();
		
			JTabbedPane pane = new JTabbedPane();
		    pane.addTab("Employees",new JPanel() );
		    pane.addTab("Properties", new JPanel());
		    pane.addTab("Buisness Plan", new JPanel());
	        pane.addTab("Financial Holdings", new JPanel());
	        setLayout(new BorderLayout());
	        setSize(new Dimension(600, 600));
	        setContentPane(pane);
	        revalidate();
			
		}
	}
	 class Actionx implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				
				
				remove(button);
				remove(cmbMessageList);
				setVisible(false);
				setVisible(true);
				
				revalidate();
			
				JTabbedPane pane = new JTabbedPane();
			    pane.addTab("Employees",new JPanel() );
			    pane.addTab("Properties", new JPanel());
			    pane.addTab("Builan", new JPanel());
		        pane.addTab("Financial Hs", new JPanel());
		        setLayout(new BorderLayout());
		        setSize(new Dimension(600, 600));
		        setContentPane(pane);
		        revalidate();
				
			}
		}
	 class Action2 implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				
				
				remove(button);
				remove(cmbMessageList);
				setVisible(false);
				setVisible(true);
				
				revalidate();
			
//				JTabbedPane pane = new JTabbedPane();
//			    pane.addTab("Employees",new JPanel() );
//			    pane.addTab("Properties", new JPanel());
//			    pane.addTab("Buisness Plan", new JPanel());
//		        pane.addTab("Financial Holdings", new JPanel());
		        setLayout(new BorderLayout());
		        setSize(new Dimension(600, 600));
		        setContentPane(createInsertRecords());
		        revalidate();
				
			}
		}
	
	public static JPanel createInsertRecords() {
		JPanel records = new JPanel();
		records.setBackground(Color.black);
		
		
		
		return records;
	}
	class Action1 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
		
			if(e.getSource()==cmbMessageList) {
				
				JComboBox cb = (JComboBox)e.getSource();
				String msg = (String)cb.getSelectedItem();
				switch(msg) {
				case"Single File Upload": lblText.setText("You Selected:"+msg);button.addActionListener(new Action());
				break;
				case"Bulk File Type Upload": lblText.setText("You Selected:"+msg);button.addActionListener(new Action2());
				break;
				case"Manual Entry": lblText.setText("You Selected:"+msg);button.addActionListener(new Action());
				break;
				default:lblText.setText("WHOOPS ERROR");
				
				}
			}
			
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==cmbMessageList) {
			
			JComboBox cb = (JComboBox)e.getSource();
			String msg = (String)cb.getSelectedItem();
			switch(msg) {
			case"Single File Upload": lblText.setText("You Selected:"+msg);;
			break;
			case"Bulk File Type Upload": lblText.setText("You Selected:"+msg);;
			break;
			case"Manual Entry": lblText.setText("You Selected:"+msg);
			break;
			default:lblText.setText("WHOOPS ERROR");
			
			}
		}
		
	}
	
	public static void main(String[] args) {
		 SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                ComboBoxExample comboBoxExample = new ComboBoxExample();
					comboBoxExample.begin();
	            }
	        });
		
	}
	
}
