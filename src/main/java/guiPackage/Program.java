package guiPackage;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings({ "unused", "serial" })
public class Program extends JFrame{
	
	CardLayout cardLayout;
	JPanel mainPanel;
	JPanel menu;
	JPanel insertRecords;
	
	public Program() {
		
		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);
		menu = createMenu();
		insertRecords = createInsertRecords();
		mainPanel.add(menu, "menu");
		mainPanel.add(insertRecords, "insertRecords");
		
		add(mainPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screensize = new Dimension(500, 300);
		setPreferredSize(screensize);
        pack();
        setLocationByPlatform(true);
        setVisible(true);
	}
	
	private JPanel createMenu() {
		JPanel menu = new JPanel();
		menu.setLayout(null);
		
		menu.setSize(500, 300);
		
		menu.setBackground(Color.GREEN);
	
		JLabel insertRecords = new JLabel("Insert Records");
		insertRecords.setFont(new Font("Monaco", Font.PLAIN, 16));
		insertRecords.setBounds(40, 41, 162, 46);
		insertRecords.addMouseListener(new MouseAdapter() {
			
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        cardLayout.show(mainPanel, "insertRecords");
		    }	
		 
		    @Override
		    public void mouseEntered(MouseEvent e) {
		    	insertRecords.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		    }
		    
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		        insertRecords.setCursor(Cursor.getDefaultCursor());
		    }
		});
		
		menu.add(insertRecords);
		return menu;
	}
	
	private JPanel createInsertRecords() {
		JPanel records = new JPanel();
		records.setBackground(Color.black);
		mainPanel.add(records);
		
		
		return records;
	}

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                @SuppressWarnings("unused")
				Program program = new Program();
            }
        });
	}
}
