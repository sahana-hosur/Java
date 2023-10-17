 
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
class Clerk{
	Scanner scan=new Scanner(System.in);
	 void dataentry(student s) {
		System.out.print("Enter the name :");
		s.name=scan.next();
		System.out.print("Enter the usn");
		s.usn=scan.next();
	}
	
}
class student{
	String name,usn;
	int IA1,IA2,IA3,CTA,CIE;
	student(){
		this.CIE=0;
		this.CTA=0;
		this.IA1=0;
		this.IA2=0;
		this.IA3=0;
	}
}
class teacher{
	String name="GMS";
	String Designation="Asst Professor";
	Scanner scan=new Scanner(System.in);
	void EntryMarks(student s) {
		System.out.println("Name :"+s.name);
		System.out.println("USN  :"+s.usn);
		System.out.print("Enter the IA1 score :");
		s.IA1=scan.nextInt();
		System.out.print("Enter the IA2 score :");
		s.IA2=scan.nextInt();
		System.out.print("Enter the IA3 score :");
		s.IA3=scan.nextInt();
		System.out.print("Enter the CTA :");
		s.CTA=scan.nextInt();
		cie(s);
		}
	void cie(student s) {
		int s1=(s.IA1+s.IA2);
		int s2=(s.IA2+s.IA3);
		int s3=(s.IA3+s.IA1);
		int x;
		if(s1>=s2 && s1>=s3)x=s1;
		else if(s2>=s1 && s2>=s3)x=s2;
		else x=s3;
		s.CIE=s.CTA+x;
		System.out.println("The cie of the student is "+s.CIE);
	}
	void display(student s) {
		System.out.println("Name :"+s.name);
		System.out.println("USN  :"+s.usn);
		System.out.println("IA1 :"+s.IA1);
		System.out.println("IA2 :"+s.IA2);
		System.out.println("IA3 :"+s.IA3);
		System.out.println("CTA :"+s.CTA);
		System.out.println("CIE :"+s.CIE);
	}
 }
public class stutechclek {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
	    System.out.println("Enter the total no of students");
	    int size=scan.nextInt();
	    student[] s=new student[size];
	    Clerk c=new Clerk();
	    for(int i=0;i<size;i++) {
	    	s[i]=new student();
	    	System.out.println("Enter the details of "+(i+1)+" student " );
	    	c.dataentry(s[i]);	
	    }
	    teacher t=new teacher();
	    for(int i=0;i<size;i++) {
	    	t.EntryMarks(s[i]);
	    }
	    System.out.print("Enter the Usn of the student whoes details is required");
	    String str=scan.next();
	    for(int i=0;i<size;i++) {
	    	String str1=s[i].usn;
	    	if(str1.equalsIgnoreCase(str)) {
	    		t.display(s[i]);
	    	}
	  
	    }
        try{
        File obj=new File("Student-data.txt");
        if(obj.createNewFile()){
            System.out.println("file created successfully "+obj.getName());
                    }else{
                        System.out.println("File already exists");
        }
        }catch(IOException e){
            System.out.println("Error in creating file");
        }
		scan.close();
	}

}
