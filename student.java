import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class student  implements ActionListener{
    private static JFrame f;   
    private static JLabel l1,l2,l3;
    private static JComboBox<String> sem;
    private static JButton go;
    public static void main(String[] args){
        Font font=new Font("Cooper Black",Font.BOLD,18);
        f=new JFrame("Detail Catalog");
        f.setSize(400, 190);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.setLocationRelativeTo(null);

        l1=new JLabel("Greetings Teacher");
        l1.setBounds(110, 20, 180, 20);
        l1.setFont(font);
        f.add(l1);

        l2=new JLabel("How may I Help you Today...");
        l2.setFont(font);
        l2.setBounds(15, 30, 280, 40);
        f.add(l2);

        l3= new JLabel("Choose Semester");
        l3.setFont(new Font("Cooper Black", Font.PLAIN, 14));
        l3.setBounds(15, 60, 130, 50);
        f.add(l3);

        String[] s={"I","II","III","IV","V","VI","VII","VIII"};
        sem=new JComboBox<>(s);
        sem.setFont(new Font("Cooper Black", Font.PLAIN, 11));
        sem.setBounds(160,80,50,20);
        f.add(sem);


        go=new JButton("Go");
        go.setFont(font);
        go.setBounds(150, 120, 90, 30);
        go.addActionListener(new student());
        f.add(go);

        f.setVisible(true);

    }
    @Override
    public void actionPerformed (ActionEvent e){
        f.setVisible(false);
        new choose((String)sem.getSelectedItem());
        

    }

}
