package adresdefteri;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresDefteri {
    private Connection connection;

    public AdresDefteri() {
        try {
            connection = DriverManager.getConnection("jdbc:h2:mem:adresdefteri;DB_CLOSE_DELAY=-1");
            initializeDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initializeDatabase() throws SQLException {
        if (!isTableExists("KISI")) {
            String createTableSQL = "CREATE TABLE KISI ("
                    + "ID INT AUTO_INCREMENT PRIMARY KEY, "
                    + "AD VARCHAR(255), "
                    + "ADRES VARCHAR(255), "
                    + "TELEFON VARCHAR(255), "
                    + "EMAIL VARCHAR(255))";
            try (Statement stmt = connection.createStatement()) {
                stmt.execute(createTableSQL);
            }
        }
    }

    private boolean isTableExists(String tableName) throws SQLException {
        DatabaseMetaData meta = connection.getMetaData();
        try (ResultSet resultSet = meta.getTables(null, null, tableName, new String[]{"TABLE"})) {
            return resultSet.next();
        }
    }

    public void kisiEkle(Kisi kisi) {
        String insertSQL = "INSERT INTO KISI (AD, ADRES, TELEFON, EMAIL) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setString(1, kisi.getAd());
            pstmt.setString(2, kisi.getAdres());
            pstmt.setString(3, kisi.getTelefon());
            pstmt.setString(4, kisi.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void kisiGuncelle(String ad, Kisi yeniKisi) {
        String updateSQL = "UPDATE KISI SET AD = ?, ADRES = ?, TELEFON = ?, EMAIL = ? WHERE AD = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(updateSQL)) {
            pstmt.setString(1, yeniKisi.getAd());
            pstmt.setString(2, yeniKisi.getAdres());
            pstmt.setString(3, yeniKisi.getTelefon());
            pstmt.setString(4, yeniKisi.getEmail());
            pstmt.setString(5, ad);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void kisiSil(String ad) {
        String deleteSQL = "DELETE FROM KISI WHERE AD = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(deleteSQL)) {
            pstmt.setString(1, ad);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Kisi kisiAra(String ad) {
        String selectSQL = "SELECT * FROM KISI WHERE AD = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(selectSQL)) {
            pstmt.setString(1, ad);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Kisi(rs.getString("AD"), rs.getString("ADRES"), rs.getString("TELEFON"), rs.getString("EMAIL"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Kisi> tumKisiler() {
        List<Kisi> kisiler = new ArrayList<>();
        String selectSQL = "SELECT * FROM KISI";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(selectSQL);
            while (rs.next()) {
                kisiler.add(new Kisi(rs.getString("AD"), rs.getString("ADRES"), rs.getString("TELEFON"), rs.getString("EMAIL")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kisiler;
    }

    public void veritabaniTemizle() {
        String deleteSQL = "DELETE FROM KISI";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(deleteSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void kapat() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
