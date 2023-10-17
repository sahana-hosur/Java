package gui;
import javax.swing.*;

public class inherit1 extends JFrame {
    JFrame f=new JFrame("BUTTON EXAMPLE");
    inherit1(){
        JButton b=new JButton("click");
        b.setBounds(140,100,100,40);

        add(b);
        setSize(400,500);
        setLayout(null);
        setVisible(true);
    }
    public static void main(String[] args) {
        new inherit1();
    }
}

