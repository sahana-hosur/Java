import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class guilist implements ActionListener {
    private static JFrame f;
    private static long total;
    private static JButton j;
    private static JLabel err,label;
    private static JComboBox<Integer> l;
    private static JLabel ans;
    private static ImageIcon image;
    public static void main(String[] args){
        
        f=new JFrame("Sorting Time Calculator");
        image=new ImageIcon("C:/Users/91990/Downloads/timer.png");
        f.setIconImage(image.getImage());
        f.setSize(400,250);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label=new JLabel("Choose the Number of Inputs");
        label.setFont(new Font("Cooper Black",Font.PLAIN, 12));
        label.setBounds(10,50,180,30);
        f.add(label);

        l=new JComboBox<>();
        l.addItem(0);
        l.addItem(10);
        l.addItem(100);
        l.addItem(1000);
        l.addItem(10000);
        l.addItem(100000);
        l.setSelectedIndex(0);
        l.setBounds(200,50,120,30);
        f.add(l);

        j=new JButton("Get Time");
        j.setBounds(150,100,90,25);
        j.setFont(new Font("Calibri",Font.BOLD,14));
        j.addActionListener(new guilist());
        f.add(j);

        err = new JLabel("");   
        f.add(err);

        ans=new JLabel("");
        f.add(ans);

        f.setVisible(true);        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==j) {
        int  s=(int)(l.getSelectedItem());
            if(s==0){
                ans.setText(null);
                err=new JLabel("Choose anything other than zero");
                err.setBounds(100,150,300,60);
                label.setForeground(Color.RED);
                f.add(err);
                f.repaint();
                return ;
            }else{
            err.setText(null);
            ans.setText(null);
            label.setForeground(Color.BLACK);
            f.repaint();
            Random r=new Random();
            linkedlist list=new linkedlist();
            int ele;
            for(int i=0;i<s;i++){
                ele=r.nextInt(1,100);
                list.insert(ele);
            }
            long starttime=System.nanoTime();
            list.sortlist();
            long endtime=System.nanoTime();
            total=endtime-starttime;
            double t=total/Math.pow(10,6);
            ans.setText("Time take to sort "+l.getSelectedItem()+" is "+t+"in milliseconds");
            ans.setBounds(40,140,350,60);
            f.add(ans);
            f.repaint();
            }
        }
    }
  
}

