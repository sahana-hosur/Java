package appdevelopment.timepass;
import java.util.Scanner;
public class JustCase {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String num="\0";
            System.out.print("Enter a numer ");
            num=sc.next();
        try{
            int number=Integer.parseInt(num);
            int result=10/number;
            System.out.println("Result :"+result);
            int[] array=new int[5];
            array[number]=10;
        }catch(NumberFormatException e){
            e.printStackTrace();
        }catch(ArithmeticException e){
            e.printStackTrace();
        }catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        sc.close();
    }
    
}
