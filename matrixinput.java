import java.util.Random;
import java.util.Scanner;

public class matrixinput {
    public static void main(String[] args){
        Scanner scan=new Scanner(System.in);
        Random rand=new Random();

        System.out.println("Enter the number of rows");
        int row =scan.nextInt();
        int col=row;
        int[][] A=new int[row][col];
        int[][] B=new int[row][col];
        int[][] C=new int[row][col];

        for(int i=0;i<row;i++){
            for (int j=0;j<col ;j++){
                A[i][j]=rand.nextInt(20);
                B[i][j]=rand.nextInt(20);
            }
        }
        long time[]=new long[row];
        multhread[] threads=new multhread[row];
        for(int i=0;i<row;i++){
            final int rows=i;
            threads[i]=new multhread(A, B, C, rows);
            threads[i].start();
        }
        int l=0;
        for(multhread thread:threads){
            try{
                thread.join();
                time[l]=thread.getTime();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        // System.out.println("Multiplicationn of matrix A and B is ");
        // for(int i=0;i<row;i++){
        //     for(int j=0;j<row;j++){
        //         System.out.print(C[i][j]+" ");
        //     }
        //     System.out.println("");
        // }
        int sum=0;
        for(int i=0;i<time.length-1;i++)
             sum+=time[i];
        System.out.println("Time Taken to multiply 2 matrix is "+sum +"nano seconds");
        scan.close();

    }

    
}
