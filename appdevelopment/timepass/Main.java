package appdevelopment.timepass;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
public class Main extends Thread{
    Main(String name){
        super(name);
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        File newFile=new File("integers.txt");
        
        newFile.createNewFile();
        FileWriter writer=new FileWriter(newFile);
        Random random=new Random();
        for(int i=0;i<15_000;i++){
            int temp=random.nextInt(10_00_000);
            writer.append(String.valueOf(temp)+"  ");
        }
        writer.close();
        Main t1=new Main("0");
        Main t2=new Main("1");
        Main t3=new Main("2");
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        File inter=new File("output.txt");
        joining(new File("t1.txt"),new File("t2.txt"),inter);
        joining(inter, new File("t3.txt"), new File("FinalOut.txt"));
    }
    public void run(){
        String name=this.getName();
        int num=Integer.parseInt(name);
        int arr[]=new int[5000];
        FileWriter writer=null;
        if(num==0){
            try {
                writer=new FileWriter("T1.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(num==1){
            try {
                writer=new FileWriter("T2.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(num==2){
            try {
                writer=new FileWriter("T3.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Scanner reader=null;
        try {
            reader=new Scanner(new File("integers.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(int i=0;i<num*5000;i++){
            reader.nextInt();
        }
        for(int i=0;i<5000;i++){
            arr[i]=reader.nextInt();
        }
        for(int i=0;i<5000;i++){
            for(int j=i+1;j<5000;j++){
                if(arr[i]>arr[j]){
                    int temp=arr[i];
                    arr[i]=arr[j];
                    arr[j]=temp;
                }
            }
        }
        for(int i=0;i<5000;i++){
            try {
                writer.append(Integer.toString(arr[i])+"  ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void joining(File t1,File t2,File fina) throws IOException{
        Scanner sc=new Scanner(t1);
        Scanner sc2=new Scanner(t2);
        FileWriter writer=new FileWriter(fina);
        int arr[]=new int[10000];
        int i,j;
        for(i=0;sc.hasNextInt();i++){
            arr[i]=sc.nextInt();
        }
        int arr2[]=new int[10000];
        for(j=0;sc2.hasNextInt();j++){
            arr2[j]=sc2.nextInt();
        }
        int k=0;
        int l=0;
        while(k<i && l<j){
            if(arr[k]<arr2[l]){
                writer.append(String.valueOf(arr[k])+" ");
                k++;
            }else{
                writer.append(String.valueOf(arr2[l]+" "));
                l++;
            }
        } 
        while(k<i){
            writer.append(String.valueOf(arr[k]+" "));
            k++;
        }     
        while(l<j){
            writer.append(String.valueOf(arr2[l]+" "));
            l++;
        }
    }
}
