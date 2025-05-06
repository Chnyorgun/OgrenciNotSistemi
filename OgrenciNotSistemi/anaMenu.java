import java.util.ArrayList;
import java.util.Scanner;

public class anaMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int secim = 0;

        do {
            System.out.println("\n=== Öğrenci Not Takip Sistemi ===");
            System.out.println("1. Öğrenci Ekle");
            System.out.println("2. Öğrenci Güncelle");
            System.out.println("3. Öğrenci Sil");
            System.out.println("4. Excel'e Aktar (.CSV)");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminiz: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("⚠️ Lütfen geçerli bir sayı girin.");
                scanner.next(); // hatalı inputu temizle
                continue;
            }

            secim = scanner.nextInt();
            scanner.nextLine(); // dummy \n

            switch (secim) {
                case 1:
                    notTakipSistemi.ogrenciEkle(); // öğrenci ekle
                    break;

                case 2:
                    System.out.print("Güncellenecek öğrencinin adını girin: ");
                    String guncelAd = scanner.nextLine();

                    ArrayList<ders> yeniDersler = new ArrayList<>();

                    while (true) {
                        System.out.print("Yeni ders adı (bitirmek için boş bırak): ");
                        String dAd = scanner.nextLine();
                        if (dAd.isEmpty()) break;

                        System.out.print("Not: ");
                        double dNot = scanner.nextDouble();
                        scanner.nextLine(); // temizle
                        yeniDersler.add(new ders(dAd, dNot));
                    }

                    dosyaIslemleri.ogrenciGuncelle(guncelAd, yeniDersler);
                    break;

                case 3:
                    System.out.print("Silinecek öğrencinin adını girin: ");
                    String silAd = scanner.nextLine();
                    dosyaIslemleri.ogrenciSil(silAd);
                    break;

                case 4:
                    csvAktar.transferData(); // CSV'ye aktarım yap
                    break;

                case 0:
                    System.out.println("Çıkış yapılıyor...");
                    break;

                default:
                    System.out.println("⚠️ Geçersiz seçim! 0 ile 4 arasında bir değer girin.");
            }

        } while (secim != 0);

        scanner.close();
    }
}
