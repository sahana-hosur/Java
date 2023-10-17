import java.util.Scanner;
class Course {
    char grade;
    int credits;
    public static void SGPA(Course courses[]) {
        double grade_point = 0;
        double total_credits = 0;
        double failed_credits = 0;
        for (int i = 0; i < courses.length; i++) {
            if (courses[i].grade == 'S' || courses[i].grade == 's') {
                grade_point += (10 * courses[i].credits);
            } else if (courses[i].grade == 'A' || courses[i].grade == 'a') {
                grade_point += (9 * courses[i].credits);
            } else if (courses[i].grade == 'B' || courses[i].grade == 'b') {
                grade_point += (8 * courses[i].credits);
            } else if (courses[i].grade == 'C' || courses[i].grade == 'c') {
                grade_point += (7 * courses[i].credits);
            } else if (courses[i].grade == 'D' || courses[i].grade == 'd') {
                grade_point += (6 * courses[i].credits);
            } else if (courses[i].grade == 'E' || courses[i].grade == 'e') {
                grade_point += (4 * courses[i].credits);
            } else if (courses[i].grade == 'F' || courses[i].grade == 'f') {
                grade_point += (0 * courses[i].credits);
                failed_credits+=courses[i].credits;
                continue;
            }
            total_credits = total_credits + courses[i].credits;
        }
        double sgpa = grade_point / (total_credits + failed_credits);
        System.out.println("Total credits : "+(total_credits+failed_credits));
        System.out.println("Total credits earned : " + total_credits);
        System.out.println("The SGPA of the given student is " + sgpa);
    }
}
public class SGPA_calc {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number of subjects registered (5-8) : " ); 
        int num=sc.nextInt();
        Course courses[]=new Course[num];
        for(int i=0;i<num;i++){
            courses[i]=new Course();
            System.out.print("Enter the credits of subject "+(i+1)+" : ");
            courses[i].credits=sc.nextInt();
            System.out.print("Enter the Grade of subject "+(i+1)+" : ");
            courses[i].grade=sc.next().charAt(0);
        }
        Course.SGPA(courses);
        sc.close();
    }        
}