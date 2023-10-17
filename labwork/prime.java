import java.io.*;
import java.util.*;

public class prime {
    public static void main(String[] args) {
        generateNumbersFile("numbers.txt", 100000);
        processNumbersFile("numbers.txt");
    }
    public static void generateNumbersFile(String fileName, int count) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            Random random = new Random();
            for (int i = 0; i < count; i++) {
                int num = random.nextInt(999999) + 2; // Generate random numbers between 2 and 1000000
                writer.println(num);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
    public static void processNumbersFile(String fileName) {
        int primeCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));
             PrintWriter writer = new PrintWriter(new FileWriter("output.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int num = Integer.parseInt(line);
                boolean isPrime = isPrime(num);

                writer.println(num + " " + isPrime);

                if (isPrime) {
                    primeCount++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Number of prime numbers: " + primeCount);
    }
}