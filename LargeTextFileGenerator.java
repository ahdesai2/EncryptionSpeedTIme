/* import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LargeTextFileGenerator {
    public static void main(String[] args) {
        String filePath = "largefile.txt"; // Name of the output file
        int sizeInMB = 100; // Desired file size

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write enough content to create a file of at least 100MB
            for (int i = 0; i < sizeInMB * 1024; i++) {
                writer.write("This is a line of text that will be repeated.\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("File generated: " + filePath);
    }
} 
*/
