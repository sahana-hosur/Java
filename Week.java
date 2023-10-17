import java.time.DayOfWeek;
import java.time.LocalDate;
import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.awt.event.*;
public class Week implements ActionListener{
        static JFrame Frame;
        static JTextField Textfield1;
        static JTextField Textfield2;
        static JLabel text1;
        static JLabel text2;
        static JButton submit; 
    public static void main(String[] args){
        Frame=new JFrame("Week Days Calculator");
        Frame.setSize(400, 300);
        Frame.setLayout(null);
        Textfield1=new JTextField();
        Textfield2=new JTextField();
        Textfield1.setBounds(250,20,120,30);
        Textfield2.setBounds(250,80,120,30);
        Frame.add(Textfield1);
        Frame.add(Textfield2);
        text1=new JLabel("         start date");
        text2=new JLabel("          last date");
        text1.setBounds(100,10,120,50);
        text2.setBounds(100,70,120,50);
        Frame.add(text1);
        Frame.add(text2);
        submit = new JButton("Submit");
        submit.setBounds(100,150,150,30);
        submit.addActionListener(new Week());
        Frame.add(submit);
        Frame.setVisible(true);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }  
          public void actionPerformed(ActionEvent e){
            if(e.getSource()==submit){
                String x=Textfield1.getText();
                String y=Textfield2.getText();
                try{
                DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate date1=LocalDate.parse(x,formatter);
                    LocalDate date2=LocalDate.parse(y,formatter);
                    int mon=Weekdays(date1,date2,DayOfWeek.MONDAY);
                    int tue=Weekdays(date1,date2,DayOfWeek.TUESDAY);
                    int wed=Weekdays(date1,date2,DayOfWeek.WEDNESDAY);
                    int thu=Weekdays(date1,date2,DayOfWeek.THURSDAY);
                    int fri=Weekdays(date1,date2,DayOfWeek.FRIDAY);
                    int sat=Weekdays(date1,date2,DayOfWeek.SATURDAY);
                    int t=(mon+tue+wed+thu+fri+sat);
                    Frame.getContentPane().removeAll();
                    JLabel days=new JLabel("Total No of Weekdays from "+date1+" to "+date2+" are:"+t);
                    days.setBounds(20,10,600,50);
                    JLabel monday= new JLabel("Mondays="+mon);
                    JLabel tuesday=new JLabel("Tuesdays="+tue);
                    JLabel wednesday=new JLabel("Wednesday="+wed);
                    JLabel thursday=new JLabel("Thursdays="+thu);
                    JLabel friday=new JLabel("Fridays="+fri);
                    JLabel saturday=new JLabel("Saturdays="+sat);
                    JLabel p=new JLabel();
                    monday.setBounds(0,50,300,50);
                    tuesday.setBounds(0,70,300,50);
                    wednesday.setBounds(0,90,300,50);
                    thursday.setBounds(0,110,300,50);
                    friday.setBounds(0,130,300,50);
                    saturday.setBounds(0,150,300,50);
                    p.setBounds(0,110,300,50);
                    Frame.add(days);
                    Frame.add(monday);
                    Frame.add(tuesday);
                    Frame.add(wednesday);
                    Frame.add(thursday);
                    Frame.add(friday);
                    Frame.add(saturday);
                    Frame.add(p);
                    Frame.repaint();
                    Frame.setVisible(true); 
                }catch(DateTimeParseException f){
                    JLabel err= new JLabel("           format \"dd-mm-yyyy\"");
                    err.setBounds(90, 170, 300,30);
                    Frame.add(err);
                    Frame.repaint();
                }
                
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
