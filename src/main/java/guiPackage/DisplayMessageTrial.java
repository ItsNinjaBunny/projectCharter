package guiPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DisplayMessageTrial extends JFrame {

private JPanel jPanel1;
private JButton button = new JButton("Click Me!");

public DisplayMessageTrial() {
    super("DisplayMessageTrial");
    jPanel1 = new JPanel();
    jPanel1.add(button);
    setContentPane(jPanel1);
    button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            jButton1ActionPerformed(e);
        }
    });
}

public void addTextTry(){
	

    String[] choices = { "CHOICE 1","CHOICE 2", "CHOICE 3","CHOICE 4","CHOICE 5","CHOICE 6"};

    final JComboBox<String> cb = new JComboBox<String>(choices);

    cb.setVisible(true);
   
    jPanel1.add(cb);
   
    jPanel1.revalidate();
}

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    addTextTry();
}  


public static void main(String[] args) {
    DisplayMessageTrial trial = new DisplayMessageTrial();
    trial.setBounds(100, 100, 300, 300);
    trial.setVisible(true);
}

}