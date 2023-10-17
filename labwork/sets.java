import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

public class sets implements ActionListener {
    private static JFrame frame;
    private static JLabel label1, label2, label3, resultlabel, head;
    private static JTextField field1, field2;
    private static JComboBox<String> box;
    private static JButton submit, clear;

    public static void main(String[] args) {
        frame = new JFrame("Set Operations");
        frame.setSize(500, 300);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label1 = new JLabel("Set1:");
        label1.setBounds(50, 30, 70, 20);
        label1.setFont(new Font("Cooper Black", Font.BOLD, 18));
        frame.add(label1);

        field1 = new JTextField();
        field1.setFont(new Font("Arial", Font.PLAIN, 16));
        field1.setBounds(120, 25, 200, 30);
        frame.add(field1);

        label2 = new JLabel("Set2:");
        label2.setBounds(50, 70, 70, 20);
        label2.setFont(new Font("Cooper Black", Font.BOLD, 18));
        frame.add(label2);

        field2 = new JTextField();
        field2.setFont(new Font("Arial", Font.PLAIN, 16));
        field2.setBounds(120, 65, 200, 30);
        frame.add(field2);

        label3 = new JLabel("Operation:");
        label3.setBounds(00, 110, 110, 20);
        label3.setFont(new Font("Cooper Black", Font.BOLD, 18));
        frame.add(label3);

        String[] str = { "Union", "Intersection", "Minus(Set1-Set2)", "Power Set" };
        box = new JComboBox<>(str);
        box.setBounds(120, 105, 110, 30);
        box.setBackground(Color.WHITE);
        frame.add(box);

        head = new JLabel();
        head.setBounds(20, 190, 100, 30);
        head.setFont(new Font("Cooper Black", Font.PLAIN, 14));
        frame.add(head);

        resultlabel = new JLabel();
        resultlabel.setBounds(40, 210, 400, 30);
        resultlabel.setFont(new Font("Calibri", Font.BOLD, 14));
        frame.add(resultlabel);

        submit = new JButton("Get Result");
        submit.setBounds(80, 150, 100, 20);
        submit.setBackground(Color.GRAY);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(new sets());
        frame.add(submit);

        clear = new JButton("Clear");
        clear.setBounds(200, 150, 70, 20);
        clear.setBackground(Color.GRAY);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(new sets());
        frame.add(clear);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clear) {
            field1.setText("");
            field2.setText("");
            head.setText("");
            box.setSelectedIndex(0);
            resultlabel.setText("");
            frame.repaint();
        }
        if (e.getSource() == submit) {
            Set<String> A = parseSet(field1.getText());
            Set<String> B = parseSet(field2.getText());
            
            if (box.getSelectedIndex() == 0) {
                performUnion(A, B);
            } else if (box.getSelectedIndex() == 1) {
                performIntersection(A, B);
            } else if (box.getSelectedIndex() == 2) {
                performDifference(A, B);
            } else if (box.getSelectedIndex() == 3) {
                performPowerSet(A);
            }
        }
    }

    private Set<String> parseSet(String input) {
        Set<String> set = new HashSet<>();
        String[] items = input.trim().split(",");
        for (String item : items) {
            set.add(item.trim());
        }
        return set;
    }

    private void performUnion(Set<String> A, Set<String> B) {
        Set<String> U = new HashSet<>(A);
        U.addAll(B);
        displayResult("Union Set:", U);
    }

    private void performIntersection(Set<String> A, Set<String> B) {
        Set<String> i = new HashSet<>(A);
        i.retainAll(B);
        if (i.isEmpty()) {
            displayResult("Intersection Set:", new HashSet<>());
        } else {
            displayResult("Intersection Set:", i);
        }
    }

    private void performDifference(Set<String> A, Set<String> B) {
        Set<String> diff = new HashSet<>(A);
        diff.removeAll(B);
        displayResult("Set1-Set2:", diff);
    }

    private void performPowerSet(Set<String> A) {
        Set<Set<String>> powerSet = new HashSet<>();
        generatePowerSet(A.toArray(new String[0]), 0, new HashSet<>(), powerSet);
        displayResult("Power Set:", powerSet);
    }

    private void generatePowerSet(String[] setArray, int index, Set<String> current, Set<Set<String>> powerSet) {
        if (index == setArray.length) {
            powerSet.add(new HashSet<>(current));
            return;
        }
        generatePowerSet(setArray, index + 1, current, powerSet);
        current.add(setArray[index]);
        generatePowerSet(setArray, index + 1, current, powerSet);
        current.remove(setArray[index]);
    }

    private void displayResult(String header, Set<?> resultSet) {
        head.setText(header);
        resultlabel.setText(resultSet.isEmpty() ? "{}" : resultSet.toString());
        frame.add(head);
        frame.add(resultlabel);
        frame.repaint();
    }
}

