import java.util.Scanner;
class box{
    private double width,height,depth;
     void Box(double w,double h,double d){
        width=w;
        height=h;
        depth=d;
    }
    double volume(){
        return width*depth*height;
}
}
public class Boxdemo {
    public static void main(String[] args){
        box myBox=new box();
        Scanner scan=new Scanner(System.in);
        System.out.println("Enter the dimensions");
        double w=scan.nextDouble();
        double d=scan.nextDouble();
        double h=scan.nextDouble();
        myBox.Box(w,h,d);
        System.out.println("The volume is ="+myBox.volume());
        scan.close();
    }
    
}
