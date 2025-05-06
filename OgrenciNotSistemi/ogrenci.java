// ogrenci.java
// Öğrenci bilgilerini tutar, ders ekler, ortalama hesaplar ve dosyaya kaydeder

import java.io.*;
import java.util.ArrayList;

public class ogrenci {
    public String ad;
    public ArrayList<ders> dersler;
    public double ortalama;

    public ogrenci(String ad) {
        this.ad = ad;
        this.dersler = new ArrayList<>();
    }

    public void dersEkle(String ad, double not) {
        dersler.add(new ders(ad, not));
    }

    public double ortalamaHesapla() {
        double toplam = 0;
        for (ders d : dersler) {
            toplam += d.not;
        }
        this.ortalama = dersler.size() > 0 ? toplam / dersler.size() : 0;
        return this.ortalama;
    }

    public void bilgileriGoster() {
        System.out.println("Öğrenci Adı: " + ad);
        for (ders d : dersler) {
            System.out.println("- " + d.ad + ": " + d.not);
        }
        double ort = ortalamaHesapla();
        System.out.println("Ortalama: " + ort);
        System.out.println(ort >= 50 ? "Geçti" : "Kaldı");
    }

    public void dosyaKaydet() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("veriler.txt", true))) {
            writer.write("Öğrenci: " + ad + "\n");
            for (ders d : dersler) {
                writer.write(" - " + d.ad + ": " + d.not + "\n");
            }
            double ort = ortalamaHesapla();
            writer.write("Ortalama: " + ort + "\n");
            writer.write("Durum: " + (ort >= 50 ? "Geçti" : "Kaldı") + "\n");
            writer.write("-----------------------------\n");
        } catch (IOException e) {
            System.out.println("Dosyaya yazma hatası: " + e.getMessage());
        }
    }
}
