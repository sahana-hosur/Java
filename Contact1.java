import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
// import javafx.scene.text;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
// import javax.swing.h
public class Contact1 implements ActionListener, DocumentListener {
    private static JFrame f;
    private static JTextField t1,filefield;
    private static JButton clear,browse;
    private static JLabel head, text,filelabel;
    private static JPanel search;
    private static DefaultTableModel stable;
    private static JTable table;
    private static JScrollPane scrollPane;

    public static void main(String[] args) {
        f = new JFrame("Contacts");
        f.setLayout(null);
        f.setSize(400, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);

        filelabel=new JLabel("Browse File:");
        filelabel.setFont(new Font("Cooper Black", Font.PLAIN, 15));
        filelabel.setBounds(5,45,100,30);
        f.add(filelabel);

        filefield=new JTextField("");
        filefield.setEditable(false);
        filefield.setBounds(110,50,160,25);
        filefield.setBackground(Color.WHITE);
        filefield.setBorder(null);
        filefield.setFont(new Font("Comic Sans MS",Font.BOLD,14));
        f.add(filefield);

        head=new JLabel("Seach For People");
        head.setBounds(120,5,210,40);
        head.setFont(new Font("Cooper Black", Font.PLAIN, 18));
        f.add(head);

        
        text = new JLabel("Name:");
        text.setBounds(50, 83, 50, 30);
        text.setFont(new Font("Cooper Black", Font.PLAIN, 15));
        f.add(text);

        t1 = new JTextField();
        t1.setBorder(null);
        t1.setBounds(110, 85, 160, 25);
        t1.setFont(new Font("Comic Sans MS",Font.BOLD,14));
        f.add(t1);

        clear = new JButton("Clear");
        clear.addActionListener(new Contact1());
        clear.setFont(new Font("Lucida Calligraphy",Font.BOLD,11));
        clear.setBounds(290, 85, 80, 25);
        f.add(clear);

        browse=new JButton("Browse");
        browse.addActionListener(new Contact1());
        browse.setFont(new Font("Lucida Calligraphy",Font.BOLD,11));
        browse.setBounds(290,50,80,25);
        f.add(browse);


        search = new JPanel();
        search.setLayout(new FlowLayout());
        search.setBounds(15, 130, 350, 200);
        scrollPane = new JScrollPane(table);
        f.getContentPane().add(scrollPane);
        scrollPane.setBounds(15, 130, 350, 200);

        stable = new DefaultTableModel(0, 2);
        stable.setColumnIdentifiers(new Object[] { "Name", "Number" });
        table = new JTable(stable); 
        table.setFont(new Font("Comic Sans MS",Font.BOLD,13));
        search.add(table);
        scrollPane.setViewportView(table);

        f.getContentPane().add(search);
        f.setVisible(true);
        t1.getDocument().addDocumentListener(new Contact1());
        f.requestFocusInWindow();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==clear){
        t1.setText("");
        stable.setRowCount(0);
        }
        else if(e.getSource()==browse){
            JFileChooser choose=new JFileChooser();
            choose.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int res=choose.showOpenDialog(f);
            if(res==JFileChooser.APPROVE_OPTION){
                String path=(String)(choose.getSelectedFile()).getAbsolutePath();
                filefield.setText(path);
                t1.setText("");
                stable.setRowCount(0);
            }
        }
    }

    public void insertUpdate(DocumentEvent e) {
        updateSearch();
    }

    public void removeUpdate(DocumentEvent e) {
        updateSearch();
    }

    public void changedUpdate(DocumentEvent e) {
        updateSearch();
    }

    public void updateSearch() {
        String s = t1.getText();
        stable.setRowCount(0);
        s = s.replaceAll("\\s", "");
        String[] found = new String[100000];
        int i = 0;
        File file = new File(filefield.getText());
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] field = line.split(",");
                if (field.length>=2 &&(field[0].toLowerCase().contains(s.toLowerCase()) || field[1].contains(s))) {
                    found[i++] = line;
                
                }
            }
            scan.close();
        } catch (FileNotFoundException f1) {
            JOptionPane.showMessageDialog(f, "Inavlid File name/path");
        }
        stable.setRowCount(0);
        for (int j = 0; j < i; j++) {
            String[] fields = found[j].split(",");
            stable.addRow(fields);
        }   
        if(stable.getRowCount()==0){
            JOptionPane.showMessageDialog(f, s+" dosen't exist in "+file, "Name Not Found", i, null);
            
        }
    }
}
