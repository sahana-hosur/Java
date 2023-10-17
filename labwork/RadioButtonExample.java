import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioButtonExample extends JFrame implements ActionListener {
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JButton submitButton;

    public RadioButtonExample() {
        setTitle("Radio Button Example");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        radioButton1 = new JRadioButton("Option 1");
        radioButton2 = new JRadioButton("Option 2");
        radioButton3 = new JRadioButton("Option 3");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        buttonGroup.add(radioButton3);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.add(radioButton1);
        panel.add(radioButton2);
        panel.add(radioButton3);
        panel.add(submitButton);

        add(panel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (radioButton1.isSelected()) {
            JOptionPane.showMessageDialog(this, "Option 1 selected");
        } else if (radioButton2.isSelected()) {
            JOptionPane.showMessageDialog(this, "Option 2 selected");
        } else if (radioButton3.isSelected()) {
            JOptionPane.showMessageDialog(this, "Option 3 selected");
        } else {
            JOptionPane.showMessageDialog(this, "Please select an option");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RadioButtonExample());
    }
}
