import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class checkBox extends JFrame implements ActionListener {
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;
    private JButton submitButton;

    public checkBox() {
        setTitle("Check Box Example");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        checkBox1 = new JCheckBox("Option 1");
        checkBox2 = new JCheckBox("Option 2");
        checkBox3 = new JCheckBox("Option 3");

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.add(checkBox1);
        panel.add(checkBox2);
        panel.add(checkBox3);
        panel.add(submitButton);

        add(panel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StringBuilder selectedOptions = new StringBuilder("Selected options:");
        
        if (checkBox1.isSelected()) {
            selectedOptions.append(" Option 1");
        }
        
        if (checkBox2.isSelected()) {
            selectedOptions.append(" Option 2");
        }
        
        if (checkBox3.isSelected()) {
            selectedOptions.append(" Option 3");
        }

        if (selectedOptions.toString().equals("Selected options:")) {
            JOptionPane.showMessageDialog(this, "No options selected");
        } else {
            JOptionPane.showMessageDialog(this, selectedOptions.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new checkBox());
    }
}
