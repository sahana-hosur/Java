import java.awt.*;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Countergui implements ActionListener {
    private static JFrame f;
    private static JLabel label1, label2;
    private static JTextField text1, text2;
    private static JButton strt1, strt2, stop1, stop2;
    private CounterThread counter1, counter2;

    public Countergui() {
        f = new JFrame("Counter GUI");
        ImageIcon img = new ImageIcon("C:/Users/91990/Downloads/timer.png");
        f.setIconImage(img.getImage());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500, 200);
        f.setLayout(null);
        f.setLocationRelativeTo(null);

        label1 = new JLabel("Counter1");
        label2 = new JLabel("Counter2");
        label1.setFont(new Font("Arial", Font.BOLD, 12));
        label2.setFont(new Font("Arial", Font.BOLD, 12));
        label1.setBounds(10, 30, 60, 30);
        label2.setBounds(10, 80, 60, 30);
        f.add(label1);
        f.add(label2);

        text1 = new JTextField("0");
        text2 = new JTextField("0");
        text1.setBounds(90, 30, 90, 30);
        text2.setBounds(90, 80, 90, 30);
        text1.setEditable(false);
        text2.setEditable(false);
        f.add(text1);
        f.add(text2);

        strt1 = new JButton("Start Counter1");
        strt1.setBounds(190, 30, 120, 30);
        f.add(strt1);
        strt2 = new JButton("Start Counter2");
        strt2.setBounds(190, 80, 120, 30);
        f.add(strt2);

        stop1 = new JButton("Stop Counter1");
        stop1.setBounds(330, 30, 120, 30);
        f.add(stop1);
        stop2 = new JButton("Stop Counter2");
        stop2.setBounds(330, 80, 120, 30);
        f.add(stop2);
        f.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == strt1) {
            

        }

    }

    private class CounterThread extends Thread{
        private volatile boolean counting;
        private JTextField counterField;
        public CounterThread(JTextField counterField){
            this.counting=true;
            this.counterField=counterField;
        }
        public void  run(){
            int counter=0;
            while(counting){
                counterField.setText(Integer.toString(counter));
                counter++;
            }
            try{
                Thread.sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        } 
        public void stopcounting(){
            counting=false;
        }
    }
    public static void main(String[] args) {
        new Countergui();
    }
}