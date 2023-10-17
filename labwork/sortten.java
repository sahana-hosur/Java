import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.time.*;

public class sortten {
    private JFrame frame;
    private JComboBox<String> dataTypeComboBox;
    private JButton sortButton;
    private JTextArea resultTextArea;

    public sortten() {
        frame = new JFrame("Sorting Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new FlowLayout());

        String[] dataTypes = { "Integer", "Double", "String" };
        dataTypeComboBox = new JComboBox<>(dataTypes);

        sortButton = new JButton("Sort Data");
        sortButton.addActionListener(new SortButtonListener());

        resultTextArea = new JTextArea(10, 30);
        resultTextArea.setEditable(false);

        frame.add(new JLabel("Select Data Type:"));
        frame.add(dataTypeComboBox);
        frame.add(sortButton);
        frame.add(new JScrollPane(resultTextArea));

        frame.setVisible(true);
    }

    private class SortButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            resultTextArea.setText(""); // Clear previous results

            String dataType = (String) dataTypeComboBox.getSelectedItem();
            Object[] data = generateRandomData(dataType);
            
            Instant start = Instant.now();
            Object[] sortedData = sortData(data);
            Instant end = Instant.now();

            long timeTaken = Duration.between(start, end).toMillis();

            resultTextArea.append("Sorting completed in " + timeTaken + " milliseconds.\n");
            resultTextArea.append("Sorted data: " + Arrays.toString(sortedData));
        }
    }

    private Object[] generateRandomData(String dataType) {
        Object[] data = new Object[100000];

        Random random = new Random();

        switch (dataType) {
            case "Integer":
                for (int i = 0; i < data.length; i++) {
                    data[i] = random.nextInt();
                }
                break;
            case "Double":
                for (int i = 0; i < data.length; i++) {
                    data[i] = random.nextDouble();
                }
                break;
            case "String":
                for (int i = 0; i < data.length; i++) {
                    byte[] array = new byte[10];
                    random.nextBytes(array);
                    data[i] = new String(array);
                }
                break;
        }

        return data;
    }

    private Object[] sortData(Object[] data) {
        Object[] sortedData = Arrays.copyOf(data, data.length);

        if (sortedData[0] instanceof Integer) {
            Integer[] integerArray = Arrays.copyOf(sortedData, sortedData.length, Integer[].class);
            Arrays.sort(integerArray);
            sortedData = integerArray;
        } else if (sortedData[0] instanceof Double) {
            Double[] doubleArray = Arrays.copyOf(sortedData, sortedData.length, Double[].class);
            Arrays.sort(doubleArray);
            sortedData = doubleArray;
        } else if (sortedData[0] instanceof String) {
            String[] stringArray = Arrays.copyOf(sortedData, sortedData.length, String[].class);
            Arrays.sort(stringArray);
            sortedData = stringArray;
        }

        return sortedData;
    }

    public static void main(String[] args) {
        new sortten();
    }
}
