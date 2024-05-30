package com.project.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import adresdefteri.AdresDefteri;
import adresdefteri.Kisi;

@ExtendWith(TestSonucKayitcisi.class)
public class AdresDefteriTest {

    private AdresDefteri adresDefteri;

    @BeforeAll
    public static void setUpLogger() {
        TestSonucKayitcisi.setFileName("adres-defteri-test-results.csv");
    }

    @BeforeEach
    public void setUp() {
        adresDefteri = new AdresDefteri();
        adresDefteri.veritabaniTemizle();
    }

    @AfterEach
    public void tearDown() {
        adresDefteri.kapat();
    }

    @Test
    public void testKisiEkle() {
        Kisi kisi = new Kisi("Ali", "Ankara", "1234567890", "ali@example.com");
        adresDefteri.kisiEkle(kisi);

        // Konsola eklenen kişiyi yazdır
        System.out.println("Eklenen Kisi: " + kisi);

        // Eklenen kişinin varlığını ve doğru verileri kontrol et
        assertEquals(1, adresDefteri.tumKisiler().size(), "Kisi sayısı dogru degil");
        Kisi eklenenKisi = adresDefteri.kisiAra("Ali");
        assertNotNull(eklenenKisi, "Kisi bulunamadı");
        assertEquals("Ali", eklenenKisi.getAd(), "Kisi adı yanlis");
        assertEquals("Ankara", eklenenKisi.getAdres(), "Kisi adresi yanlis");
        assertEquals("1234567890", eklenenKisi.getTelefon(), "Kisi telefonu yanlis");
        assertEquals("ali@example.com", eklenenKisi.getEmail(), "Kisi emaili yanlis");

        // Konsola eklenen kişinin detaylarını yazdır
        System.out.println("Bulunan Kisi: " + eklenenKisi);
    }

    @Test
    public void testKisiGuncelle() {
        Kisi kisi = new Kisi("Ali", "Ankara", "1234567890", "ali@example.com");
        adresDefteri.kisiEkle(kisi);

        Kisi yeniKisi = new Kisi("Ali", "Istanbul", "0987654321", "ali@newmail.com");
        adresDefteri.kisiGuncelle("Ali", yeniKisi);

        // Konsola güncellenmiş kişiyi yazdır
        System.out.println("Guncellenmis Kisi: " + yeniKisi);

        // Güncellenmiş kişinin doğru verileri kontrol et
        Kisi guncellenmisKisi = adresDefteri.kisiAra("Ali");
        assertEquals("Istanbul", guncellenmisKisi.getAdres(), "Güncellenmis adres yanlis");
        assertEquals("0987654321", guncellenmisKisi.getTelefon(), "Güncellenmis telefon yanlis");
        assertEquals("ali@newmail.com", guncellenmisKisi.getEmail(), "Güncellenmis email yanlis");

        // Konsola güncellenmiş kişinin detaylarını yazdır
        System.out.println("Guncellenmis Bulunan Kisi: " + guncellenmisKisi);
    }

    @Test
    public void testKisiSil() {
        Kisi kisi = new Kisi("Ali", "Ankara", "1234567890", "ali@example.com");
        adresDefteri.kisiEkle(kisi);

        adresDefteri.kisiSil("Ali");

        // Konsola silinmiş kişiyi yazdır
        System.out.println("Silinen Kisi: " + kisi);

        // Silinen kişinin artık bulunamadığını kontrol et
        assertEquals(0, adresDefteri.tumKisiler().size(), "Kisi sayisi hala 1");
        Kisi silinmisKisi = adresDefteri.kisiAra("Ali");
        assertNull(silinmisKisi, "Silinmis kisi hala bulunabiliyor");
    }

    @Test
    public void testKisiAra() {
        Kisi kisi = new Kisi("Ali", "Ankara", "1234567890", "ali@example.com");
        adresDefteri.kisiEkle(kisi);

        Kisi bulunanKisi = adresDefteri.kisiAra("Ali");

        // Konsola bulunan kişiyi yazdır
        System.out.println("Bulunan Kisi: " + bulunanKisi);

        // Bulunan kişinin doğru verileri kontrol et
        assertNotNull(bulunanKisi, "Kisi bulunamadi");
        assertEquals("Ankara", bulunanKisi.getAdres(), "Adres yanlis");
    }
}
