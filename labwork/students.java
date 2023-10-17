import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.*;

public class students implements ActionListener{
    private JFrame  f;
    private JButton getscore;
    private JLabel enterUSN;
    private JPanel resultpanel;
    private String[][] studentsData;
    private JComboBox<String> para; 
    public students(){
        f=new JFrame("TopperList");
        f.setSize(400,200);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setResizable(false);

        enterUSN=new JLabel("Select Parameter:");
        enterUSN.setFont(new Font("Cooper Black",Font.ITALIC,12));
        enterUSN.setBounds(10,20,130,30);
        f.add(enterUSN);

        String[] s={"IA1","IA2","IA3","BEST OF 2","CTA","CIE"};
        para = new JComboBox<>(s);
        para.setFont(new Font("Cooper Black",Font.ITALIC,12));
        para.setBounds(150,20,80,30);
        f.add(para);
        readFile("D:\\java\\lab-work\\students_data.csv");

        getscore=new JButton("Result");
        getscore.setFont(new Font("Cooper Black",Font.ITALIC,12));
        getscore.setBounds(100,60,80,30);
        getscore.addActionListener(this);
        f.add(getscore);

        resultpanel = new JPanel();
        resultpanel.setBounds(10, 100, 370, 280);
        resultpanel.setFont(new Font("Cooper Black",Font.ITALIC,12));
        resultpanel.setLayout(new BoxLayout(resultpanel, BoxLayout.Y_AXIS));
        f.add(resultpanel);
        f.setVisible(true);
    }
    private void readFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int numRows = 0;
            while ((line = br.readLine()) != null) {
                numRows++;
            }
            studentsData = new String[numRows][];
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int rowIndex = 0;
            while ((line = br.readLine()) != null) {
                studentsData[rowIndex] = line.split(",");
                rowIndex++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String Parameter = (String) para.getSelectedItem();
        Toppers(Parameter);
    }
    private void Toppers(String parameter) {
        resultpanel.removeAll();
        int col = -1;
        switch (parameter) {
            case "IA1":
                col = 2;
                break;
            case "IA2":
                col = 3;
                break;
            case "IA3":
                col = 4;
                break;
            case "BEST OF 2":
                col = 5;
                break;
            case "CTA":
                col = 6;
                break;
            case "CIE":
                col = 7;
                break;
            default:
                break;
        }
        for (int i = 0; i < studentsData.length - 1; i++) {
            for (int j = 0; j < studentsData.length - i - 1; j++) {
                int score1 = Integer.parseInt(studentsData[j][col]);
                int score2 = Integer.parseInt(studentsData[j + 1][col]);
                if (score1 < score2) {
                    String[] temp = studentsData[j];
                    studentsData[j] = studentsData[j + 1];
                    studentsData[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < 3 && i < studentsData.length; i++) {
            String[] student = studentsData[i];
            JLabel label = new JLabel((i+1)+". "+student[0]+" - "+student[1]+" ("+parameter+": "+student[col]+")");
            label.setFont(new Font("Cooper Black",Font.ITALIC,12));
            resultpanel.add(label);
        }
        resultpanel.revalidate();
        resultpanel.repaint();
    }
    public static void main(String[] args){
        new students();
    }
}

