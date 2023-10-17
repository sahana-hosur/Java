import java.util.Scanner;

public class grade_calc{
    static char calculateGrade(int total){
        char grade='T';
        if(total>=90){
            grade='S';
        }else if(total>=80){
            grade='A';
        }else if(total>=70){
            grade='B';
        }else if(total>=60){
            grade='C';
        }else if(total>=50){
            grade='D';
        }else if(total>=40){
            grade='E';
        }else if(total<40){
            grade='F';
        }
        return grade;
    }
    public static void main(String[] args) {
        int IA1,IA2,IA3,CTA,CIE;
        int SEE;
        int total;
        char grade;
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter the IA1 Marks : ");
        IA1=scanner.nextInt();
        System.out.print("Enter the IA2 Marks : ");
        IA2=scanner.nextInt();
        System.out.print("Enter the IA3 Marks : ");
        IA3=scanner.nextInt();
        System.out.print("Enter the CTA Marks : ");
        CTA=scanner.nextInt();

        try{
            if(IA1<=0 || IA1>=20 || IA2<=0 || IA2>=20 || IA3<=0 || IA3>=20){
                throw new IllegalArgumentException();
            }
        }catch(IllegalArgumentException e){
            System.out.println("IA Marks not in the range of 0-20");
            return;
        }

        // For Minimum
        int temp=Math.min(IA1, IA2);
        int min=Math.min(temp,IA3);

        CIE=IA1+IA2+IA3-min+CTA;

        if(CIE<20){
            System.out.println("Student not eligible for SEE ");
            System.out.println("Grade : F");
            return;
        }

        System.out.print("Enter the SEE Marks : ");
        SEE=scanner.nextInt();
        if(SEE<40){
            System.out.println("Grade : F");
            return;
        }

        total=CIE+(SEE/2);
        grade=calculateGrade(total);
        System.out.println("Grade : "+grade);
    }
}
