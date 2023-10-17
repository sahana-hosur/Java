import java.io.*;
import java.util.*;
public class int_thread {
    public static void main(String[] args) {
        try {
            int n = 100000; // Number of random integers
            int chunkSize = n / 3; // Number of integers each thread reads and sorts
            
            // Create a file named "integers.txt" and insert random integers
            createIntegersFile("integers.txt", n);
            
            List<Integer> mergedList = new ArrayList<>();
            
            // Create three threads to read and sort integers
            Thread t1 = new Thread(new FileSortingTask("integers.txt", 0, chunkSize, mergedList));
            Thread t2 = new Thread(new FileSortingTask("integers.txt", chunkSize, chunkSize * 2, mergedList));
            Thread t3 = new Thread(new FileSortingTask("integers.txt", chunkSize * 2, n, mergedList));
            
            t1.start();
            t2.start();
            t3.start();
            
            // Wait for all threads to complete
            t1.join();
            t2.join();
            t3.join();
            
            // Sort the merged list
            Collections.sort(mergedList);
            
            // Write the sorted output to "sorted_integers.txt"
            writeSortedIntegers("sorted_integers.txt", mergedList);
            
            System.out.println("Sorting completed and saved to sorted_integers.txt");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void createIntegersFile(String fileName, int n) throws IOException {
        Random random = new Random();
        FileWriter fileWriter = new FileWriter(fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (int i = 0; i < n; i++) {
            int num = random.nextInt(1000000);
            bufferedWriter.write(num + "\n");
        }

        bufferedWriter.close();
    }

    public static void writeSortedIntegers(String fileName, List<Integer> list) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (int num : list) {
            bufferedWriter.write(num + "\n");
        }

        bufferedWriter.close();
    }
}

class FileSortingTask implements Runnable {
    private String fileName;
    private int start;
    private int end;
    private List<Integer> mergedList;

    public FileSortingTask(String fileName, int start, int end, List<Integer> mergedList) {
        this.fileName = fileName;
        this.start = start;
        this.end = end;
        this.mergedList = mergedList;
    }

    @Override
    public void run() {
        List<Integer> threadList = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            int count = 0;

            while ((line = bufferedReader.readLine()) != null) {
                if (count >= start && count < end) {
                    threadList.add(Integer.parseInt(line));
                }
                count++;
            }
            bufferedReader.close();
            Collections.sort(threadList);
            // synchronized (mergedList) {
                mergedList.addAll(threadList);
            //}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
