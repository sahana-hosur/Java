import java.awt.*;
import java.util.List;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class Main implements ActionListener {
    JFrame frame = new JFrame("Student Dashboard");
    JLabel label1;
    JButton button1, button2, button3;
    JPanel panel1;
    JTable jt;

    String[] column = {"Name      ", "USN      ", "Div", "ClassTotal", "Presents", "IA-1", "IA-2", "IA-3", "Sum of 2 Best IA marks out of 30", "CTA", "CIE"};

    public Main() {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new FlowLayout());
        frame.setLocationRelativeTo(null);

        label1 = new JLabel("        Student Information              ");
        label1.setFont(new Font("Serif", Font.ITALIC, 20));

        button1 = new JButton("Marks Entry");
        button1.addActionListener(this);

        button2 = new JButton("Toppers");
        button2.addActionListener(this);

        button3 = new JButton("Query");
        button3.addActionListener(this);

        panel1 = new JPanel();
        panel1.add(button1);
        // panel1.add(button2);
        // panel1.add(button3);
        frame.add(label1);
        frame.add(panel1);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            String filePath = "DataCTA.csv";

            try {
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                List<String[]> csvData = new ArrayList<>();

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] rowData = line.split(",");
                    csvData.add(rowData);
                }

                reader.close();

                // Convert List to Object array for the table model
                Object[][] tableData = new Object[csvData.size()][];
                for (int i = 0; i < csvData.size(); i++) {
                    tableData[i] = csvData.get(i);
                }

                // Create a new JTable
                jt = new JTable();
                DefaultTableModel model = new DefaultTableModel(tableData, column) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return true; // Allow editing of all cells
                    }
                };
                jt.setModel(model);
                jt.getColumnModel().getColumn(0).setPreferredWidth(200);  // Name
                jt.getColumnModel().getColumn(1).setPreferredWidth(280);  // USN
                jt.getColumnModel().getColumn(2).setPreferredWidth(60);   // Div
                jt.getColumnModel().getColumn(3).setPreferredWidth(80);   // ClassTotal
                jt.getColumnModel().getColumn(4).setPreferredWidth(80);   // Presents
                jt.getColumnModel().getColumn(5).setPreferredWidth(60);   // IA-1
                jt.getColumnModel().getColumn(6).setPreferredWidth(60);   // IA-2
                jt.getColumnModel().getColumn(7).setPreferredWidth(60);   // IA-3
                jt.getColumnModel().getColumn(8).setPreferredWidth(180);  // Sum of 2 Best IA marks out of 30
                jt.getColumnModel().getColumn(9).setPreferredWidth(60);   // CTA
                jt.getColumnModel().getColumn(10).setPreferredWidth(60);  // CIE

                // Show the table in a scroll pane
                JScrollPane sp = new JScrollPane(jt);

                // Create the update button
                JButton button4 = new JButton("Update");
                button4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        updateData(filePath, model);
                    }
                });

                // Create a new frame for the marks entry
                JFrame frame1 = new JFrame("Marks Entry");
                frame1.setLayout(new FlowLayout());
                frame1.add(sp);
                frame1.add(button4);
                frame1.setSize(600, 600);
                frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame1.setVisible(true);

            } catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

    private void updateData(String filePath, DefaultTableModel model) {
        try {
            int rowCount = model.getRowCount();
            int columnCount = model.getColumnCount();

            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < columnCount; j++) {
                    Object value = model.getValueAt(i, j);
                    writer.write(value != null ? value.toString() : "");
                    if (j < columnCount - 1) {
                        writer.write(",");
                    }
                }
                writer.newLine();
            }

            writer.close();

            JOptionPane.showMessageDialog(null, "Data updated successfully!");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}