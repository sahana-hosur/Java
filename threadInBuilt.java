import java.util.LinkedList;
import java.util.Random;
public class threadInBuilt extends Thread{
    LinkedList<Integer> list=new LinkedList<>();
    double  execute;
    private int size;
    public threadInBuilt(LinkedList<Integer> list,int size){
        this.list=list;
        this.size=size;
        start();
    }
    public void run(){
        Random rand = new Random();
        for(int i=0;i<size;i++){
            int ele=rand.nextInt(0,size*10);
            list.add(ele);
        }
        long start=System.nanoTime();
        list.sort(null);
        long end=System.nanoTime();
         execute=(end-start)/Math.pow(10, 9);
    }
    public  double  getTime(){
        return execute;
    }
}
