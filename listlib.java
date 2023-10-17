import java.util.LinkedList;
import java.util.Random;
public class listlib {
    public static void main(String[] args){
        LinkedList<Integer> list =new LinkedList<>();
        Random ran=new Random();
        for(int i=0;i<10000000;i++){
            int ele=ran.nextInt(1000000);
            list.add(ele);
        }
        long start=System.nanoTime();
        list.sort(null);
        long end=System.nanoTime();
        System.out.println("Time taken to sort "+ (end-start)/Math.pow(10,9) +"in  seconds");
    }    
}
