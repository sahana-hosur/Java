import java.util.Scanner;
public class div {
    public static void main(String[] args){
        Scanner scan=new Scanner(System.in);
        System.out.println("Enter a=");
        int a=scan.nextInt();
        System.out.println("Enter b=");
        int b=scan.nextInt();
        try{

            int c=a/b;
            System.out.println("a/b=" +c);
        }
        catch(ArithmeticException e){
            System.out.println("cannot div by zero");
        }
        scan.close();
    }
}
