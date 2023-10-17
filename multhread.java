public class multhread extends Thread{
    private long t;
    int[][] A;
    int[][] B;
    int[][] C;
    int row;

    public multhread(int[][] A, int[][] B,int[][] C,int row){
        this.A=A;
        this.B=B;
        this.C=C;
        this.row=row;
    }

    public void run(){
        multiplyRow();

    }
    private void  multiplyRow(){
        long start=System.nanoTime();
        int col=A[0].length;
        for(int i=0;i<col;i++){
            int sum=0;
            for(int k=0;k<col;k++){
            sum+=A[row][k]*B[k][i];
            }
        C[row][i]=sum;
        }
        long end=System.nanoTime();
        t=end-start;
    } 
    public long getTime(){
        return t;
    }
}
