import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class EncryptionSpeedTest {
    public EncryptionSpeedTest() {
    }

    public static byte[] readFile(String path) throws IOException {
        File file = new File(path);
        byte[] data = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(file);

        try {
            fis.read(data);
        } finally {
            fis.close();
        }
        return data;
    }

    public static double measureSpeed(Cipher cipher, byte[] data, int iterations) throws Exception {
        long totalTime = 0L;

        for (int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            cipher.doFinal(data);
            long endTime = System.nanoTime();
            totalTime += endTime - startTime;
        }

        return (double) totalTime / iterations / 1_000_000_000; // Convert to seconds
    }

    public static Cipher getDESCipher() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        keyGen.init(56);
        SecretKey key = keyGen.generateKey();
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        byte[] iv = new byte[8];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        return cipher;
    }

    public static Cipher getAESCipher() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey key = keyGen.generateKey();
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        return cipher;
    }

    public static void main(String[] args) {
    try {
        String filePath = "largefile.txt"; // Update the path to your large file
        byte[] data = readFile(filePath);

        // Set the number of iterations to 100
        int iterations = 1000; // Change this to 100

        // Measure DES encryption speed
        Cipher desCipher = getDESCipher();
        double desTime = measureSpeed(desCipher, Arrays.copyOf(data, data.length), iterations);
        double desSpeed = (double) data.length / desTime;

        // Measure AES encryption speed
        Cipher aesCipher = getAESCipher();
        double aesTime = measureSpeed(aesCipher, Arrays.copyOf(data, data.length), iterations);
        double aesSpeed = (double) data.length / aesTime;

        // Print results
        System.out.printf("DES encryption time: %.2f seconds, Speed: %.2f bytes/second%n", desTime, desSpeed);
        System.out.printf("AES encryption time: %.2f seconds, Speed: %.2f bytes/second%n", aesTime, aesSpeed);
        
         System.out.println("import java.io.File;\r\n" + //
                        "import java.io.FileInputStream;\r\n" + //
                        "import java.io.IOException;\r\n" + //
                        "import java.security.SecureRandom;\r\n" + //
                        "import java.util.Arrays;\r\n" + //
                        "import javax.crypto.Cipher;\r\n" + //
                        "import javax.crypto.KeyGenerator;\r\n" + //
                        "import javax.crypto.SecretKey;\r\n" + //
                        "import javax.crypto.spec.IvParameterSpec;");
        
        System.out.println("OS: Windows 10");
        System.out.println("Machine type: HP Pavilion Laptop 15-eg0xxx");
        System.out.println("CPU:11th Gen Intel(R) Core(TM) i7-1165G7 @ 2.80GHz");
    
       
    
    
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
