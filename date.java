import java.time.DayOfWeek;
import java.time.LocalDate;
import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.awt.event.*;
import java.awt.*;  
public class date implements ActionListener {
       private static JFrame f,f2;
       private static JTextField t;
       private static JTextField t2;
       private static JLabel text1;
       private static JLabel text2;
       private static JButton submit;
       private static JButton clear; 
       private static JButton but;
       private static JLabel m=new JLabel();
    public static void main(String[] args){
        
        f=new JFrame("Date Calculator");
        f.setSize(400, 400);
        f.setLayout(null);
        t=new JTextField();
        t2=new JTextField();
        t.setBounds(250,20,120,30);
        t2.setBounds(250,80,120,30);
        f.add(t);
        f.add(t2);
        text1=new JLabel("Enter the first date");
        text2=new JLabel("Enter second Date");
        text1.setBounds(100,10,120,50);
        text2.setBounds(100,70,120,50);
        f.add(text1);
        f.add(text2);
        submit = new JButton("Get working days");
        clear=new JButton("Clear");
        submit.setBounds(65,150,150,30);
        submit.addActionListener(new date());
        clear.setBounds(230,150,95,30);
        clear.addActionListener(new date());
        f.add(submit);
        f.add(clear);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }  
          public void actionPerformed(ActionEvent e){
            if(e.getSource()==submit){
                String x=t.getText();
                String y=t2.getText();
                DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy");
                try{
                    LocalDate date1=LocalDate.parse(x,formatter);
                    LocalDate date2=LocalDate.parse(y,formatter);
                    int mon=Weekdays(date1,date2,DayOfWeek.MONDAY);
                    int tue=Weekdays(date1,date2,DayOfWeek.TUESDAY);
                    int wed=Weekdays(date1,date2,DayOfWeek.WEDNESDAY);
                    int thu=Weekdays(date1,date2,DayOfWeek.THURSDAY);
                    int fri=Weekdays(date1,date2,DayOfWeek.FRIDAY);
                    int sat=Weekdays(date1,date2,DayOfWeek.SATURDAY);
                    f2=new JFrame("Total Weekdays");
                    f2.setSize(600,300);
                    JLabel total=new JLabel("Total number of Week days between "+date1+" to "+date2+" are:"+(mon+tue+wed+thu+fri+sat));
                    total.setFont(new Font("Cooper Black",Font.PLAIN,16));
                    total.setBounds(0,10,600,50);
                    JLabel m= new JLabel("Mondays="+mon);
                    JLabel tu=new JLabel("Tuesdays="+tue);
                    JLabel w=new JLabel("Wednesday="+wed);
                    JLabel th=new JLabel("Thursdays="+thu);
                    JLabel fr=new JLabel("Fridays="+fri);
                    JLabel sa=new JLabel("Saturdays="+sat);
                    JLabel p=new JLabel();
                    but=new JButton("OK");
                    m.setBounds(100,50,300,50);
                    tu.setBounds(100,70,300,50);
                    w.setBounds(100,90,300,50);
                    th.setBounds(100,110,300,50);
                    fr.setBounds(100,130,300,50);
                    sa.setBounds(100,150,300,50);
                    but.setBounds(210,180,60,30);
                    but.addActionListener(new date());
                    p.setBounds(100,110,300,50);
                    f2.add(total);
                    f2.add(m);
                    f2.add(tu);
                    f2.add(w);
                    f2.add(th);
                    f2.add(fr);
                    f2.add(sa);
                    f2.add(but);
                    f2.add(p);
                    f2.setVisible(true);
                    f.setVisible(false);
                    f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }catch(DateTimeParseException g){
                     m= new JLabel("Enter in the format \"dd-mm-yyyy\"");
                    m.setFont(new Font("Times New Roman",Font.BOLD,18));
                    m.setForeground(Color.RED);
                    m.setBounds(90, 120, 300,30);
                    f.add(m);
                    f.repaint();
                }  
            }
            if(e.getSource()==clear){
                m.setText("");
                t.setText(null);
                t2.setText(null);
            }
            if(e.getSource()==but){
                f2.setVisible(false);
                f.setVisible(true);
            }
        }        
        public static int Weekdays(LocalDate date1,LocalDate date2,DayOfWeek week){
            int count =0;
            LocalDate d=date1;
            while (d.isBefore(date2)) {
                if(d.getDayOfWeek()==week){
                    count++;
                }
                d=d.plusDays(1);
        }
        return count;
    }
}