import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Nameapp extends JFrame implements ActionListener {
	
		private static final long serialVersionUID =1L;
		private  JTextField nameField;
		private JLabel nameLabel;
		private JButton submitButton;
		private JPanel panel;
		
		
		public Nameapp() {
			super("Name app");
			panel =new JPanel(new GridLayout(2,2,5,5));
			panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			panel.setPreferredSize(new Dimension(400,150));
			
			nameLabel =new JLabel("Enter your name : ");
			nameLabel.setFont(new Font("Serif", Font.BOLD,18));
			nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			nameField=new JTextField();
			nameField.setFont(new Font("Serif",Font.PLAIN,18));
			submitButton=new JButton("Submit"); 
			submitButton.setFont(new Font("Serif",Font.BOLD,18));
			submitButton.setForeground(Color.BLUE);
			
			panel.add(nameLabel);
			panel.add(nameField);
			panel.add(new JLabel(""));
			panel.add(submitButton);
			
			submitButton.addActionListener(this);
			
			add(panel);
			 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 setSize(500,200);
			 setLocationRelativeTo(null);
			 setVisible(true);
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == submitButton) {
				String name=nameField.getText();
				if(!name.isEmpty()) {
					nameLabel.setText(name);
					nameLabel.setFont(new Font("Serif", Font.BOLD,24));
					nameLabel.setForeground(Color.RED);
					nameField.setText("");
				}
			}
		}
       public static void main(String[] args) {
    	   new Nameapp();
       }
}
