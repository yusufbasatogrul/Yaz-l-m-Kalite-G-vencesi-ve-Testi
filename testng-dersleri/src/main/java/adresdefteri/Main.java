package adresdefteri;

public class Main {
    public static void main(String[] args) {
        AdresDefteri adresDefteri = new AdresDefteri();

        // Yeni kişiler ekle
        Kisi kisi1 = new Kisi("Ali", "Ankara", "1234567890", "ali@example.com");
        Kisi kisi2 = new Kisi("Ayşe", "Istanbul", "0987654321", "ayse@example.com");

        adresDefteri.kisiEkle(kisi1);
        adresDefteri.kisiEkle(kisi2);

        // Tüm kişileri listele
        System.out.println("Tum Kisiler:");
        for (Kisi kisi : adresDefteri.tumKisiler()) {
            System.out.println(kisi);
        }

        // Bir kişiyi güncelle
        Kisi yeniKisi = new Kisi("Ali", "Izmir", "1111111111", "ali@newmail.com");
        adresDefteri.kisiGuncelle("Ali", yeniKisi);

        // Güncellenmiş kişiyi ara ve yazdır
        System.out.println("\nGuncellenmis Ali:");
        Kisi bulunanKisi = adresDefteri.kisiAra("Ali");
        System.out.println(bulunanKisi);

        // Bir kişiyi sil
        adresDefteri.kisiSil("Ayse");

        // Tüm kişileri tekrar listele
        System.out.println("\nAyse silindikten sonra tum kisiler:");
        for (Kisi kisi : adresDefteri.tumKisiler()) {
            System.out.println(kisi);
        }
    }
}
