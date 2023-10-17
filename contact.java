import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;

class TrieNode {
     HashMap<Character, TrieNode> children;
    private String phoneNumber;
    private boolean isEndOfWord;

    public TrieNode() {
        children = new HashMap<>();
        phoneNumber = null;
        isEndOfWord = false;
    }

    public TrieNode getChild(char ch) {
        return children.get(ch);
    }

    public void setChild(char ch, TrieNode node) {
        children.put(ch, node);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {
        isEndOfWord = endOfWord;
    }
}

 class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word, String phoneNumber) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode child = current.getChild(ch);
            if (child == null) {
                child = new TrieNode();
                current.setChild(ch, child);
            }
            current = child;
        }
        current.setEndOfWord(true);
        current.setPhoneNumber(phoneNumber);
    }

    public String search(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode child = current.getChild(ch);
            if (child == null) {
                return null; // Word not found in the Trie
            }
            current = child;
        }
        return current.getPhoneNumber();
    }

    public List<String> getWordsWithPrefix(String prefix) {
        List<String> result = new ArrayList<>();
        TrieNode startNode = findNodeForPrefix(prefix);
        if (startNode != null) {
            findAllWordsFromNode(startNode, prefix, result);
        }
        return result;
    }

    private TrieNode findNodeForPrefix(String prefix) {
        TrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            TrieNode child = current.getChild(ch);
            if (child == null) {
                return null;
            }
            current = child;
        }
        return current;
    }

    private void findAllWordsFromNode(TrieNode node, String prefix, List<String> result) {
        if (node.isEndOfWord()) {
            result.add(prefix);
        }

        for (char ch : node.children.keySet()) {
            TrieNode child = node.children.get(ch);
            findAllWordsFromNode(child, prefix + ch, result);
        }
    }
}

public class contact implements ActionListener, DocumentListener {
    private static JFrame f;
    private static JTextField t1, filefield;
    private static JButton clear, browse;
    private static JLabel head, text, filelabel;
    private static JPanel search;
    private static DefaultTableModel stable;
    private static JTable table;
    private static JScrollPane scrollPane;
    private static Trie trie;

    public static void main(String[] args) {
        f = new JFrame("Contacts");
        f.setLayout(null);
        f.setSize(400, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);

        filelabel = new JLabel("Browse File:");
        filelabel.setFont(new Font("Cooper Black", Font.PLAIN, 15));
        filelabel.setBounds(5, 45, 100, 30);
        f.add(filelabel);

        filefield = new JTextField("");
        filefield.setEditable(false);
        filefield.setBounds(110, 50, 160, 25);
        filefield.setBackground(Color.WHITE);
        filefield.setBorder(null);
        filefield.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        f.add(filefield);

        head = new JLabel("Search For People");
        head.setBounds(120, 5, 210, 40);
        head.setFont(new Font("Cooper Black", Font.PLAIN, 18));
        f.add(head);

        text = new JLabel("Name:");
        text.setBounds(50, 83, 50, 30);
        text.setFont(new Font("Cooper Black", Font.PLAIN, 15));
        f.add(text);

        t1 = new JTextField();
        t1.setBorder(null);
        t1.setBounds(110, 85, 160, 25);
        t1.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        f.add(t1);

        clear = new JButton("Clear");
        clear.addActionListener(new contact());
        clear.setFont(new Font("Lucida Calligraphy", Font.BOLD, 11));
        clear.setBounds(290, 85, 80, 25);
        f.add(clear);

        browse = new JButton("Browse");
        browse.addActionListener(new contact());



        browse.setFont(new Font("Lucida Calligraphy", Font.BOLD, 11));
        browse.setBounds(290, 50, 80, 25);
        f.add(browse);

        search = new JPanel();
        search.setLayout(new FlowLayout());
        search.setBounds(15, 130, 350, 200);

        stable = new DefaultTableModel(0, 2);
        stable.setColumnIdentifiers(new Object[] { "Name", "Number" });
        table = new JTable(stable);
        scrollPane = new JScrollPane(table); // Move this line here
        scrollPane.setBounds(15, 130, 350, 200); // Move this line here
        search.add(scrollPane);

        f.getContentPane().add(search);
        f.setVisible(true);
        t1.getDocument().addDocumentListener(new contact());
        f.requestFocusInWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clear) {
            t1.setText("");
            stable.setRowCount(0);
        } else if (e.getSource() == browse) {
            JFileChooser choose = new JFileChooser();
            choose.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int res = choose.showOpenDialog(f);
            if (res == JFileChooser.APPROVE_OPTION) {
                String path = (String) (choose.getSelectedFile()).getAbsolutePath();
                filefield.setText(path);
                t1.setText("");
                stable.setRowCount(0);

                // Build/Update the trie based on the file data
                updateTrieFromFile(path);
                updateSearch();
            }
        }
    }

    public static void updateTrieFromFile(String filePath) {
        trie = new Trie(); // Create a new trie
        File file = new File(filePath);

        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] field = line.split(",");
                if (field.length >= 2) {
                    String name = field[0].trim();
                    String phoneNumber = field[1].trim();
                    trie.insert(name, phoneNumber);  // Assuming phone number is in the second field
                    System.out.println("Inserted into trie: Name=" + name + ", Phone=" + phoneNumber);
                }
            }
            scan.close();
        } catch (FileNotFoundException f1) {
            JOptionPane.showMessageDialog(f, "Invalid File name/path");
        }
    }

    public static List<String> getWordsWithPrefix(String prefix) {
        if (trie == null) {
            return new ArrayList<>();
        }
        return trie.getWordsWithPrefix(prefix);
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
        String s = t1.getText().trim();

        if (s.isEmpty()) {
            stable.setRowCount(0);
            return;
        }

        // Use the getWordsWithPrefix method here to get the list of matches
        List<String> matches = getWordsWithPrefix(s);

        // Print some debug information
        System.out.println("Search input: " + s);
        System.out.println("Found matches: " + matches);

        if (matches.isEmpty()) {
            JOptionPane.showMessageDialog(f, s + " doesn't exist in the Trie", "Character Not Found",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            stable.setRowCount(0); // Clear the existing table data
            for (String word : matches) {
                String phoneNumber = trie.search(word);
                if (phoneNumber != null) {
                    String[] fields = { word, phoneNumber };
                    stable.addRow(fields);
                }
            }
        }
    }
}
