import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
public class program{
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your birth year: ");
            int birthYear = scanner.nextInt();
            System.out.print("Enter your birth month: ");
            int birthMonth = scanner.nextInt();
            System.out.print("Enter your birth day: ");
            int birthDay = scanner.nextInt();
            scanner.close();
            LocalDate currentDate = LocalDate.now();
            LocalDate birthDate = LocalDate.of(birthYear, birthMonth, birthDay);
            if (birthDate.isAfter(currentDate)) {
                System.out.println("Invalid birth date. Please enter a valid date.");
            } else {
                long age = calculateAge(birthDate, currentDate);
                System.out.println("Your age is: " + age);
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter valid integers for year, month, and day.");
        }
    }
     static long calculateAge(LocalDate birthDate, LocalDate currentDate) {
        return ChronoUnit.YEARS.between(birthDate, currentDate);
    }
}
