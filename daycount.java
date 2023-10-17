import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.awt.event.*;
import java.time.LocalDate;
import java.awt.*;
import java.time.*;
public class daycount implements ActionListener {
	static JFrame frame1;
	static JTextField startdate;
	static JTextField enddate;
	static JLabel s1;
	static JLabel e1;
	static JButton submit;
	static JTextField start;
	static JTextField end;		
	public static void main(String[] args){
		frame1 =new JFrame("weekdayscount");
		frame1.setSize(400, 400);
		frame1.setLayout(new FlowLayout());
		JLabel s1 = new JLabel("start date in DD-MM-YYYY");
		 start = new JTextField(15);
		JLabel e1 = new JLabel("end date in DD-MM-YYYY");
		 end = new JTextField(15);
		submit = new JButton("submit");
		submit.addActionListener(new date());
		frame1.add(start);
		frame1.add(end);
		frame1.add(s1);
		frame1.add(e1);
		frame1.add(submit);
		frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
    public void actionPerformed(ActionEvent e) {
            if(e.getSource()==submit){
			String startdate = start.getText();
			String enddate = end.getText();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate date1 = LocalDate.parse(startdate,format);
			LocalDate date2 = LocalDate.parse(enddate,format);
            frame1.getContentPane().removeAll();
			int mo=week(date1,date2,DayOfWeek.MONDAY);
			int tu=week(date1,date2,DayOfWeek.TUESDAY);
			int we=week(date1,date2,DayOfWeek.WEDNESDAY);
			int th=week(date1,date2,DayOfWeek.THURSDAY);
			int fr=week(date1,date2,DayOfWeek.FRIDAY);
			int sa=week(date1,date2,DayOfWeek.SATURDAY);
			JLabel mon=new JLabel("Mondays:  "+mo);
			JLabel tue=new JLabel("Tuesday:  "+tu);
			JLabel wed=new JLabel("Wednesday:"+we);
			JLabel thu=new JLabel("Thursday :"+th);
			JLabel fri=new JLabel("Friday:   "+fr);
			JLabel sat=new JLabel("Saturday:"+sa);
			mon.setBounds(10,80,120,30);
			tue.setBounds(10,120,120,30);
			wed.setBounds(10,160,120,30);
			thu.setBounds(10,200,120,30);
			fri.setBounds(10,240,120,30);
			sat.setBounds(10,280,120,30);
			frame1.add(mon);
			frame1.add(tue);
			frame1.add(wed);
			frame1.add(thu);
			frame1.add(fri);
			frame1.add(sat);
			frame1.revalidate();
			frame1.repaint();
			}
        }
        public   int week(LocalDate date1,LocalDate date2, DayOfWeek w){
				LocalDate d=date1;
				int count=0;
				while(d.isBefore(date2)){
					if(d.getDayOfWeek()==w){
						count++;
					}
					d.plusDays(1);
				}
                return count;
			}
}
