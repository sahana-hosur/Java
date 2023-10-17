import java.util.Random;
class node {
    int data;
    node next;
    public node(int item){
        this.data=item;
        this.next=null;
    }
}
class linkedlist{
    node first;
    public linkedlist(){
        this.first=null;
    }
    public void insert(int data){
        node temp=new node(data);
        if(first==null){
            first=temp;
        }
        else{
            node cur= first;
            while(cur.next!=null){
                cur=cur.next;
            }
            cur.next=temp;
        }
    }
    public void delete(){
        if(first==null){
            System.out.println("List is empty");
            return;
        }
        else if(first.next==null){
            System.out.println("Element deleted="+first.data);
            return;
        }
        node cur=first;
        first=first.next;
        System.out.println("Element deleted="+cur.data);
    }
    public void display(){
        if(first==null){
            System.out.println("List is empty");
            return;
        }
        else {
            node cur=first;
            while(cur.next!=null){
                System.out.print(cur.data+"->");
                cur=cur.next;
            }
            System.out.println(cur.data);
        }
    }
    public void swap(node data1,node data2){
            int temp=data1.data;
            data1.data=data2.data;
            data2.data=temp;
    }
    public void sortlist(){
        if(first==null){
            System.out.println("List is empty");
            return;
        }
        else {
            boolean s=true;
            node cur,prev=null;
            do{
                s=false;
                cur=first;
                while(cur.next!=prev){
                    if(cur.data>cur.next.data){
                        swap(cur,cur.next);
                        s=true;
                    }
                    cur=cur.next;
                }
                prev=cur;
            }while(s);
        }
    }
    private static node getmiddle(node first){
        if(first == null || first.next ==null){
            return first;
        }
        node slownode=first,fast=first.next;
        while(fast!=null && fast.next!=null){
            slownode =slownode.next ;
            fast=fast.next.next;
        }
        return slownode;
    }
    public  linkedlist mergesort(linkedlist list){
        if(list.first == null || list.first.next==null){
            return list;
        }
        node mid=getmiddle(list.first);
        node midnext=mid.next;
        mid.next=null;
        linkedlist l=new linkedlist();
        l.first=list.first;
        linkedlist r=new linkedlist();
        r.first=midnext;
        linkedlist left=mergesort(l);
        linkedlist right=mergesort(r);
        return merge(left,right);
    }
    private static linkedlist merge(linkedlist list1,linkedlist list2){
        linkedlist list=new linkedlist();
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
        return list;
    }
   
}
public class list {
    public static void main(String[] args){
        Random r=new Random();
        linkedlist list=new linkedlist();
        int ele;
        for(int i=0;i<400000;i++){
            ele=r.nextInt(1,10000);
            list.insert(ele);
        }
        long starttime=System.nanoTime();
        list=list.mergesort(list);
        long endtime=System.nanoTime();
        long total=endtime-starttime;
        // list.display();
        System.out.println("Execution time: "+(total/Math.pow(10, 9))+" seconds");
    }

}