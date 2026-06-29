package dao;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LaporanDAO {

    Connection conn = Koneksi.getConnection();

    public boolean kirimLaporanLengkap(int userId, String judul, String kategori, String isi) {
        // Nama kolom disesuaikan dengan gambar: user_id, judul, kategori, isi
        String sql = "INSERT INTO laporan (user_id, judul, kategori, isi) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, judul);      // Masuk ke kolom judul
            ps.setString(3, kategori);   // Masuk ke kolom kategori (KDRT, dll)
            ps.setString(4, isi);        // Masuk ke kolom isi (Deskripsi singkat)
            
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("ERROR NYATA LAPORAN: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}