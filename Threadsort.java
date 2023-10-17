import java.util.Random;

class threadbubble extends Thread{
    double  execute;
    private linkedlist list=new linkedlist();
    private int size; 
    public threadbubble(linkedlist list,int size){
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
        list.sortlist();
        long end=System.nanoTime();
         execute=(end-start)/Math.pow(10, 9);
    }
    public  double  getTime(){
        return execute;
    }

}

public class Threadsort {
    public static void main(String[] args) {
        linkedlist list1=new linkedlist();
        linkedlist list2=new linkedlist();
        threadbubble t1=new threadbubble(list1,100);
        threadbubble t2=new threadbubble(list2,100000);
        try{
        t1.join();
        t2.join(); 
        }catch(InterruptedException e){
        e.printStackTrace();
    }
        linkedlist list=new linkedlist();
        long start=System.nanoTime();
        merge(list,list1,list2);
        long end=System.nanoTime();
        double total=(end-start)/Math.pow(10, 9);
        System.out.println("Execution time: "+(total+t1.getTime()+t2.getTime())+" seconds");
    }
     public static void merge(linkedlist list,linkedlist list1,linkedlist list2){
        node cur1=list1.first;
        node cur2=list2.first;
        while(cur1!=null && cur2!=null){
            if(cur1.data>cur2.data){
                list.insert(cur1.data);
                cur1=cur1.next;
            }
            else{
                list.insert(cur2.data);
                cur2=cur2.next;
            }
        }while(cur1!=null){
            list.insert(cur1.data);
            cur1=cur1.next;
        }
        while(cur2!=null){
            list.insert(cur2.data);
            cur2=cur2.next;
        }
    }
}
