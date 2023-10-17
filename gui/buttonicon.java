package gui;
import javax.swing.*;
public class buttonicon {
    buttonicon(){
        JFrame f=new JFrame("Button Example");
        JButton b=new JButton(new ImageIcon("D:\\91990\\icon.png"));
        b.setBounds(100,100,780,300);
        f.add(b);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        new buttonicon();
    }
    
}
