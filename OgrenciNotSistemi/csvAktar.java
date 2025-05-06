
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class csvAktar {

    static String inputPath = "veriler.txt";
    static String outputPath = "veriler.csv";

    public static void transferData() {
        try (
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream(inputPath), StandardCharsets.UTF_8)); BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(outputPath), StandardCharsets.UTF_8));) {
            // UTF-8 BOM yaz
            writer.write('\uFEFF');

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }

            System.out.println("Veriler başarıyla aktarıldı (UTF-8 BOM ile).");
        } catch (Exception e) {
            System.out.println("Hata: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        transferData();
    }
}
