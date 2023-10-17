import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;
public class eachstudent implements ActionListener{
    private JFrame  f;
    private JButton getscore;
    private JLabel enterUSN,resultlabel;
    private JLabel name,u,ia1,ia2,ia3,best,cta,cie,n,usn,IA1,IA2,IA3,Best,CTA,CIE;
    private JTextField USN;
    private JPanel resultpanel;
    public eachstudent(){
        f=new JFrame("Student's");
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(270,400);
        f.setLayout(null);

        enterUSN =new JLabel("Enter USN");
        enterUSN.setBounds(10,25,80,30);
        enterUSN.setFont(new Font("Cooper Black", Font.PLAIN, 14));
        f.add(enterUSN);

        USN=new JTextField();
        USN.setBounds(100,25,120,30);
        USN.setBorder(null);
        USN.setFont(new Font("Cooper Black", Font.PLAIN, 14));
        f.add(USN);

        getscore=new JButton("Get Scores");
        getscore.setBounds(60,70,100,25);
        getscore.setFont(new Font("Cooper Black", Font.PLAIN, 12));
        getscore.addActionListener(this);
        f.add(getscore);

        resultpanel=new JPanel();
        resultpanel.setBackground(Color.WHITE);
        resultpanel.setBounds(10,120,230,230);
        resultpanel.setLayout(null);
        f.add(resultpanel);

        resultlabel=new JLabel();
        // resultlabel.setForeground(Color.BLUE);
        resultlabel.setBounds(120, 10, 100, 20);
        resultlabel.setFont(new Font("Cooper Black", Font.PLAIN, 14)); 
        resultpanel.add(resultlabel);

        Font font=new Font("Cooper Black", Font.BOLD, 12);
        name=new JLabel();
        u=new JLabel();
        ia1=new JLabel();
        ia2=new JLabel();
        ia3=new JLabel();
        best=new JLabel();
        cta=new JLabel();
        cie=new JLabel();
        name.setFont(font);
        u.setFont(font);
        ia1.setFont(font);
        ia2.setFont(font);
        ia3.setFont(font);
        best.setFont(font);
        cta.setFont(font);
        cie.setFont(font);
        name.setBounds(10,30,40,20);
        u.setBounds(10, 50 , 40, 20);
        ia1.setBounds(10, 70 , 40, 20);
        ia2.setBounds(10, 90 , 40, 20);
        ia3.setBounds(10, 110 , 40, 20);
        best.setBounds(10,130,40,20);
        cta.setBounds(10, 150 , 40, 20);
        cie.setBounds(10, 170 , 40, 20);
        resultpanel.add(name);
        resultpanel.add(u);
        resultpanel.add(ia1);
        resultpanel.add(ia2);
        resultpanel.add(ia3);
        resultpanel.add(best);
        resultpanel.add(cta);
        resultpanel.add(cie);



        n=new JLabel();
        usn=new JLabel();
        IA1=new JLabel();
        IA2=new JLabel();
        IA3=new JLabel();
        Best=new JLabel();
        CTA=new JLabel();
        CIE=new JLabel();
        n.setFont(font);
        usn.setFont(font);
        IA1.setFont(font);
        IA2.setFont(font);
        IA3.setFont(font);
        Best.setFont(font);
        CTA.setFont(font);
        CIE.setFont(font);
        n.setBounds(55,30,140,20);
        usn.setBounds(55,50,80,20);
        IA1.setBounds(55,70,40,20);
        IA2.setBounds(55,90,40,20);
        IA3.setBounds(55,110,40,20);
        Best.setBounds(55,130,40,20);
        CTA.setBounds(55,150,40,20);
        CIE.setBounds(55,170,40,20);
        resultpanel.add(n);
        resultpanel.add(usn);
        resultpanel.add(IA1);
        resultpanel.add(IA2);
        resultpanel.add(IA3);
        resultpanel.add(Best);
        resultpanel.add(CTA);
        resultpanel.add(CIE);

        f.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e){
        // resultpanel.removeAll();
        String s=USN.getText();
        boolean found=false;
        File file=new File("D:\\java\\student_data.txt");
        try{
            Scanner scan=new Scanner(file);
            while(scan.hasNextLine()){
                String str=scan.nextLine();
                String[] match=str.split(",");
                if(match[1].toLowerCase().equals(s.toLowerCase())){
                    name.setText("Name:");
                    u.setText("USN:");
                    ia1.setText("IA1:");
                    ia2.setText("IA2:");
                    ia3.setText("IA3:");
                    best.setText("Best:");
                    cta.setText("CTA:");
                    cie.setText("CIE:");
                    n.setText(match[0]);
                    usn.setText(match[1]);
                    IA1.setText(match[2]);
                    IA2.setText(match[3]);
                    IA3.setText(match[4]);
                    Best.setText(match[5]);
                    CTA.setText(match[6]);
                    CIE.setText(match[7]);
                    resultpanel.repaint();
                    found = true;
                }
            
            }
             if(!found){
                    JOptionPane.showMessageDialog(f, "No student with Such Usn", "Not Found", 0);
                }
        }catch(IOException f){
            JOptionPane.showMessageDialog(null, "Cannot open the File", "Error", 0);
            return;
        }
    }
        

}