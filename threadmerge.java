import java.util.Random;
class threadmerge extends Thread{
    double  execute;
    private linkedlist list;
    private int size; 
    public threadmerge(linkedlist list,int size){
        this.size=size;
        this.list=list;
        start();
    }
    public void run(){
       Random ran=new Random();
       for(int i=0;i<size;i++){
            list.insert(ran.nextInt(0,size*10));
        }
        long start=System.nanoTime();
        list=list.mergesort(list);
        long end=System.nanoTime();
         execute=(end-start)/Math.pow(10, 9);
    }
    public  double  getTime(){
        return execute;
    }

}