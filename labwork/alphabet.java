import java.io.*;
public class alphabet {
    public static void main(String[] args) {
        try {
            // Create a file named "alphabets.txt" and insert data into it
            createAlphabetsFile("alphabets.txt");
            
            // Read the file and copy consonants to "consonants.txt" and vowels to "vowels.txt"
            copyConsonantsAndVowels("alphabets.txt", "consonants.txt", "vowels.txt");
            
            System.out.println("File operations completed successfully.");
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public static void createAlphabetsFile(String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        // Write some sample data into the file
        String data = "Hello World! This is a sample text file containing alphabets and numbers.";
        bufferedWriter.write(data);

        bufferedWriter.close();
    }

    public static void copyConsonantsAndVowels(String sourceFileName, String consonantsFileName, String vowelsFileName) throws IOException {
        FileReader fileReader = new FileReader(sourceFileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        
        FileWriter consonantsWriter = new FileWriter(consonantsFileName);
        FileWriter vowelsWriter = new FileWriter(vowelsFileName);
        
        int ch;
        while ((bufferedReader.read()!=-1)){
        while ((ch = bufferedReader.read()) != -1) {
            char character = (char) ch;

            if (Character.isLetter(character)) {
                char lowercaseChar = Character.toLowerCase(character);

                if (isVowel(lowercaseChar)) {
                    vowelsWriter.write(character);
                } else if (isConsonant(lowercaseChar)) {
                    consonantsWriter.write(character);
                }
            } else if (Character.isDigit(character)) {
                throw new IOException("Encountered a number: " + character);
            }
        }
        }

        bufferedReader.close();
        consonantsWriter.close();
        vowelsWriter.close();
    }

    public static boolean isVowel(char c) {
        return "aeiou".indexOf(c) != -1;
    }

    public static boolean isConsonant(char c) {
        return "bcdfghjklmnpqrstvwxyz".indexOf(c) != -1;
    }
}
