package database;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class Delete {
	private static JPanel panel;
	private static JPanel panel2;
	private static JPanel panel3;
	private static JPanel panel4;
	private static JPanel panel5;
	public static JPanel deleteEmployee(JPanel footnotes,String companyName) {
		JButton button = new JButton("SEARCH");
		panel = new JPanel();
		panel.setLayout(null);
		
		
		JLabel firstLabel = new JLabel("First name: ");
		JLabel lastLabel = new JLabel("Last name: ");
		JLabel hireLabel = new JLabel("SSN: ");
		
		ArrayList<JLabel> list = new ArrayList<>();
		list.add(firstLabel);
		list.add(lastLabel);
		list.add(hireLabel);
		
		int x = 10;
		int y = 20;
		for(JLabel label: list) {
			label.setBounds(x, y, 80, 25);
			y += 30;
			panel.add(label);
		}
		
		JTextField firstText = new JTextField();
		JTextField lastText = new JTextField();
		JTextField hireText = new JTextField();
		
		ArrayList<JTextField> list1 = new ArrayList<>();
		list1.add(firstText);
		list1.add(lastText);
		list1.add(hireText);
		
		int h = 20;
		int w = 100;
		for(JTextField label: list1) {
			label.setBounds(w, h, 150, 25);
			h += 30;
			panel.add(label);
		}
		

		
		button.setForeground(Color.BLACK);
		button.setOpaque(true);
		button.setBounds(320, 52, 100, 20);
		panel.add(button);
		
		JButton update = new JButton("Delete");
		update.setBounds(320, 74, 100, 20);
		update.setVisible(false);

		panel.add(update);
		
		button.addActionListener(new ActionListener() {
		
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				String hireYear = hireText.getText().replace("-", "");
				
				
				footnotes.removeAll();
				footnotes.revalidate();
			
				button.setVisible(true);
				update.setVisible(true);
				
				
				String firstName = firstText.getText();
				String lastName = lastText.getText();
				
				String db = "northwind";
				
				
				DefaultListModel document = new DefaultListModel();
				
				//findEmployee(db, firstName, lastName, hireYear, document);
				
				JList vector = new JList(document);
								
				JScrollPane scroll = new JScrollPane(vector);
				vector.setVisibleRowCount(5);
				vector.setLayoutOrientation(JList.VERTICAL);
				vector.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
				
				scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				scroll.setVisible(true);
				footnotes.add(scroll, BorderLayout.CENTER);
				
				update.addActionListener(new ActionListener() {
				
					@Override
					public void actionPerformed(ActionEvent e) {
						
						String test = String.valueOf(vector.getSelectedValue());
						//collection.deleteOne(query).first();
						System.out.println(test);
						String[] result = test.split(": ");
						for(int i = 0; i < result.length; i++) {
							System.out.println(result[i]);
						}
						//delete method here from results
						JOptionPane.showMessageDialog(null, "Now Deleting "+test.toString());
						update.setVisible(false);
						JScrollPane scroll = new JScrollPane();
						scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
						scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
						scroll.setVisible(true);
						footnotes.add(scroll, BorderLayout.CENTER);
					}
				});
				
				footnotes.revalidate();
			
			}
		});
		return panel;
	}

	//Property search method panel
	public static JPanel deleteProperty(JPanel footnotes,String companyName) {
		panel2 = new JPanel();
		panel2.setLayout(null);
		JButton button2 = new JButton("SEARCH");
				
		JLabel firstLabel = new JLabel("Property Name: ");
		
		
		ArrayList<JLabel> list = new ArrayList<>();
		list.add(firstLabel);
		
		
		int x = 10;
		int y = 20;
		for(JLabel label: list) {
			label.setBounds(x, y, 120, 25);
			y += 30;
			panel2.add(label);
		}
		
		JTextField firstText = new JTextField();
		
		
		ArrayList<JTextField> list1 = new ArrayList<>();
		list1.add(firstText);
		
		
		int h = 20;
		int w = 100;
		for(JTextField label: list1) {
			label.setBounds(w, h, 150, 25);
			h += 30;
			panel2.add(label);
		}
		
		JButton update1 = new JButton("Delete");
		update1.setBounds(320, 74, 100, 20);
		update1.setVisible(false);
		panel2.add(update1);
		button2.setForeground(Color.BLACK);
		button2.setOpaque(true);
		button2.setBounds(320, 52, 100, 20);
		panel2.add(button2);
		button2.addActionListener(new ActionListener() {
		
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
			
				footnotes.removeAll();
				footnotes.revalidate();
				update1.setVisible(true);
				DefaultListModel document = new DefaultListModel();
				//searches by property name
				//Find.findRecords(firstName, document);
				
				@SuppressWarnings({ })
				JList vector = new JList(document);
				
				
				JScrollPane scroll = new JScrollPane(vector);
				vector.setVisibleRowCount(5);
				vector.setLayoutOrientation(JList.VERTICAL);
				vector.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
				
				scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				scroll.setVisible(true);
				footnotes.add(scroll, BorderLayout.CENTER);
				
				
				update1.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						String test = String.valueOf(vector.getSelectedValue());
						//collection.deleteOne(query).first();
						System.out.println(test);
						String[] result = test.split(": ");
						for(int i = 0; i < result.length; i++) {
							System.out.println(result[i]);
						}
						//delete method here from results
						JOptionPane.showMessageDialog(null, "Now Deleting "+test.toString());
						update1.setVisible(false);
						JScrollPane scroll = new JScrollPane();
						scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
						scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
						scroll.setVisible(true);
						footnotes.add(scroll, BorderLayout.CENTER);
					}
				});
				footnotes.revalidate();
			
			}
		});
		return panel2;
	}
	
	//Products search
	public static JPanel deleteProduct(JPanel footnotes,String companyName) {
		JButton button3 = new JButton("SEARCH");
		panel3 = new JPanel();
		panel3.setLayout(null);
		
		
		JLabel firstLabel = new JLabel("Product name: ");
		JLabel lastLabel = new JLabel("Category: ");
		JLabel hireLabel = new JLabel("Supplier: ");
		
		
		ArrayList<JLabel> list = new ArrayList<>();
		list.add(firstLabel);
		list.add(lastLabel);
		list.add(hireLabel);
		
		int x = 10;
		int y = 20;
		for(JLabel label: list) {
			label.setBounds(x, y, 80, 25);
			y += 30;
			panel3.add(label);
		}
		
		JTextField firstText = new JTextField();
		JTextField lastText = new JTextField();
		JTextField hireText = new JTextField();
		
		ArrayList<JTextField> list1 = new ArrayList<>();
		list1.add(firstText);
		list1.add(lastText);
		list1.add(hireText);
		
		int h = 20;
		int w = 100;
		for(JTextField label: list1) {
			label.setBounds(w, h, 150, 25);
			h += 30;
			panel3.add(label);
		}
		

		JButton update2 = new JButton("Delete");
		update2.setBounds(320, 74, 100, 20);
		update2.setVisible(false);
		panel3.add(update2);
		
		button3.setForeground(Color.BLACK);
		button3.setOpaque(true);
		button3.setBounds(320, 52, 100, 20);
		panel3.add(button3);
		button3.addActionListener(new ActionListener() {
		
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
				
			
				update2.setVisible(true);
				footnotes.removeAll();
				footnotes.revalidate();				
				
				DefaultListModel document = new DefaultListModel();
				//insert find records for this type
				//Find.findRecords(firstName, lastName, document);
				
				@SuppressWarnings({ })
				JList vector = new JList(document);
				
				
				JScrollPane scroll = new JScrollPane(vector);
				vector.setVisibleRowCount(5);
				vector.setLayoutOrientation(JList.VERTICAL);
				vector.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
				
				scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				scroll.setVisible(true);
				footnotes.add(scroll, BorderLayout.CENTER);
				
				update2.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						String test = String.valueOf(vector.getSelectedValue());
						//collection.deleteOne(query).first();
						System.out.println(test);
						String[] result = test.split(": ");
						for(int i = 0; i < result.length; i++) {
							System.out.println(result[i]);
						}
						//delete method here from results
						JOptionPane.showMessageDialog(null, "Now Deleting "+test.toString());
						update2.setVisible(false);
						JScrollPane scroll = new JScrollPane();
						scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
						scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
						scroll.setVisible(true);
						footnotes.add(scroll, BorderLayout.CENTER);
					}
				});
				footnotes.revalidate();
			
				
			}
		});
		return panel3;
	}
	
	//search service
	public static JPanel deleteService(JPanel footnotes,String companyName) {
		JButton button4 = new JButton("SEARCH");
		panel4 = new JPanel();
		panel4.setLayout(null);
		
		
		JLabel firstLabel = new JLabel("Service name: ");
		JLabel lastLabel = new JLabel("Category: ");
		
		
		ArrayList<JLabel> list = new ArrayList<>();
		list.add(firstLabel);
		list.add(lastLabel);
	
		
		int x = 10;
		int y = 20;
		for(JLabel label: list) {
			label.setBounds(x, y, 80, 25);
			y += 30;
			panel4.add(label);
		}
		
		JTextField firstText = new JTextField();
		JTextField lastText = new JTextField();
		
		
		ArrayList<JTextField> list1 = new ArrayList<>();
		list1.add(firstText);
		list1.add(lastText);
	
		
		int h = 20;
		int w = 100;
		for(JTextField label: list1) {
			label.setBounds(w, h, 150, 25);
			h += 30;
			panel4.add(label);
		}
		

		JButton update3 = new JButton("Delete");
		update3.setBounds(320, 74, 100, 20);
		update3.setVisible(false);
		panel4.add(update3);
		button4.setForeground(Color.BLACK);
		button4.setOpaque(true);
		button4.setBounds(320, 52, 100, 20);
		panel4.add(button4);
		button4.addActionListener(new ActionListener() {
		
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				update3.setVisible(true);
				footnotes.removeAll();
				footnotes.revalidate();
				
				DefaultListModel document = new DefaultListModel();
				//insert find records for this type
				//Find.findRecords(firstName, lastName, hireYear, document);
				
				@SuppressWarnings({ })
				JList vector = new JList(document);
				
				JScrollPane scroll = new JScrollPane(vector);
				vector.setVisibleRowCount(5);
				vector.setLayoutOrientation(JList.VERTICAL);
				vector.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
				
				scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				scroll.setVisible(true);
				footnotes.add(scroll, BorderLayout.CENTER);
				update3.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						String test = String.valueOf(vector.getSelectedValue());
						//collection.deleteOne(query).first();
						System.out.println(test);
						String[] result = test.split(": ");
						for(int i = 0; i < result.length; i++) {
							System.out.println(result[i]);
						}
						//delete method here from results
						JOptionPane.showMessageDialog(null, "Now Deleting "+test.toString());
						update3.setVisible(false);
						JScrollPane scroll = new JScrollPane();
						scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
						scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
						scroll.setVisible(true);
						footnotes.add(scroll, BorderLayout.CENTER);
					}
				});
				footnotes.revalidate();
				
			}
		});
		return panel4;
	}
	
	//search Financials
	public static JPanel deleteFinancials(JPanel footnotes,String companyName) {
		JButton button5 = new JButton("SEARCH");
		panel5 = new JPanel();
		panel5.setLayout(null);
		
		
		JLabel firstLabel = new JLabel("Account name: ");
		JLabel accountLabel = new JLabel("Account ID: ");
		JLabel lastLabel = new JLabel("Bank: ");
		
		
		ArrayList<JLabel> list = new ArrayList<>();
		list.add(firstLabel);
		list.add(accountLabel);
		list.add(lastLabel);
		
		
		int x = 10;
		int y = 20;
		for(JLabel label: list) {
			label.setBounds(x, y, 100, 25);
			y += 30;
			panel5.add(label);
		}
		
		JTextField firstText = new JTextField();
		JTextField lastText = new JTextField();
		JTextField banlText = new JTextField();
		
		ArrayList<JTextField> list1 = new ArrayList<>();
		list1.add(firstText);
		list1.add(banlText);
		list1.add(lastText);
	
		
		int h = 20;
		int w = 100;
		for(JTextField label: list1) {
			label.setBounds(w, h, 150, 25);
			h += 30;
			panel5.add(label);
		}
		JButton update2 = new JButton("Delete");
		update2.setBounds(320, 74, 100, 20);
		update2.setVisible(false);
		panel5.add(update2);
		
		button5.setForeground(Color.BLACK);
		button5.setOpaque(true);
		button5.setBounds(320, 52, 100, 20);
		panel5.add(button5);
		button5.addActionListener(new ActionListener() {
		
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
				
				footnotes.removeAll();
				footnotes.revalidate();
				update2.setVisible(true);
				DefaultListModel document = new DefaultListModel();
				//insert find records for this type
				//Find.findRecords(firstName, lastName, hireYear, document);
				
				@SuppressWarnings({ })
				JList vector = new JList(document);
				
				JScrollPane scroll = new JScrollPane(vector);
				vector.setVisibleRowCount(5);
				vector.setLayoutOrientation(JList.VERTICAL);
				vector.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
				
				scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				scroll.setVisible(true);
				footnotes.add(scroll, BorderLayout.CENTER);
				update2.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						String test = String.valueOf(vector.getSelectedValue());
						//collection.deleteOne(query).first();
						System.out.println(test);
						String[] result = test.split(": ");
						for(int i = 0; i < result.length; i++) {
							System.out.println(result[i]);
						}
						//delete method here from results
						JOptionPane.showMessageDialog(null, "Now Deleting "+test.toString());
						update2.setVisible(false);
						JScrollPane scroll = new JScrollPane();
						scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
						scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
						scroll.setVisible(true);
						footnotes.add(scroll, BorderLayout.CENTER);
					}
				});
				footnotes.revalidate();
			
			
			}
		});
		return panel5;
	}

}
