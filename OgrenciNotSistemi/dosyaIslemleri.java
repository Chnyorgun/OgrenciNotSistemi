// dosyaIslemleri.java
// Öğrencileri bellekte tutar, silme ve güncelleme işlemleri yapar

import java.io.*;
import java.util.*;

public class dosyaIslemleri {

    public static ArrayList<ogrenci> verileriYukle() {
        ArrayList<ogrenci> ogrenciler = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("veriler.txt"))) {
            String line;
            ogrenci current = null;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("Öğrenci:")) {
                    String ad = line.substring(8).trim();
                    current = new ogrenci(ad);
                    ogrenciler.add(current);
                } else if (line.startsWith("-")) {
                    String[] parts = line.substring(1).split(":");
                    if (parts.length == 2 && current != null) {
                        String dersAdi = parts[0].trim();
                        double not = Double.parseDouble(parts[1].trim());
                        current.dersEkle(dersAdi, not);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Dosya okuma hatası: " + e.getMessage());
        }
        return ogrenciler;
    }

    public static void dosyaYazdir(ArrayList<ogrenci> ogrenciler) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("veriler.txt"))) {
            for (ogrenci o : ogrenciler) {
                writer.write("Öğrenci: " + o.ad + "\n");
                for (ders d : o.dersler) {
                    writer.write(" - " + d.ad + ": " + d.not + "\n");
                }
                double ort = o.ortalamaHesapla();
                writer.write("Ortalama: " + ort + "\n");
                writer.write("Durum: " + (ort >= 50 ? "Geçti" : "Kaldı") + "\n");
                writer.write("-----------------------------\n");
            }
        } catch (IOException e) {
            System.out.println("Dosya yazma hatası: " + e.getMessage());
        }
    }

    public static void ogrenciSil(String ad) {
        ArrayList<ogrenci> ogrenciler = verileriYukle();
        boolean bulundu = ogrenciler.removeIf(o -> o.ad.equalsIgnoreCase(ad.trim()));
        if (bulundu) {
            dosyaYazdir(ogrenciler);
            System.out.println(ad + " adlı öğrenci silindi.");
        } else {
            System.out.println(ad + " bulunamadı.");
        }
    }

    public static void ogrenciGuncelle(String ad, ArrayList<ders> yeniDersler) {
        ArrayList<ogrenci> ogrenciler = verileriYukle();
        boolean bulundu = false;
        for (ogrenci o : ogrenciler) {
            if (o.ad.equalsIgnoreCase(ad.trim())) {
                o.dersler = yeniDersler;
                bulundu = true;
                break;
            }
        }
        if (!bulundu) {
            ogrenciler.add(new ogrenci(ad));
        }
        for (ogrenci o : ogrenciler) {
            o.ortalamaHesapla();
        }
        dosyaYazdir(ogrenciler);
        System.out.println(ad + " güncellendi.");
    }
}
