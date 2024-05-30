package adresdefteri;

public class Kisi {
    private String ad;
    private String adres;
    private String telefon;
    private String email;

    public Kisi(String ad, String adres, String telefon, String email) {
        this.ad = ad;
        this.adres = adres;
        this.telefon = telefon;
        this.email = email;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String convertTurkishChars(String str) {
        str = str.replace('ç', 'c');
        str = str.replace('ğ', 'g');
        str = str.replace('ı', 'i');
        str = str.replace('ö', 'o');
        str = str.replace('ş', 's');
        str = str.replace('ü', 'u');
        str = str.replace('Ç', 'C');
        str = str.replace('Ğ', 'G');
        str = str.replace('İ', 'I');
        str = str.replace('Ö', 'O');
        str = str.replace('Ş', 'S');
        str = str.replace('Ü', 'U');
        return str;
    }

    @Override
    public String toString() {
        return "Kisi [ad=" + convertTurkishChars(ad) + ", adres=" + convertTurkishChars(adres) + ", telefon=" + telefon + ", email=" + email + "]";
    }
}
