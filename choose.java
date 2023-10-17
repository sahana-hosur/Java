import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class choose implements ActionListener {
    private JFrame frame;
    private JButton topperlist,individual;
    public choose(String s){
        frame=new JFrame("Choice");
        frame.setSize(400, 100);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        topperlist=new JButton("Topperlist of " +s);
        topperlist.setBounds(20,10,180,40);
        topperlist.setFont(new Font("Cooper Black",Font.ITALIC,12));
        topperlist.addActionListener(this);
        frame.add(topperlist);

        individual=new JButton("Individual Scores");
        individual.setBounds(220,10,160,40);
        individual.setFont(new Font("Cooper Black",Font.ITALIC,12));
        individual.addActionListener(this);
        frame.add(individual);

        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==topperlist){
        frame.setVisible(false);
        new topper();
        }
        if(e.getSource()==individual){
            
            new eachstudent();
            


        }
    }
}
