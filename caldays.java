import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.swing.JOptionPane;

	public class caldays {
	       public static void main(String[] args) {
	    	   
	    	   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    	   
	    	   //Get the First and Second  date from the user
	    	   String date1String = JOptionPane.showInputDialog("Enter The First Date (DD-MM-YYYY) :  ");
	    	   String date2String = JOptionPane.showInputDialog("Enter The Second Date (DD-MM-YYYY) :  ");
	    	   
	    	   //Parse and input strings into LocalDate objects
	    	   LocalDate date1 = LocalDate.parse(date1String, formatter) ;
	    	   LocalDate date2 = LocalDate.parse(date2String, formatter);
	    	   
	    	   //Compute the difference in days between the two dates
	    	   long daysBetween = ChronoUnit.DAYS.between(date1, date2);
	    	   
	    	    //Calculate the number of weekdays
	    	    int mondays = countWeekdays(date1, date2, DayOfWeek.MONDAY);
	            int tuesdays = countWeekdays(date1, date2, DayOfWeek.TUESDAY);
	            int wednesdays = countWeekdays(date1, date2, DayOfWeek.WEDNESDAY);
	            int thursdays = countWeekdays(date1, date2, DayOfWeek.THURSDAY);
	            int fridays = countWeekdays(date1, date2, DayOfWeek.FRIDAY);
	            int saturdays = countWeekdays(date1, date2, DayOfWeek.SATURDAY);
	            int sundays = countWeekdays(date1, date2, DayOfWeek.SUNDAY);
	            

                //Display the output using JOptionPane
	            String output = "The number of days between " + date1String + " and " + date2String + " is :" + daysBetween + "\n"
	            		        +  "Mondays: " + mondays + "\n"
	                            + "Tuesdays: " + tuesdays + "\n"
	                            + "Wednesdays: " + wednesdays + "\n"
	                            + "Thursdays: " + thursdays + "\n"
	                            + "Fridays: " + fridays + "\n"
	                            + "Saturdays: " + saturdays + "\n"
	                            + "Sundays: " + sundays;
	            
	            JOptionPane.showMessageDialog(null, output,"Weekday Count", JOptionPane.INFORMATION_MESSAGE);

	    	   
	    			
	       }

	public static int countWeekdays(LocalDate date1, LocalDate date2, DayOfWeek dayOfWeek) {
	        int count = 0;
	        LocalDate date = date1;

	        while (!date.isAfter(date2)) {
	            if (date.getDayOfWeek() == dayOfWeek) {
	                count++;
	            }
	            date = date.plus(1, ChronoUnit.DAYS);
	        }

	        return count;

	    }       
	}



