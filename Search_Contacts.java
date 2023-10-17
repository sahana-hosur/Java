import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Search_Contacts extends JFrame {

    private JTextField searchField;
    private JTable resultTable;
    private DefaultTableModel tableModel;

    public Search_Contacts(){
        super("Contact Search");
        // Create GUI components
        JLabel searchLabel = new JLabel("Search:");
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new SearchButtonListener());

        tableModel = new DefaultTableModel(new Object[]{"Name", "Number"}, 0);
        resultTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultTable);

        // Create layout
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(searchLabel);
        panel.add(searchField);
        panel.add(searchButton);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        setBounds(600, 200, 400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setVisible(true);
    }

    private class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String keyword = searchField.getText();
            if(keyword.equals("")) 
            {
        
            JOptionPane.showMessageDialog(rootPane, "Enter the KeyWord","Error", JOptionPane.ERROR_MESSAGE);
            return;
            }
            searchContacts(keyword);
        }
    }

    private void searchContacts(String keyword) {
        tableModel.setRowCount(0); // Clear the table

        try (BufferedReader reader = new BufferedReader(new FileReader("contact.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String name = fields[0];
                String Number = fields[1];
                if (name.toLowerCase().contains(keyword.toLowerCase()) || Number.contains(keyword)) {
                //    Vector<Object> row = new Vector<>();
                //     row.add(name);
                //     row.add(Number);
                    tableModel.addRow(fields);
                // tableModel.addRow(row);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(rootPane, "File Not Found", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (tableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "No contacts found", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new Search_Contacts();
    }
}