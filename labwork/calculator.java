import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class calculator {
    private JFrame frame;
    private JTextField displayField;
    private JButton[] numberButtons;
    private JButton[] operationButtons;
    private JButton clearButton, equalsButton;
    private String input = "";

    public calculator() {
        frame = new JFrame("Enhanced Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout());

        displayField = new JTextField();
        displayField.setEditable(false);
        displayField.setFont(new Font("Arial", Font.PLAIN, 24));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4));

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = createNumberButton(Integer.toString(i));
            buttonPanel.add(numberButtons[i]);
        }

        operationButtons = new JButton[4];
        operationButtons[0] = createOperationButton("+");
        operationButtons[1] = createOperationButton("-");
        operationButtons[2] = createOperationButton("*");
        operationButtons[3] = createOperationButton("/");

        clearButton = createControlButton("C");
        equalsButton = createControlButton("=");

        buttonPanel.add(operationButtons[0]);
        buttonPanel.add(operationButtons[1]);
        buttonPanel.add(operationButtons[2]);
        buttonPanel.add(operationButtons[3]);
        buttonPanel.add(clearButton);
        buttonPanel.add(numberButtons[0]);
        buttonPanel.add(equalsButton);

        frame.add(displayField, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private JButton createNumberButton(String label) {
        JButton button = new JButton(label);
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.addActionListener(new NumberButtonListener());
        return button;
    }

    private JButton createOperationButton(String label) {
        JButton button = new JButton(label);
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.addActionListener(new OperationButtonListener());
        return button;
    }

    private JButton createControlButton(String label) {
        JButton button = new JButton(label);
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.addActionListener(new ControlButtonListener());
        return button;
    }

    private class NumberButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String digit = e.getActionCommand();
            input += digit;
            displayField.setText(input);
        }
    }

    private class OperationButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String operation = e.getActionCommand();
            if (!input.isEmpty()) {
                input += " " + operation + " ";
                displayField.setText(input);
            }
        }
    }

    private class ControlButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("C")) {
                input = "";
                displayField.setText("");
            } else if (command.equals("=")) {
                try {
                    String[] tokens = input.split(" ");
                    double result = Double.parseDouble(tokens[0]);
                    for (int i = 1; i < tokens.length; i += 2) {
                        String operator = tokens[i];
                        double operand = Double.parseDouble(tokens[i + 1]);
                        switch (operator) {
                            case "+":
                                result += operand;
                                break;
                            case "-":
                                result -= operand;
                                break;
                            case "*":
                                result *= operand;
                                break;
                            case "/":
                                if (operand != 0) {
                                    result /= operand;
                                } else {
                                    displayField.setText("Error");
                                    return;
                                }
                                break;
                        }
                    }
                    displayField.setText(Double.toString(result));
                    input = "";
                } catch (Exception ex) {
                    displayField.setText("Error");
                    input = "";
                }
            }
        }
    }

    public static void main(String[] args) {
        new calculator();
    }
}
