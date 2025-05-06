// notTakipSistemi.java
// Öğrenci ekleme, silme ve güncelleme işlevlerini içerir

import java.util.ArrayList;
import java.util.Scanner;

public class notTakipSistemi {

    // Öğrenci ekleme işlemi
    public static void ogrenciEkle() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Öğrenci adını girin: ");
        String ad = scanner.nextLine().trim();
        ogrenci ogr = new ogrenci(ad);

        System.out.print("Kaç ders gireceksiniz? ");
        int dersSayisi = scanner.nextInt();
        scanner.nextLine(); // tampon temizleme

        for (int i = 0; i < dersSayisi; i++) {
            System.out.print("Ders adı: ");
            String dersAdi = scanner.nextLine();
            System.out.print("Notu: ");
            double not = scanner.nextDouble();
            scanner.nextLine(); // tampon temizleme
            ogr.dersEkle(dersAdi, not);
        }

        System.out.println("\nÖğrenci Bilgileri:");
        ogr.bilgileriGoster();
        ogr.dosyaKaydet();

        System.out.println("📁 Veriler dosyaya kaydedildi.");
        System.out.println("Çıkmak için ENTER'a basın...");
        scanner.nextLine();
    }

    // Öğrenci güncelleme işlemi
    public static void ogrenciGuncelle() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Güncellenecek öğrencinin adını girin: ");
        String ad = scanner.nextLine().trim();

        System.out.print("Kaç ders gireceksiniz? ");
        int dersSayisi = scanner.nextInt();
        scanner.nextLine(); // tampon temizleme

        ArrayList<ders> yeniDersler = new ArrayList<>();

        for (int i = 0; i < dersSayisi; i++) {
            System.out.print("Ders adı: ");
            String dersAdi = scanner.nextLine();
            System.out.print("Notu: ");
            double not = scanner.nextDouble();
            scanner.nextLine(); // tampon temizleme
            yeniDersler.add(new ders(dersAdi, not));
        }

        dosyaIslemleri.ogrenciGuncelle(ad, yeniDersler);

        System.out.println("📄 Güncellenmiş bilgiler kaydedildi.");
        System.out.println("Çıkmak için ENTER'a basın...");
        scanner.nextLine();
    }

    // Öğrenci silme işlemi
    public static void ogrenciSil() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Silmek istediğiniz öğrencinin adını girin: ");
        String ad = scanner.nextLine().trim();
        dosyaIslemleri.ogrenciSil(ad);
        System.out.println("Çıkmak için ENTER'a basın...");
        scanner.nextLine();
    }
}
