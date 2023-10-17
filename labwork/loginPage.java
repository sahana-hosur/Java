import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class loginPage implements ActionListener{
    JFrame frame;
    JLabel label1,label2,label3;
    JPanel panel1,panel2,panel3;
    JButton teacher,student,clerk;
    public loginPage(){
        frame=new JFrame("Dashboard");
        frame.setSize(500, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        label1=new JLabel("Welcome");
        label1.setFont(new Font("Comic Sans",Font.BOLD,38));
        frame.setLayout(new GridLayout(4,1));
        panel1=new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel1.add(label1);
        frame.add(panel1);
        panel2=new JPanel(new FlowLayout(FlowLayout.CENTER));
        teacher=new JButton("Teacher");
        student=new JButton("Student");
        clerk=new JButton("Clerk");
        panel2.add(teacher);
        panel2.add(clerk);
        panel2.add(student);
        frame.add(panel2);
        teacher.addActionListener(this);
        student.addActionListener(this);
        clerk.addActionListener(this);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new loginPage();
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==teacher){
            new Main();
        }
        if(e.getSource()==clerk){
            new Main();
        }
        if(e.getSource()==student){
            String s=JOptionPane.showInputDialog(frame, "Please enter the USN : ", "Student", JOptionPane.PLAIN_MESSAGE);
            try (Scanner reader = new Scanner(new File("Student.csv"))) {
                while(reader.hasNextLine()){
                    String s1=reader.nextLine();
                    String ele[]=s1.split(",");
                    if(ele[1].equals(s)){
                        StringBuilder output=new StringBuilder();
                        output.append("IA-1 : "+ele[3]+"\n"+"IA-2 : "+ele[4]+"\n"+"IA-3 : "+ele[5]+"\n");
                        JOptionPane.showMessageDialog(frame,output.toString() ,"Student Info" , JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }
                reader.close();
            } catch (HeadlessException | FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    }
}